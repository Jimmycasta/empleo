package com.jimmycasta.empleo.Repository;


import com.jimmycasta.empleo.Entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
}
