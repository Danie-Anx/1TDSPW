package br.com.fiap.dao;

import br.com.fiap.model.EstimativaConsumo;
import br.com.fiap.model.SugestaoEconomia;
import br.com.fiap.util.database.CommonDao;
import br.com.fiap.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SugestaoEconomiaDao extends CommonDao<SugestaoEconomia> {
    public SugestaoEconomiaDao(DatabaseConnection databaseConnection) throws Exception {
        super(SugestaoEconomia.class, databaseConnection, true);
    }

    public final List<SugestaoEconomia> findByIdUsuario(int idUsuario) throws Exception {
        String sql = "select * from T_GS_SUGESTAO_ECONOMIA WHERE T_GS_USUARIO_ID_USUARIO = ?";

        try (
                Connection con = databaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setInt(1, idUsuario);

            return super.runQuery(stmt);
        }
    }
}
