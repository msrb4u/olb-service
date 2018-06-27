package com.vassistant.controller;


import com.vassistant.config.AppPropertiesBean;
import com.vassistant.domain.PrimaryTransaction;
import com.vassistant.domain.Recipient;
import com.vassistant.domain.User;
import com.vassistant.domain.VUser;
import com.vassistant.model.*;
import com.vassistant.service.AccountService;
import com.vassistant.service.TransactionService;
import com.vassistant.service.UserService;
import com.vassistant.service.VUserService;
import com.vassistant.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
public class AccountsController {

    @Autowired
    private AppPropertiesBean appPropertiesBean;

    @Autowired
    private VUserService vuserService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/accounts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public InformationModelResponse accountsForDialogflow(@RequestBody String inputStr) throws Exception{

            InformationModelResponse response=new InformationModelResponse();
            String userId=null;
            String accountType="Savings";
            String accountBalance="";

            InformationModel im=JSONUtil.extractWebHookJsonRequest(inputStr);
            String userAction=im.getAction();

            VUser vuser=vuserService.findByVuserId(im.getUserId());

            if(vuser!=null){
                userId=vuser.getUserId();
            }else{
                userId="4";//temporary fix if user id not in fullfillment request
            }

            if(userAction.equalsIgnoreCase("accounts-balance")){
                if(im.getParameters().getParam1()!=null) {
                    accountType=im.getParameters().getParam1();
                }

                if(accountType.equalsIgnoreCase("Checking")){
                    accountBalance=accountService.checkingAccountBalance(userId);
                }else{
                    accountBalance=accountService.savingsAccountBalance(userId);
                }
                response=createWebHookResponseForAccountsBalance(accountType,accountBalance,response);
            }else if(userAction.equalsIgnoreCase("find-transaction")){
                if(im.getParameters().getParam1()!=null) {
                    accountType=im.getParameters().getParam1();
                }
                User user=userService.findByUserId(userId);
                System.out.println("user "+user);
                if(user!=null){
                    System.out.println(user.getUsername());
                }
                List<PrimaryTransaction> primaryTransactionList = transactionService.findPrimaryTransactionList(user.getUsername());

                response=createWebHookResponseForPrimaryTransactions(accountType,primaryTransactionList,response);

            }else if(userAction.equalsIgnoreCase("transfer-money")){
                String amountToTransfer=im.getParameters().getParam1();
                String toAccount=im.getParameters().getParam2();
                System.out.println("amountToTransfer "+amountToTransfer+" toAccount "+toAccount);
                User user=userService.findByUserId(userId);
                System.out.println("user to recipient "+user.getUsername());
                List<Recipient>  recipientlist=transactionService.findRecipientList(user.getUsername());
                System.out.println("recipientlist "+recipientlist.size());


                Recipient recipient = transactionService.findRecipientByName(toAccount);
                transactionService.toSomeoneElseTransfer(recipient, accountType, amountToTransfer, user.getPrimaryAccount(), user.getSavingsAccount());
                response=createWebHookResponseForTransferMoney(recipient,response);
            }
            return response;
    }

    public InformationModelResponse createWebhookResponseForFTInputValidation(InformationModelResponse response){
        FollowupEvent fe=new FollowupEvent();
        fe.setName("ft-input-validation-2");
        InvalidEventData ide=new InvalidEventData();
        fe.setParameters(ide);
        response.setFulfillmentText("Please enter a valid amount and to whom to transfer");
        response.setFollowupEventInput(fe);
        return response;
    }

    public InformationModelResponse createWebHookResponseForAccountsBalance(String accountType,String accountBalance,InformationModelResponse response){
        if(accountType.equalsIgnoreCase("Checking")){
            response.setFulfillmentText(appPropertiesBean.getChekingBalance() +accountBalance);
        }else{
            response.setFulfillmentText(appPropertiesBean.getSavingsBalance() +accountBalance);
        }
        return response;
    }

    public InformationModelResponse createWebHookResponseForPrimaryTransactions(String accountType,List<PrimaryTransaction> primaryTransactionList,InformationModelResponse response){
        List<FulfillmentMessages> fulfillmentMessagesArrayList=new ArrayList<>();
        FulfillmentMessages fulfillmentMessages=new FulfillmentMessages();

        String fulfillmentTextMessage="";

        List<String> msgList=new ArrayList<>();
        TextMessgae textMessgae = new TextMessgae();
        for(PrimaryTransaction p:primaryTransactionList){
            //m.setText("Post Date "+p.getDate()+" Description "+p.getDescription()+" Status "+p.getStatus()+" and amount "+p.getAmount());
            fulfillmentTextMessage+="amount "+p.getAmount()+" to "+p.getDescription()+" ";
            String mesg=  "Description "+p.getDescription()+" and amount "+p.getAmount();

            msgList.add(mesg);
            textMessgae.setText(msgList);
        }

        fulfillmentMessages.setText(textMessgae);
        fulfillmentMessagesArrayList.add(fulfillmentMessages);
        response.setFulfillmentMessages(fulfillmentMessagesArrayList);

        if(accountType.equalsIgnoreCase("Checking")){
            response.setFulfillmentText(appPropertiesBean.getCheckingTransactions()+fulfillmentTextMessage);
        }else{
            response.setFulfillmentText(appPropertiesBean.getSavingsTransactions()+fulfillmentTextMessage);
        }

        return response;
    }

    public InformationModelResponse createWebHookResponseForTransferMoney(Recipient recipient,InformationModelResponse response){
        response.setFulfillmentText("Money is successfully transfered to "+recipient.getName());
        return response;
    }
}
