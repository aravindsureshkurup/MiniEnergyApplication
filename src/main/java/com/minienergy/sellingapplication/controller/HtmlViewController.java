package com.minienergy.sellingapplication.controller;

import com.minienergy.sellingapplication.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HtmlViewController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping(value = "submit-energy-details")
    public String submitPage() {
        return "submit";
    }

}
