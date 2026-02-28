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
public class Noticia {
    private Long idNoticia;
    private String titulo;
    private String contenido;
    private Long vistas;
    private LocalDateTime fechaPublicacion;
    private Double promedioCalificacion;
    private Long idSubtema;
    private String portada;
    private String nombreSubtema;
    private String nombreTema;

    public Noticia() {}

    public Long getIdNoticia() { return idNoticia; }
    public void setIdNoticia(Long idNoticia) { this.idNoticia = idNoticia; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public Long getVistas() { return vistas; }
    public void setVistas(Long vistas) { this.vistas = vistas; }
    public LocalDateTime getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDateTime f) { this.fechaPublicacion = f; }
    public Double getPromedioCalificacion() { return promedioCalificacion; }
    public void setPromedioCalificacion(Double p) { this.promedioCalificacion = p; }
    public Long getIdSubtema() { return idSubtema; }
    public void setIdSubtema(Long idSubtema) { this.idSubtema = idSubtema; }
    public String getPortada() { return portada; }
    public void setPortada(String portada) { this.portada = portada; }
    public String getNombreSubtema() { return nombreSubtema; }
    public void setNombreSubtema(String s) { this.nombreSubtema = s; }
    public String getNombreTema() { return nombreTema; }
    public void setNombreTema(String s) { this.nombreTema = s; }
    public boolean isPortada() { return "S".equalsIgnoreCase(portada); }
}