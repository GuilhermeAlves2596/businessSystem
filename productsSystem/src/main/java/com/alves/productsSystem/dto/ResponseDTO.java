package com.alves.productsSystem.dto;

import com.alves.productsSystem.model.ProdutoModel;
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
    private ProdutoModel produto;
    @JsonProperty(value = "MSG")
    private String resp;

    public ResponseDTO(ProdutoModel produto) {
        this.produto = produto;
    }

    public ResponseDTO(String resp) {
        this.resp = resp;
    }
}
