package com.example.demo.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        String sql = "INSERT INTO usuario(cpf, nome, data_nascimento) VALUES (?, ?, ?)";

        int rows = jdbcTemplate.update(sql, usuario.getCpf(), usuario.getNome(), usuario.getDataNascimento());
        if (rows > 0) {
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable("cpf") int cpf) {
        String sql = "SELECT cpf, nome, data_nascimento FROM usuario WHERE cpf = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, new Object[] { cpf },
                (rs, rowNum) -> new Usuario(rs.getInt("cpf"), rs.getString("nome"), rs.getDate("data_nascimento").toLocalDate()));
        if (!usuarios.isEmpty()) {
            return new ResponseEntity<>(usuarios.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
