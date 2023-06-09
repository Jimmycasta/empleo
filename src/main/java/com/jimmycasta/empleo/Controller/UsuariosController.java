package com.jimmycasta.empleo.Controller;

import com.jimmycasta.empleo.Entities.Usuario;
import com.jimmycasta.empleo.services.IUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService usuariosService;

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Usuario> lista = usuariosService.buscarTodos();
        model.addAttribute("usuarios", lista);
        return "usuarios/listUsuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes attributes) {
        usuariosService.eliminar(id);
        attributes.addFlashAttribute("msg", "¡Usuario eliminado");
        return "redirect:/usuarios/index";

    }
}
