CREATE TABLE Oficina (
    id_oficina INT PRIMARY KEY,
    ofc_nome VARCHAR2(110) NOT NULL,
    ofc_telefone VARCHAR2(20),
    ofc_email VARCHAR2(40) UNIQUE
);

CREATE TABLE Agendamentos (
    id_agendamento INT PRIMARY KEY,
    age_data DATE NOT NULL,
    age_servico VARCHAR2(100) NOT NULL
);

CREATE TABLE Cliente (
    id_cliente INT PRIMARY KEY,
    cli_nome VARCHAR2(60) NOT NULL,
    cli_telefone VARCHAR2(20) NOT NULL,
    cli_cpf VARCHAR2(14) UNIQUE NOT NULL,
    cli_email VARCHAR2(40) UNIQUE,
    cli_veiculo VARCHAR2(40),
    cli_usuario VARCHAR2(20),
    cli_senha VARCHAR2(50),
    id_agendamento INT,
    FOREIGN KEY (id_agendamento) REFERENCES Agendamentos(id_agendamento)
);

CREATE TABLE Pagamentos (
    id_pagamento INT PRIMARY KEY,
    pag_valor INT CHECK (pag_valor >= 0),
    pag_data DATE,
    id_cliente INT,
    id_agendamento INT,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_agendamento) REFERENCES Agendamentos(id_agendamento)
);

CREATE TABLE Veiculo (
    id_veiculo INT PRIMARY KEY,
    vei_modelo VARCHAR2(50) NOT NULL,
    vei_marca VARCHAR2(50),
    vei_ano INT CHECK (vei_ano >= 1886),
    vei_quilometragem INT CHECK (vei_quilometragem >= 0),
    vei_diagnostico VARCHAR2(100),
    id_cliente INT NOT NULL,
    id_agendamento INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_agendamento) REFERENCES Agendamentos(id_agendamento)
);

-- Criação da tabela Orcamento
CREATE TABLE Orcamento (
    id_orcamento INT PRIMARY KEY,
    orc_descricao VARCHAR2(255),
    orc_valor INT CHECK (orc_valor >= 0),
    orc_tempoEstimado DATE,
    orc_veiculo INT,
    id_cliente INT,
    id_agendamento INT,
    id_pagamento INT,
    FOREIGN KEY (orc_veiculo) REFERENCES Veiculo(id_veiculo),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_agendamento) REFERENCES Agendamentos(id_agendamento),
    FOREIGN KEY (id_pagamento) REFERENCES Pagamentos(id_pagamento)
);

-- Criação da tabela Endereco
CREATE TABLE Endereco (
    id_endereco INT,
    end_cep VARCHAR2(10) NOT NULL,
    end_rua VARCHAR2(100) NOT NULL,
    end_numero INT CHECK (end_numero > 0),
    end_pais VARCHAR2(30) NOT NULL,
    end_cidade VARCHAR2(30) NOT NULL,
    end_bairro VARCHAR2(30) NOT NULL,
    id_cliente INT NOT NULL,
    id_agendamento INT NOT NULL,
    id_oficina INT,
    PRIMARY KEY (id_cliente, id_agendamento, id_endereco),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_agendamento) REFERENCES Agendamentos(id_agendamento),
    FOREIGN KEY (id_oficina) REFERENCES Oficina(id_oficina)
);

CREATE TABLE Funcionario (
    id_funcionario INT PRIMARY KEY,
    fun_nome VARCHAR2(110) NOT NULL,
    fun_telefone VARCHAR2(20),
    fun_email VARCHAR2(40) UNIQUE,
    fun_cpf VARCHAR2(14) UNIQUE NOT NULL,
    fun_dataContratacao DATE NOT NULL,
    fun_setor VARCHAR2(30) NOT NULL,
    fun_cargo VARCHAR2(50),
    fun_salario INT CHECK (fun_salario > 0),
    id_orcamento INT,
    id_oficina INT NOT NULL,
    FOREIGN KEY (id_orcamento) REFERENCES Orcamento(id_orcamento),
    FOREIGN KEY (id_oficina) REFERENCES Oficina(id_oficina)
);

CREATE TABLE Diagnostico (
    id_diagnostico INT PRIMARY KEY,
    dia_veiculo INT NOT NULL,
    dia_problema VARCHAR2(255),
    dia_descricao VARCHAR2(255),
    dia_orcamento INT,
    id_funcionario INT,
    id_oficina INT,
    FOREIGN KEY (dia_veiculo) REFERENCES Veiculo(id_veiculo),
    FOREIGN KEY (dia_orcamento) REFERENCES Orcamento(id_orcamento),
    FOREIGN KEY (id_funcionario) REFERENCES Funcionario(id_funcionario),
    FOREIGN KEY (id_oficina) REFERENCES Oficina(id_oficina)
);



-- Inserts na tabela Oficina
INSERT INTO Oficina VALUES (1, 'Auto Center ABC', '1122334455', 'abc@oficinas.com');
INSERT INTO Oficina VALUES (2, 'Oficina Premium', '1133445566', 'premium@oficinas.com');
INSERT INTO Oficina VALUES (3, 'Super Mecânica', '1144556677', 'super@oficinas.com');
INSERT INTO Oficina VALUES (4, 'Car Service', '1155667788', 'carservice@oficinas.com');
INSERT INTO Oficina VALUES (5, 'Mecânica Rápida', '1166778899', 'mecanicarapida@oficinas.com');
INSERT INTO Oficina VALUES (6, 'Oficina Express', '1177889900', 'express@oficinas.com');
INSERT INTO Oficina VALUES (7, 'Auto Reparos', '1188990011', 'autoreparos@oficinas.com');
INSERT INTO Oficina VALUES (8, 'Top Car', '1199001122', 'topcar@oficinas.com');
INSERT INTO Oficina VALUES (9, 'Mecânica do Bairro', '1100112233', 'bairro@oficinas.com');
INSERT INTO Oficina VALUES (10, 'Auto Mais', '1111223344', 'automais@oficinas.com');

-- Inserts na tabela Agendamentos
INSERT INTO Agendamentos VALUES (1, TO_DATE('2024-11-01', 'YYYY-MM-DD'), 'Revisão Completa');
INSERT INTO Agendamentos VALUES (2, TO_DATE('2024-11-02', 'YYYY-MM-DD'), 'Troca de Óleo');
INSERT INTO Agendamentos VALUES (3, TO_DATE('2024-11-03', 'YYYY-MM-DD'), 'Alinhamento e Balanceamento');
INSERT INTO Agendamentos VALUES (4, TO_DATE('2024-11-04', 'YYYY-MM-DD'), 'Reparo de Freios');
INSERT INTO Agendamentos VALUES (5, TO_DATE('2024-11-05', 'YYYY-MM-DD'), 'Troca de Pneus');
INSERT INTO Agendamentos VALUES (6, TO_DATE('2024-11-06', 'YYYY-MM-DD'), 'Inspeção de Suspensão');
INSERT INTO Agendamentos VALUES (7, TO_DATE('2024-11-07', 'YYYY-MM-DD'), 'Limpeza de Bicos');
INSERT INTO Agendamentos VALUES (8, TO_DATE('2024-11-08', 'YYYY-MM-DD'), 'Troca de Correia Dentada');
INSERT INTO Agendamentos VALUES (9, TO_DATE('2024-11-09', 'YYYY-MM-DD'), 'Serviço de Pintura');
INSERT INTO Agendamentos VALUES (10, TO_DATE('2024-11-10', 'YYYY-MM-DD'), 'Serviço de Funilaria');

-- Inserts na tabela Cliente
INSERT INTO Cliente VALUES (1, 'João Silva', '11987654321', '12345678900', 'joao.silva@mail.com', 'Ford Ka', 'joaosilva', 'senha123', 1);
INSERT INTO Cliente VALUES (2, 'Ana Santos', '11976543210', '09876543211', 'ana.santos@mail.com', 'Honda Civic', 'anasantos', 'senha456', 2);
INSERT INTO Cliente VALUES (3, 'Carlos Lima', '11965432109', '98765432122', 'carlos.lima@mail.com', 'Toyota Corolla', 'carloslima', 'senha789', 3);
INSERT INTO Cliente VALUES (4, 'Mariana Costa', '11954321098', '87654321033', 'mariana.costa@mail.com', 'Hyundai HB20', 'marianacosta', 'senha101', 4);
INSERT INTO Cliente VALUES (5, 'Pedro Oliveira', '11943210987', '76543210944', 'pedro.oliveira@mail.com', 'Chevrolet Onix', 'pedrooliveira', 'senha202', 5);
INSERT INTO Cliente VALUES (6, 'Lucas Almeida', '11932109876', '65432109855', 'lucas.almeida@mail.com', 'Volkswagen Gol', 'lucasalmeida', 'senha303', 6);
INSERT INTO Cliente VALUES (7, 'Beatriz Martins', '11921098765', '54321098766', 'beatriz.martins@mail.com', 'Renault Sandero', 'beatrizmartins', 'senha404', 7);
INSERT INTO Cliente VALUES (8, 'Mateus Ferreira', '11910987654', '43210987677', 'mateus.ferreira@mail.com', 'Fiat Argo', 'mateusferreira', 'senha505', 8);
INSERT INTO Cliente VALUES (9, 'Isabela Souza', '11909876543', '32109876588', 'isabela.souza@mail.com', 'Jeep Renegade', 'isabelasouza', 'senha606', 9);
INSERT INTO Cliente VALUES (10, 'Ricardo Pereira', '11908765432', '21098765499', 'ricardo.pereira@mail.com', 'Ford EcoSport', 'ricardopereira', 'senha707', 10);

-- Inserts na tabela Pagamentos
INSERT INTO Pagamentos VALUES (1, 300, TO_DATE('2024-11-02', 'YYYY-MM-DD'), 1, 1);
INSERT INTO Pagamentos VALUES (2, 200, TO_DATE('2024-11-03', 'YYYY-MM-DD'), 2, 2);
INSERT INTO Pagamentos VALUES (3, 150, TO_DATE('2024-11-04', 'YYYY-MM-DD'), 3, 3);
INSERT INTO Pagamentos VALUES (4, 400, TO_DATE('2024-11-05', 'YYYY-MM-DD'), 4, 4);
INSERT INTO Pagamentos VALUES (5, 250, TO_DATE('2024-11-06', 'YYYY-MM-DD'), 5, 5);
INSERT INTO Pagamentos VALUES (6, 300, TO_DATE('2024-11-07', 'YYYY-MM-DD'), 6, 6);
INSERT INTO Pagamentos VALUES (7, 350, TO_DATE('2024-11-08', 'YYYY-MM-DD'), 7, 7);
INSERT INTO Pagamentos VALUES (8, 200, TO_DATE('2024-11-09', 'YYYY-MM-DD'), 8, 8);
INSERT INTO Pagamentos VALUES (9, 500, TO_DATE('2024-11-10', 'YYYY-MM-DD'), 9, 9);
INSERT INTO Pagamentos VALUES (10, 450, TO_DATE('2024-11-11', 'YYYY-MM-DD'), 10, 10);

-- Inserts na tabela Veiculo
INSERT INTO Veiculo VALUES (1, 'Civic', 'Honda', 2019, 20000, 'Em boas condições', 1, 1);
INSERT INTO Veiculo VALUES (2, 'Corolla', 'Toyota', 2018, 25000, 'Troca de óleo', 2, 2);
INSERT INTO Veiculo VALUES (3, 'Onix', 'Chevrolet', 2020, 10000, 'Manutenção básica', 3, 3);
INSERT INTO Veiculo VALUES (4, 'HB20', 'Hyundai', 2017, 30000, 'Alinhamento necessário', 4, 4);
INSERT INTO Veiculo VALUES (5, 'Argo', 'Fiat', 2019, 15000, 'Revisão completa', 5, 5);
INSERT INTO Veiculo VALUES (6, 'Renegade', 'Jeep', 2018, 22000, 'Correção de falhas', 6, 6);
INSERT INTO Veiculo VALUES (7, 'EcoSport', 'Ford', 2020, 12000, 'Em boas condições', 7, 7);
INSERT INTO Veiculo VALUES (8, 'Sandero', 'Renault', 2017, 28000, 'Troca de correia', 8, 8);
INSERT INTO Veiculo VALUES (9, 'Gol', 'Volkswagen', 2019, 17000, 'Suspensão ajustada', 9, 9);
INSERT INTO Veiculo VALUES (10, 'Ka', 'Ford', 2018, 21000, 'Revisão completa', 10, 10);

-- Inserts na tabela Orcamento
INSERT INTO Orcamento VALUES (1, 'Troca de óleo e filtros', 150, TO_DATE('2024-11-03', 'YYYY-MM-DD'), 1, 1, 1, 1);
INSERT INTO Orcamento VALUES (2, 'Alinhamento e balanceamento', 100, TO_DATE('2024-11-04', 'YYYY-MM-DD'), 2, 2, 2, 2);
INSERT INTO Orcamento VALUES (3, 'Reparo de suspensão', 200, TO_DATE('2024-11-05', 'YYYY-MM-DD'), 3, 3, 3, 3);
INSERT INTO Orcamento VALUES (4, 'Troca de pastilhas de freio', 180, TO_DATE('2024-11-06', 'YYYY-MM-DD'), 4, 4, 4, 4);
INSERT INTO Orcamento VALUES (5, 'Revisão geral', 500, TO_DATE('2024-11-07', 'YYYY-MM-DD'), 5, 5, 5, 5);
INSERT INTO Orcamento VALUES (6, 'Limpeza de bicos injetores', 120, TO_DATE('2024-11-08', 'YYYY-MM-DD'), 6, 6, 6, 6);
INSERT INTO Orcamento VALUES (7, 'Troca de correia dentada', 250, TO_DATE('2024-11-09', 'YYYY-MM-DD'), 7, 7, 7, 7);
INSERT INTO Orcamento VALUES (8, 'Troca de velas', 90, TO_DATE('2024-11-10', 'YYYY-MM-DD'), 8, 8, 8, 8);
INSERT INTO Orcamento VALUES (9, 'Serviço de funilaria', 300, TO_DATE('2024-11-11', 'YYYY-MM-DD'), 9, 9, 9, 9);
INSERT INTO Orcamento VALUES (10, 'Pintura completa', 1000, TO_DATE('2024-11-12', 'YYYY-MM-DD'), 10, 10, 10, 10);

-- Inserts na tabela Endereco 
INSERT INTO Endereco VALUES (1, '12345-678', 'Rua A', 100, 'Brasil', 'São Paulo', 'Centro', 1, 1, 1);
INSERT INTO Endereco VALUES (2, '23456-789', 'Rua B', 101, 'Brasil', 'Rio de Janeiro', 'Zona Sul', 2, 2, 2);
INSERT INTO Endereco VALUES (3, '34567-890', 'Rua C', 102, 'Brasil', 'Curitiba', 'Centro', 3, 3, 3);
INSERT INTO Endereco VALUES (4, '45678-901', 'Rua D', 103, 'Brasil', 'Belo Horizonte', 'Savassi', 4, 4, 4);
INSERT INTO Endereco VALUES (5, '56789-012', 'Rua E', 104, 'Brasil', 'Porto Alegre', 'Centro Histórico', 5, 5, 5);
INSERT INTO Endereco VALUES (6, '67890-123', 'Rua F', 105, 'Brasil', 'Fortaleza', 'Meireles', 6, 6, 6);
INSERT INTO Endereco VALUES (7, '78901-234', 'Rua G', 106, 'Brasil', 'Brasília', 'Asa Sul', 7, 7, 7);
INSERT INTO Endereco VALUES (8, '89012-345', 'Rua H', 107, 'Brasil', 'Recife', 'Boa Viagem', 8, 8, 8);
INSERT INTO Endereco VALUES (9, '90123-456', 'Rua I', 108, 'Brasil', 'Manaus', 'Centro', 9, 9, 9);
INSERT INTO Endereco VALUES (10, '01234-567', 'Rua J', 109, 'Brasil', 'Belém', 'Umarizal', 10, 10, 10);
INSERT INTO Endereco VALUES (11, '22345-678', 'Rua K', 110, 'Brasil', 'Campinas', 'Jardim', 1, 2, 1);
INSERT INTO Endereco VALUES (12, '33456-789', 'Rua L', 111, 'Brasil', 'Santos', 'Centro', 2, 3, 2);
INSERT INTO Endereco VALUES (13, '44567-890', 'Rua M', 112, 'Brasil', 'São Paulo', 'Vila Mariana', 3, 4, 1);
INSERT INTO Endereco VALUES (14, '55678-901', 'Rua N', 113, 'Brasil', 'Curitiba', 'Batel', 4, 5, 3);
INSERT INTO Endereco VALUES (15, '66789-012', 'Rua O', 114, 'Brasil', 'Porto Alegre', 'Moinhos de Vento', 5, 6, 2);
INSERT INTO Endereco VALUES (16, '77890-123', 'Rua P', 115, 'Brasil', 'Belo Horizonte', 'Centro', 6, 7, 4);
INSERT INTO Endereco VALUES (17, '88901-234', 'Rua Q', 116, 'Brasil', 'Brasília', 'Asa Norte', 7, 8, 3);
INSERT INTO Endereco VALUES (18, '99012-345', 'Rua R', 117, 'Brasil', 'Recife', 'Boa Viagem', 8, 9, 2);
INSERT INTO Endereco VALUES (19, '10123-456', 'Rua S', 118, 'Brasil', 'Fortaleza', 'Aldeota', 9, 10, 1);
INSERT INTO Endereco VALUES (20, '11234-567', 'Rua T', 119, 'Brasil', 'Salvador', 'Pituba', 10, 1, 4);



-- Inserts na tabela Funcionario
INSERT INTO Funcionario VALUES (1, 'Maria Souza', '11999887766', 'maria.souza@mail.com', '12345678901', TO_DATE('2023-06-15', 'YYYY-MM-DD'), 'Mecânica', 'Chefe de Oficina', 3000, 1, 1);
INSERT INTO Funcionario VALUES (2, 'Carlos Almeida', '11988776655', 'carlos.almeida@mail.com', '23456789012', TO_DATE('2022-04-10', 'YYYY-MM-DD'), 'Pintura', 'Pintor', 2500, 2, 2);
INSERT INTO Funcionario VALUES (3, 'Ana Costa', '11977665544', 'ana.costa@mail.com', '34567890123', TO_DATE('2021-02-20', 'YYYY-MM-DD'), 'Funilaria', 'Funileiro', 2800, 3, 1);
INSERT INTO Funcionario VALUES (4, 'João Santos', '11966554433', 'joao.santos@mail.com', '45678901234', TO_DATE('2020-08-05', 'YYYY-MM-DD'), 'Mecânica', 'Mecânico', 3000, 4, 1);
INSERT INTO Funcionario VALUES (5, 'Mariana Ribeiro', '11955443322', 'mariana.ribeiro@mail.com', '56789012345', TO_DATE('2019-12-10', 'YYYY-MM-DD'), 'Pintura', 'Pintora', 2700, 5, 2);
INSERT INTO Funcionario VALUES (6, 'Pedro Silva', '11944332211', 'pedro.silva@mail.com', '67890123456', TO_DATE('2023-01-15', 'YYYY-MM-DD'), 'Eletricista', 'Eletricista', 3200, 6, 3);
INSERT INTO Funcionario VALUES (7, 'Bruno Moreira', '11933221100', 'bruno.moreira@mail.com', '78901234567', TO_DATE('2021-07-01', 'YYYY-MM-DD'), 'Suspensão', 'Técnico', 2900, 7, 2);
INSERT INTO Funcionario VALUES (8, 'Patrícia Souza', '11922110099', 'patricia.souza@mail.com', '89012345678', TO_DATE('2022-03-11', 'YYYY-MM-DD'), 'Funilaria', 'Funileira', 2600, 8, 4);
INSERT INTO Funcionario VALUES (9, 'Roberto Nunes', '11911009988', 'roberto.nunes@mail.com', '90123456789', TO_DATE('2020-09-18', 'YYYY-MM-DD'), 'Mecânica', 'Mecânico', 3300, 9, 3);
INSERT INTO Funcionario VALUES (10, 'Larissa Campos', '11900998877', 'larissa.campos@mail.com', '01234567890', TO_DATE('2021-06-21', 'YYYY-MM-DD'), 'Alinhamento', 'Técnica', 3100, 10, 4);

-- Inserts na tabela Diagnostico
INSERT INTO Diagnostico VALUES (1, 1, 'Falha no motor', 'Vazamento de óleo detectado', 1, 1, 1);
INSERT INTO Diagnostico VALUES (2, 2, 'Problema de suspensão', 'Amortecedores desgastados', 2, 2, 2);
INSERT INTO Diagnostico VALUES (3, 3, 'Freios desgastados', 'Pastilhas e discos precisam de troca', 3, 3, 1);
INSERT INTO Diagnostico VALUES (4, 4, 'Sistema de escapamento', 'Furo no escapamento', 4, 4, 1);
INSERT INTO Diagnostico VALUES (5, 5, 'Problema elétrico', 'Fusível queimado', 5, 5, 3);
INSERT INTO Diagnostico VALUES (6, 6, 'Pneus', 'Desgaste acentuado nos pneus traseiros', 6, 6, 2);
INSERT INTO Diagnostico VALUES (7, 7, 'Troca de óleo', 'Óleo precisa ser trocado', 7, 7, 2);
INSERT INTO Diagnostico VALUES (8, 8, 'Sistema de arrefecimento', 'Baixo nível de líquido de arrefecimento', 8, 8, 3);
INSERT INTO Diagnostico VALUES (9, 9, 'Problema de direção', 'Folga na direção hidráulica', 9, 9, 3);
INSERT INTO Diagnostico VALUES (10, 10, 'Bateria fraca', 'Bateria está próxima ao fim da vida útil', 10, 10, 4);
INSERT INTO Diagnostico VALUES (11, 1, 'Alinhamento necessário', 'Desalinhamento nas rodas dianteiras', 1, 1, 1);
INSERT INTO Diagnostico VALUES (12, 2, 'Problema no ar condicionado', 'Compressor precisa de reparo', 2, 2, 2);
INSERT INTO Diagnostico VALUES (13, 3, 'Barulho no motor', 'Ruído ao dar partida', 3, 3, 1);
INSERT INTO Diagnostico VALUES (14, 4, 'Troca de correia', 'Correia dentada está desgastada', 4, 4, 1);
INSERT INTO Diagnostico VALUES (15, 5, 'Suspensão dianteira', 'Problema nas molas', 5, 5, 3);
INSERT INTO Diagnostico VALUES (16, 6, 'Pintura arranhada', 'Arranhões na porta esquerda', 6, 6, 2);
INSERT INTO Diagnostico VALUES (17, 7, 'Filtro de ar', 'Filtro de ar precisa ser trocado', 7, 7, 2);
INSERT INTO Diagnostico VALUES (18, 8, 'Desgaste na embreagem', 'Embreagem apresenta desgaste excessivo', 8, 8, 3);
INSERT INTO Diagnostico VALUES (19, 9, 'Luzes do painel acesas', 'Necessário verificar sistema elétrico', 9, 9, 3);
INSERT INTO Diagnostico VALUES (20, 10, 'Problema no câmbio', 'Câmbio automático com problemas de troca', 10, 10, 4);


-- Listagem de veículos ordenados pela quilometragem
SELECT id_veiculo, vei_modelo, vei_marca, vei_ano, vei_quilometragem
FROM Veiculo
ORDER BY vei_quilometragem DESC;


-- Listagem de orçamentos com valor arredondado
SELECT id_orcamento, orc_descricao, ROUND(orc_valor, 2) AS orc_valor_arredondado
FROM Orcamento;


-- Total de pagamentos por cliente
-- Calcula o total de pagamentos para cada cliente usando a função SUM
SELECT id_cliente, SUM(pag_valor) AS total_pagamento
FROM Pagamentos
GROUP BY id_cliente;


-- Clientes com mais de um agendamento
-- Realiza uma subconsulta que conta a quantidade de agendamentos por cliente
-- e filtra apenas os clientes com mais de um agendamento
SELECT id_cliente, cli_nome
FROM Cliente
WHERE id_cliente IN (
    SELECT id_cliente
    FROM Agendamentos
    GROUP BY id_cliente
    HAVING COUNT(id_agendamento) > 1
);


-- Detalhes de agendamentos com informações do cliente e veículo
-- Exibe detalhes dos agendamentos juntamente com informações de cliente e veículo
-- Realiza junções entre as tabelas Agendamentos, Cliente e Veiculo com base
-- nas chaves estrangeiras para exibir um relatório abrangente
SELECT Agendamentos.id_agendamento, Agendamentos.age_data, Agendamentos.age_servico,
       Cliente.cli_nome, Cliente.cli_telefone, Veiculo.vei_modelo, Veiculo.vei_marca
FROM Agendamentos
JOIN Cliente ON Agendamentos.id_agendamento = Cliente.id_agendamento
JOIN Veiculo ON Cliente.id_cliente = Veiculo.id_cliente;


-- Listagem de funcionários ordenados por setor
-- Ordena os funcionários pelo setor em ordem alfabética para facilitar a localização
SELECT id_funcionario, fun_nome, fun_setor
FROM Funcionario
ORDER BY fun_setor ASC;


-- Média do valor de orçamento por diagnóstico
-- Calcula a média dos valores de orçamento para cada tipo de problema diagnosticado
-- Agrupa por id de diagnóstico e descrição do problema
-- Arredonda o valor médio para duas casas decimais
SELECT id_diagnostico, dia_problema, ROUND(AVG(dia_orcamento), 2) AS media_valor
FROM Diagnostico
GROUP BY id_diagnostico, dia_problema;


-- Número de veículos por cliente
-- Conta a quantidade de veículos associados a cada cliente
-- Agrupa os resultados pelo id de cliente para fornecer o número de veículos de cada cliente
SELECT id_cliente, COUNT(id_veiculo) AS num_veiculos
FROM Veiculo
GROUP BY id_cliente;


-- Clientes com pagamentos acima da média
-- Seleciona os clientes que possuem pagamentos acima da média geral dos pagamentos
-- Realiza uma subconsulta para calcular a média de todos os valores de pagamento
-- e filtra apenas os clientes que possuem valores de pagamento superiores a essa média
SELECT id_cliente, cli_nome
FROM Cliente
WHERE id_cliente IN (
    SELECT id_cliente
    FROM Pagamentos
    WHERE pag_valor > (SELECT AVG(pag_valor) FROM Pagamentos)
);


-- Diagnóstico e mecânico responsável
-- Exibe os detalhes dos diagnósticos junto com o nome do mecânico responsável
-- Realiza uma junção entre Diagnostico e Funcionario para combinar as informações
-- com base no id de funcionário
SELECT Diagnostico.id_diagnostico, Diagnostico.dia_problema, Funcionario.fun_nome AS mecanico_responsavel
FROM Diagnostico
JOIN Funcionario ON Diagnostico.id_funcionario = Funcionario.id_funcionario;


-- Selects
SELECT * FROM Oficina;

SELECT * FROM Agendamentos;

SELECT * FROM Cliente;

SELECT * FROM Pagamentos;

SELECT * FROM Veiculo;

SELECT * FROM Orcamento;

SELECT * FROM Endereco;

SELECT * FROM Funcionario;

SELECT * FROM Diagnostico;



-- Drops
DROP TABLE Diagnostico;

DROP TABLE Funcionario;

DROP TABLE Endereco;

DROP TABLE Orcamento;

DROP TABLE Veiculo;

DROP TABLE Pagamentos;

DROP TABLE Cliente;

DROP TABLE Agendamentos;

DROP TABLE Oficina;


