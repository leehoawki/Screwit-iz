package com.movitech.{{ project }}.controller.user;

import com.movitech.{{ project }}.base.Response;
import com.movitech.{{ project }}.core.user.entity.User;
import com.movitech.{{ project }}.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.movitech.{{ project }}.security.CurrentUser;

@RestController
@RequestMapping("/me")
public class MeController {

    @Autowired
    UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Response<User> getProfile(@CurrentUser User user) {
        return Response.success(user);
    }
}