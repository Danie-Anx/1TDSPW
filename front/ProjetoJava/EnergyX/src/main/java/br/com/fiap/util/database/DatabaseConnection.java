package br.com.fiap.util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

        return DriverManager.getConnection(url, System.getenv("ORACLE_DB_USERNAME"), System.getenv("ORACLE_DB_PASSWORD"));
    }
}
