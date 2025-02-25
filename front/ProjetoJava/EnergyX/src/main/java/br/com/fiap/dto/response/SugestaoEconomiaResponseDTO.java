package br.com.fiap.dto.response;

public class SugestaoEconomiaResponseDTO {
    private Integer idSugestaoEconomia;
    private String descricao;

    public Integer getIdSugestaoEconomia() {
        return idSugestaoEconomia;
    }

    public void setIdSugestaoEconomia(Integer idSugestaoEconomia) {
        this.idSugestaoEconomia = idSugestaoEconomia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
