package com.darkcode.spring.app.Controllers;

import com.darkcode.spring.app.Models.Usuario;
import com.darkcode.spring.app.Repositories.UsuarioRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/MainPage")
    public String showLoginPage() {
        return "MainPage"; 
    }

    @PostMapping("/MainPage")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
    
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get(); // Obtén el objeto Usuario del Optional
            if (usuario.getPassword().equals(password)) {
                model.addAttribute("usuario", usuario);
                return "redirect:/home?username=" + username;
            } else {
                model.addAttribute("error", "Usuario o contraseña incorrectos");
                return "MainPage";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "MainPage";
        }
    }
    
    
    

    @GetMapping("/home")
public String home(@RequestParam String username, Model model) {
    Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);
    if (optionalUsuario.isPresent()) {
        Usuario usuario = optionalUsuario.get();
        model.addAttribute("usuario", usuario); // Asegúrate de pasar el objeto 'usuario'
        return "home"; // Renderiza la plantilla 'home.html'
    } else {
        model.addAttribute("error", "Usuario no encontrado");
        return "error"; // Muestra una página de error si el usuario no existe
    }
}

}


