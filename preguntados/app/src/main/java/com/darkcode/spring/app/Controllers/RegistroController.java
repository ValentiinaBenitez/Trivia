package com.darkcode.spring.app.Controllers;

import com.darkcode.spring.app.Models.Usuario;
import com.darkcode.spring.app.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Mostrar la página de registro
    @GetMapping("/registrarse")
    public String mostrarPaginaRegistro() {
        return "registro"; // archivo registro.html en templates
    }

    // Manejar el formulario de registro
    @PostMapping("/registro")
    public String registrarUsuario(Usuario usuario, Model model) {
        // Verificar si el nombre de usuario ya está registrado
        if (usuarioRepository.findByUsername(usuario.getUsername()) != null) {
            model.addAttribute("error", "El nombre de usuario ya está registrado.");
            return "registro";
        }

        // Guardar el usuario en la base de datos
        usuarioRepository.save(usuario);
        model.addAttribute("success", "Usuario registrado con éxito.");
        return "MainPage"; // Redirige a la página de inicio de sesión
    }

   
}
