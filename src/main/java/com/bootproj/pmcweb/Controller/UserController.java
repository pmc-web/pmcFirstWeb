package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/page/{name}")
    public String getPage(@PathVariable String name){

        return "user/" + name;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> list(){
        List<User> users = userService.getUsers();
        return users;
    }

    @PostMapping("/user")
    @ResponseBody
    public void postUser(@RequestBody User user){
        userService.createUser(user);
        return;
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return;
    }

    @GetMapping("/user/{id}") //localhost:8080/api/getParameter?id=1234&password=abcd
    public User getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return user;
    }


}
