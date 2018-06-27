package com.vassistant.model;

import java.util.HashMap;

public class FollowupEvent {
    private String name;
    private InvalidEventData parameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InvalidEventData getParameters() {
        return parameters;
    }

    public void setParameters(InvalidEventData parameters) {
        this.parameters = parameters;
    }
}
