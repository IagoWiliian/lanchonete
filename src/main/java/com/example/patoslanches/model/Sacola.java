package com.example.patoslanches.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sacola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "sacola", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemSacola> itens = new ArrayList<>();

    @ManyToOne
    private Usuario cliente;

    private boolean finalizada = false;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<ItemSacola> getItens() { return itens; }
    public void setItens(List<ItemSacola> itens) { this.itens = itens; }

    public Usuario getCliente() { return cliente; }
    public void setCliente(Usuario cliente) { this.cliente = cliente; }

    public boolean isFinalizada() { return finalizada; }
    public void setFinalizada(boolean finalizada) { this.finalizada = finalizada; }

    public Double getTotal() {
        return itens.stream()
                .map(item -> item.getProduto().getPreco()
                        .multiply(BigDecimal.valueOf(item.getQuantidade())))
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
    }

    public void removerItem(Long idProduto) {
        itens.removeIf(item -> item.getProduto().getId().equals(idProduto));
    }
}
