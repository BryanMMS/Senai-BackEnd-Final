package org.example.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTG_ID")
    private long ctgId;

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres!")
    @Column(name = "CTG_NOME", nullable = false)
    private String ctgNome;


    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres!")
    @Column(name = "CTG_DESCRICAO", length = 200)
    private String ctgDescricao;

    @Column(name = "CTG_DATACADASTRO")
    private LocalDateTime ctgDataCadastro;

    @Column(name = "CTG_DATAATUALIZADO")
    private LocalDateTime ctgDataAtualizado;


    @NotNull(message = "Categoria Ativo é obrigatório!")
    @Column(name = "CTG_ATIVO", nullable = false)
    private Boolean ctgAtivo;

    public Categoria() {
    }

    public Categoria(long ctgId, String ctgNome, String ctgDescricao, LocalDateTime ctgDataCadastro, LocalDateTime ctgDataAtualizado, Boolean ctgAtivo) {
        this.ctgId = ctgId;
        this.ctgNome = ctgNome;
        this.ctgDescricao = ctgDescricao;
        this.ctgDataCadastro = ctgDataCadastro;
        this.ctgDataAtualizado = ctgDataAtualizado;
        this.ctgAtivo = ctgAtivo;
    }

    public long getCtgId() {
        return ctgId;
    }

    public void setCtgId(long ctgId) {
        this.ctgId = ctgId;
    }

    public String getCtgNome() {
        return ctgNome;
    }

    public void setCtgNome(String ctgNome) {
        this.ctgNome = ctgNome;
    }

    public String getCtgDescricao() {
        return ctgDescricao;
    }

    public void setCtgDescricao(String ctgDescricao) {
        this.ctgDescricao = ctgDescricao;
    }

    public LocalDateTime getCtgDataCadastro() {
        return ctgDataCadastro;
    }

    public void setCtgDataCadastro(LocalDateTime ctgDataCadastro) {
        this.ctgDataCadastro = ctgDataCadastro;
    }

    public LocalDateTime getCtgDataAtualizado() {
        return ctgDataAtualizado;
    }

    public void setCtgDataAtualizado(LocalDateTime ctgDataAtualizado) {
        this.ctgDataAtualizado = ctgDataAtualizado;
    }

    public Boolean getCtgAtivo() {
        return ctgAtivo;
    }

    public void setCtgAtivo(Boolean ctgAtivo) {
        this.ctgAtivo = ctgAtivo;
    }
}
