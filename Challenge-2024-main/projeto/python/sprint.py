import requests
import random
import string
import re
from crud import *  
from banco import*
from api import *

# Função para consultar CEP e obter o endereço
def consulta_cep(cep):
    
    url = f'https://brasilapi.com.br/api/cep/v1/{cep}'
    response = requests.get(url)
    
    if response.status_code == 200:
        return response.json()
    else:
        return None


# Função para gerar uma senha forte
def gerar_sugestao_senha():
    caracteres = string.ascii_letters + string.digits + string.punctuation
    senha_sugerida = ''.join(random.choice(caracteres) for i in range(12))
    return senha_sugerida

# Função para exibir subtítulos com espaçamento
def exibir_subtitulos(texto):
    print(texto)
    print()

# Validação de email
def validar_email(email):
    return re.match(r"[^@]+@[^@]+\.[^@]+", email) is not None

# Validação de telefone
def validar_telefone(telefone):
    return len(telefone) >= 10 and telefone.isdigit()

# Validação de idade
def validar_idade(idade):
    return 0 <= idade <= 120

# Validação de senha
def validar_senha(senha):
    return (len(senha) >= 8 and 
            any(char.isdigit() for char in senha) and 
            any(char.isupper() for char in senha) and 
            any(char in string.punctuation for char in senha))

# Validação de CPF
def validar_cpf(cpf):
    return len(cpf) == 11 and cpf.isdigit()

# Validação de placa
def validar_placa(placa):
    return re.match(r"^[A-Z]{3}-\d{4}$", placa) is not None

# Função para login
def login():
    exibir_subtitulos("\n🔒 **Login**\n")
    usuario = input("Digite seu nome de usuário: ")
    senha = input("Digite sua senha: ")

    # Consultar clientes no banco de dados para verificar login
    connection = conexao()
    cur = connection.cursor()

    try:
        cur.execute("SELECT nome, senha FROM CLIENTES WHERE nome_usuario = :1", (usuario,))
        resultado = cur.fetchone()

        if resultado and resultado[1] == senha:
            exibir_subtitulos(f"Bem-vindo, {resultado[0]}!")
            return True
        else:
            exibir_subtitulos("⚠️ Usuário ou senha inválidos.")
            return False

    except cx_Oracle.DatabaseError as e:
        print(f"Erro ao consultar o banco de dados: {e}")
        return False

    finally:
        cur.close()  
        connection.close()


# Função para cadastrar clientes
def cadastrar_cliente():
    exibir_subtitulos("\n📋 **Cadastro de Clientes**\n")
    
    while True:
        try:
            cpf = input("Digite o CPF do cliente (11 dígitos): ")
            if not validar_cpf(cpf):
                exibir_subtitulos("⚠️ O CPF deve ter exatamente 11 dígitos numéricos.")
                continue
            
            if any(cliente['CPF'] == cpf for cliente in clientes):
                exibir_subtitulos("⚠️ Este CPF já está cadastrado.")
                continue

            nome = input("Nome completo: ")
            email = input("Email: ")
            if not validar_email(email):
                exibir_subtitulos("⚠️ Email inválido. Tente novamente.")
                continue

            idade = int(input("Idade: "))
            if not validar_idade(idade):
                exibir_subtitulos("⚠️ Idade inválida. Deve estar entre 0 e 120 anos.")
                continue

            telefone = input("Número de telefone: ")
            if not validar_telefone(telefone):
                exibir_subtitulos("⚠️ Telefone inválido. Deve ter pelo menos 10 dígitos.")
                continue
            
            nome_usuario = input("Escolha um nome de usuário: ")

            if any(cliente['Usuário'] == nome_usuario for cliente in clientes):
                exibir_subtitulos("⚠️ Este nome de usuário já está em uso.")
                continue

            # Pergunta se o usuário quer uma sugestão de senha forte
            senha = input("Crie uma senha forte ou digite 's' para sugerir uma: ")
            if senha.lower() == 's':
                senha = gerar_sugestao_senha()
                print(f"Sugestão de senha: {senha}")
            senha_confirmada = input("Confirme a senha: ")

            if not validar_senha(senha):
                exibir_subtitulos("⚠️ A senha deve ter pelo menos 8 caracteres, incluindo letras maiúsculas, números e caracteres especiais.")
                continue
            
            if senha != senha_confirmada:
                exibir_subtitulos("⚠️ As senhas não coincidem.")
                continue

            inserir_cliente(nome_usuario, nome, email, idade, cpf, telefone, senha)
            exibir_subtitulos(f"\n🎉 Cliente cadastrado com sucesso!\n")

            break
        except ValueError as ve:
            exibir_subtitulos(f"⚠️ Erro: {ve}")
        except Exception as e:
            exibir_subtitulos(f"⚠️ Ocorreu um erro inesperado: {e}")

# Função para cadastrar veículos usando a função CRUD
def cadastrar_veiculo():
    exibir_subtitulos("\n🚗 **Cadastro de Veículos**\n")
    try:
        modelo = input("Modelo do veículo: ")

        while True:
            placa = input("Placa do veículo (formato: AAA-0000): ")
            if not validar_placa(placa):
                exibir_subtitulos("⚠️ A placa deve estar no formato AAA-0000.")
                continue
            elif any(veiculo['Placa'] == placa for veiculo in veiculos):
                exibir_subtitulos("⚠️ Esta placa já está cadastrada.")
                continue
            else:
                break

        cor = input("Cor do veículo: ")
        veiculo = {"Modelo": modelo, "Placa": placa, "Cor": cor}
        veiculos.append(veiculo)
        exibir_subtitulos(f"\n🎉 Veículo cadastrado com sucesso!\n{veiculo}")
    except Exception as e:
        exibir_subtitulos(f"⚠️ Ocorreu um erro inesperado: {e}")

# Função para cadastrar oficinas usando a função CRUD
def cadastrar_oficina():
    exibir_subtitulos("\n🔧 **Cadastro de Oficinas**\n")
    
    while True:
        cep = input("CEP: ")
        endereco_info = consulta_cep(cep)
        if endereco_info:
            endereco = f"{endereco_info['street']}, {endereco_info['neighborhood']}, {endereco_info['city']} - {endereco_info['state']}"
            print(f"Endereço encontrado: {endereco}")
            break 
        else:
            print("⚠️ CEP inválido ou não encontrado. Por favor, insira o CEP novamente.")
    
    try:
        nome_oficina = input("Nome da Oficina: ")
        telefone_oficina = input("Número de telefone: ")

        if not validar_telefone(telefone_oficina):
            exibir_subtitulos("⚠️ Telefone inválido. Deve ter pelo menos 10 dígitos.")
            return

        oficina = {
            "CEP": cep,
            "Endereço": endereco,
            "Nome da Oficina": nome_oficina,
            "Telefone": telefone_oficina
        }
        oficinas.append(oficina)
        exibir_subtitulos(f"\n🎉 Oficina cadastrada com sucesso!\n{oficina}")
    except Exception as e:
        exibir_subtitulos(f"⚠️ Ocorreu um erro inesperado: {e}")


# Função para cadastrar funcionários usando a função CRUD
def cadastrar_funcionario():
    exibir_subtitulos("\n👨‍💼 **Cadastro de Funcionários**\n")
    try:
        nome_funcionario = input("Nome completo: ")
        cargo = input("Cargo: ")
        data_contrata = input("Data de contratação (DD/MM/AAAA): ")
        salario = float(input("Salário (R$): "))
        setor = input("Setor: ")
        tempo_empresa = int(input("Tempo de empresa (em anos): "))

        funcionario = {
            "Nome": nome_funcionario,
            "Cargo": cargo,
            "Data de contratação": data_contrata,
            "Salário": salario,
            "Setor": setor,
            "Tempo de empresa": tempo_empresa
        }
        funcionarios.append(funcionario)
        exibir_subtitulos(f"\n🎉 Funcionário cadastrado com sucesso!\n{funcionario}")
    except ValueError as ve:
        exibir_subtitulos(f"⚠️ Erro de valor: {ve}")
    except Exception as e:
        exibir_subtitulos(f"⚠️ Ocorreu um erro inesperado: {e}")

# Função para gerenciar o estoque de peças usando a função CRUD
def gerenciar_estoque():
    exibir_subtitulos("\n📦 **Gerenciamento de Estoque**\n")
    while True:
        print("1. Adicionar peça")
        print("2. Remover peça")
        print("3. Listar peças")
        print("4. Voltar ao menu principal")
        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            try:
                nome_peca = input("Nome da peça: ")
                quantidade = int(input("Quantidade: "))
                preco = float(input("Preço unitário (R$): "))
                peca = {"Nome": nome_peca, "Quantidade": quantidade, "Preço": preco}
                estoque.append(peca)
                exibir_subtitulos(f"\n🎉 Peça adicionada ao estoque com sucesso!\n{peca}")
            except ValueError as ve:
                exibir_subtitulos(f"⚠️ Erro de valor: {ve}")

        elif opcao == "2":
            try:
                nome_peca = input("Nome da peça para remover: ")
                for peca in estoque:
                    if peca["Nome"].lower() == nome_peca.lower():
                        estoque.remove(peca)
                        exibir_subtitulos(f"\n🗑️ Peça removida do estoque com sucesso!\n{peca}")
                        break
                else:
                    exibir_subtitulos("⚠️ Peça não encontrada.")
            except Exception as e:
                exibir_subtitulos(f"⚠️ Ocorreu um erro inesperado: {e}")

        elif opcao == "3":
            if estoque:
                exibir_subtitulos("📦 Lista de Peças em Estoque:")
                for peca in estoque:
                    print(f"- {peca['Nome']} (Quantidade: {peca['Quantidade']}, Preço: R$ {peca['Preço']})")
            else:
                exibir_subtitulos("⚠️ Não há peças no estoque.")

        elif opcao == "4":
            break

        else:
            exibir_subtitulos("⚠️ Opção inválida. Tente novamente.")

# Função para agendar serviços
def agendar_servico():
    exibir_subtitulos("\n📅 **Agendar Serviços**\n")
    try:
        cliente_nome = input("Nome do cliente: ")
        veiculo_modelo = input("Modelo do veículo: ")
        data_servico = input("Data do serviço (DD/MM/AAAA): ")
        descricao_servico = input("Descrição do serviço: ")

        servico = {
            "Cliente": cliente_nome,
            "Veículo": veiculo_modelo,
            "Data": data_servico,
            "Descrição": descricao_servico
        }
        servicos_agendados.append(servico)
        exibir_subtitulos(f"\n🎉 Serviço agendado com sucesso!\n{servico}")
    except Exception as e:
        exibir_subtitulos(f"⚠️ Ocorreu um erro inesperado: {e}")

# Função principal do menu
def main():
    while True:
        print("\n🌟 **Menu Principal** 🌟")
        print("=" * 30)
        print("1. Login")
        print("2. Cadastro de Clientes")
        print("3. Cadastro de Veículos")
        print("4. Cadastro de Oficinas")
        print("5. Cadastro de Funcionários")
        print("6. Gerenciar Estoque")
        print("7. Agendar Serviços")
        print("8. Mecânico Virtual")  
        print("9. Sair")
        print("=" * 30)
        
        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            login()
        elif opcao == "2":
            cadastrar_cliente()
        elif opcao == "3":
            cadastrar_veiculo()
        elif opcao == "4":
            cadastrar_oficina()
        elif opcao == "5":
            cadastrar_funcionario()
        elif opcao == "6":
            gerenciar_estoque()
        elif opcao == "7":
            agendar_servico()
        elif opcao == "8":
            mecanico()  # Chama a função do mecânico virtual
        elif opcao == "9":
            print("👋 Até logo!")
            break
        else:
            print("⚠️ Opção inválida. Tente novamente.")

if __name__ == "__main__":
    main()
