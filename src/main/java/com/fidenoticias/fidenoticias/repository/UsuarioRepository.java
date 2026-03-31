package com.fidenoticias.fidenoticias.repository;

import com.fidenoticias.fidenoticias.model.Usuario;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void crearUsuario(String nombre, String correo, String contrasenaHash) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_USUARIOS")
                .withProcedureName("SP_CREAR_USUARIO");

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("P_NOMBRE", nombre)
                .addValue("P_CORREO", correo)
                .addValue("P_CONTRASENA_HASH", contrasenaHash);

        call.execute(params);
    }

    public Usuario buscarPorCorreo(String correo) {
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_USUARIOS")
                .withFunctionName("FN_BUSCAR_POR_CORREO")
                .returningResultSet("RESULTADO", (ResultSet rs, int rowNum) -> {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getLong("ID_USUARIO"));
                    u.setNombre(rs.getString("NOMBRE"));
                    u.setCorreo(rs.getString("CORREO"));
                    u.setContrasenaHash(rs.getString("CONTRASENA_HASH"));
                    u.setEstado(rs.getString("ESTADO"));

                    if (rs.getDate("FECHA_REGISTRO") != null) {
                        u.setFechaRegistro(rs.getDate("FECHA_REGISTRO").toLocalDate());
                    }

                    return u;
                });

        Map<String, Object> result = call.execute(
                new MapSqlParameterSource().addValue("P_CORREO", correo)
        );

        List<Usuario> usuarios = (List<Usuario>) result.get("RESULTADO");

        if (usuarios == null || usuarios.isEmpty()) {
            return null;
        }

        return usuarios.get(0);
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