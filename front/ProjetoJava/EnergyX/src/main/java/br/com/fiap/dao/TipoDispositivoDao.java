package br.com.fiap.dao;

import br.com.fiap.model.TipoDispositivo;
import br.com.fiap.util.database.CommonDao;
import br.com.fiap.util.database.DatabaseConnection;

public class TipoDispositivoDao extends CommonDao<TipoDispositivo> {
    public TipoDispositivoDao(DatabaseConnection databaseConnection) throws Exception {
        super(TipoDispositivo.class, databaseConnection, true);
    }
}
