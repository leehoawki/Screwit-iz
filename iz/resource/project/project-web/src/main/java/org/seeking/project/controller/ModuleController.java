package org.seeking.{{ project }}.controller;


import org.seeking.{{ project }}.controller.common.WebResponse;
import org.seeking.{{ project }}.model.{{ module.capitalize() }};
import org.seeking.{{ project }}.service.{{ module.capitalize() }}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/{{ module }}")
public class {{ module.capitalize() }}Controller {

    @Autowired
    {{ module.capitalize() }}Service service;

    @RequestMapping(value = "/")
    @ResponseBody
    public WebResponse<List<{{ module.capitalize() }}>> index() {
        return WebResponse.create(service.findAll());
    }
}