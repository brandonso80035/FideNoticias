package com.fidenoticias.fidenoticias.controller;

import com.fidenoticias.fidenoticias.model.Usuario;
import com.fidenoticias.fidenoticias.service.NoticiaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/tema/{idTema}")
    public String listarPorTema(@PathVariable Long idTema, Model model, HttpSession session) {
        model.addAttribute("noticias", noticiaService.getUltimasNoticiasPorTema(idTema));
        model.addAttribute("idTema", idTema);
        model.addAttribute("usuarioLogueado", session.getAttribute("usuarioLogueado"));
        return "noticias/lista";
    }

    @PostMapping("/{idNoticia}/visita")
    public String registrarVisita(@PathVariable Long idNoticia,
                                  @RequestParam Long idTema,
                                  HttpSession session,
                                  RedirectAttributes redirectAttrs) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            redirectAttrs.addFlashAttribute("error", "Debes iniciar sesión para registrar visitas");
            return "redirect:/login";
        }

        noticiaService.registrarVisita(idNoticia, usuario.getIdUsuario());
        redirectAttrs.addFlashAttribute("mensaje", "Visita registrada correctamente");
        return "redirect:/noticias/tema/" + idTema;
    }

    @PostMapping("/{idNoticia}/calificar")
    public String calificar(@PathVariable Long idNoticia,
                            @RequestParam Long idTema,
                            @RequestParam Integer puntuacion,
                            HttpSession session,
                            RedirectAttributes redirectAttrs) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            redirectAttrs.addFlashAttribute("error", "Debes iniciar sesión para calificar");
            return "redirect:/login";
        }

        try {
            noticiaService.registrarCalificacion(idNoticia, usuario.getIdUsuario(), puntuacion);
            redirectAttrs.addFlashAttribute("mensaje", "¡Calificación registrada!");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/noticias/tema/" + idTema;
    }
}