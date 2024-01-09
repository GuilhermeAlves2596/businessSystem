package com.alves.productsSystem.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
