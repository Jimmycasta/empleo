package com.jimmycasta.empleo.services;

import com.jimmycasta.empleo.Entities.Vacante;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class VacanteServiceImpl implements IVacanteService {

    List<Vacante> lista = null;
    public VacanteServiceImpl() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        lista = new LinkedList<>();

        try {

            Vacante vacante1 = new Vacante();
            vacante1.setId(1);
            vacante1.setNombre("Ing Civil");
            vacante1.setDescripcion("Construir obras");
            vacante1.setFecha(sdf.parse("02-05-2023"));
            vacante1.setSalario(2000);
            vacante1.setDestacado(1);
            vacante1.setImagen("empresa1.png");

            Vacante vacante2 = new Vacante();
            vacante2.setId(2);
            vacante2.setNombre("Ing Sistemas");
            vacante2.setDescripcion("Desarrollar aplicaciones");
            vacante2.setFecha(sdf.parse("02-04-2023"));
            vacante2.setSalario(3000);
            vacante2.setDestacado(0);
            vacante2.setImagen("empresa2.png");

            Vacante vacante3 = new Vacante();
            vacante3.setId(3);
            vacante3.setNombre("Ing agropecuario");
            vacante3.setDescripcion("Sembrar Frutas");
            vacante3.setFecha(sdf.parse("05-05-2023"));
            vacante3.setSalario(1000);
            vacante3.setDestacado(0);
            vacante3.setImagen("empresa3.png");

            Vacante vacante4 = new Vacante();
            vacante4.setId(4);
            vacante4.setNombre("Conductor");
            vacante4.setDescripcion("Trasportar personas");
            vacante4.setFecha(sdf.parse("04-04-2023"));
            vacante4.setSalario(1000);
            vacante4.setDestacado(1);
            vacante4.setImagen("empresa4.png");

            Vacante vacante5 = new Vacante();
            vacante5.setId(5);
            vacante5.setNombre("Secretaria");
            vacante5.setDescripcion("Contestar y atender ");
            vacante5.setFecha(sdf.parse("10-04-2023"));
            vacante5.setSalario(1500);
            vacante5.setDestacado(1);
            vacante5.setImagen("empresa5.png");

            lista.add(vacante1);
            lista.add(vacante2);
            lista.add(vacante3);
            lista.add(vacante4);
            lista.add(vacante5);


        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Vacante> buscarTodos() {

        return lista;
    }

    @Override
    public Vacante buscarPorId(int id) {

        for (Vacante v : lista) {

            if (v.getId() == id)
                return v;
        }

        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        lista.add(vacante);
    }
}
