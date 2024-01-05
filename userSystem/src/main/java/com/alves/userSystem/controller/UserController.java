package com.alves.userSystem.controller;

import com.alves.userSystem.dto.ResponseDTO;
import com.alves.userSystem.dto.UsuarioDTO;
import com.alves.userSystem.model.UsuarioModel;
import com.alves.userSystem.repository.UsuarioRepository;
import com.alves.userSystem.service.SenhaBcrypt;
import com.alves.userSystem.service.ValidaUsuario;
import com.alves.userSystem.utils.ParseUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ParseUsuario parseUsuario;
    @Autowired
    private ValidaUsuario validaUsuario;
    @Autowired
    private SenhaBcrypt bcrypt;

    // Salvar
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> salvarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        boolean validUser = validaUsuario.validaCampos(usuarioDTO);
        if(validUser){
            String senhaBcrypt = bcrypt.encripitaSenha(usuarioDTO.getSenha());
            usuarioDTO.setSenha(senhaBcrypt);
            UsuarioModel user = usuarioRepository.save(parseUsuario.parseUsuario(usuarioDTO));
            return ResponseEntity.ok(new ResponseDTO(user));
        } else {
            return ResponseEntity.ok(new ResponseDTO("Dados invalidos"));
        }
    };

    // Listar todos
    @GetMapping("/list")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios(){
        List<UsuarioModel> usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok(usuarios);
    }

    // Listar por id
    @GetMapping("/list/{id}")
    public ResponseEntity<?> listarUsuarioById(@PathVariable Integer id){
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario com o id: " + id + " não encontrado");

        }
    }

    // Atualizar
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> atualizar(@RequestBody UsuarioDTO usuarioDTO){
        boolean validUser = validaUsuario.validaCampos(usuarioDTO);
        if(validUser){
            Optional<UsuarioModel> usuario = usuarioRepository.findById(usuarioDTO.getId());
            if(usuario.isPresent()){
                UsuarioModel updatedUsuario = getUsuarioModel(usuarioDTO, usuario);

                usuarioRepository.save(updatedUsuario);

                return ResponseEntity.ok(new ResponseDTO(updatedUsuario));
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO("Usuario com o id: " + usuarioDTO.getId() + " não encontrado"));
            }
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("Erro ao atualizar o usuario"));
        }
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deletar(@PathVariable Integer id){
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseDTO("Usuario excluido com sucesso"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("Usuario não encontrado"));
        }
    }

    private UsuarioModel getUsuarioModel(UsuarioDTO usuarioDTO, Optional<UsuarioModel> usuario) {
        UsuarioModel updatedUsuario = usuario.get();

        if(!bcrypt.comparaSenha(updatedUsuario.getSenha(), usuarioDTO.getSenha())){
            updatedUsuario.setSenha(bcrypt.encripitaSenha(usuarioDTO.getSenha()));
        }

        updatedUsuario.setUsuario(usuarioDTO.getUsuario());
        updatedUsuario.setNome(usuarioDTO.getNome());
        updatedUsuario.setIdade(usuarioDTO.getIdade());
        updatedUsuario.setRua(usuarioDTO.getRua());
        updatedUsuario.setCidade(usuarioDTO.getCidade());
        updatedUsuario.setEstado(usuarioDTO.getEstado());
        updatedUsuario.setPais(usuarioDTO.getPais());
        updatedUsuario.setTelefone(usuarioDTO.getTelefone());
        updatedUsuario.setSalario(usuarioDTO.getSalario());
        updatedUsuario.setCargo(usuarioDTO.getCargo());
        updatedUsuario.setAnoContratacao(usuarioDTO.getAnoContratacao());
        updatedUsuario.setAdm(usuarioDTO.isAdm());
        return updatedUsuario;
    }
};
