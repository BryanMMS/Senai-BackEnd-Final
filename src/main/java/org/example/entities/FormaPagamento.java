package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class FormaPagamento  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FPG_ID")
    private Long fpgId;

    @NotBlank(message = "Descrição é obrigatório!")
    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres!")
    @Column(name = "FPG_DESCRICAO", length = 200, nullable = false)
    private String fpgDescricao;

    @NotBlank(message = "Tipo é obrigatório!")
    @Size(max = 50, message = "Tipo da Forma Pagamento deve ter no máximo 50 caracteres!")
    @Column(name = "FPG_TIPO", length = 50, nullable = false)
    private String fpgTipo;



    public FormaPagamento() {
    }

    public FormaPagamento(Long fpgId, String fpgDescricao, String fpgTipo) {
        this.fpgId = fpgId;
        this.fpgDescricao = fpgDescricao;
        this.fpgTipo = fpgTipo;
    }

    public Long getFpgId() {
        return fpgId;
    }

    public void setFpgId(Long fpgId) {
        this.fpgId = fpgId;
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
}