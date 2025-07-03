package org.example.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class CargoFunc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAR_ID")
    private long carId;

    @OneToMany(mappedBy = "cargoFunc")
    private List<Funcionario> funcionarios;

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 50, message = "Nome deve ter no máximo 50 caracteres!")
    @Column(name = "CAR_NOME", nullable = false)
    private String carNome;

    @Size(max = 200, message = "Descrição deve ter no máximo 200 caracteres!")
    @Column(name = "CAR_DESCRICAO", nullable = false)
    private String carDescricao;

    @Column(name = "CAR_DATACADASTRO")
    private LocalDateTime carDataCadastro;

    @Column(name = "DATAATUALIZADO")
    private LocalDateTime carDataAtualizado;


    @NotNull(message = "Cargo Ativo é obrigatório!")
    @Column(name = "CAR_ATIVO", nullable = false)
    private Boolean carAtivo;

    public CargoFunc() {
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getCarNome() {
        return carNome;
    }

    public void setCarNome(String carNome) {
        this.carNome = carNome;
    }

    public String getCarDescricao() {
        return carDescricao;
    }

    public void setCarDescricao(String carDescricao) {
        this.carDescricao = carDescricao;
    }

    public LocalDateTime getCarDataCadastro() {
        return carDataCadastro;
    }

    public void setCarDataCadastro(LocalDateTime carDataCadastro) {
        this.carDataCadastro = carDataCadastro;
    }

    public LocalDateTime getCarDataAtualizado() {
        return carDataAtualizado;
    }

    public void setCarDataAtualizado(LocalDateTime carDataAtualizado) {
        this.carDataAtualizado = carDataAtualizado;
    }

    public Boolean getCarAtivo() {
        return carAtivo;
    }

    public void setCarAtivo(Boolean carAtivo) {
        this.carAtivo = carAtivo;
    }



}
