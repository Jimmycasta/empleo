package com.jimmycasta.empleo.Controller;

import com.jimmycasta.empleo.Entities.Vacante;
import com.jimmycasta.empleo.services.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private IVacanteService vacanteService;

    @GetMapping("/")
    public String mostrarHome(Model model) {
       List<Vacante> lista = vacanteService.buscarTodos();
       model.addAttribute("vacantes", lista);
        return "home";
    }

    @GetMapping("listado")
    public String mostrarLista(Model model) {
        LinkedList<String> listado = new LinkedList<>();
        listado.add("Desarrollo de Sistemas");
        listado.add("Contabilidad");
        listado.add("Mesero");
        listado.add("Chofer");
        model.addAttribute("empleos", listado);
        return "listado";

    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model) {
        Vacante vacante = new Vacante();
        vacante.setNombre("Ing en Sistemas");
        vacante.setDescripcion("Soporte a la Red");
        vacante.setFecha(new Date());
        vacante.setSalario(14000);
        model.addAttribute(vacante);
        return "detalle";

    }

    @GetMapping("/tabla")
    public String mostrarTabla(Model model) {
        List<Vacante> lista = vacanteService.buscarTodos();
        model.addAttribute("vacantes", lista);
        return "tabla";
    }


}
