package org.seeking.{{ project }}.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.seeking.{{ project }}.base.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BaseControllerTest {
    protected static Gson gson = new Gson();

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
        token = gson.fromJson(response.getBody(), Response.class).getData().toString();
    }

    @Test
    public void setup() {
        Assert.assertNotNull(token);
    }

    protected <T> T get(String path, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.GET, entity, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        T t = gson.fromJson(new JsonParser().parse(response.getBody()).getAsJsonObject().get("data").getAsJsonObject(), clazz);
        return t;
    }

    protected <T> T post(String path, Class<T> clazz, T t) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(t, headers);
        ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.POST, entity, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        T result = gson.fromJson(new JsonParser().parse(response.getBody()).getAsJsonObject().get("data").getAsJsonObject(), clazz);
        return result;
    }

    protected <T> List<T> get4List(String path, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.GET, entity, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonArray array = new JsonParser().parse(response.getBody()).getAsJsonObject().get("data").getAsJsonArray();
        List<T> list = new ArrayList<>();
        array.forEach(jsonElement -> list.add(gson.fromJson(jsonElement, clazz)));
        return list;
    }

    protected <T> void delete(String path) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.DELETE, entity, String.class);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}