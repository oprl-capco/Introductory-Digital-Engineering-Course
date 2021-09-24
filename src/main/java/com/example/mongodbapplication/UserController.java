package com.example.mongodbapplication;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.UUID;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {

    // Get logger

    private static Logger logger = LogManager.getLogger(UserController.class);
    private String uniqueID;

    @Autowired
    public UserRepository userRepository;

    // GET request to find all users
    @GetMapping(value="/users")
    public List <User> findAllUsers() {

        // Creating transaction ID
        uniqueID = UUID.randomUUID().toString();

        logger.info("findAllUsers, Transaction [{}]: Started, retrieving all user records.", uniqueID);
        List <User> result = userRepository.findAll();

        if (result.isEmpty()) {
            logger.info("findAllUsers, Transaction [{}]: Failed, user collection is empty, Send POST request to fill collection", uniqueID);
        }
        else {
            logger.info("findAllUsers, Transaction [{}]: Complete, found [{}] users in collection", uniqueID, result.size());
        }
        return result;
    }

    // GET request to find user by ID
    @GetMapping(value="/users/{userid}")
    public List <User> findUserById(@PathVariable("userid") String id) {

        // Creating transaction ID
        uniqueID = UUID.randomUUID().toString();

        logger.info("findUserById, Transaction [{}]: Started, Finding user record with the ID", uniqueID);
        List <User> result = userRepository.findUserById(id);

        if (result.isEmpty()) {
            logger.info("findUserById, Transaction [{}]: Failed, No user record has been found with the ID", uniqueID);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID not found");
        }
        else {
            logger.info("findUserById, Transaction [{}]: Complete, Found [{}] user record with the ID", uniqueID, result.size());
        }
        return result;
    }

    // POST request to add new user
    @PostMapping(value="/users")
    public void addUser(@RequestParam Map<String,String> requestParams) {

        // Creating transaction ID
        uniqueID = UUID.randomUUID().toString();

        if (requestParams.containsKey("name1") && requestParams.containsKey("name2")) {
            String firstName = requestParams.get("name1");
            String lastName = requestParams.get("name2");

            logger.info("addUser, Transaction [{}]: Started, Adding user record by name: ", uniqueID);

            User user = new User(firstName, lastName);
            userRepository.save(user);

            logger.info("addUser, Transaction [{}]: Complete, Saved user record", uniqueID);
        }

        else {
            logger.warn("addUser, Transaction [{}]: Failed, parameters are name1, name2, not [{}]", uniqueID, (requestParams.keySet()).toString());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect parameters, please use name1 and name2.");
        }
    }
}
