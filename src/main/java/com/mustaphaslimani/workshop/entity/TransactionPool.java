package com.mustaphaslimani.workshop.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionPool {
    private final List<Transaction> pendingTransactions;

    public TransactionPool(){
        this.pendingTransactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction){
        this.pendingTransactions.add(transaction);
    }

    public List<Transaction> getPendingTransactions() {
        return pendingTransactions;
    }

    public void removeTransactions(List<Transaction> transactions){
        this.pendingTransactions.removeAll(transactions);
    }

    public void removeTransaction(Transaction transaction){
        this.pendingTransactions.remove(transaction);
    }

}
