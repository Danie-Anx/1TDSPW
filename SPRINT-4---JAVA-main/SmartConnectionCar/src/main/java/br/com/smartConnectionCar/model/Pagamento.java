package br.com.smartConnectionCar.model;

import java.time.LocalDate;

public class Pagamento {
    private int idPagamento;
    private int valor;
    private LocalDate data;
    private int idCliente;
    private int idAgendamento;

    public Pagamento() {
    }


    public Pagamento(int idPagamento, int valor, LocalDate data, int idCliente, int idAgendamento) {
        this.idPagamento = idPagamento;
        this.valor = valor;
        this.data = data;
        this.idCliente = idCliente;
        this.idAgendamento = idAgendamento;
    }

    // Getters e Setters
    public int getIdPagamento() { return idPagamento; }
    public void setIdPagamento(int idPagamento) { this.idPagamento = idPagamento; }

    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(int idAgendamento) { this.idAgendamento = idAgendamento; }

    // Método toString para exibir as informações do objeto
    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", valor=" + valor +
                ", data=" + data +
                ", idCliente=" + idCliente +
                ", idAgendamento=" + idAgendamento +
                '}';
    }
}
