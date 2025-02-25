import datetime as dt
from banco import *

# Listas para armazenar os dados
clientes = []
veiculos = []
oficinas = []
funcionarios = []
estoque = []
servicos_agendados = []

# Funções CRUD para clientes
def criar_cliente(id_cliente, nome_usuario, nome, email, idade, cpf, telefone, senha):
    cliente = {
        "id_cliente": id_cliente,
        "Nome_usuario": nome_usuario,
        "Nome": nome,
        "Email": email,
        "Idade": idade,
        "Cpf": cpf,
        "Telefone": telefone,
        "Senha": senha
    }
    clientes.append(cliente)
    return inserir_cliente(id_cliente, nome_usuario, nome, email, idade, cpf, telefone, senha)

def ler_cliente(indice=-1):
    if 0 <= indice < len(clientes):
        return clientes[indice]
    else:
        return clientes

def atualizar_cliente(indice, nome_usuario=None, nome=None, email=None, idade=None, cpf=None, telefone=None):
    if 0 <= indice < len(clientes):
        if nome_usuario is not None:
            clientes[indice]["Usuário"] = nome_usuario
        if nome is not None:
            clientes[indice]["Nome"] = nome
        if email is not None:
            clientes[indice]["Email"] = email
        if idade is not None:
            clientes[indice]["Idade"] = idade
        if cpf is not None:
            clientes[indice]["CPF"] = cpf
        if telefone is not None:
            clientes[indice]["Telefone"] = telefone
        return True
    return False

def deletar_cliente(indice):
    if 0 <= indice < len(clientes):
        clientes.pop(indice)
        return True
    return False

# Funções CRUD para veículos
def criar_veiculo(id_veiculo, nome_cliente, modelo, ano, placa, descricao_problema):
    data = dt.datetime.now()
    veiculo = {
        "ID Veículo": id_veiculo,
        "Nome": nome_cliente,
        "Data": data,
        "Modelo": modelo,
        "Ano": ano,
        "Placa": placa,
        "Descrição problema": descricao_problema
    }
    veiculos.append(veiculo)
    return inserir_veiculo(id_veiculo, nome_cliente, modelo, ano, placa, descricao_problema)

def ler_veiculo(indice=-1):
    if 0 <= indice < len(veiculos):
        return veiculos[indice]
    else:
        return veiculos

def atualizar_veiculo(indice, nome_cliente=None, modelo=None, ano=None, placa=None, descricao_problema=None, desc_reparo=None):
    if 0 <= indice < len(veiculos):
        if nome_cliente is not None:
            veiculos[indice]["Nome"] = nome_cliente
        if modelo is not None:
            veiculos[indice]["Modelo"] = modelo
        if ano is not None:
            veiculos[indice]["Ano"] = ano
        if placa is not None:
            veiculos[indice]["Placa"] = placa
        if descricao_problema is not None:
            veiculos[indice]["Descricao problema"] = descricao_problema
        if desc_reparo is not None:
            veiculos[indice]["Desc_reparo"] = desc_reparo
            veiculos[indice]["Saida"] = dt.datetime.now()
        return True
    return False

def deletar_veiculo(indice):
    if 0 <= indice < len(veiculos):
        veiculos.pop(indice)
        return True
    return False

# Funções CRUD para oficinas
def criar_oficina(id_oficina,cep, endereco, nome_oficina, telefone_oficina):
    oficina = {
        "Id_oficina":id_oficina,
        "CEP": cep,
        "Endereço": endereco,
        "Nome da Oficina": nome_oficina,
        "Telefone": telefone_oficina
    }
    oficinas.append(oficina)
    return inserir_oficina(id_oficina,cep, endereco, nome_oficina, telefone_oficina)

def ler_oficina(indice=-1):
    if 0 <= indice < len(oficinas):
        return oficinas[indice]
    else:
        return oficinas

def atualizar_oficina(indice, cep=None, endereco=None, nome_oficina=None, telefone_oficina=None):
    if 0 <= indice < len(oficinas):
        if cep is not None:
            oficinas[indice]["CEP"] = cep
        if endereco is not None:
            oficinas[indice]["Endereço"] = endereco
        if nome_oficina is not None:
            oficinas[indice]["Nome da Oficina"] = nome_oficina
        if telefone_oficina is not None:
            oficinas[indice]["Telefone"] = telefone_oficina
        return True
    return False

def deletar_oficina(indice):
    if 0 <= indice < len(oficinas):
        oficinas.pop(indice)
        return True
    return False

# Funções CRUD para funcionários
def criar_funcionario(id_funcionario, nome_funcionario, cargo, data_contrata, salario, setor, tempo_empresa):
    funcionario = {
        "ID Funcionario":id_funcionario,
        "Nome": nome_funcionario,
        "Cargo": cargo,
        "Data de contratação": data_contrata,
        "Salário": salario,
        "Setor": setor,
        "Tempo de empresa": tempo_empresa
    }
    funcionarios.append(funcionario)
    return inserir_funcionario(id_funcionario, nome_funcionario, cargo, data_contrata, salario, setor, tempo_empresa)

def ler_funcionario(indice=-1):
    if 0 <= indice < len(funcionarios):
        return funcionarios[indice]
    else:
        return funcionarios

def atualizar_funcionario(indice, nome_funcionario=None, cargo=None, data_contrata=None, salario=None, setor=None, tempo_empresa=None):
    if 0 <= indice < len(funcionarios):
        if nome_funcionario is not None:
            funcionarios[indice]["Nome"] = nome_funcionario
        if cargo is not None:
            funcionarios[indice]["Cargo"] = cargo
        if data_contrata is not None:
            funcionarios[indice]["Data de contratação"] = data_contrata
        if salario is not None:
            funcionarios[indice]["Salário"] = salario
        if setor is not None:
            funcionarios[indice]["Setor"] = setor
        if tempo_empresa is not None:
            funcionarios[indice]["Tempo de empresa"] = tempo_empresa
        return True
    return False

def deletar_funcionario(indice):
    if 0 <= indice < len(funcionarios):
        funcionarios.pop(indice)
        return True
    return False

# Funções para estoque
def adicionar_peca_estoque(id_estoque,nome_peca, quantidade, preco, fornecedor):
    peca = {
        "ID Estoque":id_estoque,
        "Nome da Peça": nome_peca,
        "Quantidade Disponível": quantidade,
        "Preço Unitário": preco,
        "Fornecedor": fornecedor
    }
    estoque.append(peca)
    return inserir_estoque(id_estoque,nome_peca, quantidade, preco, fornecedor)

def ler_estoque(indice=-1):
    if 0 <= indice < len(estoque):
        return f"Peça {indice + 1}: {estoque[indice]}"
    else:
        estoque_formatado = []
        for i, peca in enumerate(estoque):
            estoque_formatado.append(f"Peça {i + 1}: {peca}")
        return "\n".join(estoque_formatado) if estoque_formatado else "⚠️ Estoque vazio."

def atualizar_estoque(indice, nome_peca=None, quantidade=None, preco=None, fornecedor=None):
    if 0 <= indice < len(estoque):
        if nome_peca is not None:
            estoque[indice]["Nome da Peça"] = nome_peca
        if quantidade is not None:
            estoque[indice]["Quantidade Disponível"] = quantidade
        if preco is not None:
            estoque[indice]["Preço Unitário"] = preco
        if fornecedor is not None:
            estoque[indice]["Fornecedor"] = fornecedor
        return True
    return False

def deletar_peca_estoque(indice):
    if 0 <= indice < len(estoque):
        estoque.pop(indice)
        return True
    return False

# Funções para serviços agendados
def agendar_servico(id_servico, nome_cliente, nome_funcionario, data_agendamento, veiculo_placa, descricao_servico):
    servico = {
        "id_servico":id_servico,
        "Nome do Cliente": nome_cliente,
        "Nome do Funcionário": nome_funcionario,
        "Data de Agendamento": data_agendamento,
        "Placa do Veículo": veiculo_placa,
        "Descrição do Serviço": descricao_servico
    }
    servicos_agendados.append(servico)
    return inserir_servico(id_servico, nome_cliente, nome_funcionario, data_agendamento, veiculo_placa, descricao_servico)

def ler_servico_agendado(indice=-1):
    if 0 <= indice < len(servicos_agendados):
        return servicos_agendados[indice]
    else:
        return servicos_agendados

def atualizar_servico_agendado(indice, nome_cliente=None, nome_funcionario=None, data_agendamento=None, veiculo_placa=None, descricao_servico=None):
    if 0 <= indice < len(servicos_agendados):
        if nome_cliente is not None:
            servicos_agendados[indice]["Nome do Cliente"] = nome_cliente
        if nome_funcionario is not None:
            servicos_agendados[indice]["Nome do Funcionário"] = nome_funcionario
        if data_agendamento is not None:
            servicos_agendados[indice]["Data de Agendamento"] = data_agendamento
        if veiculo_placa is not None:
            servicos_agendados[indice]["Placa do Veículo"] = veiculo_placa
        if descricao_servico is not None:
            servicos_agendados[indice]["Descrição do Serviço"] = descricao_servico
        return True
    return False

def deletar_servico_agendado(indice):
    if 0 <= indice < len(servicos_agendados):
        servicos_agendados.pop(indice)
        return True
    return False