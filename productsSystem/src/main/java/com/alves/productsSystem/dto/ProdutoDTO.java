package com.alves.productsSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer id;
    private String tipo;
    private String marca;
    private String modelo;
    private String sistemaOperacional;
    private String capacidadeArmazenamento;
    private String tamanhoTela;
    private String processador;
    private String memoriaRAM;
    private String conectividade;
    private String quantidade;

}
