package org.example.dto;

import org.example.entities.CargoFunc;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

public class FuncionarioDTO {

    private Long funId;
    private String funNome;
    @CPF(message = "CPF inválido")
    private String funCpf;
    private LocalDateTime funDataAdmissao;

    private Long carId;


    private String endRua;
    private String endNumero;
    private String endCidade;
    private String endCep;
    private String endEstado;


    private String conCelular;
    private String conTelefoneComercial;
    private String conEmail;


    public FuncionarioDTO() {
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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
