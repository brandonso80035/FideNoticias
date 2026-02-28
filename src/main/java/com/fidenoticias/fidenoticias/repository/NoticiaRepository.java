/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.repository;

import com.fidenoticias.fidenoticias.model.Noticia;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bsoli
 */
@Repository
public class NoticiaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void registrarVisita(Long idNoticia, Long idUsuario) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_NOTICIAS")
                .withProcedureName("SP_REGISTRAR_VISITA");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_NOTICIA", idNoticia)
                .addValue("P_ID_USUARIO", idUsuario);

        call.execute(params);
    }

    public void registrarCalificacion(Long idNoticia, Long idUsuario, Integer puntuacion) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_NOTICIAS")
                .withProcedureName("SP_REGISTRAR_CALIFICACION");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_NOTICIA", idNoticia)
                .addValue("P_ID_USUARIO", idUsuario)
                .addValue("P_PUNTUACION", puntuacion);

        call.execute(params);
    }

    public List<Noticia> getUltimasNoticiasPorTema(Long idTema, Integer maxResultados) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_NOTICIAS")
                .withFunctionName("FN_ULTIMAS_NOTICIAS")
                .returningResultSet("RESULTADO", noticiaRowMapper());

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_TEMA", idTema)
                .addValue("P_MAX_RESULTADOS", maxResultados);

        Map<String, Object> result = call.execute(params);
        return (List<Noticia>) result.get("RESULTADO");
    }

    public List<Noticia> getRankingMasVisitadas(Long idTema, String soloPortada) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_RANKINGS")
                .withFunctionName("FN_RANKING_VISITAS")
                .returningResultSet("RESULTADO", noticiaRowMapper());

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_TEMA", idTema)
                .addValue("P_SOLO_PORTADA", soloPortada);

        Map<String, Object> result = call.execute(params);
        return (List<Noticia>) result.get("RESULTADO");
    }

    public List<Noticia> getRankingMejorCalificadas(Long idTema, String soloPortada) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_RANKINGS")
                .withFunctionName("FN_RANKING_CALIFICACION")
                .returningResultSet("RESULTADO", noticiaRowMapper());

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_TEMA", idTema)
                .addValue("P_SOLO_PORTADA", soloPortada);

        Map<String, Object> result = call.execute(params);
        return (List<Noticia>) result.get("RESULTADO");
    }

    public List<Noticia> getRankingMasEnviadasCorreo(Long idTema, String soloPortada) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_RANKINGS")
                .withFunctionName("FN_RANKING_CORREOS")
                .returningResultSet("RESULTADO", noticiaRowMapper());

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_TEMA", idTema)
                .addValue("P_SOLO_PORTADA", soloPortada);

        Map<String, Object> result = call.execute(params);
        return (List<Noticia>) result.get("RESULTADO");
    }

    private RowMapper<Noticia> noticiaRowMapper() {
        return (rs, rowNum) -> {
            Noticia n = new Noticia();
            n.setIdNoticia(rs.getLong("ID_NOTICIA"));
            n.setTitulo(rs.getString("TITULO"));
            n.setVistas(rs.getLong("VISTAS_TOTALES"));
            n.setPortada(rs.getString("PORTADA"));
            n.setPromedioCalificacion(rs.getDouble("PROMEDIO_CALIFICACION"));
            n.setIdSubtema(rs.getLong("ID_SUBTEMA"));
            try { n.setNombreSubtema(rs.getString("NOMBRE_SUBTEMA")); } catch (Exception ignored) {}
            try { n.setNombreTema(rs.getString("NOMBRE_TEMA")); } catch (Exception ignored) {}
            try { n.setContenido(rs.getString("CONTENIDO")); } catch (Exception ignored) {}
            if (rs.getTimestamp("FECHA_PUBLICACION") != null) {
                n.setFechaPublicacion(rs.getTimestamp("FECHA_PUBLICACION").toLocalDateTime());
            }
            return n;
        };
    }
}