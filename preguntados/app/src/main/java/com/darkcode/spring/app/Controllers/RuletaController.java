package com.darkcode.spring.app.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.darkcode.spring.app.Models.Usuario;

import com.darkcode.spring.app.Repositories.UsuarioRepository;
@Controller
public class RuletaController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/ruleta")
    public String mostrarRuleta(@RequestParam String username, Model model) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return "ruleta"; // Retorna el archivo ruleta.html
        } else {
            return "redirect:/MainPage";
        }
    }


}
