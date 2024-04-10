package com.draekk.salesmanagement.models;

public enum ResponseMessage {

    CREATED("Item created correctly."),
    FOUND("Item found successful."),
    UPDATED("Item updated corectly."),
    DELETED("Item deleted correctly."),
    NOT_CREATED("Error creating the item."),
    NOT_FOUND("Item not found."),
    NOT_UPDATED("A problem  has occurred in the update."),
    NOT_DELETED("A problem has occurred in the deleting process.");
    
    private String message;
    
    private ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
