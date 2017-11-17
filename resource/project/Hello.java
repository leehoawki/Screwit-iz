package com.movitech.{{project.lower() }}.base.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "servicehi")
public interface Hello {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String hello(@RequestParam(value = "name") String name);
}