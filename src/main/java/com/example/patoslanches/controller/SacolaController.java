package com.example.patoslanches.controller;

import com.example.patoslanches.model.Sacola;
import com.example.patoslanches.service.SacolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sacola")
public class SacolaController {

    @Autowired
    private SacolaService sacolaService;

    @PostMapping("/{idUsuario}/adicionar/{idProduto}")
    public Sacola adicionarItem(@PathVariable Long idUsuario,
                                @PathVariable Long idProduto,
                                @RequestParam int quantidade) {
        return sacolaService.adicionarItem(idUsuario, idProduto, quantidade);
    }

    @DeleteMapping("/{idUsuario}/remover/{idProduto}")
    public Sacola removerItem(@PathVariable Long idUsuario,
                              @PathVariable Long idProduto) {
        return sacolaService.removerItem(idUsuario, idProduto);
    }

    @PostMapping("/{idUsuario}/finalizar")
    public Sacola finalizarSacola(@PathVariable Long idUsuario) {
        return sacolaService.finalizarSacola(idUsuario);
    }

    @GetMapping("/{idUsuario}/pedidos")
    public List<Sacola> listarPedidos(@PathVariable Long idUsuario) {
        return sacolaService.listarPedidos(idUsuario);
    }
}