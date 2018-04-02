package com.fico.kriti.explorer.controllers;
import com.fico.kriti.explorer.model.User;
import com.fico.kriti.explorer.services.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by KritiJain on 8/16/2017.
 */
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @ResponseBody
    @RequestMapping(value = "/users", consumes= {"application/json"}, produces= {"application/json"}, method= {RequestMethod.POST})
    public User createUser(@RequestBody User obj){
        return userService.createUser(obj);
    }

    @ResponseBody
    @RequestMapping(value = "/users", produces= {"application/json"}, method= {RequestMethod.GET})
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    @ResponseBody
    @RequestMapping(value = "/users/{userId}", produces= {"application/json"}, method= {RequestMethod.GET})
    public User findUserById(@PathVariable("userId") long userId){
        return userService.findUserById(userId);
    }


}
