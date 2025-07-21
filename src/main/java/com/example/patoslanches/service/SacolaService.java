package com.example.patoslanches.service;

import com.example.patoslanches.model.*;
import com.example.patoslanches.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SacolaService {

    @Autowired
    private SacolaRepository sacolaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Sacola adicionarItem(Long idUsuario, Long idProduto, int quantidade) {
        Usuario cliente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Sacola sacola = sacolaRepository.findByClienteAndFinalizadaFalse(cliente)
                .orElse(new Sacola());

        sacola.setCliente(cliente);
        sacola.getItens().add(new ItemSacola(produto, quantidade));

        return sacolaRepository.save(sacola);
    }

    public Sacola removerItem(Long idUsuario, Long idProduto) {
        Usuario cliente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Sacola sacola = sacolaRepository.findByClienteAndFinalizadaFalse(cliente)
                .orElseThrow(() -> new RuntimeException("Sacola não encontrada"));

        sacola.removerItem(idProduto);
        return sacolaRepository.save(sacola);
    }

    public Sacola finalizarSacola(Long idUsuario) {
        Usuario cliente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Sacola sacola = sacolaRepository.findByClienteAndFinalizadaFalse(cliente)
                .orElseThrow(() -> new RuntimeException("Sacola não encontrada"));

        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Não é possível finalizar uma sacola vazia.");
        }

        sacola.setFinalizada(true);
        return sacolaRepository.save(sacola);
    }

    public List<Sacola> listarPedidos(Long idUsuario) {
        Usuario cliente = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return sacolaRepository.findAll().stream()
                .filter(s -> s.getCliente().equals(cliente) && s.isFinalizada())
                .toList();
    }
}