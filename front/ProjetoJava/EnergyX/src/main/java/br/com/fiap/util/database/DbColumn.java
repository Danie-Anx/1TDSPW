package br.com.fiap.util.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbColumn {
    String name() default "";
    String type() default "";
    boolean primaryKey() default false;
    boolean generatedByDefault() default false;
    String foreignKey() default "";
    boolean notNull() default false;
    boolean unique() default false;

    // Padroes do projeto:
    // Uma coluna generated deve ser sempre PK e do tipo Integer
    // Apenas uma primary key por tabela
    // Apenas uma generated por tabela
    // getById equivalente a get por primary key
    // Colunas primaryKey não são atualizadas
    // Foreign key deve referenciar nome_tabela
    // Nome da FK segue o padrã0 nome_tabela_nome_coluna

    // TODO: testar tabela sem PK
    // TODO: suporte a CHECK
}
