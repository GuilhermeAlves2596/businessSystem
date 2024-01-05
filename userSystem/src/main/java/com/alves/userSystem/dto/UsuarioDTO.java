package com.alves.userSystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Integer id;
    private String usuario;
    private String senha;
    private String nome;
    private String idade;
    private String rua;
    private String cidade;
    private String estado;
    private String pais;
    private String telefone;
    private String salario;
    private String cargo;
    private String anoContratacao;
    private boolean adm;

}
