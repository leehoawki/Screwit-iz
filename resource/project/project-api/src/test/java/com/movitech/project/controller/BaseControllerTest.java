package com.movitech.{{ project }}.controller;

import com.google.gson.Gson;
import com.movitech.{{ project }}.base.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BaseControllerTest {
    protected Gson gson = new Gson();
    public static final int SUCCESS_CODE = 2000;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected String token;

    static {
        MariaDB4J.setup();
    }

    @Before
    public void login() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "admin");
        params.add("password", "admin");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/login", requestEntity, String.class);
        Gson gson = new Gson();
        token = gson.fromJson(response.getBody(), Response.class).getData().toString();
    }

    @Test
    public void setup() {
        Assert.assertNotNull(token);
    }
}
