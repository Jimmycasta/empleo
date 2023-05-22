package com.jimmycasta.empleo.Repository;


import com.jimmycasta.empleo.Entities.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

    List<Vacante> findByEstatus(String estatus);

    List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado,String estatus);

    List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double salario1, double salario2);

    List<Vacante> findByEstatusIn(String[] estatus);
}
