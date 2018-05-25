package org.seeking.{{ project }}.controller.user;

import org.seeking.{{ project }}.base.Response;
import org.seeking.{{ project }}.core.user.service.UserService;
import org.seeking.{{ project }}.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Response<List<User>> getUsers() {
        return Response.success(service.getUsers());
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Response<User> addUser(@RequestBody User user) {
        return Response.success(service.addUser(user));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Response<User> getUser(@PathVariable String id) {
        return Response.success(service.getUser(id));
    }
}