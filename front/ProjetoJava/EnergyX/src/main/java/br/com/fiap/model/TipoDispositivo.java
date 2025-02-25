package br.com.fiap.model;

import br.com.fiap.util.database.DbColumn;
import br.com.fiap.util.database.DbTable;

@DbTable("t_gs_tipo_dispositivo")
public class TipoDispositivo {
    @DbColumn(primaryKey = true, generatedByDefault = true)
    private Integer idTipoDispositivo;
    @DbColumn(type = "VARCHAR(50)", unique = true, notNull = true)
    private String nome;
    @DbColumn(type = "NUMBER(7,2)")
    private Double consumoMedio;

    public Integer getIdTipoDispositivo() {
        return idTipoDispositivo;
    }

    public void setIdTipoDispositivo(Integer idTipoDispositivo) {
        this.idTipoDispositivo = idTipoDispositivo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getConsumoMedio() {
        return consumoMedio;
    }

    public void setConsumoMedio(Double consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    @Override
    public String toString() {
        return "TipoDispositivo{" +
                "idTipoDispositivo=" + idTipoDispositivo +
                ", nome='" + nome + '\'' +
                ", consumoMedio=" + consumoMedio +
                '}';
    }
}
