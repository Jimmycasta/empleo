package com.jimmycasta.empleo.Controller;

import com.jimmycasta.empleo.Entities.Perfil;
import com.jimmycasta.empleo.Entities.Usuario;
import com.jimmycasta.empleo.Entities.Vacante;
import com.jimmycasta.empleo.services.ICategoriaService;
import com.jimmycasta.empleo.services.IUsuariosService;
import com.jimmycasta.empleo.services.IVacanteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private IVacanteService vacanteService;

    @Autowired
    private IUsuariosService usuariosService;

    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @GetMapping("/registro")
    public String registro(Usuario usuario) {
        return "formRegistro";
    }

    @PostMapping("/registro")
    public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
        String pwdPlano = usuario.getPassword();
        String pwdEncriptado = passwordEncoder.encode(pwdPlano);
        usuario.setPassword(pwdEncriptado);
        usuario.setEstatus(1);
        usuario.setFechaRegistro(new Date());
        Perfil perfil = new Perfil();
        perfil.setId(3);
        usuario.agregar(perfil);
        usuariosService.guardar(usuario);
        attributes.addFlashAttribute("msg", "Registro guardado");
        return "redirect:/usuarios/index";

    }

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {
        System.out.println("Buscado por: " + vacante);
        Example<Vacante> example = Example.of(vacante);
        List<Vacante> lista = vacanteService.buscarByExample(example);
        model.addAttribute("vacantes", lista);
        return "home";
    }

    //si detecta campos String vacios en los DataBinding, se configuran a null
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ModelAttribute
    public void setAttribute(Model model) {
        Vacante vacanteSearch = new Vacante();
        vacanteSearch.reset();
        model.addAttribute("categorias", categoriaService.buscarTodos());
        model.addAttribute("search", vacanteSearch);

    }

    @GetMapping("/index")
    public String mostrarIndex(Authentication auth, HttpSession session,Model model) {
        String username = auth.getName();
        System.out.println("El nombre es: " + username);

        //Recupera Roles de usuario que ingreso.
        for (GrantedAuthority rol : auth.getAuthorities()) {
            System.out.println("Rol: " + rol.getAuthority());
        }
        //verifica si el atributo usuario es null, si es null lo crea.
        if (session.getAttribute("usuario") == null) {
            Usuario usuario = usuariosService.buscarPorUsername(username);
            session.setAttribute("usuario", usuario);
            usuario.setPassword("null");
            System.out.println("usuario" + usuario);
        }
        return "redirect:/";
    }

    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
    public String encriptar(@PathVariable("texto") String texto) {
        return "El password " + texto + " " + "encriptado con Bcrypt: " + passwordEncoder.encode(texto);
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "formLogin";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request,null,null);
        return "redirect:/login";

    }


}
