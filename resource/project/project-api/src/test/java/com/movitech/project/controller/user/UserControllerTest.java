package com.movitech.{{ project }}.controller.user;

import com.movitech.{{ project }}.controller.BaseControllerTest;
import org.junit.Assert;

import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class UserControllerTest extends BaseControllerTest {
    @Test
    public void registrationTest() {
        restTemplate.getForObject("/register?username={username}&password={password}", String.class, "wahaha", "subara");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "wahaha");
        params.add("password", "subara");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        restTemplate.postForEntity("/login", requestEntity, String.class);
    }
}
