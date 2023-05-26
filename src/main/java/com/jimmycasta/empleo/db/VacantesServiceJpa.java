package com.jimmycasta.empleo.db;

import com.jimmycasta.empleo.Entities.Vacante;
import com.jimmycasta.empleo.Repository.VacantesRepository;
import com.jimmycasta.empleo.services.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class VacantesServiceJpa implements IVacanteService {

    @Autowired
    private VacantesRepository vacantesRepository;

    @Override
    public List<Vacante> buscarTodos() {
        return vacantesRepository.findAll();
    }

    @Override
    public Page<Vacante> buscarTodos(Pageable page) {
        return vacantesRepository.findAll(page);
    }

    @Override
    public Vacante buscarPorId(int id) {
        Optional<Vacante> optional = vacantesRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        vacantesRepository.save(vacante);

    }

    @Override
    public List<Vacante> buscarDestacados() {
        return vacantesRepository.findByDestacadoAndEstatusOrderByIdDesc(1, "aprobada");
    }

    @Override
    public void eliminar(int id) {
        vacantesRepository.deleteById(id);
    }

    @Override
    public List<Vacante> buscarByExample(Example<Vacante> example) {
        return vacantesRepository.findAll(example);
    }


}
