# Sistema de Gest�o de Consumo

## Descri��o
Este � um sistema para gest�o e monitoramento do consumo de energia, desenvolvido em Python, com integra��o ao banco de dados Oracle. Ele permite realizar opera��es de CRUD (Criar, Ler, Atualizar e Excluir) em tabelas relacionadas a usu�rios, dispositivos, estimativas de consumo e sugest�es de economia. O sistema tamb�m permite exportar dados para arquivos JSON e CSV.

---

## Funcionalidades
### Usu�rios
- Inserir, atualizar, excluir e consultar usu�rios.

### Dispositivos
- Inserir, atualizar, excluir e consultar dispositivos.

### Estimativas de Consumo
- Inserir, atualizar, excluir e consultar estimativas de consumo.

### Sugest�es de Economia
- Inserir, atualizar, excluir e consultar sugest�es de economia.

### Exporta��o
- Exportar dados de estimativas para arquivos JSON e CSV.

---

## Valida��es Implementadas
O sistema implementa valida��es robustas para garantir a integridade dos dados fornecidos pelos usu�rios. Aqui est�o as principais valida��es:

### 1. **Email**
- Foi utilizada uma express�o regular (`regex`) para validar o formato do email.
- O email deve:
  - Conter uma parte local (antes do `@`).
  - Conter um dom�nio (depois do `@`), seguido de um ponto (`.`) e uma extens�o v�lida.
- Exemplos:
  - ? `usuario@dominio.com`
  - ? `usuario@dominio`
  - ? `usuariodominio.com`

### 2. **ID**
- O ID deve ser **num�rico** e **positivo**.
- Exemplos:
  - ? `1`, `42`, `100`
  - ? `-1`, `abc`, `0`

### 3. **Nome**
- O nome deve conter apenas **caracteres alfab�ticos** e **espa�os**.
- Exemplos:
  - ? `Jo�o Silva`
  - ? `Jo�o123`, `@Maria`

### 4. **N�meros (Como Consumo M�dio)**
- Verifica se o valor � um n�mero v�lido, com op��o de limitar valores m�nimos e m�ximos.
- Exemplos:
  - ? `250.5`, `100`
  - ? `abc`, `-10` (caso um valor positivo seja obrigat�rio).

### 5. **Senha**
- A senha deve atender aos seguintes crit�rios:
  - Ter pelo menos **8 caracteres**.
  - Incluir letras **mai�sculas** e **min�sculas**.
  - Conter pelo menos **1 n�mero**.
  - Conter pelo menos **1 caractere especial** (ex.: `@`, `#`, `!`).
- Exemplos:
  - ? `Senha@123`
  - ? `senha`, `Senha123`, `senha@123`

As valida��es est�o implementadas no arquivo `validators.py`.

---

## Requisitos do Sistema
1. **Banco de Dados Oracle**
   - Tabelas: `t_gs_usuario`, `t_gs_tipo_dispositivo`, `t_gs_estimativa_consumo`, `t_gs_sugestao_economia`.
   - Conex�o configurada em `database.py`.

2. **Valida��es de Entrada**
   - Emails v�lidos, IDs num�ricos e positivos, senhas seguras.

3. **Tratamento de Exce��es**
   - Erros de banco de dados e entradas inv�lidas s�o tratados.

4. **Estruturas**
   - Menu principal com estruturas de decis�o e repeti��o.

5. **Exporta��o de Dados**
   - Permite exportar consultas para JSON e CSV.

---

## Requisitos de Instala��o
1. Instale o Python 3.8+.
2. Instale o pacote `cx_Oracle`:
   ```bash
   pip install cx_Oracle
