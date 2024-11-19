package com.darkcode.spring.app.Repositories;

import com.darkcode.spring.app.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
