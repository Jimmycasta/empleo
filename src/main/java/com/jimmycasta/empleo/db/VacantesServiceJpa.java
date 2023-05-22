package com.jimmycasta.empleo.db;

import com.jimmycasta.empleo.Entities.Vacante;
import com.jimmycasta.empleo.Repository.VacantesRepository;
import com.jimmycasta.empleo.services.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class VacantesServiceJpa implements IVacanteService {

    @Autowired
    private VacantesRepository vacanteService;

    @Override
    public List<Vacante> buscarTodos() {

        return vacanteService.findAll();
    }

    @Override
    public Vacante buscarPorId(int id) {

        Optional<Vacante> optional = vacanteService.findById(id);

        if (optional.isPresent()) {

            return optional.get();
        }

        return null;
    }

    @Override
    public void guardar(Vacante vacante) {

        vacanteService.save(vacante);

    }
}
