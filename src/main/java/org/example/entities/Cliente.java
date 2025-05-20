package org.example.entities;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLI_ID")
    private Long cliId;

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres!")
    @Column(name = "CLI_NOME", nullable = false)
    private String cliNome;

    @NotBlank(message = "Cpf é obrigatório!")
    @CPF(message = "CPF inválido!")
    @Column(name = "CLI_CPF", length = 11 , nullable = false, unique = true)
    private String cliCpf;

    @NotBlank(message = "Email é obrigatório!")
    @Size(max = 74, message = "Email deve ter no máximo 64 caracteres!")
    @Column(name = "CLI_EMAIL", length = 74, nullable = false)
    private String cliEmail;

    @NotBlank(message = "Telefone é obrigatório!")
    @Size(max = 14, message = "O Telefone deve ter no máximo 14 caracteres!")
    @Column(name = "CLI_TELEFONE", length = 14 , nullable = false)
    private String cliTelefone;

    @NotBlank(message = "Endereço é obrigatório!")
    @Size(max = 100, message = "O Endereço deve ter no máximo 100 caracteres!")
    @Column(name = "CLI_ENDERECO", length = 100, nullable = false)
    private String cliEndereco;
    public Cliente() {
    }

    public Cliente(Long cliId, String cliNome, String cliCpf, String cliEmail, String cliTelefone, String cliEndereco) {
        this.cliId = cliId;
        this.cliNome = cliNome;
        this.cliCpf = cliCpf;
        this.cliEmail = cliEmail;
        this.cliTelefone = cliTelefone;
        this.cliEndereco = cliEndereco;
    }

    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliCpf() {
        return cliCpf;
    }

    public void setCliCpf(String cliCpf) {
        this.cliCpf = cliCpf;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public String getCliTelefone() {
        return cliTelefone;
    }

    public void setCliTelefone(String cliTelefone) {
        this.cliTelefone = cliTelefone;
    }

    public String getCliEndereco() {
        return cliEndereco;
    }

    public void setCliEndereco(String cliEndereco) {
        this.cliEndereco = cliEndereco;
    }
}
