package com.vassistant.config;

import org.springframework.beans.factory.annotation.Value;

public class AppPropertiesBean {
    @Value("${accounts-balance.savings}")
    private String savingsBalance;

    @Value("${accounts-balance.cheking}")
    private String chekingBalance;

    @Value("${find-transaction.cheking}")
    private String checkingTransactions;

    public String getCheckingTransactions() {
        return checkingTransactions;
    }

    public void setCheckingTransactions(String checkingTransactions) {
        this.checkingTransactions = checkingTransactions;
    }

    public String getSavingsTransactions() {
        return savingsTransactions;
    }

    public void setSavingsTransactions(String savingsTransactions) {
        this.savingsTransactions = savingsTransactions;
    }

    @Value("${find-transaction.savings}")
    private String savingsTransactions;

    public AppPropertiesBean(){}

    public String getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(String savingsBalance) {
        this.savingsBalance = savingsBalance;
    }

    public String getChekingBalance() {
        return chekingBalance;
    }

    public void setChekingBalance(String chekingBalance) {
        this.chekingBalance = chekingBalance;
    }
}
