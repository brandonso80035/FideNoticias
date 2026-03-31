package com.fidenoticias.fidenoticias.controller;

import com.fidenoticias.fidenoticias.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios/lista";
    }

    @GetMapping("/{idUsuario}/reporte")
    public String reporte(@PathVariable Long idUsuario, Model model) {
        model.addAttribute("reporte", usuarioService.getReporteUsuario(idUsuario));
        model.addAttribute("idUsuario", idUsuario);
        return "reportes/usuario";
    }
}