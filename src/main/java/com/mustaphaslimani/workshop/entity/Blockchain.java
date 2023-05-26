package com.mustaphaslimani.workshop.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Blockchain {
    private List<Block> chain;
    private TransactionPool transactionPool;
    private int difficulty;
    
    public Blockchain(int difficulty){
        // chain is a list of blocks
        this.chain = new ArrayList<>();
        // transactionPool is a list of transactions
        this.transactionPool = new TransactionPool();
        // difficulty is the number of leading zeros in the hash
        this.difficulty = difficulty;

        // create the genesis block
        Block genesisBlock = createGenesisBlock();
        this.chain.add(genesisBlock);
    }

    private Block createGenesisBlock() {
        List<Transaction> transactions = new ArrayList<>();
        return new Block(0, "0", transactions, 0);
    }

    public Block getLatestBlock(){
        return this.chain.get(this.chain.size() - 1);
    }

    public Block addBlock(Block block){
        if(isValidBlock(block)){
            // add the block to the chain
            this.chain.add(block);
            // Pool is a collection of transactions that have not yet been added to a block
            // remove the transactions from the transaction pool.
            // We need to do this because the transactions are now in the block
            this.transactionPool.removeTransactions(block.getTransactions());
            return block;
        }
        throw new RuntimeException("Invalid block");
    }

    public Boolean isValidBlock(Block block){
        Block latestBlock = getLatestBlock();

        // Check if the block index is correct
        if(latestBlock.getIndex() + 1 != block.getIndex()){
            System.out.println("Invalid index");
            return false;
        }

        // Check if the previous hash is correct
        if(!latestBlock.getCurrentHash().equals(block.getPreviousHash())){
            System.out.println("Invalid previous hash");
            return false;
        }

        // Check if the current block hash is meeting the difficulty
        String prefix = getDifficultyPrefix(difficulty);
        return block.getCurrentHash().startsWith(prefix);
    }

    public String getDifficultyPrefix(int difficulty){
        return "0".repeat(difficulty);
    }

    public Block mineBlock(){
        Block block = new Block(getChain().size(), getLatestBlock().getCurrentHash(), transactionPool.getPendingTransactions(), 0);
        block.setPreviousHash(getLatestBlock().getCurrentHash());
        String calculatedHash = block.calculateHash();
        while(!calculatedHash.startsWith(getDifficultyPrefix(difficulty))){
            block.incrementNonce();
            calculatedHash = block.calculateHash();
        }
        block.setCurrentHash(calculatedHash);

        return addBlock(block);
    }

    public boolean validateChain(){
        for(int i = 1; i < chain.size(); i++){
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            // Check if the hash of current block is correct
            if(!currentBlock.getCurrentHash().equals(currentBlock.calculateHash())){
                System.out.println("Current hashes are not equal");
                return false;
            }

            // Check if the previous hash of current block is correct
            if(!currentBlock.getPreviousHash().equals(previousBlock.getCurrentHash())){
                System.out.println("Previous hashes are not equal");
                return false;
            }
        }
        return true;
    }

    public void addTransaction(Transaction transaction){
        this.transactionPool.addTransaction(transaction);
    }
}
