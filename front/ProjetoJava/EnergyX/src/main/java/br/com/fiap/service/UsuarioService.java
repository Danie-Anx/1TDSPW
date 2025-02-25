package br.com.fiap.service;

import br.com.fiap.application.App;
import br.com.fiap.dto.request.UsuarioLoginRequestDTO;
import br.com.fiap.dto.request.UsuarioRequestDTO;
import br.com.fiap.dto.response.UsuarioResponseDTO;
import br.com.fiap.model.Usuario;
import br.com.fiap.util.api.UnauthorizedException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

public class UsuarioService {

    private UsuarioResponseDTO buildResponseDtoFrom (Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());

        return dto;
    }

    private Usuario buildModelFrom (UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        return usuario;
    }

    public UsuarioResponseDTO login (UsuarioLoginRequestDTO dtoEntrada) throws Exception {
        Usuario usuario;

        try {
            usuario = App.usuarioDao.findByEmailAndPassword(dtoEntrada.getEmail(), dtoEntrada.getSenha());
        }
        catch (Exception exception) {
            throw new UnauthorizedException("Credenciais inválidas");
        }

        if (usuario == null) {
            throw new UnauthorizedException("Credenciais inválidas");
        }

        return buildResponseDtoFrom(usuario);
    }

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dtoEntrada) throws Exception {
        Usuario usuario = buildModelFrom(dtoEntrada);

        try {
            App.usuarioDao.save(usuario);
        }
        catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }

        return buildResponseDtoFrom(usuario);
    }

    public UsuarioResponseDTO obterUsuarioPorId(int idUsuario) throws Exception {
        Usuario usuario = App.usuarioDao.findById(idUsuario);

        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        return buildResponseDtoFrom(usuario);
    }

    public boolean existeUsuario(int idUsuario) {
        try {
            Usuario usuario = App.usuarioDao.findById(idUsuario);
            return usuario != null;
        }
        catch (Exception exception) {
            return false;
        }
    }

    public UsuarioResponseDTO atualizarUsuarioPorId(int idUsuario, UsuarioRequestDTO dtoEntrada) throws Exception {
        Usuario usuario = buildModelFrom(dtoEntrada);
        usuario.setIdUsuario(idUsuario);
        try {
            App.usuarioDao.update(usuario);
        }
        catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }

        return obterUsuarioPorId(idUsuario);
    }

}
