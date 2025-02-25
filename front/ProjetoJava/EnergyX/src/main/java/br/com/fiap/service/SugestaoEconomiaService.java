package br.com.fiap.service;

import br.com.fiap.application.App;
import br.com.fiap.dto.request.EstimativaConsumoRequestDTO;
import br.com.fiap.dto.response.EstimativaConsumoResponseDTO;
import br.com.fiap.dto.response.SugestaoEconomiaResponseDTO;
import br.com.fiap.model.EstimativaConsumo;
import br.com.fiap.model.SugestaoEconomia;
import br.com.fiap.model.Usuario;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SugestaoEconomiaService {

    private SugestaoEconomiaResponseDTO buildResponseDtoFrom (SugestaoEconomia sugestaoEconomia) {
        SugestaoEconomiaResponseDTO dto = new SugestaoEconomiaResponseDTO();

        dto.setIdSugestaoEconomia(sugestaoEconomia.getIdSugestaoEconomia());
        dto.setDescricao(sugestaoEconomia.getDescricao());

        return dto;
    }
    public List<SugestaoEconomiaResponseDTO> obterSugestoesEconomia(int idUsuario) throws Exception {
        if (!App.usuarioService.existeUsuario(idUsuario)) {
            throw new NotFoundException("Usuário não encontrado");
        }

        List<SugestaoEconomia> sugestaoEconomiaList = App.sugestaoEconomiaDao.findByIdUsuario(idUsuario);

        List<SugestaoEconomiaResponseDTO> dtos = new ArrayList<>();

        for (SugestaoEconomia sugestaoEconomia : sugestaoEconomiaList) {
            dtos.add(buildResponseDtoFrom(sugestaoEconomia));
        }

        return dtos;
    }

    public void processarSugestoesEconomia(int idUsuario) throws Exception {
        if (!App.usuarioService.existeUsuario(idUsuario)) {
            throw new NotFoundException("Usuário não encontrado");
        }

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);

        SugestaoEconomia sugestaoEconomia1 = new SugestaoEconomia();
        sugestaoEconomia1.setDescricao("Desligue os aparelhos eletrônicos quando não estiverem em uso");
        sugestaoEconomia1.setUsuario(usuario);

        App.sugestaoEconomiaDao.save(sugestaoEconomia1);

        SugestaoEconomia sugestaoEconomia2 = new SugestaoEconomia();
        sugestaoEconomia2.setDescricao("Utilize lâmpadas de LED");
        sugestaoEconomia2.setUsuario(usuario);

        App.sugestaoEconomiaDao.save(sugestaoEconomia2);
    }
}
