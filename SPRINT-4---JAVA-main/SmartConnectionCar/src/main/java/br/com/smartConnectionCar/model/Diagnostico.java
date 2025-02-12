package br.com.smartConnectionCar.model;

public class Diagnostico {
    private int idDiagnostico;
    private int idVeiculo;
    private String problema;
    private String descricao;
    private int idOrcamento;
    private int idFuncionario;
    private int idOficina;

    public Diagnostico() {
    }

    public Diagnostico(int idDiagnostico, int idVeiculo, String problema, String descricao, int idOrcamento, int idFuncionario, int idOficina) {
        this.idDiagnostico = idDiagnostico;
        this.idVeiculo = idVeiculo;
        this.problema = problema;
        this.descricao = descricao;
        this.idOrcamento = idOrcamento;
        this.idFuncionario = idFuncionario;
        this.idOficina = idOficina;
    }

    public int getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(int idDiagnostico) { this.idDiagnostico = idDiagnostico; }

    public int getIdVeiculo() { return idVeiculo; }
    public void setIdVeiculo(int idVeiculo) { this.idVeiculo = idVeiculo; }

    public String getProblema() { return problema; }
    public void setProblema(String problema) { this.problema = problema; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getIdOrcamento() { return idOrcamento; }
    public void setIdOrcamento(int idOrcamento) { this.idOrcamento = idOrcamento; }

    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public int getIdOficina() { return idOficina; }
    public void setIdOficina(int idOficina) { this.idOficina = idOficina; }

    // Método toString para exibir as informações do objeto
    @Override
    public String toString() {
        return "Diagnostico{" +
                "idDiagnostico=" + idDiagnostico +
                ", idVeiculo=" + idVeiculo +
                ", problema='" + problema + '\'' +
                ", descricao='" + descricao + '\'' +
                ", idOrcamento=" + idOrcamento +
                ", idFuncionario=" + idFuncionario +
                ", idOficina=" + idOficina +
                '}';
    }
}
