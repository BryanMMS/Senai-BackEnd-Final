package org.example.entities;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FUN_ID")
    private Long funId;

    @OneToMany(mappedBy = "endFuncionario", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "conFuncionario", cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres!")
    @Column(name = "FUN_NOME", length = 100, nullable = false)
    private String funNome;

    @NotBlank(message = "Cpf é obrigatório!")
    @CPF(message = "CPF inválido!")
    @Column(name = "FUN_CPF", length = 14, nullable = false, unique = true)
    private String funCpf;

    @Column(name = "FUN_DATAADMISSAO")
    private LocalDateTime funDataAdmissao;

    public Funcionario() {
    }

    public Funcionario(Long funId, String funNome, String funCpf, LocalDateTime funDataAdmissao) {
        this.funId = funId;
        this.funNome = funNome;
        this.funCpf = funCpf;
        this.funDataAdmissao = funDataAdmissao;
    }

    public Long getFunId() {
        return funId;
    }

    public void setFunId(Long funId) {
        this.funId = funId;
    }

    public String getFunNome() {
        return funNome;
    }

    public void setFunNome(String funNome) {
        this.funNome = funNome;
    }

    public String getFunCpf() {
        return funCpf;
    }

    public void setFunCpf(String funCpf) {
        this.funCpf = funCpf;
    }

    public LocalDateTime getFunDataAdmissao() {
        return funDataAdmissao;
    }

    public void setFunDataAdmissao(LocalDateTime funDataAdmissao) {
        this.funDataAdmissao = funDataAdmissao;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
