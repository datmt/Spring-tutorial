package com.datmt.activemq.data;

import java.io.Serializable;

public class Letter implements Serializable {
    private String content;
    private String sender;
    private String receiver;

    public Letter(String content, String sender, String receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }


    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

}
