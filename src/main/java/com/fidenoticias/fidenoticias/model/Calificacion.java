/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.model;

import java.time.LocalDateTime;

/**
 *
 * @author bsoli
 */
public class Calificacion {
    private Long idCalificacion;
    private Long idUsuario;
    private Long idNoticia;
    private Integer puntuacion;
    private LocalDateTime fechaCalificacion;

    public Calificacion() {}
    public Long getIdCalificacion() { return idCalificacion; }
    public void setIdCalificacion(Long id) { this.idCalificacion = id; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long id) { this.idUsuario = id; }
    public Long getIdNoticia() { return idNoticia; }
    public void setIdNoticia(Long id) { this.idNoticia = id; }
    public Integer getPuntuacion() { return puntuacion; }
    public void setPuntuacion(Integer p) { this.puntuacion = p; }
    public LocalDateTime getFechaCalificacion() { return fechaCalificacion; }
    public void setFechaCalificacion(LocalDateTime f) { this.fechaCalificacion = f; }
}