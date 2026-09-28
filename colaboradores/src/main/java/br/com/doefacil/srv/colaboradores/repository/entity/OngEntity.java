package br.com.doefacil.srv.colaboradores.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ongs")
public class OngEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private  String nome;
    @Column(name = "cnpj")
    private  String cnpj;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "email")
    private String email;
    @Column(name = "area_atuacao")
    private String area_atuacao;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;

    @Deprecated
    public OngEntity() {
    }

    public OngEntity(Long id, String nome, String cnpj, String telefone, String email, String area_atuacao, EnderecoEntity endereco) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.email = email;
        this.area_atuacao = area_atuacao;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getArea_atuacao() {
        return area_atuacao;
    }

    public EnderecoEntity getEndereco() {
        return endereco;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setArea_atuacao(String area_atuacao) {
        this.area_atuacao = area_atuacao;
    }

    public void setEndereco(EnderecoEntity endereco) {
        this.endereco = endereco;
    }
}