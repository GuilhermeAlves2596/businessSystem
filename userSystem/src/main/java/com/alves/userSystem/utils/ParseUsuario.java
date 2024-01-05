package com.alves.userSystem.utils;

import com.alves.userSystem.dto.UsuarioDTO;
import com.alves.userSystem.model.UsuarioModel;
import org.springframework.stereotype.Component;

@Component
public class ParseUsuario {
    public UsuarioModel parseUsuario(UsuarioDTO usuarioDTO){
        return UsuarioModel.builder().usuario(usuarioDTO.getUsuario())
                .senha(usuarioDTO.getSenha())
                .nome(usuarioDTO.getNome())
                .idade(usuarioDTO.getIdade())
                .rua(usuarioDTO.getRua())
                .cidade(usuarioDTO.getCidade())
                .estado(usuarioDTO.getEstado())
                .pais(usuarioDTO.getPais())
                .telefone(usuarioDTO.getTelefone())
                .salario(usuarioDTO.getSalario())
                .cargo(usuarioDTO.getCargo())
                .anoContratacao(usuarioDTO.getAnoContratacao())
                .adm(usuarioDTO.isAdm()).build();
    }
}
