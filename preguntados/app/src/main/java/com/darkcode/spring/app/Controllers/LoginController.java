package com.darkcode.spring.app.Controllers;

import com.darkcode.spring.app.Models.Usuario;
import com.darkcode.spring.app.Repositories.UsuarioRepository;
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
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            model.addAttribute("usuario", usuario);
            return "redirect:/home?username=" + username; 
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "MainPage";
        }
    }
    

    @GetMapping("/home")
    public String homePage(@RequestParam String username, Model model) {
    Usuario usuario = usuarioRepository.findByUsername(username);
    if (usuario != null) {
        model.addAttribute("usuario", usuario);
        return "home"; 
    } else {
        return "redirect:/MainPage";
    }
}

}
