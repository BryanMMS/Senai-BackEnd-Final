package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.entities.Cliente;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;

public class FornecedorDTO {

    private Long forId;
    private String forNomeFantasia;
    @CNPJ(message = "CNPJ inválido!")
    private String forCnpj;
    private String forRazaoSocial;
private Boolean forAtivo;

    private String endRua;
    private String endNumero;
    private String endCidade;
    private String endCep;
    private String endEstado;


    private String conCelular;
    private String conTelefoneComercial;

    @Email(message = "E-mail inválido")
    private String conEmail;

public FornecedorDTO(){

}

    public Long getForId() {
        return forId;
    }

    public void setForId(Long forId) {
        this.forId = forId;
    }

    public String getForNomeFantasia() {
        return forNomeFantasia;
    }

    public void setForNomeFantasia(String forNomeFantasia) {
        this.forNomeFantasia = forNomeFantasia;
    }

    public String getForCnpj() {
        return forCnpj;
    }

    public void setForCnpj(String forCnpj) {
        this.forCnpj = forCnpj;
    }

    public String getForRazaoSocial() {
        return forRazaoSocial;
    }

    public void setForRazaoSocial(String forRazaoSocial) {
        this.forRazaoSocial = forRazaoSocial;
    }

    public Boolean getForAtivo() {
        return forAtivo;
    }

    public void setForAtivo(Boolean forAtivo) {
        this.forAtivo = forAtivo;
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
}
