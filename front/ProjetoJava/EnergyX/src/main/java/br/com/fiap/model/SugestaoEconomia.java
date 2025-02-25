package br.com.fiap.model;

import br.com.fiap.util.database.DbColumn;
import br.com.fiap.util.database.DbTable;

@DbTable("t_gs_sugestao_economia")
public class SugestaoEconomia {
    @DbColumn(primaryKey = true, generatedByDefault = true)
    private Integer idSugestaoEconomia;
    @DbColumn(foreignKey = "t_gs_usuario", notNull = true)
    private Usuario usuario;
    @DbColumn(type = "VARCHAR(1000)", notNull = true)
    private String descricao;

    public Integer getIdSugestaoEconomia() {
        return idSugestaoEconomia;
    }

    public void setIdSugestaoEconomia(Integer idSugestaoEconomia) {
        this.idSugestaoEconomia = idSugestaoEconomia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "SugestaoEconomia{" +
                "idSugestaoEconomia=" + idSugestaoEconomia +
                ", usuario=" + usuario +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
