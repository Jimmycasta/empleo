package com.jimmycasta.empleo.Repository;

import com.jimmycasta.empleo.Entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudesRepository extends JpaRepository<Solicitud,Integer> {
}
