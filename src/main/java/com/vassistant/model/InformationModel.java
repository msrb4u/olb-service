package com.vassistant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InformationModel {
    private String action;
    private Parameters parameters;
    private String userId;
    private String sessionId;

    public InformationModel(){}

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "InformationModel{" +
                "action='" + action + '\'' +
                ", parameters=" + parameters +
                ", userId='" + userId + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
