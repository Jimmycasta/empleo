package com.jimmycasta.empleo.services;

import com.jimmycasta.empleo.Entities.Vacante;

import java.util.List;

public interface IVacanteService {

    List<Vacante> buscarTodos();

    Vacante buscarPorId(int id);
    void guardar(Vacante vacante);
}
