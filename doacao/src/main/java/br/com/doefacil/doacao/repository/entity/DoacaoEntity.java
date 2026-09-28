package br.com.doefacil.doacao.repository.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "doacoes")
public class DoacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String tipo;
    private String descricao;
    private Float valor;
    private LocalDate data_doacao;
    private String doador;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "colaborador_id", referencedColumnName = "id")
    private ColaboradorEntity colaborador;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ong_id", referencedColumnName = "id")
    private OngsEntity ong;

    public DoacaoEntity(String tipo, String descricao, Float valor, LocalDate data_doacao, String doador, ColaboradorEntity colaborador, OngsEntity ong) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.data_doacao = data_doacao;
        this.doador = doador;
        this.colaborador = colaborador;
        this.ong = ong;
    }

    @Deprecated
    public DoacaoEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Float getValor() {
        return valor;
    }

    public LocalDate getData_doacao() {
        return data_doacao;
    }

    public String getDoador() {
        return doador;
    }

    public ColaboradorEntity getColaborador() {
        return colaborador;
    }

    public OngsEntity getOng() {
        return ong;
    }

}