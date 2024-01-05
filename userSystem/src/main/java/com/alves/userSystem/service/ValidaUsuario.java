package com.alves.userSystem.service;

import com.alves.userSystem.dto.UsuarioDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidaUsuario {

    public boolean validaCampos(UsuarioDTO usuarioDTO){
        if(validaNome(usuarioDTO.getNome())){
            if(validaUsuario(usuarioDTO.getUsuario())){
                if(validaIdade(usuarioDTO.getIdade())){
                    if(validaRua(usuarioDTO.getRua())){
                        if(validaCidade(usuarioDTO.getCidade())){
                            if(validaEstado(usuarioDTO.getEstado())){
                                if(validaPais(usuarioDTO.getPais())){
                                    if(validaTelefone(usuarioDTO.getTelefone())){
                                        if(validaSalario(usuarioDTO.getSalario())){
                                            if(validaAno(usuarioDTO.getAnoContratacao())){
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean validaNome(String nome){
        return !nome.matches(".*[/$%&*?].*");
    }

    public boolean validaUsuario(String usuario){
        return !usuario.matches(".*[/$%&*?].*");
    }

    public boolean validaIdade(String idade){
        if(idade.matches("\\d+")){
            return Integer.parseInt(idade) > 0 && Integer.parseInt(idade) < 150;
        }
        return false;
    }

    public boolean validaRua(String rua){
        return !rua.matches(".*[/$%&*?].*");
    }
    public boolean validaCidade(String cidade){
        return !cidade.matches(".*[/$%&*?].*");
    }

    public boolean validaEstado(String estado){
        return !estado.matches(".*[/$%&*?].*");
    }

    public boolean validaPais(String pais){
        return !pais.matches(".*[/$%&*?].*");
    }

    public boolean validaTelefone(String telefone){
        String fone = telefone.replaceAll(".*[/$%&*()?-].*", "");

        return fone.matches("\\d+");
    }

    public boolean validaSalario(String salario){
        String sal = salario.replaceAll("[^0-9]", "");
        return sal.matches("\\d+");
    }

    public boolean validaAno(String ano){
        LocalDate year = LocalDate.now();
        if(ano.matches("\\d+")){
            return Integer.parseInt(ano) > 2000 && Integer.parseInt(ano) < year.getYear();
        }
        return false;
    }
}
