package com.example.simplejdbc.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.simplejdbc.controllers.UsuarioController;
import com.example.simplejdbc.dto.ApiMeta;
import com.example.simplejdbc.dto.ApiResponse;

@ControllerAdvice
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        boolean esUsuarioController = returnType.getContainingClass().equals(UsuarioController.class);

        return esUsuarioController;
    }

    @Override
    public Object beforeBodyWrite(Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {

        ApiMeta meta = new ApiMeta();

        return new ApiResponse(meta, body, null);
    }

}
