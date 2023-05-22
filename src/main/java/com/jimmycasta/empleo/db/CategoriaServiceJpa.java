package com.jimmycasta.empleo.db;

import com.jimmycasta.empleo.Entities.Categoria;
import com.jimmycasta.empleo.Repository.CategoriasRepository;
import com.jimmycasta.empleo.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CategoriaServiceJpa implements ICategoriaService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public List<Categoria> buscarTodos() {
        return categoriasRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(int id) {

        Optional<Categoria> optional = categoriasRepository.findById(id);

        if (optional.isPresent()) {

            return optional.get();

        }
        return null;
    }

    @Override
    public void guardar(Categoria categoria) {

        categoriasRepository.save(categoria);

    }
}
