package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long proId;

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres!")
    @Column(name = "PRO_NOME",length = 100, nullable = false)
    private String proNome;

    @NotBlank(message = "Preco de Custo é obrigatório!")
    @Column(name = "PRO_PRECO_CUSTO", precision = 10, scale = 2, nullable = false)
    private Double proPrecoCusto;

    @NotBlank(message = "Preco de Venda é obrigatório!")
    @Column(name = "PRO_PRECO_VENDA", precision = 10, scale = 2, nullable = false)
    private Double proPrecoVenda;

    @NotBlank(message = "Quantidade é obrigatório!")
    @Column(name = "PRO_QUANTIDADE", nullable = false)
    private int proQuantidade;

    @NotBlank(message = "Descrição é obrigatório!")
    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres!")
    @Column(name = "PRO_DESCRICAO", length = 200)
    private String proDescricao;

    @NotBlank(message = "Codigo de Barras é obrigatório!")
    @Size(max = 13, message = "Codigo de Barras deve ter no máximo 13 caracteres!")
    @Column(name = "PRO_CODIGOBARRAS",length = 13, nullable = false, unique = true)
    private String proCodigoBarras;

    @NotBlank(message = "Marca é obrigatório!")
    @Size(max = 100, message = "Marca deve ter no máximo 100 caracteres!")
    @Column(name = "PRO_MARCA", length = 100, nullable = false)
    private String proMarca;

    @NotBlank(message = "Produto Ativo é obrigatório!")
    @Column(name = "PRO_ATIVO", nullable = false)
    private Boolean proAtivo;

    @Column(name = "PRO_DATACADASTRO")
    private LocalDateTime proDataCadastro;

    @Column(name = "PRO_DATAATUALIZADO")
    private LocalDateTime proDataAtualizacao;

    @NotBlank(message = "Categoria é obrigatório!")
    @Size(max = 100, message = "Categoria deve ter no máximo 100 caracteres!")
    @Column(name = "PRO_CATEGORIA", nullable = false, length = 100)
    private String proCategoria;

    public Produto() {
    }

    public Produto(Long proId, String proNome, Double proPrecoCusto, Double proPrecoVenda, int proQuantidade, String proDescricao, String proCodigoBarras, String proMarca, Boolean proAtivo, LocalDateTime proDataCadastro, LocalDateTime proDataAtualizacao, String proCategoria) {
        this.proId = proId;
        this.proNome = proNome;
        this.proPrecoCusto = proPrecoCusto;
        this.proPrecoVenda = proPrecoVenda;
        this.proQuantidade = proQuantidade;
        this.proDescricao = proDescricao;
        this.proCodigoBarras = proCodigoBarras;
        this.proMarca = proMarca;
        this.proAtivo = proAtivo;
        this.proDataCadastro = proDataCadastro;
        this.proDataAtualizacao = proDataAtualizacao;
        this.proCategoria = proCategoria;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProNome() {
        return proNome;
    }

    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    public Double getProPrecoCusto() {
        return proPrecoCusto;
    }

    public void setProPrecoCusto(Double proPrecoCusto) {
        this.proPrecoCusto = proPrecoCusto;
    }

    public Double getProPrecoVenda() {
        return proPrecoVenda;
    }

    public void setProPrecoVenda(Double proPrecoVenda) {
        this.proPrecoVenda = proPrecoVenda;
    }

    public int getProQuantidade() {
        return proQuantidade;
    }

    public void setProQuantidade(int proQuantidade) {
        this.proQuantidade = proQuantidade;
    }

    public String getProDescricao() {
        return proDescricao;
    }

    public void setProDescricao(String proDescricao) {
        this.proDescricao = proDescricao;
    }

    public String getProCodigoBarras() {
        return proCodigoBarras;
    }

    public void setProCodigoBarras(String proCodigoBarras) {
        this.proCodigoBarras = proCodigoBarras;
    }

    public String getProMarca() {
        return proMarca;
    }

    public void setProMarca(String proMarca) {
        this.proMarca = proMarca;
    }

    public Boolean getProAtivo() {
        return proAtivo;
    }

    public void setProAtivo(Boolean proAtivo) {
        this.proAtivo = proAtivo;
    }

    public LocalDateTime getProDataCadastro() {
        return proDataCadastro;
    }

    public void setProDataCadastro(LocalDateTime proDataCadastro) {
        this.proDataCadastro = proDataCadastro;
    }

    public LocalDateTime getProDataAtualizacao() {
        return proDataAtualizacao;
    }

    public void setProDataAtualizacao(LocalDateTime proDataAtualizacao) {
        this.proDataAtualizacao = proDataAtualizacao;
    }

    public String getProCategoria() {
        return proCategoria;
    }

    public void setProCategoria(String proCategoria) {
        this.proCategoria = proCategoria;
    }
}