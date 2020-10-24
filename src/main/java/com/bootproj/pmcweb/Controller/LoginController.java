package com.bootproj.pmcweb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class LoginController {

    @GetMapping("/page/{name}")
    public String getPage(@PathVariable String name){
        return "views/user/" + name;
    }
}
