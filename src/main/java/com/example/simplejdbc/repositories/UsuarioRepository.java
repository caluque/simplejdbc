package com.example.simplejdbc.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.simplejdbc.entities.Usuario;
import com.example.simplejdbc.mappers.UsuarioRowMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@AllArgsConstructor
public class UsuarioRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UsuarioRowMapper usuarioRowMapper;

    public List<Usuario> findAll() {
        String query = "SELECT id, nombre, edad FROM usuarios;";

        return jdbcTemplate.query(query, usuarioRowMapper);
    }

    public Usuario findById(int id) {
        String query = "SELECT id, nombre, edad FROM usuarios WHERE id = ?;";
        List<Usuario> list = jdbcTemplate.query(query, usuarioRowMapper, id);

        if(list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    public boolean existsById(int id) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE id = ?;";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, id);

        return count != null && count > 0;
    }

    public boolean existsByNombre(String nombre) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?;";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, nombre);

        return count != null && count > 0;
    }

    public Usuario save(Usuario usuario) {
        String sql = usuario.getId() != null ?
            "UPDATE usuarios SET nombre = ?, edad = ? WHERE id = ?" :
            "INSERT INTO usuarios (nombre, edad) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Number id = null;

        PreparedStatementCreator psc = conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getEdad());

            if(usuario.getId() != null) {
                ps.setInt(3, usuario.getId());
            }

            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);

        if(!keyHolder.getKeys().isEmpty()) {
            Map<String, Object> keys = keyHolder.getKeys(); 
            id = (Number) keys.get("id");
        }

        if (id != null) {
            usuario.setId(id.intValue());
        }

        return usuario;
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?;";
        jdbcTemplate.update(sql, id);
    }

}
