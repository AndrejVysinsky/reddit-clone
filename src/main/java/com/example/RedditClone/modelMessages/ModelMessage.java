package com.example.RedditClone.modelMessages;

public class ModelMessage
{
    private String message;
    private boolean isErrorMessage;

    public ModelMessage() {
    }

    public String getMessage() {
        return message;
    }

    public boolean isErrorMessage() { return isErrorMessage; }

    public void setMessage(String message, boolean isErrorMessage) {
        this.message = message;
        this.isErrorMessage = isErrorMessage;
    }
}
