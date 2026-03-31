package com.fidenoticias.fidenoticias.controller;

import com.fidenoticias.fidenoticias.model.Usuario;
import com.fidenoticias.fidenoticias.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo,
                        @RequestParam String contrasena,
                        HttpSession session,
                        RedirectAttributes redirectAttrs) {
        try {
            Usuario usuario = usuarioService.autenticar(correo, contrasena);
            session.setAttribute("usuarioLogueado", usuario);
            redirectAttrs.addFlashAttribute("mensaje", "Bienvenido, " + usuario.getNombre());
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "auth/registro";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,
                           @RequestParam String correo,
                           @RequestParam String contrasena,
                           @RequestParam String confirmarContrasena,
                           RedirectAttributes redirectAttrs) {
        try {
            usuarioService.registrarUsuario(nombre, correo, contrasena, confirmarContrasena);
            redirectAttrs.addFlashAttribute("mensaje", "Usuario registrado exitosamente. Ahora puedes iniciar sesión.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/registro";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttrs) {
        session.invalidate();
        redirectAttrs.addFlashAttribute("mensaje", "Sesión cerrada correctamente");
        return "redirect:/login";
    }
}