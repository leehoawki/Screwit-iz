package org.seeking.{{ project }}.controller.{{ module }};

import org.seeking.{{ project }}.controller.BaseControllerTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.*;
import org.seeking.{{ project }}.core.{{ module }}.entity.{{ module.capitalize() }};

public class {{ module.capitalize() }}ControllerTest extends BaseControllerTest {
    @Test
    public void findAllTest() {
        get4List("/{{ module }}/?pagenum=0&pagesize=20", {{ module.capitalize() }}.class);
    }

    @Test
    public void findTest() {
        {{ module.capitalize() }} {{ module }} = post("/{{ module }}/", {{ module.capitalize() }}.class, new {{ module.capitalize() }}());
        Assert.assertEquals({{ module }}.getId(), get("/{{ module }}/" + {{ module }}.getId(), {{ module.capitalize() }}.class).getId());
    }

    @Test
    public void addTest() {
        post("/{{ module }}/", {{ module.capitalize() }}.class, new {{ module.capitalize() }}());
    }

    @Test
    public void updateTest() {
        {{ module.capitalize() }} {{ module }} = post("/{{ module }}/", {{ module.capitalize() }}.class, new {{ module.capitalize() }}());
        post("/{{ module }}/" + {{ module }}.getId(), {{ module.capitalize() }}.class, {{ module }});
    }

    @Test
    public void deleteTest() {
        {{ module.capitalize() }} {{ module }} = post("/{{ module }}/", {{ module.capitalize() }}.class, new {{ module.capitalize() }}());
        delete("/{{ module }}/" + {{ module }}.getId());
    }
}