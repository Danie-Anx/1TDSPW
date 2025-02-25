package br.com.fiap.dto.request;

public class EstimativaConsumoRequestDTO {

    private Integer idTipoDispositivo;
    private Double usoMedio;
    private String frequenciaUsoMedio;
    private Double consumoMedio;

    public Integer getIdTipoDispositivo() {
        return idTipoDispositivo;
    }

    public void setIdTipoDispositivo(Integer idTipoDispositivo) {
        this.idTipoDispositivo = idTipoDispositivo;
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
