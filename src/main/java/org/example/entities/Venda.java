    package org.example.entities;
    import java.util.List;

    import com.fasterxml.jackson.annotation.JsonBackReference;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import org.aspectj.apache.bcel.classfile.NestMembers;

    import javax.persistence.*;
    import javax.validation.constraints.NotNull;
    import javax.validation.constraints.Size;
    import java.time.LocalDateTime;
    import java.util.ArrayList;

    @Entity
    public class Venda {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "VND_ID")
        private Long vndId;


        @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<ItemVenda> itens = new ArrayList<>();

        @ManyToOne(optional = false)
        @JoinColumn(name = "FPG_ID", nullable = false)
        private FormaPagamento formaPagamento;

        @ManyToOne(optional = false)
        @JoinColumn(name = "FUN_ID", nullable = false)
        private Funcionario funcionario;

        @ManyToOne(optional = false)
        @JoinColumn(name = "CLI_ID", nullable = false)
        private Cliente cliente;

    @Column(name = "VND_DATAVENDA")
        private LocalDateTime vndDataVenda;

    @Column(name = "VND_TOTAL", nullable = false)
        private double vndTotal;

    @NotNull(message = "Status da venda é obrigatório!")
    @Column(name = "VND_CONCLUIDA", nullable = false)
    private Boolean vndConcluida;

    @Size(max = 200, message = "Observações deve ter no máximo 200 caracteres!")
        @Column(name = "VND_OBSERVACAO", length = 200)
        private String vndObservacao;


        public Venda() {
        }

        public Long getVndId() {
            return vndId;
        }

        public void setVndId(Long vndId) {
            this.vndId = vndId;
        }

        public List<ItemVenda> getItens() {
            return itens;
        }

        public void setItens(List<ItemVenda> itens) {
            this.itens = itens;
        }

        public FormaPagamento getFormaPagamento() {
            return formaPagamento;
        }

        public void setFormaPagamento(FormaPagamento formaPagamento) {
            this.formaPagamento = formaPagamento;
        }

        public Funcionario getFuncionario() {
            return funcionario;
        }

        public void setFuncionario(Funcionario funcionario) {
            this.funcionario = funcionario;
        }

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public LocalDateTime getVndDataVenda() {
            return vndDataVenda;
        }

        public void setVndDataVenda(LocalDateTime vndDataVenda) {
            this.vndDataVenda = vndDataVenda;
        }

        public double getVndTotal() {
            return vndTotal;
        }

        public void setVndTotal(double vndTotal) {
            this.vndTotal = vndTotal;
        }

        public Boolean getVndConcluida() {
            return vndConcluida;
        }

        public void setVndConcluida(Boolean vndConcluida) {
            this.vndConcluida = vndConcluida;
        }

        public String getVndObservacao() {
            return vndObservacao;
        }

        public void setVndObservacao(String vndObservacao) {
            this.vndObservacao = vndObservacao;
        }
    }
