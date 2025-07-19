package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class FormaPagamento  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_ID")
    private Long fpgId;

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 200, message = "Nome deve ter no máximo 100 caracteres!")
    @Column(name = "FPG_NOME", length = 100, nullable = false)
    private String fpgNome;

    @NotBlank(message = "Descrição é obrigatório!")
    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres!")
    @Column(name = "FPG_DESCRICAO", length = 200, nullable = false)
    private String fpgDescricao;

    @NotBlank(message = "Tipo é obrigatório!")
    @Size(max = 50, message = "Tipo da Forma Pagamento deve ter no máximo 50 caracteres!")
    @Column(name = "FPG_TIPO", length = 50, nullable = false)
    private String fpgTipo;

    @NotBlank(message= "Permite Parcelamento é obrigatório")
    @Column(name = "FPG_PERMITEPARCELAMENTO", nullable = false)
    private Boolean fpgPermiteParcelamento;

    @NotNull(message = "Numero Maximo de Parcelas é obrigatório")
    @Column(name = "FPG_NUMMAXPARCELAS", nullable = false)
    private Integer fpgNumMaxParcelas;

    @NotNull(message = "Taxa adicional é obrigatório")
    @Column(name = "FPG_TAXAADICIONAL", nullable = false)
    private BigDecimal fpgTaxaAdicional;


    public FormaPagamento() {
    }

    public FormaPagamento(Long fpgId, String fpgNome, String fpgDescricao, String fpgTipo, Boolean fpgPermiteParcelamento, Integer fpgNumMaxParcelas, BigDecimal fpgTaxaAdicional) {
        this.fpgId = fpgId;
        this.fpgNome = fpgNome;
        this.fpgDescricao = fpgDescricao;
        this.fpgTipo = fpgTipo;
        this.fpgPermiteParcelamento = fpgPermiteParcelamento;
        this.fpgNumMaxParcelas = fpgNumMaxParcelas;
        this.fpgTaxaAdicional = fpgTaxaAdicional;
    }

    public Long getFpgId() {
        return fpgId;
    }

    public void setFpgId(Long fpgId) {
        this.fpgId = fpgId;
    }

    public String getFpgNome() {
        return fpgNome;
    }

    public void setFpgNome(String fpgNome) {
        this.fpgNome = fpgNome;
    }

    public String getFpgDescricao() {
        return fpgDescricao;
    }

    public void setFpgDescricao(String fpgDescricao) {
        this.fpgDescricao = fpgDescricao;
    }

    public String getFpgTipo() {
        return fpgTipo;
    }

    public void setFpgTipo(String fpgTipo) {
        this.fpgTipo = fpgTipo;
    }

    public Boolean getFpgPermiteParcelamento() {
        return fpgPermiteParcelamento;
    }

    public void setFpgPermiteParcelamento(Boolean fpgPermiteParcelamento) {
        this.fpgPermiteParcelamento = fpgPermiteParcelamento;
    }

    public Integer getFpgNumMaxParcelas() {
        return fpgNumMaxParcelas;
    }

    public void setFpgNumMaxParcelas(Integer fpgNumMaxParcelas) {
        this.fpgNumMaxParcelas = fpgNumMaxParcelas;
    }

    public BigDecimal getFpgTaxaAdicional() {
        return fpgTaxaAdicional;
    }

    public void setFpgTaxaAdicional(BigDecimal fpgTaxaAdicional) {
        this.fpgTaxaAdicional = fpgTaxaAdicional;
    }
}