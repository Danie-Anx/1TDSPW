package br.com.fiap.model;

import br.com.fiap.util.database.DbColumn;
import br.com.fiap.util.database.DbTable;

@DbTable("t_gs_usuario")
public class Usuario {
    @DbColumn(primaryKey = true, generatedByDefault = true)
    private Integer idUsuario;
    @DbColumn(notNull = true)
    private String nome;
    @DbColumn(unique = true, notNull = true)
    private String email;
    @DbColumn(notNull = true)
    private String senha;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
