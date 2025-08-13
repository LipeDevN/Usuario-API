package com.crud.usuario_api.application.dto;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private Integer senha;
}
