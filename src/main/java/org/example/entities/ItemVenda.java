package org.example.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IVD_ID")
    private Long ivdId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRO_ID", nullable = false)
    private Produto produto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "VND_ID", nullable = false)
    @JsonBackReference
    private Venda venda;

    @Column(name = "IVD_QUANTIDADE", nullable = false)
    private int ivdQuantidade;

    @Column(name = "IVD_PRECOUNITARIO", nullable = false)
    private double ivdPrecoUnitario;

    @Column(name = "IVD_SUBTOTAL", nullable = false)
    private double ivdSubtotal;


    public ItemVenda() {
    }

    public Long getIvdId() {
        return ivdId;
    }

    public void setIvdId(Long ivdId) {
        this.ivdId = ivdId;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public int getIvdQuantidade() {
        return ivdQuantidade;
    }

    public void setIvdQuantidade(int ivdQuantidade) {
        this.ivdQuantidade = ivdQuantidade;
    }

    public double getIvdPrecoUnitario() {
        return ivdPrecoUnitario;
    }

    public void setIvdPrecoUnitario(double ivdPrecoUnitario) {
        this.ivdPrecoUnitario = ivdPrecoUnitario;
    }

    public double getIvdSubtotal() {
        return ivdSubtotal;
    }

    public void setIvdSubtotal(double ivdSubtotal) {
        this.ivdSubtotal = ivdSubtotal;
    }
}