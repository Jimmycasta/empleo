package com.jimmycasta.empleo.db;

import com.jimmycasta.empleo.Entities.Solicitud;
import com.jimmycasta.empleo.Repository.SolicitudesRepository;
import com.jimmycasta.empleo.services.ISolicitudesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudesService implements ISolicitudesService {

    @Autowired
    private SolicitudesRepository solicitudesRepository;

    @Override
    public void guardar(Solicitud solicitud) {
        solicitudesRepository.save(solicitud);
    }

    @Override
    public void eliminar(int id) {
        solicitudesRepository.deleteById(id);
    }

    @Override
    public List<Solicitud> buscarTodos() {
        return solicitudesRepository.findAll();
    }

    @Override
    public Solicitud buscarPorId(int id) {
        Optional<Solicitud> optional = solicitudesRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    @Override
    public Page<Solicitud> buscarTodos(Pageable page) {
        return solicitudesRepository.findAll(page);
    }
}
