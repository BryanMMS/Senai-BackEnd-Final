package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "END_ID")
    private Long endId;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "END_CLI_ID")
    private Cliente endCliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "END_FOR_ID")
    private Fornecedor endFornecedor;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "END_FUN_ID")
    private Funcionario endFuncionario;

    @Column(name = "END_RUA", length = 50)
    private String endRua;

    @Column(name = "END_NUMERO", length = 15)
    private String endNumero;

    @Column(name = "END_CIDADE", length = 100)
    private String endCidade;

    @Column(name = "END_CEP", length = 8)
    private String endCep;

    @Column(name = "END_ESTADO", length = 100)
    private String endEstado;

    public Endereco() {
    }

    public Endereco(Long id, Cliente cliente, String rua, String numero, String cidade, String cep, String estado) {
        this.endId = id;
        this.endCliente = cliente;
        this.endRua = rua;
        this.endNumero = numero;
        this.endCidade = cidade;
        this.endCep = cep;
        this.endEstado = estado;
    }

    // Construtor para Fornecedor
    public Endereco(Long id, Fornecedor fornecedor, String rua, String numero, String cidade, String cep, String estado) {
        this.endId = id;
        this.endFornecedor = fornecedor;
        this.endRua = rua;
        this.endNumero = numero;
        this.endCidade = cidade;
        this.endCep = cep;
        this.endEstado = estado;
    }


    //Construtor para Funcionario
    public Endereco(Long endId, Funcionario funcionario, String endRua, String endNumero, String endCidade, String endCep, String endEstado) {
        this.endId = endId;
        this.endFuncionario = funcionario;
        this.endRua = endRua;
        this.endNumero = endNumero;
        this.endCidade = endCidade;
        this.endCep = endCep;
        this.endEstado = endEstado;
    }

    public Long getEndId() {
        return endId;
    }

    public void setEndId(Long endId) {
        this.endId = endId;
    }

    public String getEndRua() {
        return endRua;
    }

    public void setEndRua(String endRua) {
        this.endRua = endRua;
    }

    public String getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndCidade() {
        return endCidade;
    }

    public void setEndCidade(String endCidade) {
        this.endCidade = endCidade;
    }

    public String getEndCep() {
        return endCep;
    }

    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public String getEndEstado() {
        return endEstado;
    }

    public void setEndEstado(String endEstado) {
        this.endEstado = endEstado;
    }

    public Cliente getEndCliente() {
        return endCliente;
    }

    public void setEndCliente(Cliente endCliente) {
        this.endCliente = endCliente;
    }

    public Fornecedor getEndFornecedor() {
        return endFornecedor;
    }

    public void setEndFornecedor(Fornecedor endFornecedor) {
        this.endFornecedor = endFornecedor;
    }


    public Funcionario getEndFuncionario() {
        return endFuncionario;
    }

    public void setEndFuncionario(Funcionario endFuncionario) {
        this.endFuncionario = endFuncionario;
    }
}