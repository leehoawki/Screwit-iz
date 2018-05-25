package org.seeking.{{ project }}.controller.user;

import org.seeking.{{ project }}.base.Response;
import org.seeking.{{ project }}.core.user.entity.User;
import org.seeking.{{ project }}.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.seeking.{{ project }}.security.CurrentUser;

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