package com.darkcode.spring.app.Controllers;

import com.darkcode.spring.app.Models.Usuario;
import com.darkcode.spring.app.Repositories.UsuarioRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") // Prefijo común para tus endpoints
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para guardar un usuario
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
     @PutMapping("/usuarios/{id}/puntos")
    public ResponseEntity<Usuario> actualizarPuntaje(@PathVariable Long id, @RequestParam int puntos) {
        // Buscar al usuario por su ID
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            // Actualizar el puntaje del usuario
            usuario.setPuntos(usuario.getPuntos() + puntos);
            // Guardar los cambios en la base de datos
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // @PostMapping("/api/actualizarPuntos")
    // public ResponseEntity<String> actualizarPuntos(@RequestParam String username, @RequestParam int puntos) {
    //     Usuario usuario = usuarioRepository.findByUsername(username);
    //     if (usuario != null) {
    //         usuario.setPuntos(usuario.getPuntos() + puntos); // Incrementar puntos
    //         usuarioRepository.save(usuario); // Guardar en la base de datos
    //         return ResponseEntity.ok("Puntos actualizados correctamente.");
    //     } else {
    //         return ResponseEntity.badRequest().body("Usuario no encontrado.");
    //     }
    // }
    

}

