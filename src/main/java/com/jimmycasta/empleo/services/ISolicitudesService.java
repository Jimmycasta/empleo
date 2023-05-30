package com.jimmycasta.empleo.services;

import com.jimmycasta.empleo.Entities.Solicitud;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISolicitudesService {

    void guardar(Solicitud solicitud);

    void eliminar(int id);

    List<Solicitud> buscarTodos();

    Solicitud buscarPorId(int id);

    Page<Solicitud> buscarTodos(Pageable page);


}
