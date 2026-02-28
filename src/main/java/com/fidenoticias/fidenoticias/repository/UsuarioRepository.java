/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fidenoticias.fidenoticias.repository;

import com.fidenoticias.fidenoticias.model.Usuario;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bsoli
 */
@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void crearUsuario(String nombre, String correo) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_USUARIOS")
                .withProcedureName("SP_CREAR_USUARIO");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_NOMBRE", nombre)
                .addValue("P_CORREO", correo);

        call.execute(params);
    }

    public List<Usuario> listarUsuarios() {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_USUARIOS")
                .withFunctionName("FN_LISTAR_USUARIOS")
                .returningResultSet("RESULTADO", (rs, rowNum) -> {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getLong("ID_USUARIO"));
                    u.setNombre(rs.getString("NOMBRE"));
                    u.setCorreo(rs.getString("CORREO"));
                    u.setEstado(rs.getString("ESTADO"));
                    if (rs.getDate("FECHA_REGISTRO") != null) {
                        u.setFechaRegistro(rs.getDate("FECHA_REGISTRO").toLocalDate());
                    }
                    return u;
                });

        Map<String, Object> result = call.execute();
        return (List<Usuario>) result.get("RESULTADO");
    }

    public Map<String, Object> getReporteUsuario(Long idUsuario) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_USUARIOS")
                .withProcedureName("SP_REPORTE_USUARIO");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_ID_USUARIO", idUsuario);

        return call.execute(params);
    }
}