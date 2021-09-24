package com.example.mongodbapplication;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // New queries that implement on MongoDB's existing query repository

    public List <User> findUserByFirstName (String firstName);
    public List <User> findUserByLastName (String lastName);
    public List <User> findUserById (String id);

}
