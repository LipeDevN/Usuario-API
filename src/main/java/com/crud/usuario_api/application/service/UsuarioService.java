package com.crud.usuario_api.application.service;

import com.crud.usuario_api.application.dto.UsuarioRequestDTO;
import com.crud.usuario_api.application.dto.UsuarioResponseDTO;
import com.crud.usuario_api.shared.entity.Usuario;
import com.crud.usuario_api.shared.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO user){
        Usuario usuario = new Usuario();
        usuario.setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        usuario.setSenha(user.getSenha());
        Usuario savedUser = usuarioRepository.save(usuario);
        return toResponseDTO(savedUser);
    }

    public UsuarioResponseDTO findUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
        return toResponseDTO(usuario);
    }

    public List<UsuarioResponseDTO> findAll(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO requestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        usuario.setNome(requestDTO.getNome());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setSenha(requestDTO.getSenha());

        usuarioRepository.save(usuario);

        return toResponseDTO(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario){
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setEmail(usuario.getEmail());
        dto.setNome(usuario.getNome());

        return dto;
    }
}
