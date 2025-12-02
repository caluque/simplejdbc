package com.example.simplejdbc.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.simplejdbc.dto.UsuarioRequestDto;
import com.example.simplejdbc.dto.UsuarioResponseDto;
import com.example.simplejdbc.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDto dto);

    UsuarioResponseDto toDto(Usuario entity);

    List<UsuarioResponseDto> toDtoList(List<Usuario> list);

}
