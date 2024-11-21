package com.darkcode.spring.app.Models;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.darkcode.spring.app.Repositories.UsuarioRepository;


@Component
public class TestDatabaseConnection implements CommandLineRunner {

    public TestDatabaseConnection(UsuarioRepository usuarioRepository) {
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Conexión a la base de datos probada correctamente.");
    }
}

