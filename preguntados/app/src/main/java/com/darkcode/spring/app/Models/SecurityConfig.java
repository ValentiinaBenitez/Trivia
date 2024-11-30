
package com.darkcode.spring.app.Models;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Configurar autorizaciones
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/MainPage", "/registrarse", "/registro", "/css/**", "/js/**").permitAll() // Rutas públicas
                .anyRequest().authenticated() // Proteger todas las demás rutas
            )
            // Configurar inicio de sesión
            .formLogin(login -> login
                .loginPage("/MainPage") // Ruta de la página de inicio de sesión personalizada
                .defaultSuccessUrl("/home", true) // Redirigir después de un inicio de sesión exitoso
                .permitAll() // Permitir acceso a la página de inicio de sesión sin autenticación
            )
            // Configurar cierre de sesión
            .logout(logout -> logout
                .logoutUrl("/logout") // Ruta para cerrar sesión
                .logoutSuccessUrl("/MainPage?logout") // Redirigir después de cerrar sesión
                .permitAll() // Permitir el acceso a la URL de cierre de sesión
            );

        return http.build();
    }
}

