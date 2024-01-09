package com.alves.productsSystem.controller;

import com.alves.productsSystem.dto.ProdutoDTO;
import com.alves.productsSystem.dto.ProdutoEntradaEstoqueDTO;
import com.alves.productsSystem.dto.ResponseDTO;
import com.alves.productsSystem.model.ProdutoModel;
import com.alves.productsSystem.repository.ProdutoRepository;
import com.alves.productsSystem.util.ParseProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/produto")
public class ProdutosController {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ParseProduto parseProduto;

    // Salvar
    @PostMapping("/save")
    public ResponseEntity<?> salvarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoModel produto = produtoRepository.save(parseProduto.produtoDtoToModel(produtoDTO));

        return ResponseEntity.ok(new ResponseDTO(produto));
    }

    // Listar todos
    @GetMapping("/list")
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        List<ProdutoModel> produtos = produtoRepository.findAll();

        return ResponseEntity.ok(produtos);

    }

    // Listar por id
    @GetMapping("/list/{id}")
    public ResponseEntity<?> listarUsuarioById(@PathVariable Integer id) {
        Optional<ProdutoModel> usuario = produtoRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto com o id: " + id + " não encontrado");

        }
    }

    // Atualizar
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> atualizar(@RequestBody ProdutoDTO produtoDTO) {

        Optional<ProdutoModel> produto = produtoRepository.findById(produtoDTO.getId());
        if (produto.isPresent()) {
            ProdutoModel updatedProduto = getUProdutoModel(produtoDTO, produto);

            produtoRepository.save(updatedProduto);

            return ResponseEntity.ok(new ResponseDTO(updatedProduto));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("Produto com o id: " + produtoDTO.getId() + " não encontrado"));
        }
    }

    // Atualizar estoque
    @PutMapping("/update/estoque")
    public ResponseEntity<ResponseDTO> adicionar(@RequestBody ProdutoEntradaEstoqueDTO produtoEstoqueDTO) {
        Optional<ProdutoModel> produto = produtoRepository.findById(produtoEstoqueDTO.getId());
        int quantidade = Integer.parseInt(produtoEstoqueDTO.getQuantidade());
        int op = 0;

        if(quantidade <= 0){
            return ResponseEntity.ok(new ResponseDTO("Quantidade invalida"));
        }

        if (produto.isPresent()) {
            ProdutoModel updatedProduto = produto.get();
            int quantProduto = Integer.parseInt(updatedProduto.getQuantidade());

            if("soma".equalsIgnoreCase(produtoEstoqueDTO.getOperacao())){
                op = quantProduto + quantidade;
                updatedProduto.setQuantidade(String.valueOf(op));
            } else if("subtracao".equalsIgnoreCase(produtoEstoqueDTO.getOperacao())){
                if(quantProduto < quantidade){
                    return ResponseEntity.badRequest().body(new ResponseDTO("Quantidade no estoque inferior a quantidade informada"));
                }
                op = quantProduto - quantidade;
                updatedProduto.setQuantidade(String.valueOf(op));
            }

            produtoRepository.save(updatedProduto);

            return ResponseEntity.ok(new ResponseDTO(updatedProduto));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("Produto com o id: " + produtoEstoqueDTO.getId() + " não encontrado"));
        }
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deletar(@PathVariable Integer id){
        Optional<ProdutoModel> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseDTO("Produto excluido com sucesso"));
        } else {
            return ResponseEntity.badRequest().body(new ResponseDTO("Produto não encontrado"));
        }
    }

    private ProdutoModel getUProdutoModel(ProdutoDTO produtoDTO, Optional<ProdutoModel> produto) {
        ProdutoModel updatedProduto = new ProdutoModel();
        if(produto.isPresent()){
            updatedProduto = produto.get();
        }

        updatedProduto.setTipo(produtoDTO.getTipo());
        updatedProduto.setMarca(produtoDTO.getMarca());
        updatedProduto.setModelo(produtoDTO.getModelo());
        updatedProduto.setSistemaOperacional(produtoDTO.getSistemaOperacional());
        updatedProduto.setCapacidadeArmazenamento(produtoDTO.getCapacidadeArmazenamento());
        updatedProduto.setTamanhoTela(produtoDTO.getTamanhoTela());
        updatedProduto.setProcessador(produtoDTO.getProcessador());
        updatedProduto.setMemoriaRAM(produtoDTO.getMemoriaRAM());
        updatedProduto.setConectividade(produtoDTO.getConectividade());

        return updatedProduto;
    }
}
