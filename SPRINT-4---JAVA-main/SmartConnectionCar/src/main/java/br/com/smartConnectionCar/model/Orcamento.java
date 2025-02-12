package br.com.smartConnectionCar.model;

import java.time.LocalDate;

public class Orcamento {
    private int idOrcamento;
    private String descricao;
    private int valor;
    private LocalDate tempoEstimado;
    private int idVeiculo;
    private int idCliente;
    private int idAgendamento;
    private int idPagamento;


    public Orcamento() {
    }

    public Orcamento(int idOrcamento, String descricao, int valor, LocalDate tempoEstimado, int idVeiculo, int idCliente, int idAgendamento, int idPagamento) {
        this.idOrcamento = idOrcamento;
        this.descricao = descricao;
        this.valor = valor;
        this.tempoEstimado = tempoEstimado;
        this.idVeiculo = idVeiculo;
        this.idCliente = idCliente;
        this.idAgendamento = idAgendamento;
        this.idPagamento = idPagamento;
    }

    // Getters e Setters
    public int getIdOrcamento() { return idOrcamento; }
    public void setIdOrcamento(int idOrcamento) { this.idOrcamento = idOrcamento; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }

    public LocalDate getTempoEstimado() { return tempoEstimado; }
    public void setTempoEstimado(LocalDate tempoEstimado) { this.tempoEstimado = tempoEstimado; }

    public int getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(int idVeiculo) { this.idVeiculo = idVeiculo; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(int idAgendamento) { this.idAgendamento = idAgendamento; }

    public int getIdPagamento() { return idPagamento; }
    public void setIdPagamento(int idPagamento) { this.idPagamento = idPagamento; }

    // Método toString para exibir as informações do objeto
    @Override
    public String toString() {
        return "Orcamento{" +
                "idOrcamento=" + idOrcamento +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", tempoEstimado=" + tempoEstimado +
                ", idVeiculo=" + idVeiculo +
                ", idCliente=" + idCliente +
                ", idAgendamento=" + idAgendamento +
                ", idPagamento=" + idPagamento +
                '}';
    }
}
