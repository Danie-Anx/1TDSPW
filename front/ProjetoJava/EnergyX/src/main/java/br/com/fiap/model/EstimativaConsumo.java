package br.com.fiap.model;

import br.com.fiap.util.database.DbColumn;
import br.com.fiap.util.database.DbTable;

@DbTable("t_gs_estimativa_consumo")
public class EstimativaConsumo {
    @DbColumn(primaryKey = true, generatedByDefault = true)
    private Integer idEstimativaConsumo;
    @DbColumn(foreignKey = "t_gs_tipo_dispositivo", notNull = true)
    private TipoDispositivo tipoDispositivo;
    @DbColumn(foreignKey = "t_gs_usuario", notNull = true)
    private Usuario usuario;
    @DbColumn(type = "NUMBER(4,1)", notNull = true)
    private Double usoMedio; // em horas
    @DbColumn(notNull = true)
    private String frequenciaUsoMedio; // diario, semanal, mensal
    @DbColumn(type = "NUMBER(7,2)", notNull = true)
    private Double consumoMedio; // por padrao e o mesmo do TipoDispositivo mas pode ser customizado

    public Integer getIdEstimativaConsumo() {
        return idEstimativaConsumo;
    }

    public void setIdEstimativaConsumo(Integer idEstimativaConsumo) {
        this.idEstimativaConsumo = idEstimativaConsumo;
    }

    public TipoDispositivo getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(TipoDispositivo tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getUsoMedio() {
        return usoMedio;
    }

    public void setUsoMedio(Double usoMedio) {
        this.usoMedio = usoMedio;
    }

    public String getFrequenciaUsoMedio() {
        return frequenciaUsoMedio;
    }

    public void setFrequenciaUsoMedio(String frequenciaUsoMedio) {
        this.frequenciaUsoMedio = frequenciaUsoMedio;
    }

    public Double getConsumoMedio() {
        return consumoMedio;
    }

    public void setConsumoMedio(Double consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    @Override
    public String toString() {
        return "EstimativaConsumo{" +
                "idEstimativaConsumo=" + idEstimativaConsumo +
                ", tipoDispositivo=" + tipoDispositivo +
                ", usuario=" + usuario +
                ", usoMedio=" + usoMedio +
                ", frequenciaUsoMedio='" + frequenciaUsoMedio + '\'' +
                ", consumoMedio=" + consumoMedio +
                '}';
    }
}
