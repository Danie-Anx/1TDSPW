package br.com.fiap.util.database;

import br.com.fiap.util.GeneralUtils;
import br.com.fiap.util.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TODO: log
public class CommonDao<Type> extends QueryDao<Type> {


    public CommonDao(Class<Type> typeClass, DatabaseConnection databaseConnection, boolean shouldCreateTable) throws Exception {
        super(typeClass, databaseConnection);

        if (!tableExists()) {
            if (shouldCreateTable) {
                createTable();
            }
            else {
                Logger.log("Execute o script DDL da tabela acima para o correto funcionamento do sistema", this);
            }
        }
        // TODO: verificar se a tabela existente está de acordo com o model

    }

    private boolean tableExists() throws Exception {
        String tableName = DbUtils.getTableName(this.typeClass);
        Logger.log("Verificando existência da tabela '" + tableName + "'", this);

        String sql = "SELECT COUNT(*) AS COUNT FROM user_tables " +
                "WHERE upper(table_name) = upper(?)";

        try (
                Connection con = databaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setString(1, tableName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    if (rs.getInt("COUNT") == 1) {
                        Logger.log("Tabela '" + tableName + "' existe", this);
                        return true;
                    }
                }
            }
        }
        Logger.log("Tabela '" + tableName + "' não existe", this);
        return  false;
    }

    private void createTable() throws Exception {
        String tableName = DbUtils.getTableName(this.typeClass);
        Logger.log("Criando tabela '" + tableName + "'", this);
        String sql = DbUtils.generateTableDdl(this.typeClass);
        Logger.log("SQL DDL: '" + sql + "'", this);

        try (
                Connection con = databaseConnection.getConnection();
                Statement stmt = con.createStatement()
        ) {
            stmt.executeUpdate(sql);
            Logger.log("Tabela '" + tableName + "' criada", this);
        }
    }
    public final Type save(Type object) throws Exception {
        String sql = DbUtils.generateInsertDml(this.typeClass);

        DbColumnObject generatedColumn = DbUtils.getGeneratedColumn(this.typeClass);

        try (
                Connection con = databaseConnection.getConnection();
                PreparedStatement stmt = (generatedColumn == null) ?
                        con.prepareStatement(sql) :
                        con.prepareStatement(sql, new String[]{generatedColumn.getName()})
        ) {
            ArrayList<DbColumnObject> nonGeneratedColumns = DbUtils.getNonGeneratedColumns(this.typeClass);
            for (int i = 0; i < nonGeneratedColumns.size(); i++) {
                DbColumnObject column = nonGeneratedColumns.get(i);

                Method getter = this.typeClass.getMethod(
                        GeneralUtils.getGetterName(column.getRelatedField().getName())
                );

                Object returnValue = getter.invoke(object);

                if (column.getForeignKeyReference() != null) {
                    DbColumnObject foreignKeyReference = column.getForeignKeyReference();

                    Method foreignKeyGetter = returnValue.getClass().getMethod(
                            GeneralUtils.getGetterName(foreignKeyReference.getRelatedField().getName())
                    );

                    returnValue = foreignKeyGetter.invoke(returnValue);
                }

                stmt.setObject(i + 1, returnValue);
            }

            stmt.executeUpdate();

            if (generatedColumn != null) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {

                        Method setter = this.typeClass.getMethod(
                                GeneralUtils.getSetterName(generatedColumn.getRelatedField().getName()),
                                generatedColumn.getRelatedField().getType()
                        );

                        setter.invoke(object, rs.getInt(1));
                    }
                }
            }
        }

        return object;
    }

    public final Type findById(Object id) throws Exception {
        String sql = DbUtils.generateSelectByIdDql(this.typeClass);

        try (
                Connection con = databaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            stmt.setObject(1, id);

            List<Type> list = super.runQuery(stmt);
            if (list.size() == 1) {
                return list.get(0);
            }
        }
        return null;
    }

    public final List<Type> findAll() throws Exception {
        String sql = DbUtils.generateSelectAllDql(this.typeClass);

        try (
                Connection con = databaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
        ) {
            return super.runQuery(stmt);
        }
    }

    public final void update(Type object) throws Exception {
        String sql = DbUtils.generateUpdateDml(this.typeClass);

        try (
                Connection con = databaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            ArrayList<DbColumnObject> columns = DbUtils.getNonPrimaryKeyColumns(this.typeClass);

            int i = 0;
            for (; i < columns.size(); i++) {
                DbColumnObject column = columns.get(i);

                Method getter = this.typeClass.getMethod(
                        GeneralUtils.getGetterName(column.getRelatedField().getName())
                );

                Object returnValue = getter.invoke(object);

                if (column.getForeignKeyReference() != null) {
                    DbColumnObject foreignKeyReference = column.getForeignKeyReference();

                    Method foreignKeyGetter = returnValue.getClass().getMethod(
                            GeneralUtils.getGetterName(foreignKeyReference.getRelatedField().getName())
                    );

                    returnValue = foreignKeyGetter.invoke(returnValue);
                }

                stmt.setObject(i + 1, returnValue);
            }

            DbColumnObject primaryKeyColumn = DbUtils.getPrimaryKeyColumn(this.typeClass);
            if (primaryKeyColumn == null) {
                throw new Exception("Chave primária não encontrada na tabela: " + DbUtils.getTableName(this.typeClass));
            }

            Method getter = this.typeClass.getMethod(
                    GeneralUtils.getGetterName(primaryKeyColumn.getRelatedField().getName())
            );

            Object id = getter.invoke(object);

            stmt.setObject(i + 1, id);

            stmt.executeUpdate();

        }
    }

    public final void delete(Object id) throws Exception {
        String sql = DbUtils.generateDeleteDml(this.typeClass);

        try (
                Connection con = databaseConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)
        ) {

            stmt.setObject(1, id);

            stmt.executeUpdate();

        }
    }

}
