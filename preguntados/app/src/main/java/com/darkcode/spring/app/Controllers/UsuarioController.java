package com.darkcode.spring.app.Controllers;

import com.darkcode.spring.app.Models.Usuario;
import com.darkcode.spring.app.Repositories.UsuarioRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api") // Prefijo común para tus endpoints
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> obtenerTodosUsuarios() {
        return usuarioRepository.findAll();
    }

   
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
     @PutMapping("/usuarios/{id}/puntos")
    public ResponseEntity<Usuario> actualizarPuntaje(@PathVariable Long id, @RequestParam int puntos) {
      
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
           
            usuario.setPuntos(usuario.getPuntos() + puntos);
          
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

}

