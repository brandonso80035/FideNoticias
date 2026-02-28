/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.model;

/**
 *
 * @author bsoli
 */
public class SubTema {
    private Long idSubtema;
    private String nombre;
    private Long idTema;
    private String nombreTema;

    public SubTema() {}
    public Long getIdSubtema() { return idSubtema; }
    public void setIdSubtema(Long idSubtema) { this.idSubtema = idSubtema; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Long getIdTema() { return idTema; }
    public void setIdTema(Long idTema) { this.idTema = idTema; }
    public String getNombreTema() { return nombreTema; }
    public void setNombreTema(String nombreTema) { this.nombreTema = nombreTema; }
}