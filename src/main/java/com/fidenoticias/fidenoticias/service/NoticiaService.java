/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.service;

import com.fidenoticias.fidenoticias.model.Noticia;
import com.fidenoticias.fidenoticias.repository.NoticiaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 *
 * @author bsoli
 */
@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Value("${app.noticias.max-resultados:10}")
    private Integer maxResultados;

    public void registrarVisita(Long idNoticia, Long idUsuario) {
        noticiaRepository.registrarVisita(idNoticia, idUsuario);
    }

    public void registrarCalificacion(Long idNoticia, Long idUsuario, Integer puntuacion) {
        if (puntuacion < 1 || puntuacion > 5) {
            throw new IllegalArgumentException("La puntuación debe estar entre 1 y 5");
        }
        noticiaRepository.registrarCalificacion(idNoticia, idUsuario, puntuacion);
    }

    public List<Noticia> getUltimasNoticiasPorTema(Long idTema) {
        return noticiaRepository.getUltimasNoticiasPorTema(idTema, maxResultados);
    }

    public List<Noticia> getRankingMasVisitadas(Long idTema, boolean soloPortada) {
        return noticiaRepository.getRankingMasVisitadas(idTema, soloPortada ? "S" : "N");
    }

    public List<Noticia> getRankingMejorCalificadas(Long idTema, boolean soloPortada) {
        return noticiaRepository.getRankingMejorCalificadas(idTema, soloPortada ? "S" : "N");
    }

    public List<Noticia> getRankingMasEnviadasCorreo(Long idTema, boolean soloPortada) {
        return noticiaRepository.getRankingMasEnviadasCorreo(idTema, soloPortada ? "S" : "N");
    }
}