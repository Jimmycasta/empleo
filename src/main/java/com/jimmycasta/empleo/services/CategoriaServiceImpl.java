package com.jimmycasta.empleo.services;

import com.jimmycasta.empleo.Entities.Categoria;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    List<Categoria> listaCategoria = null;

    public CategoriaServiceImpl() {

        listaCategoria = new LinkedList<>();

        Categoria categoria1 = new Categoria();
        categoria1.setId(1);
        categoria1.setNombre("Ventas");
        categoria1.setDescripcion("Asesor de Ventas");

        Categoria categoria2 = new Categoria();
        categoria2.setId(2);
        categoria2.setNombre("Contabilidad");
        categoria2.setDescripcion("Contador");


        Categoria categoria3 = new Categoria();
        categoria3.setId(3);
        categoria3.setNombre("Transporte");
        categoria3.setDescripcion("Manejo de Bus");


        Categoria categoria4 = new Categoria();
        categoria4.setId(4);
        categoria4.setNombre("Informatica");
        categoria4.setDescripcion("Soporte Tecnico");


        Categoria categoria5 = new Categoria();
        categoria5.setId(5);
        categoria5.setNombre("Construcción");
        categoria5.setDescripcion("Construcción de Viviendas");


        Categoria categoria6 = new Categoria();
        categoria6.setId(6);
        categoria6.setNombre("Desarrollo de Software");
        categoria6.setDescripcion("Desarrollo de Aplicaciones");

        Categoria categoria7 = new Categoria();
        categoria7.setId(7);
        categoria7.setNombre("Infraestructura");
        categoria7.setDescripcion("Administrador de Infraestructura");

        listaCategoria.add(categoria1);
        listaCategoria.add(categoria2);
        listaCategoria.add(categoria3);
        listaCategoria.add(categoria4);
        listaCategoria.add(categoria5);
        listaCategoria.add(categoria6);
        listaCategoria.add(categoria7);

    }

    @Override
    public List<Categoria> buscarTodos() {

        return listaCategoria;
    }

    @Override
    public Categoria buscarPorId(int id) {

        for (Categoria categoria : listaCategoria) {

            if (categoria.getId() == id)
                return categoria;
        }

        return null;
    }


    @Override
    public void guardar(Categoria categoria) {

        listaCategoria.add(categoria);

    }
}
