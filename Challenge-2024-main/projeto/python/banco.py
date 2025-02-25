import cx_Oracle
import datetime as dt
import json
from crud import * 


# Conexão com o banco de dados
def conexao():
    usuario = "rm557825"
    senha = "170506"
    db = "oracle.fiap.com.br:1521/orcl"

    # Criar a conexão
    connection = cx_Oracle.connect(
        user=usuario,
        password=senha,
        dsn=db
    )
    return connection

def menu_principal():
    while True:
        print("\nMenu Principal:")
        print("1. Gerenciar Dados")
        print("2. Sair")
        opcao = input("Escolha uma opção: ")

        if opcao == '1':
            menu_gerenciamento()
        elif opcao == '2':
            print("Saindo...")
            break
        else:
            print("Opção inválida! Tente novamente.")

def menu_gerenciamento():
    while True:
        print("\nMenu de Gerenciamento:")
        print("1. Inserir Dados")
        print("2. Alterar Dados")
        print("3. Excluir Dados")
        print("4. Consultar Dados")
        print("5. Exportar Dados para JSON")
        print("6. Voltar ao Menu Principal")
        opcao = input("Escolha uma opção: ")

        if opcao == '1':
           inserir_dados()
        elif opcao == '2':
            alterar_cliente()
        elif opcao == '3':
            excluir_cliente()
        elif opcao == '4':
            consultar_clientes()
        elif opcao == '5':
            exportar_dados_para_json()
        elif opcao == '6':
            break
        else:
            print("Opção inválida! Tente novamente.")

def exportar_dados_para_json():
    tabela = input("Digite o nome da tabela que deseja exportar (CLIENTES, OFICINAS, etc.): ")
    arquivo_json = input("Digite o nome do arquivo JSON para exportação (ex: dados.json): ")

    exportar_para_json(tabela, arquivo_json)

def exportar_para_json(tabela, arquivo_json):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute(f"SELECT * FROM {tabela}")
        resultados = cur.fetchall()

        # Obtendo os nomes das colunas
        colunas = [col[0] for col in cur.description]

        # Criando uma lista de dicionários para exportação
        dados_list = []
        for row in resultados:
            dados_dict = dict(zip(colunas, row))
            dados_list.append(dados_dict)

        # Exportando para um arquivo JSON
        with open(arquivo_json, 'w') as json_file:
            json.dump(dados_list, json_file, indent=4)

        print(f"Dados exportados para {arquivo_json} com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao exportar os dados para JSON: {e}")
    except Exception as e:
        print(f"Erro: {e}")
    finally:
        cur.close()
        connection.close()

# Funções CRUD para clientes
def inserir_cliente(id_cliente, nome_usuario, nome, email, idade, cpf, telefone, senha):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute(
            "INSERT INTO clientes (id_cliente, nome_usuario, nome, email, idade, cpf, telefone, senha) "
            "VALUES (:1, :2, :3, :4, :5, :6, :7, :8)", 
            (id_cliente, nome_usuario, nome, email, idade, cpf, telefone, senha)
        )
        connection.commit()
        print("Cliente inserido com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao inserir cliente: {e}")
        connection.rollback() 

    finally:
        cur.close()  
        connection.close() 

    return True


def alterar_cliente():
    connection = conexao()
    cur = connection.cursor()

    try:
        # Pergunta o ID do cliente que será alterado
        id_cliente = input("Digite o ID do cliente a ser alterado: ")

        # Verifica se o cliente com o ID existe
        cur.execute("SELECT * FROM CLIENTES WHERE id = :1", (id_cliente,))
        resultado = cur.fetchone()

        if not resultado:
            print("Cliente com o ID especificado não encontrado.")
            return

        # Exibir dados atuais do cliente
        print("Cliente encontrado:")
        print(f"ID: {resultado[0]}, Nome: {resultado[1]}, Email: {resultado[2]}, Idade: {resultado[3]}, CPF: {resultado[4]}, Telefone: {resultado[5]}")

        # Exibir opções de campos para alterar
        print("Selecione o que deseja alterar:")
        print("1. Nome")
        print("2. Email")
        print("3. Idade")
        print("4. CPF")
        print("5. Telefone")
        print("6. Senha")
        opcao = input("Escolha a opção que deseja alterar: ")

        if opcao == '1':
            novo_nome = input("Digite o novo nome: ")
            cur.execute("UPDATE CLIENTES SET nome = :1 WHERE id = :2", (novo_nome, id_cliente))
        
        elif opcao == '2':
            novo_email = input("Digite o novo email: ")
            cur.execute("UPDATE CLIENTES SET email = :1 WHERE id = :2", (novo_email, id_cliente))
        
        elif opcao == '3':
            nova_idade = input("Digite a nova idade: ")
            cur.execute("UPDATE CLIENTES SET idade = :1 WHERE id = :2", (nova_idade, id_cliente))
        
        elif opcao == '4':
            novo_cpf = input("Digite o novo CPF: ")
            cur.execute("UPDATE CLIENTES SET cpf = :1 WHERE id = :2", (novo_cpf, id_cliente))
        
        elif opcao == '5':
            novo_telefone = input("Digite o novo telefone: ")
            cur.execute("UPDATE CLIENTES SET telefone = :1 WHERE id = :2", (novo_telefone, id_cliente))
        
        elif opcao == '6':
            nova_senha = input("Digite a nova senha: ")
            cur.execute("UPDATE CLIENTES SET senha = :1 WHERE id = :2", (nova_senha, id_cliente))
        
        else:
            print("Opção inválida! Tente novamente.")
            return

        # Confirma a alteração
        connection.commit()
        print("Cliente alterado com sucesso!")

    except ValueError:
        print("Erro: O ID do cliente deve ser um número inteiro.")
    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao atualizar o banco de dados: {e}")
    finally:
        cur.close()
        connection.close()




def excluir_cliente(id_cliente):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("DELETE FROM clientes WHERE id_cliente = :1", (id_cliente,))
        connection.commit()

        if cur.rowcount > 0:
            print(f"Cliente com ID {id_cliente} excluído com sucesso!")
        else:
            print(f"Nenhum cliente encontrado com o ID {id_cliente}.")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao excluir cliente no banco de dados: {e}")
    finally:
        cur.close()
        connection.close()

        connection.close()


def consultar_clientes():
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("SELECT * FROM CLIENTES")
        resultados = cur.fetchall()

        colunas = [col[0] for col in cur.description]
        print("\nVeículos cadastrados:")
        print(f"{' | '.join(colunas)}")  
        print("-" * 50)
        
        for row in resultados:
            print(" | ".join(str(item).ljust(20) for item in row))  

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao consultar clientes no banco de dados: {e}")
    
    finally:
        cur.close()  
        connection.close()  

# Funções CRUD para veículos
def inserir_veiculo(id_veiculo, nome_cliente, modelo, ano, placa, descricao_problema, data_entrada):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute(
            "INSERT INTO veiculos (id_veiculo, nome_cliente, modelo, ano, placa, descricao_problema, data_entrada) "
            "VALUES (:1, :2, :3, :4, :5, :6, :7)", 
            (id_veiculo, nome_cliente, modelo, ano, placa, descricao_problema, data_entrada)
        )
        connection.commit()
        print("Veículo inserido com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao inserir veículo: {e}")
        connection.rollback()  

    finally:
        cur.close()  
        connection.close()  

    return True


def alterar_veiculo():
    connection = conexao()
    cur = connection.cursor()

    try:
        # Pergunta o ID do veículo que será alterado
        id_veiculo = input("Digite o ID do veículo a ser alterado: ")

        # Verifica se o veículo com o ID existe
        cur.execute("SELECT * FROM VEICULOS WHERE id_veiculo = :1", (id_veiculo,))
        resultado = cur.fetchone()

        if not resultado:
            print("Veículo com o ID especificado não encontrado.")
            return

        # Exibir dados atuais do veículo
        print("Veículo encontrado:")
        print(f"ID: {resultado[0]}, Modelo: {resultado[1]}, Ano: {resultado[2]}, Placa: {resultado[3]}, Descrição do Problema: {resultado[4]}")

        # Exibir opções de campos para alterar
        print("Escolha o que deseja alterar:")
        print("1. Modelo")
        print("2. Ano")
        print("3. Placa")
        print("4. Descrição do Problema")
        
        opcao = input("Digite o número da opção desejada: ")

        if opcao == '1':
            novo_modelo = input("Digite o novo modelo: ")
            cur.execute("UPDATE VEICULOS SET modelo = :1 WHERE id_veiculo = :2", (novo_modelo, id_veiculo))

        elif opcao == '2':
            novo_ano = input("Digite o novo ano: ")
            cur.execute("UPDATE VEICULOS SET ano = :1 WHERE id_veiculo = :2", (novo_ano, id_veiculo))

        elif opcao == '3':
            nova_placa = input("Digite a nova placa: ")
            cur.execute("UPDATE VEICULOS SET placa = :1 WHERE id_veiculo = :2", (nova_placa, id_veiculo))

        elif opcao == '4':
            nova_descricao_problema = input("Digite a nova descrição do problema: ")
            cur.execute("UPDATE VEICULOS SET descricao_problema = :1 WHERE id_veiculo = :2", (nova_descricao_problema, id_veiculo))

        else:
            print("Opção inválida! Tente novamente.")
            return

        # Confirma a alteração
        connection.commit()
        print("Veículo alterado com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao alterar veículo: {e}")
    
    finally:
        cur.close()
        connection.close()

def excluir_veiculo(id_veiculo):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("DELETE FROM veiculos WHERE id_veiculo = :1", (id_veiculo,))
        connection.commit()

        if cur.rowcount > 0:
            print(f"Veiculo com ID {id_veiculo} excluído com sucesso!")
        else:
            print(f"Nenhum veiculo encontrado com o ID {id_veiculo}.")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao excluir veiculo no banco de dados: {e}")
    finally:
        cur.close()
        connection.close()

        connection.close()



def consultar_veiculos():
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("SELECT * FROM veiculos")
        resultados = cur.fetchall()

        colunas = [col[0] for col in cur.description]
        print("\nVeículos cadastrados:")
        print(f"{' | '.join(colunas)}")  
        print("-" * 50)
        
        for row in resultados:
            print(" | ".join(str(item).ljust(20) for item in row))  

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao consultar veículos no banco de dados: {e}")
    
    finally:
        cur.close()  
        connection.close()  



# Funções CRUD para oficinas
def inserir_oficina(id_oficina, cep, endereco, nome_oficina, telefone_oficina):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute(
            "INSERT INTO oficinas (id_oficina, cep, endereco, nome_oficina, telefone_oficina) "
            "VALUES (:1, :2, :3, :4, :5)", 
            (id_oficina, cep, endereco, nome_oficina, telefone_oficina)
        )
        connection.commit()
        print("Oficina inserida com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao inserir oficina: {e}")
        connection.rollback()  

    finally:
        cur.close() 
        connection.close()  

    return True

def alterar_oficina():
    connection = conexao()
    cur = connection.cursor()

    try:
        # Pergunta o ID da oficina que será alterada
        id_oficina = input("Digite o ID da oficina a ser alterada: ")

        # Verifica se a oficina com o ID existe
        cur.execute("SELECT * FROM OFICINAS WHERE id = :1", (id_oficina,))
        resultado = cur.fetchone()

        if not resultado:
            print("Oficina com o ID especificado não encontrada.")
            return

        # Exibir dados atuais da oficina
        print("Oficina encontrada:")
        print(f"ID: {resultado[0]}, Nome: {resultado[1]}, Endereço: {resultado[2]}, Telefone: {resultado[3]}")

        # Exibir opções de campos para alterar
        print("Quais dados você deseja alterar?")
        print("1. Nome da Oficina")
        print("2. Endereço")
        print("3. Telefone")
        
        opcao = input("Escolha a opção (1-3): ")

        if opcao == '1':
            novo_nome = input("Digite o novo nome da oficina: ")
            cur.execute("UPDATE OFICINAS SET nome_oficina = :1 WHERE id = :2", (novo_nome, id_oficina))
        elif opcao == '2':
            novo_endereco = input("Digite o novo endereço da oficina: ")
            cur.execute("UPDATE OFICINAS SET endereco = :1 WHERE id = :2", (novo_endereco, id_oficina))
        elif opcao == '3':
            novo_telefone = input("Digite o novo telefone da oficina: ")
            cur.execute("UPDATE OFICINAS SET telefone_oficina = :1 WHERE id = :2", (novo_telefone, id_oficina))
        else:
            print("Opção inválida! Nenhuma alteração foi feita.")
            return

        # Confirma a alteração
        connection.commit()
        print("Oficina alterada com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao atualizar o banco de dados: {e}")
    finally:
        cur.close()
        connection.close()




def excluir_oficina(id_oficina):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("DELETE FROM oficinas WHERE id_oficina = :1", (id_oficina,))
        connection.commit()

        if cur.rowcount > 0:
            print(f"Oficina com ID {id_oficina} excluído com sucesso!")
        else:
            print(f"Nenhuma oficina encontrada com o ID {id_oficina}.")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao excluir oficina no banco de dados: {e}")
    finally:
        cur.close()
        connection.close()


def consultar_oficinas():
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("SELECT * FROM OFICINAS")
        resultados = cur.fetchall()

        colunas = [col[0] for col in cur.description]
        print("\nVeículos cadastrados:")
        print(f"{' | '.join(colunas)}")  
        print("-" * 50)
        
        for row in resultados:
            print(" | ".join(str(item).ljust(20) for item in row))  

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao consultar oficinas no banco de dados: {e}")
    
    finally:
        cur.close()  
        connection.close()  


# Funções CRUD para funcionários
def inserir_funcionario(id_funcionario, nome_funcionario, cargo, data_contrata, salario, setor, tempo_empresa):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute(
            "INSERT INTO FUNCIONARIOS (id_funcionario, nome_funcionario, cargo, data_contrata, salario, setor, tempo_empresa) "
            "VALUES (:1, :2, :3, :4, :5, :6, :7)", 
            (id_funcionario, nome_funcionario, cargo, data_contrata, salario, setor, tempo_empresa)
        )
        connection.commit()
        print("Funcionário inserido com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao inserir funcionário: {e}")
        connection.rollback()  

    finally:
        cur.close()  
        connection.close()  


def alterar_funcionario():
    connection = conexao()
    cur = connection.cursor()

    try:
        id_funcionario = input("Digite o ID do funcionário a ser alterado: ")

        # Verificar se o funcionário existe antes de tentar atualizar
        cur.execute("SELECT * FROM FUNCIONARIOS WHERE id = :1", (id_funcionario,))
        funcionario = cur.fetchone()

        if funcionario is None:
            print("Funcionário não encontrado.")
            return

        print("Funcionário encontrado:")
        print(f"ID: {funcionario[0]}, Nome: {funcionario[1]}, Cargo: {funcionario[2]}, Salário: {funcionario[3]}, Setor: {funcionario[4]}, Tempo de Empresa: {funcionario[5]}")

        print("Quais dados você deseja alterar?")
        print("1. Nome do Funcionário")
        print("2. Cargo")
        print("3. Salário")
        print("4. Setor")
        print("5. Tempo de Empresa")

        opcao = input("Escolha a opção (1-5): ")

        if opcao == '1':
            novo_nome = input("Digite o novo nome do funcionário: ")
            cur.execute("UPDATE FUNCIONARIOS SET nome_funcionario = :1 WHERE id = :2", (novo_nome, id_funcionario))
        
        elif opcao == '2':
            novo_cargo = input("Digite o novo cargo do funcionário: ")
            cur.execute("UPDATE FUNCIONARIOS SET cargo = :1 WHERE id = :2", (novo_cargo, id_funcionario))
        
        elif opcao == '3':
            novo_salario = input("Digite o novo salário do funcionário: ")
            cur.execute("UPDATE FUNCIONARIOS SET salario = :1 WHERE id = :2", (novo_salario, id_funcionario))
        
        elif opcao == '4':
            novo_setor = input("Digite o novo setor do funcionário: ")
            cur.execute("UPDATE FUNCIONARIOS SET setor = :1 WHERE id = :2", (novo_setor, id_funcionario))
        
        elif opcao == '5':
            novo_tempo_empresa = input("Digite o novo tempo de empresa do funcionário: ")
            cur.execute("UPDATE FUNCIONARIOS SET tempo_empresa = :1 WHERE id = :2", (novo_tempo_empresa, id_funcionario))
        
        else:
            print("Opção inválida! Nenhuma alteração foi feita.")
            return

        connection.commit()
        print("Funcionário alterado com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao atualizar o banco de dados: {e}")
    finally:
        cur.close()
        connection.close()



def excluir_funcionario(id_funcionario):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("DELETE FROM veiculos WHERE id_funcionario = :1", (id_funcionario,))
        connection.commit()

        if cur.rowcount > 0:
            print(f"Funcionário com ID {id_funcionario} excluído com sucesso!")
        else:
            print(f"Nenhum funcionário encontrado com o ID {id_funcionario}.")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao excluir funcionário no banco de dados: {e}")
    finally:
        cur.close()
        connection.close()



def consultar_funcionarios():
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("SELECT * FROM FUNCIONARIO")
        resultados = cur.fetchall()

        colunas = [col[0] for col in cur.description]
        print("\nFuncionários cadastrados:")
        print(f"{' | '.join(colunas)}")  
        print("-" * 50)
        
        for row in resultados:
            print(" | ".join(str(item).ljust(20) for item in row))  

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao consultar funcionario no banco de dados: {e}")
    
    finally:
        cur.close()  
        connection.close()  

# Funções CRUD para estoque
def inserir_estoque(id_estoque,nome_peca, quantidade, preco, fornecedor):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute(
            "INSERT INTO FUNCIONARIOS (id_estoque,nome_peca, quantidade, preco, fornecedor) "
            "VALUES (:1, :2, :3, :4, :5)", 
            (id_estoque,nome_peca, quantidade, preco, fornecedor)
        )
        connection.commit()
        print("Estoque inserido com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao inserir estoque: {e}")
        connection.rollback()  

    finally:
        cur.close()  
        connection.close() 

def alterar_estoque():
    connection = conexao()
    cur = connection.cursor()

    try:
        # Pergunta o ID do produto que será alterado
        id_produto = input("Digite o ID do produto a ser alterado: ")

        # Verifica se o produto com o ID existe
        cur.execute("SELECT * FROM ESTOQUE WHERE id = :1", (id_produto,))
        resultado = cur.fetchone()

        if not resultado:
            print("Produto com o ID especificado não encontrado.")
            return

    
        print("Produto encontrado:")
        print(f"ID: {resultado[0]}, Nome: {resultado[1]}, Quantidade: {resultado[2]}, Valor Unitário: {resultado[3]}")

        
        print("Selecione o que deseja alterar:")
        print("1. Nome do Produto")
        print("2. Quantidade")
        print("3. Valor Unitário")
        opcao = input("Escolha a opção que deseja alterar: ")

        if opcao == '1':
            novo_nome_produto = input("Digite o novo nome do produto: ")
            cur.execute("UPDATE ESTOQUE SET nome_produto = :1 WHERE id = :2", (novo_nome_produto, id_produto))
        
        elif opcao == '2':
            nova_quantidade = input("Digite a nova quantidade: ")
            cur.execute("UPDATE ESTOQUE SET quantidade = :1 WHERE id = :2", (nova_quantidade, id_produto))
        
        elif opcao == '3':
            novo_valor_unitario = input("Digite o novo valor unitário: ")
            cur.execute("UPDATE ESTOQUE SET valor_unitario = :1 WHERE id = :2", (novo_valor_unitario, id_produto))
        
        else:
            print("Opção inválida! Tente novamente.")
            return

        # Confirma a alteração
        connection.commit()
        print("Produto alterado no estoque com sucesso!")

    except ValueError:
        print("Erro: O ID do produto deve ser um número inteiro.")
    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao atualizar o banco de dados: {e}")
    finally:
        cur.close() 
        connection.close()  



def excluir_estoque(id_estoque):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("DELETE FROM veiculos WHERE ID_ESTOQUE = :1", (id_estoque,))
        connection.commit()

        if cur.rowcount > 0:
            print(f"Estoque com ID {id_estoque} excluído com sucesso!")
        else:
            print(f"Nenhum estoque encontrado com o ID {id_estoque}.")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao excluir estoque no banco de dados: {e}")
    finally:
        cur.close()
        connection.close()


def consultar_estoque():
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("SELECT * FROM ESTOQUE")
        resultados = cur.fetchall()

        colunas = [col[0] for col in cur.description]
        print("\Estoque cadastrados:")
        print(f"{' | '.join(colunas)}")  
        print("-" * 50)
        
        for row in resultados:
            print(" | ".join(str(item).ljust(20) for item in row))  

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao consultar Estoque no banco de dados: {e}")
    
    finally:
        cur.close()  
        connection.close()  


# Funções CRUD para serviços agendados
def inserir_servico(id_servico, nome_cliente, nome_funcionario, data_agendamento, veiculo_placa, descricao_servico):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute(
            "INSERT INTO FUNCIONARIOS (id_servico, nome_cliente, nome_funcionario, data_agendamento, veiculo_placa, descricao_servico) "
            "VALUES (:1, :2, :3, :4, :5, :6)", 
            (id_servico, nome_cliente, nome_funcionario, data_agendamento, veiculo_placa, descricao_servico)
        )
        connection.commit()
        print("Serviço inserido com sucesso!")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao inserir serviço: {e}")
        connection.rollback()  

    finally:
        cur.close()  
        connection.close()  


def alterar_servico():
    connection = conexao()
    cur = connection.cursor()

    try:
        # Pergunta o ID do serviço que será alterado
        id_servico = input("Digite o ID do serviço a ser alterado: ")

        # Verifica se o ID do serviço existe
        cur.execute("SELECT * FROM SERVIÇOS_AGENDADOS WHERE id = :1", (id_servico,))
        resultado = cur.fetchone()

        if resultado is None:
            print("Serviço não encontrado!")
            return

        # Exibir dados atuais do serviço
        print("Serviço encontrado:")
        print(f"ID: {resultado[0]}, Descrição: {resultado[1]}")  

        # Exibir opções de campos para alterar
        print("Selecione o que deseja alterar:")
        print("1. Descrição do Serviço")
        opcao = input("Escolha a opção que deseja alterar: ")

        if opcao == '1':
            nova_descricao = input("Digite a nova descrição do serviço: ")
            
            cur.execute("UPDATE SERVIÇOS_AGENDADOS SET descricao_servico = :1 WHERE id = :2", (nova_descricao, id_servico))
            connection.commit()
            print("Serviço alterado com sucesso!")
        else:
            print("Opção inválida! Tente novamente.")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao atualizar o banco de dados: {e}")
    finally:
        cur.close()  
        connection.close()  



def excluir_servico(id_servico):
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("DELETE FROM SERVICOS_AGENDADOS WHERE ID_SERVICO = :1", (id_servico,))
        connection.commit()

        if cur.rowcount > 0:
            print(f"Serviço com ID {id_servico} excluído com sucesso!")
        else:
            print(f"Nenhum serviço encontrado com o ID {id_servico}.")

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao excluir serviço no banco de dados: {e}")
    finally:
        cur.close()
        connection.close()


def consultar_servicos():
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("SELECT * FROM SERVICOS_AGENDADOS")
        resultados = cur.fetchall()

        colunas = [col[0] for col in cur.description]
        print("\Serviços cadastrados:")
        print(f"{' | '.join(colunas)}")  
        print("-" * 50)
        
        for row in resultados:
            print(" | ".join(str(item).ljust(20) for item in row))  

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao consultar Estoque no banco de dados: {e}")
    
    finally:
        cur.close()  
        connection.close()  


# Função principal para inserir, alterar, excluir e consultar dados
def inserir_dados():
    print("1. Inserir Cliente")
    print("2. Inserir Veículo")
    print("3. Inserir Oficina")
    print("4. Inserir Funcionário")
    print("5. Inserir Produto no Estoque")
    print("6. Inserir Serviço Agendado")
    opcao = input("Escolha uma opção: ")

    if opcao == '1':
        inserir_cliente()
    elif opcao == '2':
        inserir_veiculo()
    elif opcao == '3':
        inserir_oficina()
    elif opcao == '4':
        inserir_funcionario()
    elif opcao == '5':
        inserir_estoque()
    elif opcao == '6':
        inserir_servico()
    else:
        print("Opção inválida! Tente novamente.")

def alterar_dados():
    print("1. Alterar Cliente")
    print("2. Alterar Veículo")
    print("3. Alterar Oficina")
    print("4. Alterar Funcionário")
    print("5. Alterar Produto no Estoque")
    print("6. Alterar Serviço Agendado")
    opcao = input("Escolha uma opção: ")

    if opcao == '1':
        alterar_cliente()
    elif opcao == '2':
        alterar_veiculo()
    elif opcao == '3':
        alterar_oficina()
    elif opcao == '4':
        alterar_funcionario()
    elif opcao == '5':
        alterar_estoque()
    elif opcao == '6':
        alterar_servico()
    else:
        print("Opção inválida! Tente novamente.")

def excluir_dados():
    print("1. Excluir Cliente")
    print("2. Excluir Veículo")
    print("3. Excluir Oficina")
    print("4. Excluir Funcionário")
    print("5. Excluir Produto do Estoque")
    print("6. Excluir Serviço Agendado")
    opcao = input("Escolha uma opção: ")

    if opcao == '1':
        excluir_cliente()
    elif opcao == '2':
        excluir_veiculo()
    elif opcao == '3':
        excluir_oficina()
    elif opcao == '4':
        excluir_funcionario()
    elif opcao == '5':
        excluir_estoque()
    elif opcao == '6':
        excluir_servico()
    else:
        print("Opção inválida! Tente novamente.")

def consultar_dados():
    print("1. Consultar Clientes")
    print("2. Consultar Veículos")
    print("3. Consultar Oficinas")
    print("4. Consultar Funcionários")
    print("5. Consultar Produtos em Estoque")
    print("6. Consultar Serviços Agendados")
    opcao = input("Escolha uma opção: ")

    if opcao == '1':
        consultar_clientes()
    elif opcao == '2':
        consultar_veiculos()
    elif opcao == '3':
        consultar_oficinas()
    elif opcao == '4':
        consultar_funcionarios()
    elif opcao == '5':
        consultar_estoque()
    elif opcao == '6':
        consultar_servicos()
    else:
        print("Opção inválida! Tente novamente.")

# Iniciar o programa
if __name__ == "__main__":
    menu_principal()
