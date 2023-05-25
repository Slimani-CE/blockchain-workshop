package com.mustaphaslimani.workshop.entity;

import com.mustaphaslimani.workshop.helper.HashUtils;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class Block {
    private int index;
    // timestamp is the time of the block creation
    private Instant timestamp;
    // previousHash is the hash of the previous block
    private String previousHash;
    private String currentHash;
    private List<Transaction> transactions;
    private int nonce;

    public Block(int index, String previousHash, List<Transaction> transcations, int nonce){
        this.index = index;
        this.timestamp = Instant.now();
        this.previousHash = previousHash;
        this.transactions = transcations;
        this.nonce = nonce;
        this.currentHash = calculateHash();
    }

    public void incrementNonce(){
        this.nonce++;
    }

    public String calculateHash() {
        String dataToHash = index + timestamp.toString() + previousHash + transactions.toString() + nonce;
        return HashUtils.calculateSHA256(dataToHash);
    }

}
