package com.darkcode.spring.app.Controllers;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import com.darkcode.spring.app.Models.Usuario;
import com.darkcode.spring.app.Repositories.UsuarioRepository;


@Controller
public class MenuController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

   @GetMapping("/multijugador")
   public String multijugador(@RequestParam String username, Model model) {
    Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
    if (optionalUsuario.isPresent()) {
        Usuario usuario = optionalUsuario.get();
        model.addAttribute("usuario", usuario);  // Pasa el objeto Usuario al modelo
    return "multijugador"; // Retorna el archivo multijugador.html
    }model.addAttribute("error", "Usuario no encontrado");
    return "error"; 
}
    }
