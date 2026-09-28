package br.com.doefacil.srv.ongs.repository.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "ongs")
public class OngsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String nome;
    @Column(name = "area_atuacao")
    private String area_atuacao;
    @Column
    private String cnpj;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;
    @Column(name = "senha")
    private String senha;
    @Column
    private String email;
    @Column
    private String telefone;
    @Column(name="responsavel")
    private String reponsavel;
    @Column
    private String cpf;
    @Column(name = "termos_versao", nullable = false)
    private String termosVersao;
    @Column(name = "privacidade_versao", nullable = false)
    private String privacidadeVersao;
    @Column(name = "consentimento_em", nullable = false)
    private Instant consentimentoEm;

    public OngsEntity(String nome, String cnpj, String email, String telefone, String area_atuacao, String senha,
                      EnderecoEntity endereco, String responsavel, String cpf, String termosVersao,
                      String privacidadeVersao, Instant consentimentoEm) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
        this.area_atuacao = area_atuacao;
        this.senha = senha;
        this.endereco = endereco;
        this.reponsavel = responsavel;
        this.cpf = cpf;
        this.termosVersao = termosVersao;
        this.privacidadeVersao = privacidadeVersao;
        this.consentimentoEm = consentimentoEm;
    }

    @Deprecated
    public OngsEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getcnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getAreaAtuacao() {
        return area_atuacao;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getReponsavel() {
        return reponsavel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
