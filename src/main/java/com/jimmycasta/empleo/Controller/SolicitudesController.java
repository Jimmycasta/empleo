package com.jimmycasta.empleo.Controller;

import com.jimmycasta.empleo.Entities.Solicitud;
import com.jimmycasta.empleo.Entities.Vacante;
import com.jimmycasta.empleo.Util.Utileria;
import com.jimmycasta.empleo.services.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Autowired
    private IVacanteService vacanteService;

    @Value("${ruta.hoja.vida}")
    private String rutaCV;

    @GetMapping("/crear/{id}")
    public String crear(@PathVariable("id") int id, Model model) {
        Vacante vacante = vacanteService.buscarPorId(id);
        Solicitud solicitud = new Solicitud();
        model.addAttribute("vacante", vacante);
        model.addAttribute("solicitud", solicitud);
        System.out.println("El Id: " + id);
        return "solicitudes/formSolicitud";

    }

    @PostMapping("/guardar")
    public String guardar(Solicitud solicitud, BindingResult result, @RequestParam("archivoCV") MultipartFile multipartFile) {
        if (result.hasErrors()) {
            System.out.println("Hay errores en el formulario");
            return "solicitudes/formSolicitud";
        }
        if (!multipartFile.isEmpty()) {
            String nombreArchivo = Utileria.guardarArchivo(multipartFile, rutaCV);
            solicitud.setArchivo(nombreArchivo);

        }
        System.out.println("Solicitud" + solicitud);
        return "redirect:/";

    }
}
