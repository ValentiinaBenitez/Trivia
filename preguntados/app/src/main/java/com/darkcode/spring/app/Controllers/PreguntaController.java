package com.darkcode.spring.app.Controllers;

import com.darkcode.spring.app.Services.OpenAIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreguntaController {

    private final OpenAIService openAIService;

    public PreguntaController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/api/pregunta/{categoria}")
    public String obtenerPregunta(@PathVariable String categoria) throws Exception {
        return openAIService.generarPregunta(categoria);
    }
    
}

