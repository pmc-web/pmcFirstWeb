package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/page/{name}")
    public String getPage(@PathVariable String name){

        return "user/" + name;
    }

    @GetMapping("/users")
    public List<User> list(){
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/user")
    public User post(@RequestBody User user){
        User newUser = userService.addUser(user);
        return newUser;
    }

    @GetMapping("/user/{id}") //localhost:8080/api/getParameter?id=1234&password=abcd
    public User getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return user;
    }


}
