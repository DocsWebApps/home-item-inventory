package com.docswebapps.homeiteminventory.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {

    @GetMapping
    public String returnHomePage(Model model) {
        model.addAttribute("homeTitle", "My Home Item Inventory");
        return "homePage";
    }
}
