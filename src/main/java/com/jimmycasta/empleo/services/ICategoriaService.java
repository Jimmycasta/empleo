package com.jimmycasta.empleo.services;

import com.jimmycasta.empleo.Entities.Categoria;

import java.util.List;

public interface ICategoriaService {

    List<Categoria> buscarTodos();
    Categoria buscarPorId(int id);
    void guardar(Categoria categoria);



}
