package com.example.simplejdbc.services;

import java.util.List;

import com.example.simplejdbc.dto.UsuarioRequestDto;
import com.example.simplejdbc.dto.UsuarioResponseDto;

public interface UsuarioService {

    List<UsuarioResponseDto> obtenerUsuarios();
    UsuarioResponseDto obtenerUsuarioPorId(int id);
    UsuarioResponseDto agregarUsuario(UsuarioRequestDto dto);
    void actualizarUsuario(int id, UsuarioRequestDto dto);
    void eliminarUsuarioPorId(int id);

}
