/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.controller;


import com.fidenoticias.fidenoticias.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo() {
        return "usuarios/nuevo";
    }

    @PostMapping("/crear")
    public String crearUsuario(@RequestParam String nombre,
                                @RequestParam String correo,
                                RedirectAttributes redirectAttrs) {
        try {
            usuarioService.crearUsuario(nombre, correo);
            redirectAttrs.addFlashAttribute("mensaje", "Usuario creado exitosamente");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/usuarios/nuevo";
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/{idUsuario}/reporte")
    public String reporte(@PathVariable Long idUsuario, Model model) {
        model.addAttribute("reporte", usuarioService.getReporteUsuario(idUsuario));
        model.addAttribute("idUsuario", idUsuario);
        return "reportes/usuario";
    }
}