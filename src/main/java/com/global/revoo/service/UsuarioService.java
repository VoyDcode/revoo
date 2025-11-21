package com.global.revoo.service;

import com.global.revoo.domain.enums.PapelUsuario;
import com.global.revoo.domain.model.Usuario;
import com.global.revoo.domain.repository.UsuarioRepository;
import com.global.revoo.web.dto.usuario.UsuarioCadastroRequest;
import com.global.revoo.web.dto.usuario.UsuarioResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // usado pelo Spring Security (login)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    // usado para cadastro de novos usuários
    public UsuarioResponse cadastrar(UsuarioCadastroRequest dto) {
        String emailNormalizado = dto.getEmail().trim().toLowerCase();

        if (usuarioRepository.existsByEmail(emailNormalizado)) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome().trim());
        usuario.setEmail(emailNormalizado);
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPapel(PapelUsuario.USUARIO);

        Usuario salvo = usuarioRepository.save(usuario);
        return toResponse(salvo);
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPapel().name()
        );
    }
}
