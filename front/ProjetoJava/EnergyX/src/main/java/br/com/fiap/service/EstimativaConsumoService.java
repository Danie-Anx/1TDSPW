package br.com.fiap.service;

import br.com.fiap.application.App;
import br.com.fiap.dto.request.EstimativaConsumoRequestDTO;
import br.com.fiap.dto.response.EstimativaConsumoResponseDTO;
import br.com.fiap.dto.response.TipoDispositivoResponseDTO;
import br.com.fiap.model.EstimativaConsumo;
import br.com.fiap.model.TipoDispositivo;
import br.com.fiap.model.Usuario;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class EstimativaConsumoService {

    private EstimativaConsumoResponseDTO buildResponseDtoFrom (EstimativaConsumo estimativaConsumo) {
        EstimativaConsumoResponseDTO dto = new EstimativaConsumoResponseDTO();

        dto.setIdEstimativaConsumo(estimativaConsumo.getIdEstimativaConsumo());
        dto.setUsoMedio(estimativaConsumo.getUsoMedio());
        dto.setFrequenciaUsoMedio(estimativaConsumo.getFrequenciaUsoMedio());
        dto.setConsumoMedio(estimativaConsumo.getConsumoMedio());

        try {
            TipoDispositivoResponseDTO tipoDispositivoResponseDTO = App.tipoDispositivoService.obterTipoDispositivoPorId(
                    estimativaConsumo.getTipoDispositivo().getIdTipoDispositivo()
            );
            dto.setTipoDispositivo(tipoDispositivoResponseDTO);
        }
        catch (Exception exception) {
            // Exibindo erro no log apenas para exceptions desconhecidas
            exception.printStackTrace();
        }

        return dto;
    }

    private EstimativaConsumo buildModelFrom (int idUsuario, EstimativaConsumoRequestDTO dto) {
        EstimativaConsumo estimativaConsumo = new EstimativaConsumo();
        estimativaConsumo.setConsumoMedio(dto.getConsumoMedio());
        estimativaConsumo.setUsoMedio(dto.getUsoMedio());
        estimativaConsumo.setFrequenciaUsoMedio(dto.getFrequenciaUsoMedio());

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        TipoDispositivo tipoDispositivo = new TipoDispositivo();
        tipoDispositivo.setIdTipoDispositivo(dto.getIdTipoDispositivo());

        estimativaConsumo.setUsuario(usuario);
        estimativaConsumo.setTipoDispositivo(tipoDispositivo);

        return estimativaConsumo;
    }

    public List<EstimativaConsumoResponseDTO> obterEstimativasConsumo(int idUsuario) throws Exception {
        if (!App.usuarioService.existeUsuario(idUsuario)) {
            throw new NotFoundException("Usuário não encontrado");
        }

        List<EstimativaConsumo> estimativaConsumoList = App.estimativaConsumoDao.findByIdUsuario(idUsuario);

        List<EstimativaConsumoResponseDTO> dtos = new ArrayList<>();

        for (EstimativaConsumo estimativaConsumo : estimativaConsumoList) {
            dtos.add(buildResponseDtoFrom(estimativaConsumo));
        }

        return dtos;
    }

    public EstimativaConsumoResponseDTO criarEstimativaConsumo(int idUsuario, EstimativaConsumoRequestDTO dtoEntrada) throws Exception {
        if (!App.usuarioService.existeUsuario(idUsuario)) {
            throw new NotFoundException("Usuário não encontrado");
        }

        EstimativaConsumo estimativaConsumo = buildModelFrom(idUsuario,dtoEntrada);

        try {
            App.estimativaConsumoDao.save(estimativaConsumo);
        }
        catch (SQLIntegrityConstraintViolationException exception) {
            throw new BadRequestException(exception.getMessage());
        }

        return buildResponseDtoFrom(estimativaConsumo);
    }

    public EstimativaConsumoResponseDTO obterEstimativaConsumoPorId(int idUsuario, int idEstimativaConsumo) throws Exception {
        if (!App.usuarioService.existeUsuario(idUsuario)) {
            throw new NotFoundException("Usuário não encontrado");
        }

        EstimativaConsumo estimativaConsumo = App.estimativaConsumoDao.findById(idEstimativaConsumo);

        if (estimativaConsumo == null || estimativaConsumo.getUsuario() == null || estimativaConsumo.getUsuario().getIdUsuario() != idUsuario) {
            throw new NotFoundException("EstimativaConsumo não encontrada");
        }

        return buildResponseDtoFrom(estimativaConsumo);
    }

    public EstimativaConsumoResponseDTO atualizarEstimativaConsumoPorId(int idUsuario, int idEstimativaConsumo, EstimativaConsumoRequestDTO dtoEntrada) throws  Exception {
        EstimativaConsumo estimativaConsumo = App.estimativaConsumoDao.findById(idEstimativaConsumo);
        if (estimativaConsumo == null || estimativaConsumo.getUsuario() == null || estimativaConsumo.getUsuario().getIdUsuario() != idUsuario) {
            throw new NotFoundException("EstimativaConsumo não encontrada");
        }

        estimativaConsumo = buildModelFrom(idUsuario, dtoEntrada);
        estimativaConsumo.setIdEstimativaConsumo(idEstimativaConsumo);

        try {
            App.estimativaConsumoDao.update(estimativaConsumo);
        }
        catch (SQLIntegrityConstraintViolationException exception) {
            throw new BadRequestException(exception.getMessage());
        }

        return obterEstimativaConsumoPorId(idUsuario, idEstimativaConsumo);
    }

    public void deletarEstimativaConsumo (int idUsuario, int idEstimativaConsumo) throws Exception {
        EstimativaConsumo estimativaConsumo = App.estimativaConsumoDao.findById(idEstimativaConsumo);
        if (estimativaConsumo == null || estimativaConsumo.getUsuario() == null || estimativaConsumo.getUsuario().getIdUsuario() != idUsuario) {
            throw new NotFoundException("EstimativaConsumo não encontrada");
        }

        try {
            App.estimativaConsumoDao.delete(idEstimativaConsumo);
        }
        catch (SQLIntegrityConstraintViolationException exception) {
            throw new BadRequestException(exception.getMessage());
        }

    }
}
