package com.jimmycasta.empleo.services;

import com.jimmycasta.empleo.Entities.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoriaService {

    List<Categoria> buscarTodos();

    Page<Categoria> buscarTodos(Pageable page);
    Categoria buscarPorId(int id);
    void guardar(Categoria categoria);

    void eliminar(int id);



}
