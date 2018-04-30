package com.movitech.{{ project }}.controller.{{ module }};

import com.movitech.{{ project }}.controller.BaseControllerTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.*;

public class {{ module.capitalize() }}ControllerTest extends BaseControllerTest {
    private String token;

    @Test
    public void findAllTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", token);
        HttpEntity entity = new HttpEntity(null, headers);
        Assert.assertEquals(restTemplate.exchange("/{{ module }}/", HttpMethod.GET, entity, String.class).getStatusCode(), HttpStatus.OK);
    }
}