package com.docswebapps.homeiteminventory.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@Slf4j
@RequestMapping({"/",""})
public class HomePageController {

    @GetMapping
    public String returnHomePage(Model model) {
        log.info("HomePageController: returnHomePage called");
        int year = LocalDate.now().getYear();
        model.addAttribute("homeTitle", "My Home Item Inventory");
        model.addAttribute("year", year);
        return "homePage";
    }
}
