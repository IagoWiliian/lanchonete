package com.example.patoslanches.controller;

import com.example.patoslanches.model.Sacola;
import com.example.patoslanches.service.SacolaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sacolas")
public class SacolaController {

    @Autowired
    private SacolaService sacolaService;

    // Adicionar item na sacola do usuário
    @PostMapping("/{idUsuario}/adicionar/{idProduto}")
    public ResponseEntity<Sacola> adicionarItem(
            @PathVariable Long idUsuario,
            @PathVariable Long idProduto,
            @RequestParam int quantidade) {

        Sacola sacolaAtualizada = sacolaService.adicionarItem(idUsuario, idProduto, quantidade);
        return ResponseEntity.ok(sacolaAtualizada);
    }

    // Remover item da sacola do usuário
    @DeleteMapping("/{idUsuario}/remover/{idProduto}")
    public ResponseEntity<Sacola> removerItem(
            @PathVariable Long idUsuario,
            @PathVariable Long idProduto) {

        Sacola sacolaAtualizada = sacolaService.removerItem(idUsuario, idProduto);
        return ResponseEntity.ok(sacolaAtualizada);
    }

    // Finalizar sacola (pedido)
    @PostMapping("/{idUsuario}/finalizar")
    public ResponseEntity<Sacola> finalizarSacola(@PathVariable Long idUsuario) {
        Sacola sacolaFinalizada = sacolaService.finalizarSacola(idUsuario);
        return ResponseEntity.ok(sacolaFinalizada);
    }

    // Listar pedidos finalizados do usuário
    @GetMapping("/{idUsuario}/pedidos")
    public ResponseEntity<List<Sacola>> listarPedidos(@PathVariable Long idUsuario) {
        List<Sacola> pedidos = sacolaService.listarPedidos(idUsuario);
        return ResponseEntity.ok(pedidos);
    }
}
