package com.jimmycasta.empleo.Controller;

import com.jimmycasta.empleo.Entities.Vacante;
import com.jimmycasta.empleo.Util.Utileria;
import com.jimmycasta.empleo.services.ICategoriaService;
import com.jimmycasta.empleo.services.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/vacantes")
public class VacanteController {

    @Autowired
    private IVacanteService vacanteService;
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = vacanteService.buscarPorId(idVacante);
        System.out.println("El id de la Vacante es: " + vacante);
        model.addAttribute("vacante", vacante);
        return "detalle";

    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam("id") int idVacante, Model model) {
        model.addAttribute("id", idVacante);
        System.out.println("Vacante eliminada con id:" + idVacante);
        return "eliminar";

    }

    @GetMapping("/crear")
    public String crear(@ModelAttribute("vacante") Vacante vacante, Model model) {
        model.addAttribute("categorias", categoriaService.buscarTodos());
        return "vacantes/formVacante";
    }

    @PostMapping("/guardar")
    public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
                          @RequestParam("archivoImagen") MultipartFile multipart) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurro el siguiente error" + error.getDefaultMessage());
            }
            return "vacantes/formVacante";
        }
        if (!multipart.isEmpty()) {

            String ruta = "c:/imagenes/";
            String nombreImagen = Utileria.guardarArchivo(multipart, ruta);

            if (nombreImagen != null) {

                vacante.setImage(nombreImagen);

            }

        }
        vacanteService.guardar(vacante);
        attributes.addFlashAttribute("mensaje", "¡Vacante guardada!");
        return "redirect:/vacantes/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Vacante> listaVacantes = vacanteService.buscarTodos();
        model.addAttribute("listaVacantes", listaVacantes);

        return "vacantes/listVacantes";

    }

}
