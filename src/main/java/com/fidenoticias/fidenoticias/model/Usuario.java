/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.model;

import java.time.LocalDate;

/**
 *
 * @author bsoli
 */
public class Usuario {
    private Long idUsuario;
    private String nombre;
    private String correo;
    private LocalDate fechaRegistro;
    private String estado;

    public Usuario() {}
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate f) { this.fechaRegistro = f; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public boolean isActivo() { return "A".equalsIgnoreCase(estado); }
}
