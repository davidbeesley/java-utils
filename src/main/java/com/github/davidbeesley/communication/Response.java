package com.github.davidbeesley.communication;

public class Response extends Serializable{
    boolean connectionSuccess;
    String message;

    public Response(boolean connectionSuccess, String message) {
        this.connectionSuccess = connectionSuccess;
        this.message = message;
    }

    public boolean isConnectionSuccess() {
        return connectionSuccess;
    }

    public void setConnectionSuccess(boolean connectionSuccess) {
        this.connectionSuccess = connectionSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response() {
    }
}
