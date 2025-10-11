package com.example.patoslanches.model;

import jakarta.persistence.*;

@Entity
public class ItemSacola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "sacola_id")
    private Sacola sacola;

    public ItemSacola() {}

    public ItemSacola(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Sacola getSacola() { return sacola; }
    public void setSacola(Sacola sacola) { this.sacola = sacola; }
}
