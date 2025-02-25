package br.com.fiap.dao;

import br.com.fiap.model.Usuario;
import br.com.fiap.util.database.CommonDao;
import br.com.fiap.util.database.DatabaseConnection;
import br.com.fiap.util.database.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UsuarioDao extends CommonDao<Usuario> {
    public UsuarioDao(DatabaseConnection databaseConnection) throws Exception {
        super(Usuario.class, databaseConnection, true);
    }

    public final Usuario findByEmailAndPassword(String email, String password) throws Exception {
        String sql = "select * from T_GS_USUARIO WHERE email = ? AND senha = ?";

        try (
                Connection con = databaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            List<Usuario> list = super.runQuery(stmt);
            if (list.size() == 1) {
                return list.get(0);
            }
        }
        return null;
    }
}
