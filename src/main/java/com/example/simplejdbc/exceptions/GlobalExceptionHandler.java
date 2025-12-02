package com.example.simplejdbc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import com.example.simplejdbc.dto.ApiError;
import com.example.simplejdbc.dto.ApiMeta;
import com.example.simplejdbc.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNoExisteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleUsuarioNoExisteException(UsuarioNoExisteException ex) {
        ApiMeta meta = new ApiMeta();
        ApiError error = new ApiError(ex.getMessage());

        return new ApiResponse(meta, null, error);
    }

    @ExceptionHandler(UsuarioExisteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleUsuarioExisteException(UsuarioExisteException ex) {
        ApiMeta meta = new ApiMeta();
        ApiError error = new ApiError(ex.getMessage());

        return new ApiResponse(meta, null, error);
    }

    // Controla los valores incorrectos en path params y query params
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ApiMeta meta = new ApiMeta();
        ApiError error = new ApiError(ex.getMessage());

        return new ApiResponse(meta, null, error);
    }

    // Controla los json malformados en las peticiones de entrada
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ApiMeta meta = new ApiMeta();
        ApiError error = new ApiError(ex.getMessage());

        return new ApiResponse(meta, null, error);
    }

    // Controla las validaciones en las propiedades del dto
    // Como @NotNull, @NotBlank, @Size, @Min, @Max, etc
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String detail = ex.getFieldErrors().stream()
            .map(s -> s.getDefaultMessage())
            .findFirst()
            .orElse("");

        ApiMeta meta = new ApiMeta();
        ApiError error = new ApiError(detail);

        return new ApiResponse(meta, null, error);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Si ReponseStatus tiene otro codigo http, agrega el nuevo
    public ApiResponse handleResponseStatusException(ResponseStatusException ex) {
        ApiMeta meta = new ApiMeta();
        ApiError error = new ApiError(ex.getReason());

        return new ApiResponse(meta, null, error);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleException(Exception ex) {
        ApiMeta meta = new ApiMeta();
        ApiError error = new ApiError(ex.getMessage());

        return new ApiResponse(meta, null, error);
    }

}
