package com.movitech.{{ project.lower() }}.controller;

import com.movitech.{{ project.lower() }}.{{ module.lower() }}.service.{{ module }}Service;
{% for entity in entities %}
import com.movitech.{{ project.lower() }}.base.entity.{{ entity }};
{% endfor %}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{{ module.lower() }}")
public class {{ module }}Controller {

    @Autowired
    {{ module }}Service service;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return service.hello();
    }


    {% for entity in entities %}
    @RequestMapping("/{{ entity }}/{id}")
    @ResponseBody
    public {{ entity }} get{{ entity }}(@PathVariable String id) {
        return service.get{{ entity }}(id);
    }
    {% endfor %}
}
