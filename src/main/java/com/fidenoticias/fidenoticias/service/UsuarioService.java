package com.fidenoticias.fidenoticias.service;

import com.fidenoticias.fidenoticias.model.Usuario;
import com.fidenoticias.fidenoticias.repository.UsuarioRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarUsuario(String nombre, String correo, String contrasena, String confirmarContrasena) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (correo == null || correo.isBlank() || !correo.contains("@")) {
            throw new IllegalArgumentException("Correo electrónico inválido");
        }

        if (contrasena == null || contrasena.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        if (contrasena.length() < 6) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres");
        }

        if (confirmarContrasena == null || !contrasena.equals(confirmarContrasena)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        String contrasenaHash = generarHashSHA256(contrasena);
        usuarioRepository.crearUsuario(nombre.trim(), correo.trim(), contrasenaHash);
    }

    public Usuario autenticar(String correo, String contrasena) {
        if (correo == null || correo.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar el correo");
        }

        if (contrasena == null || contrasena.isBlank()) {
            throw new IllegalArgumentException("Debe ingresar la contraseña");
        }

        Usuario usuario = usuarioRepository.buscarPorCorreo(correo.trim());

        if (usuario == null) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        if (!usuario.isActivo()) {
            throw new IllegalArgumentException("El usuario está inactivo");
        }

        String contrasenaHash = generarHashSHA256(contrasena);

        if (!contrasenaHash.equals(usuario.getContrasenaHash())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    public Map<String, Object> getReporteUsuario(Long idUsuario) {
        return usuarioRepository.getReporteUsuario(idUsuario);
    }

    private String generarHashSHA256(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(texto.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar el hash de la contraseña", e);
        }
    }
}