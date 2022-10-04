package com.example.test;

import com.example.test.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface repo extends MongoRepository<Demo, String> {
    @Query(value="{productName:'?0'}")
    public Demo findByName(String productName);
}
