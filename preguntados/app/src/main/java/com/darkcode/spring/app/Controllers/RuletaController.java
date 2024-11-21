package com.darkcode.spring.app.Controllers;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RuletaController {

    @GetMapping("/ruleta")
    public String mostrarRuleta(Model model) {
        // Pasar datos al HTML si es necesario
        return "ruleta"; // Debe coincidir con el nombre del archivo HTML en resources/templates
    }
}
