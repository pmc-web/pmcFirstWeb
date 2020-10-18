package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.City;
import com.bootproj.pmcweb.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CityService cityService;

    @GetMapping("/test")
    public String Home(Model model){
        List<City> cityList = cityService.selectCityList();
        model.addAttribute("cityList", cityList);

        return "login";
    }

    @GetMapping("/")
    public String home(){

        return "main";
    }

}
