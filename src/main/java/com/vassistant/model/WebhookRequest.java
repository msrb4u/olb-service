package com.vassistant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebhookRequest {

    private String responseId;
    private String session;
    private QueryResult queryResult;

    public WebhookRequest(){}
    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public QueryResult getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }

    private class QueryResult{
        private String action;
        private OriginalDetectIntentRequest originalDetectIntentRequest;

        public QueryResult(){}

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public OriginalDetectIntentRequest getOriginalDetectIntentRequest() {
            return originalDetectIntentRequest;
        }

        public void setOriginalDetectIntentRequest(OriginalDetectIntentRequest originalDetectIntentRequest) {
            this.originalDetectIntentRequest = originalDetectIntentRequest;
        }
    }

    private class OriginalDetectIntentRequest{
        public OriginalDetectIntentRequest(){}
        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        private User user;
    }

    private class User{
        public User(){}
        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }

    @Override
    public String toString() {
        return "WebhookRequest{" +
                "responseId='" + responseId + '\'' +
                ", session='" + session + '\'' +
                ", queryResult=" + queryResult +
                '}';
    }
}

