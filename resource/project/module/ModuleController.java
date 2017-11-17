package com.movitech.{{ project.lower() }}.controller;

import com.movitech.{{ project.lower() }}.{{ module.lower() }}.service.{{ module }}Service;
import com.movitech.{{ project.lower() }}.base.entity.{{ module }};
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

    @RequestMapping("/{id}")
    @ResponseBody
    public {{ module }} get{{ module }}(@PathVariable String id) {
        return service.get{{ module }}(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public {{ module }} add{{ module }}(@PathVariable String id, @RequestBody {{ module }} {{ module.lower() }}) {
        {{ module.lower() }}.setId(id);
        return service.add{{ module }}({{ module.lower() }});
    }
}
