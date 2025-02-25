package br.com.fiap.dao;

import br.com.fiap.model.EstimativaConsumo;
import br.com.fiap.model.Usuario;
import br.com.fiap.util.database.CommonDao;
import br.com.fiap.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class EstimativaConsumoDao extends CommonDao<EstimativaConsumo> {
    public EstimativaConsumoDao(DatabaseConnection databaseConnection) throws Exception {
        super(EstimativaConsumo.class, databaseConnection, true);
    }

    public final List<EstimativaConsumo> findByIdUsuario(int idUsuario) throws Exception {
        String sql = "select * from T_GS_ESTIMATIVA_CONSUMO WHERE T_GS_USUARIO_ID_USUARIO = ?";

        try (
                Connection con = databaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setInt(1, idUsuario);

            return super.runQuery(stmt);
        }
    }
}
