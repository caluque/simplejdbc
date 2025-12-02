package com.example.simplejdbc.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class ApiMeta {

    private String requestId = UUID.randomUUID().toString();
    private String timestamp = LocalDateTime.now().toString();

}
