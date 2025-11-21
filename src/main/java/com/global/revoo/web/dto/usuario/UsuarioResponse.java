package com.global.revoo.web.dto.usuario;

public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String papel;

    public UsuarioResponse(Long id, String nome, String email, String papel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.papel = papel;
    }

    public Long getId() { return id; }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public String getPapel() { return papel; }
}
