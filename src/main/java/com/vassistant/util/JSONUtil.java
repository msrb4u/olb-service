package com.vassistant.util;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vassistant.model.InformationModel;
import com.vassistant.model.Parameters;

import java.io.File;
import java.io.IOException;

public class JSONUtil {

    public static InformationModel extractWebHookJsonRequest(String inputStr) throws JsonMappingException, IOException {
        System.out.println("inputStr \n"+inputStr);
        InformationModel im=new InformationModel();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode j = mapper.readTree(inputStr);

        String actionType=j.get("queryResult").get("action").textValue();

        if(actionType.equalsIgnoreCase("accounts-balance")){
            Parameters parameters=new Parameters();
            parameters.setParam1(j.get("queryResult").get("parameters").get("account-type").textValue());
            parameters.setParam2(j.get("queryResult").get("parameters").get("balance").textValue());
            im.setParameters(parameters);
        }

        if(actionType.equalsIgnoreCase("find-transaction")){
            Parameters parameters=new Parameters();
            parameters.setParam1(j.get("queryResult").get("parameters").get("account-type").textValue());
            parameters.setParam2(j.get("queryResult").get("parameters").get("trans-history").textValue());
            im.setParameters(parameters);
        }

        if(actionType.equalsIgnoreCase("transfer-money")){
            Parameters parameters=new Parameters();
            System.out.println("Amout :: "+j.get("queryResult").get("parameters").get("amount-to-transfer").get("amount").toString());
            parameters.setParam1(j.get("queryResult").get("parameters").get("amount-to-transfer").get("amount").toString());
            parameters.setParam2(j.get("queryResult").get("parameters").get("to-account").textValue());
            im.setParameters(parameters);
        }


        im.setAction(j.get("queryResult").get("action").textValue());
        if(j.get("originalDetectIntentRequest")!=null && j.get("originalDetectIntentRequest").get("payload")!=null && j.get("originalDetectIntentRequest").get("payload").get("user")!=null) {
            im.setUserId(j.get("originalDetectIntentRequest").get("payload").get("user").get("userId").textValue());
        }
        im.setSessionId(j.get("session").textValue());

        return im;
    }

}
