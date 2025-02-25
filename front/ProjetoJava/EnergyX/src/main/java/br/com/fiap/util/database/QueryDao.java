package br.com.fiap.util.database;

import br.com.fiap.util.GeneralUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// public class AggregateDao extends ConsultaDao<Aggregate>{
public class QueryDao<Type> {

    protected final DatabaseConnection databaseConnection;
    protected final Class<?> typeClass;

    public QueryDao(Class<Type> typeClass, DatabaseConnection databaseConnection) {
        this.typeClass = typeClass;
        this.databaseConnection = databaseConnection;
    }

    public final List<Type> runQuery(PreparedStatement stmt) throws Exception {
        List<Type> list = new ArrayList<>();
        ArrayList<DbColumnObject> columns = DbUtils.getTableColumns(this.typeClass);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                Constructor<?> constructor = this.typeClass.getConstructor();
                Type object = (Type) constructor.newInstance();

                for (DbColumnObject column : columns) {
                    Field relatedField = column.getRelatedField();

                    Method objectSetter = this.typeClass.getMethod(
                            GeneralUtils.getSetterName(relatedField.getName()),
                            relatedField.getType()
                    );

                    if (column.getForeignKeyReference() != null) {

                        DbColumnObject foreignKeyReference = column.getForeignKeyReference();

                        Method rsGetter = rs.getClass().getMethod(
                                DbUtils.getDefaultResultSetGetterFromJavaType(foreignKeyReference.getRelatedField().getType()),
                                String.class
                        );
                        rsGetter.setAccessible(true);

                        Object foreignKeyValue = rsGetter.invoke(rs, column.getName());

                        Constructor<?> referencedTypeConstructor = foreignKeyReference.getRelatedField().getDeclaringClass().getConstructor();
                        Type referencedObject = (Type) referencedTypeConstructor.newInstance();

                        Method referencedPrimaryKeySetter = referencedObject.getClass().getMethod(
                                GeneralUtils.getSetterName(foreignKeyReference.getRelatedField().getName()),
                                foreignKeyReference.getRelatedField().getType()
                        );

                        referencedPrimaryKeySetter.invoke(referencedObject, foreignKeyValue);

                        objectSetter.invoke(object, referencedObject);

                    }
                    else if (relatedField.getType() == LocalDateTime.class) {
                        Timestamp returnValue = rs.getTimestamp(column.getName());
                        if (returnValue != null) {
                            objectSetter.invoke(object, returnValue.toLocalDateTime());
                        }
                    }
                    else if (relatedField.getType() == LocalDate.class) {
                        Timestamp returnValue = rs.getTimestamp(column.getName());
                        if (returnValue != null) {
                            objectSetter.invoke(object, returnValue.toLocalDateTime().toLocalDate());
                        }
                    }
                    else {
                        Method rsGetter = rs.getClass().getMethod(
                                DbUtils.getDefaultResultSetGetterFromJavaType(relatedField.getType()),
                                String.class
                        );
                        rsGetter.setAccessible(true);

                        Object returnValue = rsGetter.invoke(rs, column.getName());
                        objectSetter.invoke(object, returnValue);
                    }

                }

                list.add(object);
            }
        }
        return list;
    }

}
