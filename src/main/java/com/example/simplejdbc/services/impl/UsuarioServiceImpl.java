package com.example.simplejdbc.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.simplejdbc.dto.UsuarioRequestDto;
import com.example.simplejdbc.dto.UsuarioResponseDto;
import com.example.simplejdbc.entities.Usuario;
import com.example.simplejdbc.exceptions.UsuarioExisteException;
import com.example.simplejdbc.exceptions.UsuarioNoExisteException;
import com.example.simplejdbc.mappers.UsuarioMapper;
import com.example.simplejdbc.repositories.UsuarioRepository;
import com.example.simplejdbc.services.UsuarioService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioResponseDto> obtenerUsuarios() {
        List<Usuario> list = usuarioRepository.findAll();

        return usuarioMapper.toDtoList(list);
    }

    @Override
    public UsuarioResponseDto obtenerUsuarioPorId(int id) {
        Usuario usuario = usuarioRepository.findById(id);

        if(usuario == null) {
            throw new UsuarioNoExisteException(id);
        }

        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioResponseDto agregarUsuario(UsuarioRequestDto dto) {
        if(usuarioRepository.existsByNombre(dto.getNombre())) {
            throw new UsuarioExisteException(dto.getNombre());
        }

        Usuario usuario = usuarioMapper.toEntity(dto);
        Usuario usuarioNuevo = usuarioRepository.save(usuario);
        
        return usuarioMapper.toDto(usuarioNuevo);
    }

    @Override
    public void actualizarUsuario(int id, UsuarioRequestDto dto) {
        if(!usuarioRepository.existsById(id)) {
            throw new UsuarioNoExisteException(id);
        }

        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setId(id);

        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuarioPorId(int id) {
        if(!usuarioRepository.existsById(id)) {
            throw new UsuarioNoExisteException(id);
        }

        usuarioRepository.deleteById(id);
    }

}
