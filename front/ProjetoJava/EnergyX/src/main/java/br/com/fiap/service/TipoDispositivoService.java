package br.com.fiap.service;

import br.com.fiap.application.App;
import br.com.fiap.dto.response.EstimativaConsumoResponseDTO;
import br.com.fiap.dto.response.TipoDispositivoResponseDTO;
import br.com.fiap.model.TipoDispositivo;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TipoDispositivoService {

    private TipoDispositivoResponseDTO buildResponseDtoFrom (TipoDispositivo tipoDispositivo) {
        TipoDispositivoResponseDTO dto = new TipoDispositivoResponseDTO();

        dto.setIdTipoDispositivo(tipoDispositivo.getIdTipoDispositivo());
        dto.setNome(tipoDispositivo.getNome());
        dto.setConsumoMedio(tipoDispositivo.getConsumoMedio());

        return dto;
    }

    public List<TipoDispositivoResponseDTO> obterTiposDispositivo() throws Exception{
        List<TipoDispositivo> tiposDispositivo = App.tipoDispositivoDao.findAll();

        List<TipoDispositivoResponseDTO> dtos = new ArrayList<>();

        for (TipoDispositivo tipoDispositivo : tiposDispositivo) {
            dtos.add(buildResponseDtoFrom(tipoDispositivo));
        }

        return dtos;
    }

    public TipoDispositivoResponseDTO obterTipoDispositivoPorId(int idTipoDispositivo) throws Exception {
        TipoDispositivo tipoDispositivo = App.tipoDispositivoDao.findById(idTipoDispositivo);

        if (tipoDispositivo == null) {
            throw new NotFoundException("TipoDispositivo não encontrado");
        }

        return buildResponseDtoFrom(tipoDispositivo);
    }

    public boolean existeTipoDispositivo(int idTipoDispositivo) {
        try {
            TipoDispositivo tipoDispositivo = App.tipoDispositivoDao.findById(idTipoDispositivo);
            return tipoDispositivo != null;
        }
        catch (Exception exception) {
            return false;
        }
    }
}
