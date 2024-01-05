package com.alves.userSystem.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class SenhaBcrypt {

    public String encripitaSenha(String senha){
        return BCrypt.withDefaults().hashToString(15, senha.toCharArray());
    }

    public boolean comparaSenha(String senhaAtual, String senhaAtualizada){
        BCrypt.Result result = BCrypt.verifyer().verify(senhaAtualizada.toCharArray(), senhaAtual);

        return result.verified;
    }
}
