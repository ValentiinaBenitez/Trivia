package com.darkcode.spring.app.Controllers;
import java.util.Optional;

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
    public String ruleta(@RequestParam String username, Model model) {
    Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
    if (optionalUsuario.isPresent()) {
        Usuario usuario = optionalUsuario.get();
        model.addAttribute("usuario", usuario); // Pasar el objeto 'usuario' al modelo
        return "ruleta"; // Renderizar la plantilla 'ruleta.html'
    } else {
        model.addAttribute("error", "Usuario no encontrado");
        return "error"; // Mostrar una página de error si el usuario no existe
    }
}

    
    


}
