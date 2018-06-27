package com.vassistant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformationModelResponse {
    private String fulfillmentText;
    private List<FulfillmentMessages> fulfillmentMessages;
    private FollowupEvent followupEventInput;

    public FollowupEvent getFollowupEventInput() {
        return followupEventInput;
    }

    public void setFollowupEventInput(FollowupEvent followupEventInput) {
        this.followupEventInput = followupEventInput;
    }

    public InformationModelResponse(){}


    public List<FulfillmentMessages> getFulfillmentMessages() {

        return fulfillmentMessages;
    }

    public void setFulfillmentMessages(List<FulfillmentMessages> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }

    public String getFulfillmentText() {
        return fulfillmentText;
    }

    public void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }




}
