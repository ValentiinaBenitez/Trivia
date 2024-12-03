package com.darkcode.spring.app.Controllers;

import com.darkcode.spring.app.Models.Usuario;
import com.darkcode.spring.app.Repositories.UsuarioRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

   
    @GetMapping("/registrarse")
    public String mostrarPaginaRegistro() {
        return "registro"; 
    }

    @PostMapping("/registro")
    public String registrarUsuario(@Validated Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registro";
        }
    
        // Validar si el nombre de usuario ya existe
        Optional<Usuario> usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());
        if (usuarioExistente.isPresent()) { // Cambia la lógica para verificar si el Optional tiene un valor
            model.addAttribute("error", "El nombre de usuario ya está registrado.");
            return "registro";
        }
    
        // Guardar el nuevo usuario
        usuarioRepository.save(usuario);
        return "redirect:/MainPage";
    }
    


   
}
