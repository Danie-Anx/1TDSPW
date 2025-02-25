import cx_Oracle
import logging

def conectar_bd():
    """
    Estabelece conexão com o banco de dados Oracle.

    Returns:
        cx_Oracle.Connection: Conexão ao banco de dados.
    """
    try:
        connection = cx_Oracle.connect(
            user="RM555881",
            password="270505",
            dsn="oracle.fiap.com.br:1521/orcl"
        )
        logging.info("Conexão ao banco de dados estabelecida com sucesso.")
        return connection
    except cx_Oracle.DatabaseError as e:
        logging.error(f"Erro ao conectar ao banco de dados: {e}")
        return None
