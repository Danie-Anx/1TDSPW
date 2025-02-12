package br.com.smartConnectionCar.model;

public class Veiculo {
    private int idVeiculo;
    private String modelo;
    private String marca;
    private int ano;
    private int quilometragem;
    private String diagnostico;
    private int idCliente;
    private int idAgendamento;


    public Veiculo() {
    }

    public Veiculo(int idVeiculo, String modelo, String marca, int ano, int quilometragem, String diagnostico, int idCliente, int idAgendamento) {
        this.idVeiculo = idVeiculo;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.diagnostico = diagnostico;
        this.idCliente = idCliente;
        this.idAgendamento = idAgendamento;
    }

    // Getters e Setters
    public int getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(int idVeiculo) { this.idVeiculo = idVeiculo; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public int getQuilometragem() { return quilometragem; }
    public void setQuilometragem(int quilometragem) { this.quilometragem = quilometragem; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(int idAgendamento) { this.idAgendamento = idAgendamento; }

    // Método toString para exibir as informações do objeto
    @Override
    public String toString() {
        return "Veiculo{" +
                "idVeiculo=" + idVeiculo +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", ano=" + ano +
                ", quilometragem=" + quilometragem +
                ", diagnostico='" + diagnostico + '\'' +
                ", idCliente=" + idCliente +
                ", idAgendamento=" + idAgendamento +
                '}';
    }
}
