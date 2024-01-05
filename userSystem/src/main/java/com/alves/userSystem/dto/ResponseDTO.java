package com.alves.userSystem.dto;

import com.alves.userSystem.model.UsuarioModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    @JsonProperty(value = "usuarioCadastrado")
    private UsuarioModel usuario;
    @JsonProperty(value = "MSG")
    private String resp;

    public ResponseDTO(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public ResponseDTO(String resp) {
        this.resp = resp;
    }
}
