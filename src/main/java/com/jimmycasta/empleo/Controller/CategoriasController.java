package com.jimmycasta.empleo.Controller;

import com.jimmycasta.empleo.Entities.Categoria;
import com.jimmycasta.empleo.services.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private ICategoriaService categoriaService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String mostrarCategorias(Model model) {
        List<Categoria> listaCategoria = categoriaService.buscarTodos();
        model.addAttribute("listaCategoria", listaCategoria);
        return "categorias/listCategorias";
    }

    @GetMapping("/indexPaginate")
        public String mostrarIndexPaginado(Model model, Pageable page){
        Page<Categoria> listaCategoria = categoriaService.buscarTodos(page);
        model.addAttribute("listaCategorias", listaCategoria);
        return "categorias/listCategorias";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public String crearCategoria(Model model) {

        return "categorias/formCategoria";
    }

   @PostMapping("/guardar")
    public String guardarCategoria(Categoria categoria, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {

            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio el siguiente error: " + error.getDefaultMessage());
            }
            return "categorias/listarCategorias";
        }
        categoriaService.guardar(categoria);
        attributes.addFlashAttribute("mensaje", "Categoria guardada correctamente");
        return "redirect:/categorias/index";


    }
     @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes ){
        categoriaService.eliminar(id);
        attributes.addFlashAttribute("msg", "¡Categoria eliminada!");
        return "redirect:/categorias/index";
     }

     @GetMapping("/editar/{id}")
    public String editar (@PathVariable("id") int id, Model model){

        Categoria categoria = categoriaService.buscarPorId(id);

        model.addAttribute("categoria", categoria);

        return "/categorias/formCategoria";
     }
}
