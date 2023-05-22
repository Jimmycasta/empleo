package com.jimmycasta.empleo.Repository;


import com.jimmycasta.empleo.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
