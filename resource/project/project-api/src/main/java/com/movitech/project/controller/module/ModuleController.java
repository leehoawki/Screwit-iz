package com.movitech.{{ project }}.controller.{{ module }};

import com.movitech.{{ project }}.base.Request;
import com.movitech.{{ project }}.base.Response;
import com.movitech.{{ project }}.core.{{ module }}.service.{{ module.capitalize() }}Service;
import com.movitech.{{ project }}.core.{{ module }}.entity.{{ module.capitalize() }};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{{ module }}")
public class {{ module.capitalize() }}Controller {

    @Autowired
    {{ module.capitalize() }}Service service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<{{ module.capitalize() }}> get{{ module.capitalize() }}(@PathVariable String id) {
        return Response.success(service.get{{ module.capitalize() }}(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<{{ module.capitalize() }}>> index() {
        return Response.success(service.findAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response<List<{{ module.capitalize() }}>> index(@RequestBody Request<{{ module.capitalize() }}> request) {
        return Response.success(service.find(request.getData(), request.getPageInfo().pageable()));
    }
}