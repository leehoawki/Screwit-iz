package com.movitech.{{project.lower() }}.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "servicehi")
public interface HelloRemote {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String hello(@RequestParam(value = "name") String name);
}