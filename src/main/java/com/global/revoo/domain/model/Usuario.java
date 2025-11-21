package com.global.revoo.domain.model;

import com.global.revoo.domain.enums.PapelUsuario;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USUARIO")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOM_USUARIO", nullable = false, length = 100)
    private String nome;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "SENHA_HASH", nullable = false, length = 200)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAPEL", nullable = false, length = 20)
    private PapelUsuario papel = PapelUsuario.USUARIO;

    @Column(name = "DT_NASC")
    private LocalDate dataNascimento;

    @Column(name = "DT_CRIACAO", insertable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DT_ATUALIZACAO", insertable = false, updatable = false)
    private LocalDateTime dataAtualizacao;

    // ====== Getters e setters de domínio ======

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public PapelUsuario getPapel() { return papel; }

    public void setPapel(PapelUsuario papel) { this.papel = papel; }

    public LocalDate getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getDataCriacao() { return dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }

    // ====== UserDetails (Spring Security) ======

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ROLE_ADMIN ou ROLE_USUARIO
        return List.of(new SimpleGrantedAuthority("ROLE_" + papel.name()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        // usamos o e-mail como "username" para login
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
