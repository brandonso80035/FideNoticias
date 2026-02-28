/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.service;

import com.fidenoticias.fidenoticias.model.Usuario;
import com.fidenoticias.fidenoticias.repository.UsuarioRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author bsoli
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void crearUsuario(String nombre, String correo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("Correo electrónico inválido");
        }
        usuarioRepository.crearUsuario(nombre, correo);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    public Map<String, Object> getReporteUsuario(Long idUsuario) {
        return usuarioRepository.getReporteUsuario(idUsuario);
    }
}
