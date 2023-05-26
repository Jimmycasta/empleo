package com.jimmycasta.empleo.services;

import com.jimmycasta.empleo.Entities.Usuario;

import java.util.List;

public interface IUsuariosService {

void guardar(Usuario usuario);

void eliminar (int id);

List<Usuario> buscarTodos();

}
