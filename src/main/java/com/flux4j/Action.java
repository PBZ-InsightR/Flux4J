package com.flux4j;

public class Action {
    private String actionType;

    Action(String actionType) {
        this.actionType = actionType;
    }

    String getAction()
    {
        return actionType;
    }
}
