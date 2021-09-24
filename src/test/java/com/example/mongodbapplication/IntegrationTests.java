package com.example.mongodbapplication;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.Assert;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class IntegrationTests {

    private User user;
    private JSONObject foo;

    @Autowired
    private MockMvc mockMvc;

    // Testing that POST request to controller creates collection with user document and firstName field Oliver
    @Test
    void PostDB() throws Exception {
        this.mockMvc.perform(post("/users?name1=Oliver&name2=Pressland")).andExpect(status().isOk());
    }

    // Testing that GET request to controller returns document with firstName field Oliver
    @Test
    void GetDB() throws Exception {
        this.mockMvc.perform(post("/users?name1=Oliver&name2=Pressland"));
        this.mockMvc.perform(get("/users")).andDo(print()).
                andExpect(status().isOk()).andExpect(content().string(containsString("Oliver")));
    }

    // Testing that GET request by incorrect User ID flags 404 response code
    @Test
    void GetId404DB() throws Exception {
        this.mockMvc.perform(post("/users?name1=Oliver&name2=Pressland"));
        this.mockMvc.perform(get("/users/123")).andDo(print()).andExpect(status().isNotFound());
    }

    // Testing that POST request by incorrect parameter flags 400 response code
    @Test
    void Post400DB() throws Exception {
        this.mockMvc.perform(post("/users?first=O&last=P")).andExpect(status().isBadRequest());
    }
}