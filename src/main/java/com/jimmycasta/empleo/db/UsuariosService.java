package com.jimmycasta.empleo.db;

import com.jimmycasta.empleo.Entities.Usuario;
import com.jimmycasta.empleo.Repository.UsuarioRepository;
import com.jimmycasta.empleo.services.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService implements IUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void guardar(Usuario usuario) {

        usuarioRepository.save(usuario);

    }

    @Override
    public void eliminar(int id) {

        usuarioRepository.deleteById(id);

    }

    @Override
    public List<Usuario> buscarTodos() {

        return usuarioRepository.findAll();
    }
}
