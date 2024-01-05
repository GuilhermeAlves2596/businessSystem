package com.alves.userSystem.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString(){
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
