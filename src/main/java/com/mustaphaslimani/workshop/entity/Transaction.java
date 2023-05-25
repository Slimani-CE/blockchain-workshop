package com.mustaphaslimani.workshop.entity;

import lombok.Data;

@Data
public class Transaction {
private String sender;
    private String recipient;
    private int amount;

    public Transaction(String sender, String recipient, int amount){
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

}
