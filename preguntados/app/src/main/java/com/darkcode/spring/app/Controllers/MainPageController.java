package com.darkcode.spring.app.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String mainPage() {
        return "MainPage";  
    }

    @GetMapping("/Jugar")
    public String redirectToRuleta() {
        return "redirect:/ruleta";}
}
