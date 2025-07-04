package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CON_ID")
    private Long conId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CON_CLI_ID")
    private Cliente conCliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CON_FOR_ID")
    private Fornecedor conFornecedor;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CON_FUN_ID")
    private Funcionario conFuncionario;

    @Column(name = "CON_CELULAR", length = 20)
    private String conCelular;

    @Column(name = "CON_TELEFONE_COMERCIAL", length = 20)
    private String conTelefoneComercial;

    @Email(message = "E-mail inválido")
    @Column(length = 55, name = "CON_EMAIL")
    private String conEmail;

    public Contato() {
    }

    // Construtor para Cliente
    public Contato(Long id, Cliente cliente, String celular, String telefoneComercial, String email) {
        this.conId = id;
        this.conCliente = cliente;
        this.conCelular = celular;
        this.conTelefoneComercial = telefoneComercial;
        this.conEmail = email;
    }

    // Construtor para Fornecedor
    public Contato(Long id, Fornecedor fornecedor, String celular, String telefoneComercial, String email) {
        this.conId = id;
        this.conFornecedor = fornecedor;
        this.conCelular = celular;
        this.conTelefoneComercial = telefoneComercial;
        this.conEmail = email;
    }


    //Construtor para Funcionario


    public Contato(Long conId, Funcionario funcionario, String conCelular, String conTelefoneComercial, String conEmail) {
        this.conId = conId;
        this.conFuncionario = funcionario;
        this.conCelular = conCelular;
        this.conTelefoneComercial = conTelefoneComercial;
        this.conEmail = conEmail;
    }

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public String getConCelular() {
        return conCelular;
    }

    public void setConCelular(String conCelular) {
        this.conCelular = conCelular;
    }

    public String getConTelefoneComercial() {
        return conTelefoneComercial;
    }

    public void setConTelefoneComercial(String conTelefoneComercial) {
        this.conTelefoneComercial = conTelefoneComercial;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public Cliente getConCliente() {
        return conCliente;
    }

    public void setConCliente(Cliente conCliente) {
        this.conCliente = conCliente;
    }

    public Fornecedor getConFornecedor() {
        return conFornecedor;
    }

    public void setConFornecedor(Fornecedor conFornecedor) {
        this.conFornecedor = conFornecedor;
    }

    public Funcionario getConFuncionario() {
        return conFuncionario;
    }

    public void setConFuncionario(Funcionario conFuncionario) {
        this.conFuncionario = conFuncionario;
    }
}
