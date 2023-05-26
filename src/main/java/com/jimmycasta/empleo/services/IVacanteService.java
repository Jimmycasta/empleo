package com.jimmycasta.empleo.services;

import com.jimmycasta.empleo.Entities.Vacante;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVacanteService {

    List<Vacante> buscarTodos();

    Page<Vacante> buscarTodos(Pageable page);

    List<Vacante> buscarByExample(Example<Vacante> example);

    Vacante buscarPorId(int id);

    void guardar(Vacante vacante);

    List<Vacante> buscarDestacados();

    void eliminar(int id);




}
