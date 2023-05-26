package com.jimmycasta.empleo.Controller;

import com.jimmycasta.empleo.Entities.Vacante;
import com.jimmycasta.empleo.Util.Utileria;
import com.jimmycasta.empleo.services.ICategoriaService;
import com.jimmycasta.empleo.services.IVacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Value("${ruta.imagenes}")
    private String rutaImagenes;

    @Autowired
    private IVacanteService vacanteService;
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {
        Vacante vacante = vacanteService.buscarPorId(idVacante);
        model.addAttribute("vacante", vacante);
        return "detalle";

    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
        vacanteService.eliminar(id);
        attributes.addFlashAttribute("msg", "¡Vacante eliminada!");
        return "redirect:/vacantes/index";

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

            String ruta = rutaImagenes;
            String nombreImagen = Utileria.guardarArchivo(multipart, ruta);

            if (nombreImagen != null) {
                vacante.setImagen(nombreImagen);
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

    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Vacante> listaVacante = vacanteService.buscarTodos(page);
        model.addAttribute("listaVacantes", listaVacante);
        return "vacantes/listVacantes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        Vacante vacante = vacanteService.buscarPorId(id);
        model.addAttribute("vacante", vacante);
        model.addAttribute("categorias", categoriaService.buscarTodos());
        return "/vacantes/formvacante";
    }


}
