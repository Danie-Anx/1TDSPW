package br.com.smartConnectionCar.model;

import java.time.LocalDate;

public class Agendamento {
    private int idAgendamento;
    private LocalDate data;
    private String servico;


    public Agendamento() {
    }

    public Agendamento(int idAgendamento, LocalDate data, String servico) {
        this.idAgendamento = idAgendamento;
        this.data = data;
        this.servico = servico;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "idAgendamento=" + idAgendamento +
                ", data=" + data +
                ", servico='" + servico + '\'' +
                '}';
    }
}
