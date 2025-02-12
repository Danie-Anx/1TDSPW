package br.com.smartConnectionCar.model;

public class Endereco {
    private int idEndereco;
    private String cep;
    private String rua;
    private int numero;
    private String pais;
    private String cidade;
    private String bairro;
    private int idCliente;
    private int idAgendamento;
    private int idOficina;

    public Endereco(int idEndereco, String cep, String rua, int numero, String pais, String cidade, String bairro, int idCliente, int idAgendamento, int idOficina) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.pais = pais;
        this.cidade = cidade;
        this.bairro = bairro;
        this.idCliente = idCliente;
        this.idAgendamento = idAgendamento;
        this.idOficina = idOficina;
    }

    public Endereco() {
    }

    public int getIdEndereco() { return idEndereco; }
    public void setIdEndereco(int idEndereco) { this.idEndereco = idEndereco; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdAgendamento() { return idAgendamento; }
    public void setIdAgendamento(int idAgendamento) { this.idAgendamento = idAgendamento; }

    public int getIdOficina() { return idOficina; }
    public void setIdOficina(int idOficina) { this.idOficina = idOficina; }

    // Método toString para exibir as informações do objeto
    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco=" + idEndereco +
                ", cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", numero=" + numero +
                ", pais='" + pais + '\'' +
                ", cidade='" + cidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", idCliente=" + idCliente +
                ", idAgendamento=" + idAgendamento +
                ", idOficina=" + idOficina +
                '}';
    }
}
