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

/**
 *
 * @author bsoli
 */
@Controller
@RequestMapping("/rankings")
public class RankingController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping
    public String index() {
        return "rankings/index";
    }

    @GetMapping("/visitas")
    public String rankingVisitas(@RequestParam(required = false) Long idTema,
                                  @RequestParam(defaultValue = "false") boolean portada,
                                  Model model) {
        model.addAttribute("noticias", noticiaService.getRankingMasVisitadas(idTema, portada));
        model.addAttribute("tipo", "Más Visitadas");
        return "rankings/lista";
    }

    @GetMapping("/calificacion")
    public String rankingCalificacion(@RequestParam(required = false) Long idTema,
                                       @RequestParam(defaultValue = "false") boolean portada,
                                       Model model) {
        model.addAttribute("noticias", noticiaService.getRankingMejorCalificadas(idTema, portada));
        model.addAttribute("tipo", "Mejor Calificadas");
        return "rankings/lista";
    }

    @GetMapping("/correos")
    public String rankingCorreos(@RequestParam(required = false) Long idTema,
                                  @RequestParam(defaultValue = "false") boolean portada,
                                  Model model) {
        model.addAttribute("noticias", noticiaService.getRankingMasEnviadasCorreo(idTema, portada));
        model.addAttribute("tipo", "Más Enviadas por Correo");
        return "rankings/lista";
    }
}