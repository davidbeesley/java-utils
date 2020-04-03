package com.github.davidbeesley.communication;

public class Request extends Serializable {
    String message;

    public Request() {
    }

    public Request(String message) {
        this.message = message;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
