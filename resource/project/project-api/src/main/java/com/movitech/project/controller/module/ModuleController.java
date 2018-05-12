package com.movitech.{{ project }}.controller.{{ module }};

import com.movitech.{{ project }}.base.PageInfo;
import com.movitech.{{ project }}.base.Response;
import com.movitech.{{ project }}.core.{{ module }}.service.{{ module.capitalize() }}Service;
import com.movitech.{{ project }}.core.{{ module }}.entity.{{ module.capitalize() }};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{{ module }}")
public class {{ module.capitalize() }}Controller {

    @Autowired
    {{ module.capitalize() }}Service service;

    @RequestMapping(value = "/")
    @ResponseBody
    public Response<List<{{ module.capitalize() }}>> index({{ module.capitalize() }} {{ module }}, PageInfo pageInfo) {
        Page<{{ module.capitalize() }}> page = service.find({{ module }}, pageInfo.pageable());
        return Response.success(page.getContent(), new PageInfo(page));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<{{ module.capitalize() }}> get{{ module.capitalize() }}(@PathVariable int id) {
        return Response.success(service.get{{ module.capitalize() }}(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response<{{ module.capitalize() }}> add(@RequestBody {{ module.capitalize() }} {{ module }}) {
        return Response.success(service.add({{ module }}));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Response<{{ module.capitalize() }}> update(@PathVariable int id, @RequestBody {{ module.capitalize() }} {{ module }}) {
        {{ module }}.setId(id);
        return Response.success(service.update({{ module }}));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Response<Integer> delete(@PathVariable int id) {
        return Response.success(service.delete(id));
    }
}