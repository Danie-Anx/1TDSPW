package br.com.smartConnectionCar.model;

public class Oficina {

    private int idOficina;
    private String nome;
    private String telefone;
    private String email;


    public Oficina() {
    }

    public Oficina(int idOficina, String nome, String telefone, String email) {
        this.idOficina = idOficina;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getIdOficina() { return idOficina; }
    public void setIdOficina(int idOficina) { this.idOficina = idOficina; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Oficina{" +
                "idOficina=" + idOficina +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
