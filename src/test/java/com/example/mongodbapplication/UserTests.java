package com.example.mongodbapplication;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class UserTests {


    private User user;
    private JSONObject foo;
    private String oof;

    @Test
    void ToJSONTest() throws Exception {
        // Ignoring ID for this test as it will be assigned by Mongo.
        user = new User("Oliver", "Pressland");
        foo = new JSONObject();

        foo.put("firstName", "Oliver");
        foo.put("lastName", "Pressland");
        foo.put("id", null);

        oof = user.ConvertToReadableJSON();

        Assert.assertEquals(oof, foo.toString());
    }
}
