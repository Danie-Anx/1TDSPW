# Sistema de Gestão de Consumo

## Descrição
Este é um sistema para gestão e monitoramento do consumo de energia, desenvolvido em Python, com integração ao banco de dados Oracle. Ele permite realizar operações de CRUD (Criar, Ler, Atualizar e Excluir) em tabelas relacionadas a usuários, dispositivos, estimativas de consumo e sugestões de economia. O sistema também permite exportar dados para arquivos JSON e CSV.

---

## Funcionalidades
### Usuários
- Inserir, atualizar, excluir e consultar usuários.

### Dispositivos
- Inserir, atualizar, excluir e consultar dispositivos.

### Estimativas de Consumo
- Inserir, atualizar, excluir e consultar estimativas de consumo.

### Sugestões de Economia
- Inserir, atualizar, excluir e consultar sugestões de economia.

### Exportação
- Exportar dados de estimativas para arquivos JSON e CSV.

---

## Validações Implementadas
O sistema implementa validações robustas para garantir a integridade dos dados fornecidos pelos usuários. Aqui estão as principais validações:

### 1. **Email**
- Foi utilizada uma expressão regular (`regex`) para validar o formato do email.
- O email deve:
  - Conter uma parte local (antes do `@`).
  - Conter um domínio (depois do `@`), seguido de um ponto (`.`) e uma extensão válida.
- Exemplos:
  - ? `usuario@dominio.com`
  - ? `usuario@dominio`
  - ? `usuariodominio.com`

### 2. **ID**
- O ID deve ser **numérico** e **positivo**.
- Exemplos:
  - ? `1`, `42`, `100`
  - ? `-1`, `abc`, `0`

### 3. **Nome**
- O nome deve conter apenas **caracteres alfabéticos** e **espaços**.
- Exemplos:
  - ? `João Silva`
  - ? `João123`, `@Maria`

### 4. **Números (Como Consumo Médio)**
- Verifica se o valor é um número válido, com opção de limitar valores mínimos e máximos.
- Exemplos:
  - ? `250.5`, `100`
  - ? `abc`, `-10` (caso um valor positivo seja obrigatório).

### 5. **Senha**
- A senha deve atender aos seguintes critérios:
  - Ter pelo menos **8 caracteres**.
  - Incluir letras **maiúsculas** e **minúsculas**.
  - Conter pelo menos **1 número**.
  - Conter pelo menos **1 caractere especial** (ex.: `@`, `#`, `!`).
- Exemplos:
  - ? `Senha@123`
  - ? `senha`, `Senha123`, `senha@123`

As validações estão implementadas no arquivo `validators.py`.

---

## Requisitos do Sistema
1. **Banco de Dados Oracle**
   - Tabelas: `t_gs_usuario`, `t_gs_tipo_dispositivo`, `t_gs_estimativa_consumo`, `t_gs_sugestao_economia`.
   - Conexão configurada em `database.py`.

2. **Validações de Entrada**
   - Emails válidos, IDs numéricos e positivos, senhas seguras.

3. **Tratamento de Exceções**
   - Erros de banco de dados e entradas inválidas são tratados.

4. **Estruturas**
   - Menu principal com estruturas de decisão e repetição.

5. **Exportação de Dados**
   - Permite exportar consultas para JSON e CSV.

---

## Requisitos de Instalação
1. Instale o Python 3.8+.
2. Instale o pacote `cx_Oracle`:
   ```bash
   pip install cx_Oracle
