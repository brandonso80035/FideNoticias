/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.model;

/**
 *
 * @author bsoli
 */
public class Tema {
    private Long idTema;
    private String nombre;

    public Tema() {}
    public Tema(Long idTema, String nombre) {
        this.idTema = idTema;
        this.nombre = nombre;
    }
    public Long getIdTema() { return idTema; }
    public void setIdTema(Long idTema) { this.idTema = idTema; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}