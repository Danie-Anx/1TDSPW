import logging
from validators import validar_email, validar_id, validar_nome, validar_senha


def inserir_usuario(conexao):
    """
    Insere um novo usuário no banco de dados.
    """
    try:
        email = input("Email: ").strip()
        nome = input("Nome: ").strip()
        senha = input("Senha: ").strip()

        if not validar_email(email):
            print("Erro: Email inválido!")
            return

        if not validar_nome(nome):
            print("Erro: Nome inválido! O nome deve conter apenas letras e espaços.")
            return

        if not validar_senha(senha):
            print("Erro: A senha deve conter pelo menos 8 caracteres, incluindo:")
            print(" - Letras maiúsculas e minúsculas")
            print(" - Pelo menos 1 número")
            print(" - Pelo menos 1 caractere especial (ex.: @, #, $)")
            return

        cursor = conexao.cursor()
        sql = """
        INSERT INTO t_gs_usuario (email, nome, senha)
        VALUES (:email, :nome, :senha)
        """
        cursor.execute(sql, {'email': email, 'nome': nome, 'senha': senha})
        conexao.commit()
        print("Usuário inserido com sucesso!")
    except Exception as e:
        print("Erro ao inserir usuário:", e)

def excluir_usuario(conexao):
    """
    Exclui um usuário do banco de dados.

    Args:
        conexao (cx_Oracle.Connection): Conexão com o banco de dados.
    """
    try:
        id_usuario = input("ID do usuário a excluir: ").strip()

        if not validar_id(id_usuario):
            print("Erro: ID inválido!")
            logging.warning("ID inválido fornecido ao tentar excluir usuário.")
            return

        id_usuario = int(id_usuario)

        cursor = conexao.cursor()
        sql = "DELETE FROM t_gs_usuario WHERE id_usuario = :id_usuario"
        cursor.execute(sql, {'id_usuario': id_usuario})
        conexao.commit()
        print("Usuário excluído com sucesso!")
        logging.info(f"Usuário com ID {id_usuario} excluído com sucesso.")
    except Exception as e:
        logging.error(f"Erro ao excluir usuário: {e}")
        print("Erro ao excluir usuário:", e)


def inserir_dispositivo(conexao):
    """
    Insere um novo dispositivo no banco de dados.

    Args:
        conexao (cx_Oracle.Connection): Conexão com o banco de dados.
    """
    try:
        nome = input("Nome do dispositivo: ").strip()
        consumo_medio = input("Consumo médio (kWh): ").strip()

        if not nome or not consumo_medio:
            print("Erro: Todos os campos são obrigatórios!")
            logging.warning("Campos obrigatórios não preenchidos ao tentar inserir dispositivo.")
            return

        if not consumo_medio.replace('.', '', 1).isdigit():
            print("Erro: Consumo médio deve ser numérico!")
            logging.warning("Consumo médio inválido fornecido.")
            return

        consumo_medio = float(consumo_medio)

        cursor = conexao.cursor()
        sql = """
        INSERT INTO t_gs_tipo_dispositivo (nome, consumo_medio)
        VALUES (:nome, :consumo_medio)
        """
        cursor.execute(sql, {'nome': nome, 'consumo_medio': consumo_medio})
        conexao.commit()
        print("Dispositivo inserido com sucesso!")
        logging.info(f"Dispositivo '{nome}' inserido com sucesso.")
    except Exception as e:
        logging.error(f"Erro ao inserir dispositivo: {e}")
        print("Erro ao inserir dispositivo:", e)



def atualizar_dispositivo(conexao):
    """
    Atualiza as informações de um dispositivo no banco de dados.

    Args:
        conexao (cx_Oracle.Connection): Conexão com o banco de dados.
    """
    try:
        id_dispositivo = input("ID do dispositivo a ser atualizado: ").strip()
        novo_nome = input("Novo nome do dispositivo: ").strip()
        novo_consumo = input("Novo consumo médio (kWh): ").strip()

        if not validar_id(id_dispositivo) or not novo_nome or not novo_consumo:
            print("Erro: Todos os campos são obrigatórios!")
            logging.warning("Campos obrigatórios não preenchidos ao tentar atualizar dispositivo.")
            return

        if not novo_consumo.replace('.', '', 1).isdigit():
            print("Erro: Consumo médio deve ser numérico!")
            logging.warning("Consumo médio inválido fornecido.")
            return

        id_dispositivo = int(id_dispositivo)
        novo_consumo = float(novo_consumo)

        cursor = conexao.cursor()
        sql = """
        UPDATE t_gs_tipo_dispositivo
        SET nome = :novo_nome, consumo_medio = :novo_consumo
        WHERE id_tipo_dispositivo = :id_dispositivo
        """
        cursor.execute(sql, {'novo_nome': novo_nome, 'novo_consumo': novo_consumo, 'id_dispositivo': id_dispositivo})
        conexao.commit()
        print("Dispositivo atualizado com sucesso!")
        logging.info(f"Dispositivo com ID {id_dispositivo} atualizado com sucesso.")
    except Exception as e:
        logging.error(f"Erro ao atualizar dispositivo: {e}")
        print("Erro ao atualizar dispositivo:", e)




def consultar_usuarios(conexao):
    """
    Consulta todos os usuários do banco de dados.

    Args:
        conexao (cx_Oracle.Connection): Conexão com o banco de dados.
    """
    try:
        cursor = conexao.cursor()
        sql = "SELECT * FROM t_gs_usuario"
        cursor.execute(sql)
        usuarios = cursor.fetchall()

        if not usuarios:
            print("Nenhum usuário encontrado.")
            logging.info("Nenhum usuário encontrado na consulta.")
        else:
            for usuario in usuarios:
                print(usuario)
            logging.info(f"{len(usuarios)} usuário(s) consultado(s).")
    except Exception as e:
        logging.error(f"Erro ao consultar usuários: {e}")
        print("Erro ao consultar usuários:", e)


def consultar_estimativas(conexao):
    """
    Consulta todas as estimativas de consumo do banco de dados.

    Args:
        conexao (cx_Oracle.Connection): Conexão com o banco de dados.
    """
    try:
        cursor = conexao.cursor()
        sql = """
        SELECT e.id_estimativa_consumo, d.nome AS dispositivo, e.consumo_medio, 
               e.uso_medio, e.frequencia_uso_medio
        FROM t_gs_estimativa_consumo e
        JOIN t_gs_tipo_dispositivo d ON e.t_gs_tipo_dispositivo_id_tipo_dispositivo = d.id_tipo_dispositivo
        """
        cursor.execute(sql)
        estimativas = cursor.fetchall()

        if not estimativas:
            print("Nenhuma estimativa encontrada.")
            logging.info("Nenhuma estimativa encontrada na consulta.")
        else:
            for estimativa in estimativas:
                print(estimativa)
            logging.info(f"{len(estimativas)} estimativa(s) consultada(s).")
    except Exception as e:
        logging.error(f"Erro ao consultar estimativas: {e}")
        print("Erro ao consultar estimativas:", e)


def atualizar_usuario(conexao):
    """
    Atualiza as informações de um usuário no banco de dados.

    Args:
        conexao (cx_Oracle.Connection): Conexão com o banco de dados.
    """
    try:
        usuario_id = input("ID do usuário: ").strip()
        novo_email = input("Novo e-mail: ").strip()
        novo_nome = input("Novo nome: ").strip()
        nova_senha = input("Nova senha: ").strip()

        if not validar_id(usuario_id):
            print("Erro: ID inválido!")
            logging.warning("ID inválido fornecido.")
            return

        if not novo_email or not novo_nome or not nova_senha:
            print("Erro: Todos os campos são obrigatórios!")
            logging.warning("Campos obrigatórios não preenchidos ao tentar atualizar usuário.")
            return

        if not validar_email(novo_email):
            print("Erro: Email inválido!")
            logging.warning("Email inválido fornecido.")
            return

        usuario_id = int(usuario_id)

        cursor = conexao.cursor()

        # Verificar se o e-mail já está sendo usado por outro usuário
        sql_verificacao = """
        SELECT id_usuario FROM t_gs_usuario
        WHERE email = :email AND id_usuario != :usuario_id
        """
        cursor.execute(sql_verificacao, {'email': novo_email, 'usuario_id': usuario_id})
        resultado = cursor.fetchone()

        if resultado:
            print("Erro: O e-mail já está sendo usado por outro usuário.")
            logging.warning("E-mail duplicado encontrado na tentativa de atualização.")
            return

        # Atualizar o usuário
        sql_atualizacao = """
        UPDATE t_gs_usuario
        SET email = :novo_email, nome = :novo_nome, senha = :nova_senha
        WHERE id_usuario = :usuario_id
        """
        cursor.execute(sql_atualizacao, {
            'novo_email': novo_email,
            'novo_nome': novo_nome,
            'nova_senha': nova_senha,
            'usuario_id': usuario_id
        })
        conexao.commit()
        print("Usuário atualizado com sucesso!")
        logging.info(f"Usuário com ID {usuario_id} atualizado com sucesso.")
    except Exception as e:
        logging.error(f"Erro ao atualizar usuário: {e}")
        print("Erro ao atualizar usuário:", e)


def consultar_dispositivos(conexao):
    """
    Consulta todos os dispositivos cadastrados no banco de dados.

    Args:
        conexao (cx_Oracle.Connection): Conexão com o banco de dados.
    """
    try:
        cursor = conexao.cursor()
        sql = """
        SELECT id_tipo_dispositivo, nome, consumo_medio
        FROM t_gs_tipo_dispositivo
        """
        cursor.execute(sql)
        dispositivos = cursor.fetchall()

        if not dispositivos:
            print("Nenhum dispositivo encontrado.")
            logging.info("Nenhum dispositivo encontrado na consulta.")
        else:
            print("\nDispositivos Cadastrados:")
            print("=" * 60)
            for dispositivo in dispositivos:
                print(f"ID: {dispositivo[0]}")
                print(f"Nome: {dispositivo[1]}")
                print(f"Consumo Médio: {dispositivo[2]:.2f} kWh")
                print("-" * 60)
            logging.info(f"{len(dispositivos)} dispositivo(s) consultado(s).")
    except Exception as e:
        logging.error(f"Erro ao consultar dispositivos: {e}")
        print("Erro ao consultar dispositivos:", e)



def excluir_dispositivo(conexao):
    """
    Exclui um dispositivo do banco de dados e todas as estimativas associadas a ele.

    Args:
        conexao (cx_Oracle.Connection): Conexão com o banco de dados.
    """
    try:
        id_dispositivo = input("ID do dispositivo a excluir: ").strip()

        if not validar_id(id_dispositivo):
            print("Erro: ID inválido!")
            logging.warning("ID inválido fornecido para exclusão de dispositivo.")
            return

        id_dispositivo = int(id_dispositivo)

        # Verificar se o dispositivo existe
        if not verificar_id_existente(conexao, "t_gs_tipo_dispositivo", "id_tipo_dispositivo", id_dispositivo):
            print(f"Erro: Dispositivo com ID {id_dispositivo} não encontrado!")
            logging.warning(f"ID do dispositivo {id_dispositivo} não existe.")
            return

        cursor = conexao.cursor()

        # Excluir todas as estimativas associadas ao dispositivo
        sql_estimativas = """
        DELETE FROM t_gs_estimativa_consumo
        WHERE t_gs_tipo_dispositivo_id_tipo_dispositivo = :id_dispositivo
        """
        cursor.execute(sql_estimativas, {'id_dispositivo': id_dispositivo})

        # Excluir o dispositivo
        sql_dispositivo = "DELETE FROM t_gs_tipo_dispositivo WHERE id_tipo_dispositivo = :id_dispositivo"
        cursor.execute(sql_dispositivo, {'id_dispositivo': id_dispositivo})

        conexao.commit()
        print(f"Dispositivo com ID {id_dispositivo} e suas estimativas associadas foram excluídos com sucesso!")
        logging.info(f"Dispositivo com ID {id_dispositivo} e suas estimativas associadas foram excluídos com sucesso.")
    except Exception as e:
        logging.error(f"Erro ao excluir dispositivo: {e}")
        print("Erro ao excluir dispositivo:", e)


def inserir_sugestao(conexao):
    """
    Insere uma nova sugestão de economia no banco de dados.
    """
    try:
        descricao = input("Descrição da sugestão: ").strip()
        id_usuario = input("ID do usuário: ").strip()

        if not descricao or not id_usuario:
            print("Erro: Todos os campos são obrigatórios!")
            logging.warning("Campos obrigatórios não preenchidos ao tentar inserir sugestão.")
            return

        if not validar_id(id_usuario):
            print("Erro: ID do usuário inválido!")
            logging.warning("ID do usuário inválido fornecido.")
            return

        id_usuario = int(id_usuario)

        cursor = conexao.cursor()
        sql = """
        INSERT INTO t_gs_sugestao_economia (descricao, t_gs_usuario_id_usuario)
        VALUES (:descricao, :id_usuario)
        """
        cursor.execute(sql, {'descricao': descricao, 'id_usuario': id_usuario})
        conexao.commit()
        print("Sugestão inserida com sucesso!")
        logging.info(f"Sugestão de economia inserida com sucesso para o usuário com ID {id_usuario}.")
    except Exception as e:
        logging.error(f"Erro ao inserir sugestão de economia: {e}")
        print("Erro ao inserir sugestão de economia:", e)


def consultar_sugestoes(conexao):
    """
    Consulta todas as sugestões de economia no banco de dados.
    """
    try:
        cursor = conexao.cursor()
        sql = """
        SELECT s.id_sugestao_economia, s.descricao, u.nome AS usuario
        FROM t_gs_sugestao_economia s
        JOIN t_gs_usuario u ON s.t_gs_usuario_id_usuario = u.id_usuario
        """
        cursor.execute(sql)
        sugestoes = cursor.fetchall()

        if not sugestoes:
            print("Nenhuma sugestão encontrada.")
            logging.info("Nenhuma sugestão de economia encontrada na consulta.")
        else:
            for sugestao in sugestoes:
                print(f"ID: {sugestao[0]}, Descrição: {sugestao[1]}, Usuário: {sugestao[2]}")
            logging.info(f"{len(sugestoes)} sugestão(ões) de economia consultada(s).")
    except Exception as e:
        logging.error(f"Erro ao consultar sugestões de economia: {e}")
        print("Erro ao consultar sugestões de economia:", e)


def atualizar_sugestao(conexao):
    """
    Atualiza uma sugestão de economia no banco de dados.
    """
    try:
        id_sugestao = input("ID da sugestão a ser atualizada: ").strip()
        nova_descricao = input("Nova descrição da sugestão: ").strip()

        if not validar_id(id_sugestao) or not nova_descricao:
            print("Erro: Todos os campos são obrigatórios!")
            logging.warning("Campos obrigatórios não preenchidos ao tentar atualizar sugestão.")
            return

        id_sugestao = int(id_sugestao)

        cursor = conexao.cursor()
        sql = """
        UPDATE t_gs_sugestao_economia
        SET descricao = :nova_descricao
        WHERE id_sugestao_economia = :id_sugestao
        """
        cursor.execute(sql, {'nova_descricao': nova_descricao, 'id_sugestao': id_sugestao})
        conexao.commit()
        print("Sugestão atualizada com sucesso!")
        logging.info(f"Sugestão com ID {id_sugestao} atualizada com sucesso.")
    except Exception as e:
        logging.error(f"Erro ao atualizar sugestão de economia: {e}")
        print("Erro ao atualizar sugestão de economia:", e)


def excluir_sugestao(conexao):
    """
    Exclui uma sugestão de economia do banco de dados.
    """
    try:
        id_sugestao = input("ID da sugestão a excluir: ").strip()

        if not validar_id(id_sugestao):
            print("Erro: ID inválido!")
            logging.warning("ID inválido fornecido ao tentar excluir sugestão.")
            return

        id_sugestao = int(id_sugestao)

        cursor = conexao.cursor()
        sql = "DELETE FROM t_gs_sugestao_economia WHERE id_sugestao_economia = :id_sugestao"
        cursor.execute(sql, {'id_sugestao': id_sugestao})
        conexao.commit()
        print("Sugestão excluída com sucesso!")
        logging.info(f"Sugestão com ID {id_sugestao} excluída com sucesso.")
    except Exception as e:
        logging.error(f"Erro ao excluir sugestão de economia: {e}")
        print("Erro ao excluir sugestão de economia:", e)


def verificar_id_existente(conexao, tabela, campo, valor):
    """
    Verifica se um ID existe em uma tabela específica no banco de dados.

    Args:
        conexao (cx_Oracle.Connection): Conexão com o banco de dados.
        tabela (str): Nome da tabela.
        campo (str): Nome do campo (coluna).
        valor (int): Valor a ser verificado.

    Returns:
        bool: True se o ID existe, False caso contrário.
    """
    try:
        cursor = conexao.cursor()
        sql = f"SELECT COUNT(*) FROM {tabela} WHERE {campo} = :valor"
        cursor.execute(sql, {'valor': valor})
        resultado = cursor.fetchone()
        return resultado[0] > 0
    except Exception as e:
        logging.error(f"Erro ao verificar ID na tabela {tabela}: {e}")
        return False


def inserir_estimativa(conexao):
    """
    Insere uma nova estimativa de consumo no banco de dados.
    """
    try:
        id_dispositivo = input("ID do dispositivo: ").strip()
        id_usuario = input("ID do usuário: ").strip()
        consumo_medio = input("Consumo médio (kWh): ").strip()
        uso_medio = input("Uso médio (horas): ").strip()
        frequencia = input("Frequência de uso médio: ").strip()

        if not all([id_dispositivo, id_usuario, consumo_medio, uso_medio, frequencia]):
            print("Erro: Todos os campos são obrigatórios!")
            logging.warning("Campos obrigatórios não preenchidos ao tentar inserir estimativa.")
            return

        if not (validar_id(id_dispositivo) and validar_id(id_usuario)):
            print("Erro: IDs do dispositivo e usuário devem ser válidos!")
            logging.warning("IDs inválidos fornecidos.")
            return

        id_dispositivo = int(id_dispositivo)
        id_usuario = int(id_usuario)

        # Verificar se os IDs existem
        if not verificar_id_existente(conexao, "t_gs_tipo_dispositivo", "id_tipo_dispositivo", id_dispositivo):
            print(f"Erro: Dispositivo com ID {id_dispositivo} não encontrado!")
            logging.warning(f"ID do dispositivo {id_dispositivo} não existe.")
            return

        if not verificar_id_existente(conexao, "t_gs_usuario", "id_usuario", id_usuario):
            print(f"Erro: Usuário com ID {id_usuario} não encontrado!")
            logging.warning(f"ID do usuário {id_usuario} não existe.")
            return

        consumo_medio = float(consumo_medio)
        uso_medio = float(uso_medio)

        cursor = conexao.cursor()
        sql = """
        INSERT INTO t_gs_estimativa_consumo (
            consumo_medio, uso_medio, frequencia_uso_medio,
            t_gs_tipo_dispositivo_id_tipo_dispositivo, t_gs_usuario_id_usuario
        )
        VALUES (:consumo_medio, :uso_medio, :frequencia, :id_dispositivo, :id_usuario)
        """
        cursor.execute(sql, {
            'consumo_medio': consumo_medio, 'uso_medio': uso_medio,
            'frequencia': frequencia, 'id_dispositivo': id_dispositivo, 'id_usuario': id_usuario
        })
        conexao.commit()
        print("Estimativa de consumo inserida com sucesso!")
        logging.info(f"Estimativa de consumo inserida para o dispositivo {id_dispositivo} e usuário {id_usuario}.")
    except Exception as e:
        logging.error(f"Erro ao inserir estimativa de consumo: {e}")
        print("Erro ao inserir estimativa de consumo:", e)

def consultar_estimativas(conexao):
    """
    Consulta todas as estimativas de consumo no banco de dados.
    """
    try:
        cursor = conexao.cursor()
        sql = """
        SELECT e.id_estimativa_consumo, d.nome AS dispositivo, e.consumo_medio,
               e.uso_medio, e.frequencia_uso_medio, u.nome AS usuario
        FROM t_gs_estimativa_consumo e
        JOIN t_gs_tipo_dispositivo d ON e.t_gs_tipo_dispositivo_id_tipo_dispositivo = d.id_tipo_dispositivo
        JOIN t_gs_usuario u ON e.t_gs_usuario_id_usuario = u.id_usuario
        """
        cursor.execute(sql)
        estimativas = cursor.fetchall()

        if not estimativas:
            print("Nenhuma estimativa encontrada.")
            logging.info("Nenhuma estimativa encontrada na consulta.")
        else:
            for estimativa in estimativas:
                print(f"ID: {estimativa[0]}, Dispositivo: {estimativa[1]}, "
                      f"Consumo: {estimativa[2]} kWh, Uso: {estimativa[3]} horas, "
                      f"Frequência: {estimativa[4]}, Usuário: {estimativa[5]}")
            logging.info(f"{len(estimativas)} estimativa(s) consultada(s).")
    except Exception as e:
        logging.error(f"Erro ao consultar estimativas de consumo: {e}")
        print("Erro ao consultar estimativas de consumo:", e)

def atualizar_estimativa(conexao):
    """
    Atualiza uma estimativa de consumo no banco de dados.
    """
    try:
        id_estimativa = input("ID da estimativa a ser atualizada: ").strip()
        novo_consumo = input("Novo consumo médio (kWh): ").strip()
        novo_uso = input("Novo uso médio (horas): ").strip()
        nova_frequencia = input("Nova frequência de uso médio: ").strip()

        if not (validar_id(id_estimativa) and novo_consumo and novo_uso and nova_frequencia):
            print("Erro: Todos os campos são obrigatórios!")
            logging.warning("Campos obrigatórios não preenchidos ao tentar atualizar estimativa.")
            return

        id_estimativa = int(id_estimativa)
        novo_consumo = float(novo_consumo)
        novo_uso = float(novo_uso)

        cursor = conexao.cursor()
        sql = """
        UPDATE t_gs_estimativa_consumo
        SET consumo_medio = :novo_consumo, uso_medio = :novo_uso,
            frequencia_uso_medio = :nova_frequencia
        WHERE id_estimativa_consumo = :id_estimativa
        """
        cursor.execute(sql, {
            'novo_consumo': novo_consumo, 'novo_uso': novo_uso,
            'nova_frequencia': nova_frequencia, 'id_estimativa': id_estimativa
        })
        conexao.commit()
        print("Estimativa de consumo atualizada com sucesso!")
        logging.info(f"Estimativa com ID {id_estimativa} atualizada com sucesso.")
    except Exception as e:
        logging.error(f"Erro ao atualizar estimativa de consumo: {e}")
        print("Erro ao atualizar estimativa de consumo:", e)

def excluir_estimativa(conexao):
    """
    Exclui uma estimativa de consumo do banco de dados.
    """
    try:
        id_estimativa = input("ID da estimativa a excluir: ").strip()

        if not validar_id(id_estimativa):
            print("Erro: ID inválido!")
            logging.warning("ID inválido fornecido ao tentar excluir estimativa.")
            return

        id_estimativa = int(id_estimativa)

        cursor = conexao.cursor()
        sql = "DELETE FROM t_gs_estimativa_consumo WHERE id_estimativa_consumo = :id_estimativa"
        cursor.execute(sql, {'id_estimativa': id_estimativa})
        conexao.commit()
        print("Estimativa excluída com sucesso!")
        logging.info(f"Estimativa com ID {id_estimativa} excluída com sucesso.")
    except Exception as e:
        logging.error(f"Erro ao excluir estimativa de consumo: {e}")
        print("Erro ao excluir estimativa de consumo:", e)
