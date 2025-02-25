package br.com.fiap.dto.response;

public class EstimativaConsumoResponseDTO {

    private Integer idEstimativaConsumo;
    private TipoDispositivoResponseDTO tipoDispositivo;
    private Double usoMedio;
    private String frequenciaUsoMedio;
    private Double consumoMedio;

    public Integer getIdEstimativaConsumo() {
        return idEstimativaConsumo;
    }

    public void setIdEstimativaConsumo(Integer idEstimativaConsumo) {
        this.idEstimativaConsumo = idEstimativaConsumo;
    }

    public TipoDispositivoResponseDTO getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(TipoDispositivoResponseDTO tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public Double getUsoMedio() {
        return usoMedio;
    }

    public void setUsoMedio(Double usoMedio) {
        this.usoMedio = usoMedio;
    }

    public String getFrequenciaUsoMedio() {
        return frequenciaUsoMedio;
    }

    public void setFrequenciaUsoMedio(String frequenciaUsoMedio) {
        this.frequenciaUsoMedio = frequenciaUsoMedio;
    }

    public Double getConsumoMedio() {
        return consumoMedio;
    }

    public void setConsumoMedio(Double consumoMedio) {
        this.consumoMedio = consumoMedio;
    }
}
