package com.example.simplejdbc.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestDto {

    @NotNull(message = "El campo nombre no debe ser nulo")
    @NotBlank(message = "El campo nombre no debe estar vacio")
    @Size(max = 50, message = "El campo nombre no debe tener mas de 50 caracteres")
    private String nombre;

    @Min(value = 18, message = "El campo debe de ser igual o mayor a 18")
    @Max(value = 75, message = "El campo debe de ser igual o menor a 75")
    private int edad;

}
