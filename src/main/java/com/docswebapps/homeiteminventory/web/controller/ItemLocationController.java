package com.docswebapps.homeiteminventory.web.controller;

import com.docswebapps.homeiteminventory.service.ItemLocationService;
import com.docswebapps.homeiteminventory.web.dto.ItemLocationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/web")
public class ItemLocationController {
    private final ItemLocationService itemLocationService;

    @Autowired
    public ItemLocationController(@Qualifier("itemLocationServiceImpl") ItemLocationService itemLocationService) {
        this.itemLocationService = itemLocationService;
    }

    @GetMapping("/location")
    public String returnMainLocationPage(Model model) {
        int year = LocalDate.now().getYear();
        List<ItemLocationDto> allLocations = this.itemLocationService.getAllLocations();
        model.addAttribute("year", year);
        model.addAttribute("allLocations", allLocations);
        return "config/itemLocationPage";
    }
}
