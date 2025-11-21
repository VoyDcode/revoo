package com.global.revoo.web.controller;

import com.global.revoo.config.security.JwtService;
import com.global.revoo.domain.model.Usuario;
import com.global.revoo.service.UsuarioService;
import com.global.revoo.web.dto.usuario.LoginRequest;
import com.global.revoo.web.dto.usuario.TokenResponse;
import com.global.revoo.web.dto.usuario.UsuarioCadastroRequest;
import com.global.revoo.web.dto.usuario.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UsuarioService usuarioService,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(
            @Valid @RequestBody UsuarioCadastroRequest request) {

        UsuarioResponse usuario = usuarioService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @Valid @RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                );

        Authentication authentication = authenticationManager.authenticate(authToken);

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtService.generateToken(usuario);

        return ResponseEntity.ok(new TokenResponse(token));
    }
}
