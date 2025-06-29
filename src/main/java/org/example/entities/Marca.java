package org.example.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAR_ID")
    private long marId;

    @OneToMany(mappedBy = "marca")
    private List<Produto> produtos;
    @NotBlank(message = "Nome é obrigatório!")
    @Size(max =  50, message = "Nome deve ter no máximo 50 caracteres!")
    @Column(name = "MAR_NOME", nullable = false)
    private String marNome;


    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres!")
    @Column(name = "MAR_DESCRICAO", length = 200)
    private String marDescricao;

    @Column(name = "MAR_DATACADASTRO")
    private LocalDateTime marDataCadastro;

    @Column(name = "MAR_DATAATUALIZADO")
    private LocalDateTime marDataAtualizado;

    @NotNull(message = "Marca Ativo é obrigatório!")
    @Column(name = "MAR_ATIVO", nullable = false)
    private Boolean marAtivo;


    public Marca() {
    }

    public Marca(long marId, String marNome, String marDescricao, LocalDateTime marDataCadastro, LocalDateTime marDataAtualizado, Boolean marAtivo) {
        this.marId = marId;
        this.marNome = marNome;
        this.marDescricao = marDescricao;
        this.marDataCadastro = marDataCadastro;
        this.marDataAtualizado = marDataAtualizado;
        this.marAtivo = marAtivo;
    }

    public long getMarId() {
        return marId;
    }

    public void setMarId(long marId) {
        this.marId = marId;
    }

    public String getMarNome() {
        return marNome;
    }

    public void setMarNome(String marNome) {
        this.marNome = marNome;
    }

    public String getMarDescricao() {
        return marDescricao;
    }

    public void setMarDescricao(String marDescricao) {
        this.marDescricao = marDescricao;
    }

    public LocalDateTime getMarDataCadastro() {
        return marDataCadastro;
    }

    public void setMarDataCadastro(LocalDateTime marDataCadastro) {
        this.marDataCadastro = marDataCadastro;
    }

    public LocalDateTime getMarDataAtualizado() {
        return marDataAtualizado;
    }

    public void setMarDataAtualizado(LocalDateTime marDataAtualizado) {
        this.marDataAtualizado = marDataAtualizado;
    }

    public Boolean getMarAtivo() {
        return marAtivo;
    }

    public void setMarAtivo(Boolean marAtivo) {
        this.marAtivo = marAtivo;
    }
}
