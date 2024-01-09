package com.alves.productsSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntradaEstoqueDTO {

    private Integer id;
    private String quantidade;
    private String operacao;

}
