package br.com.doefacil.srv.colaboradores.repository.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "colaboradores")
public class ColaboradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "")
    private String nome;
    @Column(name = "")
    private String cpf;
    @Column(name = "")
    private String email;
    @Column(name = "")
    private String telefone;
    @Column(name = "")
    private String cargo;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ong_id", referencedColumnName = "id")
    private OngEntity ong;

    @Deprecated
    public ColaboradorEntity() {
    }

    public ColaboradorEntity( String nome, String cpf, String email, String telefone, String cargo, OngEntity ong) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cargo = cargo;
        this.ong = ong;
    }

    public void setOng(OngEntity ong) {
        this.ong = ong;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public OngEntity getOng() {
        return ong;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCargo() {
        return cargo;
    }
}
