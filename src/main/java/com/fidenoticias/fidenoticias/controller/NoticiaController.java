/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.controller;

import com.fidenoticias.fidenoticias.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author bsoli
 */
@Controller
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/tema/{idTema}")
    public String listarPorTema(@PathVariable Long idTema, Model model) {
        model.addAttribute("noticias", noticiaService.getUltimasNoticiasPorTema(idTema));
        model.addAttribute("idTema", idTema);
        return "noticias/lista";
    }

    @PostMapping("/{idNoticia}/visita")
    public String registrarVisita(@PathVariable Long idNoticia,
                                  @RequestParam Long idUsuario,
                                  RedirectAttributes redirectAttrs) {
        noticiaService.registrarVisita(idNoticia, idUsuario);
        redirectAttrs.addFlashAttribute("mensaje", "Visita registrada correctamente");
        return "redirect:/noticias/tema/0";
    }

    @PostMapping("/{idNoticia}/calificar")
    public String calificar(@PathVariable Long idNoticia,
                            @RequestParam Long idUsuario,
                            @RequestParam Integer puntuacion,
                            RedirectAttributes redirectAttrs) {
        try {
            noticiaService.registrarCalificacion(idNoticia, idUsuario, puntuacion);
            redirectAttrs.addFlashAttribute("mensaje", "¡Calificación registrada!");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/noticias/tema/0";
    }
}
