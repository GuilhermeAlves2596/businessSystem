package com.alves.productsSystem.util;

import com.alves.productsSystem.dto.ProdutoDTO;
import com.alves.productsSystem.model.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ParseProduto {

    public ProdutoModel produtoDtoToModel(ProdutoDTO produtoDTO){
      return ProdutoModel.builder().tipo(produtoDTO.getTipo())
              .marca(produtoDTO.getMarca())
              .modelo(produtoDTO.getModelo())
              .sistemaOperacional(produtoDTO.getSistemaOperacional())
              .capacidadeArmazenamento(produtoDTO.getCapacidadeArmazenamento())
              .tamanhoTela(produtoDTO.getTamanhoTela())
              .processador(produtoDTO.getProcessador())
              .memoriaRAM(produtoDTO.getMemoriaRAM())
              .conectividade(produtoDTO.getConectividade())
              .quantidade(produtoDTO.getQuantidade()).build();
    };
}
