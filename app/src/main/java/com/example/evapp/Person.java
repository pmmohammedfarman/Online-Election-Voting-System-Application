package com.example.evapp;

public class Person {
    private String SenderID;
    private String Subject;


    public Person(String senderID, String subject) {
        this.SenderID = senderID;
        this.Subject = subject;
    }

    public String getSenderID() {
        return SenderID;
    }

    public void setSenderID(String senderID) {
        SenderID = senderID;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
