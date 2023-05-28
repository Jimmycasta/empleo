package com.jimmycasta.empleo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;

//configuracion para que Spring Security tome los usuarios de la base de datos por defecto.
@Configuration
@EnableWebSecurity
public class DataBaseWebSecurity {

    @Bean
    UserDetailsManager users(DataSource dataSource) {

        //solo con esta línea, Usa la base de datos por defecto
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

        // si se añade estos lines, se usa la Base de datos propia
        users.setUsersByUsernameQuery("SELECT username,password,estatus FROM usuarios WHERE username = ?");
        users.setAuthoritiesByUsernameQuery("SELECT u.username, p.perfil FROM usuarioperfil up INNER JOIN usuarios u ON u.id = up.idUsuario INNER JOIN perfiles p ON p.id = up.idPerfil WHERE u.username = ?");

        return users;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()

                //Los recursos estáticos no requieres autenticación.
                .requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/images/**", "/bcrypt/**").permitAll()

                //las vistas públicas o requieren autenticación.
                .requestMatchers("/", "/registro", "/search", "/vacantes/view").permitAll()

                //Asignar permisos a URL por roles.
                .requestMatchers("/vacantes/**").hasAnyAuthority("Usuario","Administrador")
                .requestMatchers("/categorias/**").hasAnyAuthority("Usuario","Administrador")
                .requestMatchers("/usuarios/**").hasAnyAuthority("Administrador")

                //Todas las demás URL de la aplicación requieren autenticación.
                .anyRequest().authenticated()

                //El formulario de login no requiere autenticación.
                .and().formLogin().loginPage("/login").permitAll();

        return http.build();

    }

    //Método para decirle a Spring que vamos a usar un Login propio (ver la linea de arriba formLogin).

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
