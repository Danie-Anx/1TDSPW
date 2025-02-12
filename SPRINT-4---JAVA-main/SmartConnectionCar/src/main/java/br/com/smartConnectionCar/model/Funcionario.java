package br.com.smartConnectionCar.model;

import java.time.LocalDate;

public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private LocalDate dataContratacao;
    private String setor;
    private String cargo;
    private int salario;
    private int idOrcamento;
    private int idOficina;


    public Funcionario(int idFuncionario, String nome, String telefone, String email, String cpf,
                       LocalDate dataContratacao, String setor, String cargo, int salario,
                       int idOrcamento, int idOficina) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.dataContratacao = dataContratacao;
        this.setor = setor;
        this.cargo = cargo;
        this.salario = salario;
        this.idOrcamento = idOrcamento;
        this.idOficina = idOficina;
    }

    // Construtor padrão (sem parâmetros)
    public Funcionario() {
    }

    // Getters e Setters
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataContratacao() { return dataContratacao; }
    public void setDataContratacao(LocalDate dataContratacao) { this.dataContratacao = dataContratacao; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public int getSalario() { return salario; }
    public void setSalario(int salario) { this.salario = salario; }

    public int getIdOrcamento() { return idOrcamento; }
    public void setIdOrcamento(int idOrcamento) { this.idOrcamento = idOrcamento; }

    public int getIdOficina() { return idOficina; }
    public void setIdOficina(int idOficina) { this.idOficina = idOficina; }

    // Método toString
    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + idFuncionario +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataContratacao=" + dataContratacao +
                ", setor='" + setor + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", idOrcamento=" + idOrcamento +
                ", idOficina=" + idOficina +
                '}';
    }
}
