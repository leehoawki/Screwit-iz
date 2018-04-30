package com.movitech.{{ project }}.controller.user;

import com.movitech.{{ project }}.base.Response;
import com.movitech.{{ project }}.core.user.entity.User;
import com.movitech.{{ project }}.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BaseController {

    @Autowired
    UserService service;

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Response<User> register(@RequestParam String username, @RequestParam String password) {
        return Response.success(service.register(username, password));
    }
}