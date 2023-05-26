package com.mustaphaslimani.workshop.configuration;

import com.mustaphaslimani.workshop.entity.Blockchain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// BlockchainConfig is a configuration class that creates a singleton bean of type Blockchain
// The bean is created with a difficulty of 2. This means that the hash of a block must start with 2 zeros
@Configuration
public class BlockchainConfig {
    @Bean
    @Scope("singleton")
    public Blockchain blockchain(){
        return new Blockchain(2);
    }
}
