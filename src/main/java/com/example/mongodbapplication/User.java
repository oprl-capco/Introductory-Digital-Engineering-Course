package com.example.mongodbapplication;

import net.minidev.json.JSONObject;
import org.springframework.data.annotation.Id;

public class User {

    // Class that contains all fields for user

    @Id
    private String id;

    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String s) {
        this.firstName = s;
    }

    public void setLastName(String s) {
        this.lastName = s;
    }

    // Clean visualisation of user record

    public String ConvertToReadableJSON() {
        JSONObject result = new JSONObject();

        result.put("id", this.getId());
        result.put("firstName", this.getFirstName());
        result.put("lastName", this.getLastName());

        return result.toJSONString();
    }
}
