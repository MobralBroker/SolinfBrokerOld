-- cliente
-- Inserção de 10 clientes, sendo 10 Pessoa Física (PF) e 10 Pessoa Jurídica (PJ), cada um com um username, senha, e-mail, e saldo fictícios.
INSERT INTO cliente (tipo, usuario, senha, email, saldo) VALUES
('PF', 'pessoafisica1', 'senha1', 'pf1@email.com', 1000.00),
('PF', 'pessoafisica2', 'senha2', 'pf2@email.com', 1500.50),
('PF', 'pessoafisica3', 'senha3', 'pf3@email.com', 800.25),
('PF', 'pessoafisica4', 'senha4', 'pf4@email.com', 2000.75),
('PF', 'pessoafisica5', 'senha5', 'pf5@email.com', 1200.90),
('PF', 'pessoafisica6', 'senha6', 'pf6@email.com', 500.00),
('PF', 'pessoafisica7', 'senha7', 'pf7@email.com', 300.50),
('PF', 'pessoafisica8', 'senha8', 'pf8@email.com', 1800.20),
('PF', 'pessoafisica9', 'senha9', 'pf9@email.com', 700.75),
('PF', 'pessoafisica10', 'senha10', 'pf10@email.com', 2500.40),
('PJ', 'pessoajuridica1', 'senha11', 'pj1@email.com', 3000.00),
('PJ', 'pessoajuridica2', 'senha12', 'pj2@email.com', 2500.50),
('PJ', 'pessoajuridica3', 'senha13', 'pj3@email.com', 1800.75),
('PJ', 'pessoajuridica4', 'senha14', 'pj4@email.com', 3500.75),
('PJ', 'pessoajuridica5', 'senha15', 'pj5@email.com', 4200.90),
('PJ', 'pessoajuridica6', 'senha16', 'pj6@email.com', 5000.00),
('PJ', 'pessoajuridica7', 'senha17', 'pj7@email.com', 2300.50),
('PJ', 'pessoajuridica8', 'senha18', 'pj8@email.com', 2800.20),
('PJ', 'pessoajuridica9', 'senha19', 'pj9@email.com', 1500.75),
('PJ', 'pessoajuridica10', 'senha20', 'pj10@email.com', 3200.40);

-- pessoafisica
-- Inserção de 10 registros na tabela pessoafisica, associando cada CPF a um cliente existente e fornecendo nome e data de nascimento fictícios.
INSERT INTO pessoafisica (cpf, id_cliente, nome, nascimento) VALUES
('12345678909', 1, 'Pessoa Fisica 1', '1990-01-01'),
('23456789012', 2, 'Pessoa Fisica 2', '1985-02-15'),
('34567890123', 3, 'Pessoa Fisica 3', '1992-05-20'),
('45678901234', 4, 'Pessoa Fisica 4', '1988-07-10'),
('56789012345', 5, 'Pessoa Fisica 5', '1995-09-30'),
('67890123456', 6, 'Pessoa Fisica 6', '1982-12-05'),
('78901234567', 7, 'Pessoa Fisica 7', '1998-04-18'),
('89012345678', 8, 'Pessoa Fisica 8', '1980-06-25'),
('90123456789', 9, 'Pessoa Fisica 9', '1993-08-12'),
('01234567890', 10, 'Pessoa Fisica 10', '1987-11-28');

-- pessoajuridica
-- Inserção de 10 registros na tabela pessoajuridica, associando cada CNPJ a um cliente existente e fornecendo razão social e nome fantasia fictícios.
INSERT INTO pessoajuridica (cnpj, id_cliente, razao_social, nome_fantasia) VALUES
('12345678901234', 11, 'Empresa 1', 'Fantasia 1'),
('23456789012345', 12, 'Empresa 2', 'Fantasia 2'),
('34567890123456', 13, 'Empresa 3', 'Fantasia 3'),
('45678901234567', 14, 'Empresa 4', 'Fantasia 4'),
('56789012345678', 15, 'Empresa 5', 'Fantasia 5'),
('67890123456789', 16, 'Empresa 6', 'Fantasia 6'),
('78901234567890', 17, 'Empresa 7', 'Fantasia 7'),
('89012345678901', 18, 'Empresa 8', 'Fantasia 8'),
('90123456789012', 19, 'Empresa 9', 'Fantasia 9'),
('01234567890123', 20, 'Empresa 10', 'Fantasia 10');

-- empresas
-- Inserção de 10 empresas na tabela empresa, cada uma com uma razão social, nome fantasia e CNPJ fictícios.
INSERT INTO empresa (razao_social, nome_fantasia, cnpj) VALUES
('Empresa A', 'Fantasia A', '12345678901234'),
('Empresa B', 'Fantasia B', '23456789012345'),
('Empresa C', 'Fantasia C', '34567890123456'),
('Empresa D', 'Fantasia D', '45678901234567'),
('Empresa E', 'Fantasia E', '56789012345678'),
('Empresa F', 'Fantasia F', '67890123456789'),
('Empresa G', 'Fantasia G', '78901234567890'),
('Empresa H', 'Fantasia H', '89012345678901'),
('Empresa I', 'Fantasia I', '90123456789012'),
('Empresa J', 'Fantasia J', '01234567890123');

-- ativos
-- Inserção de 10 ativos na tabela ativo, associando cada ativo a uma empresa existente e fornecendo informações como sigla, nome, atualização, quantidade de papéis, valor máximo, valor mínimo e valor fictícios.
INSERT INTO ativo (id_empresa, sigla, nome, atualizacao, quantidades_papeis, valor_max, valor_min, valor) VALUES
(1, 'EMP-A', 'Ativo Empresa A', '2022-12-01', 1000, 150.00, 120.00, 140.50),
(2, 'EMP-B', 'Ativo Empresa B', '2022-12-01', 800, 220.50, 180.20, 200.75),
(3, 'EMP-C', 'Ativo Empresa C', '2022-12-01', 1200, 180.75, 150.25, 165.30),
(4, 'EMP-D', 'Ativo Empresa D', '2022-12-01', 1500, 300.20, 250.60, 280.90),
(5, 'EMP-E', 'Ativo Empresa E', '2022-12-01', 900, 90.50, 70.80, 80.25),
(6, 'EMP-F', 'Ativo Empresa F', '2022-12-01', 700, 160.20, 130.40, 145.60),
(7, 'EMP-G', 'Ativo Empresa G', '2022-12-01', 1100, 120.75, 100.30, 110.50),
(8, 'EMP-H', 'Ativo Empresa H', '2022-12-01', 1300, 240.90, 200.25, 220.75),
(9, 'EMP-I', 'Ativo Empresa I', '2022-12-01', 1000, 170.40, 140.60, 155.80),
(10, 'EMP-J', 'Ativo Empresa J', '2022-12-01', 800, 280.30, 230.50, 260.20);

-- carteira
-- Inserção de 30 registros na tabela carteira, associando cada item a um cliente e um ativo existentes, e fornecendo quantidades fictícias.
INSERT INTO carteira (id_cliente, id_ativo, quantidade) VALUES
(1, 1, 50),
(2, 5, 30),
(3, 8, 25),
(4, 2, 40),
(5, 10, 20),
(6, 3, 35),
(7, 7, 45),
(8, 9, 28),
(9, 4, 22),
(10, 6, 18),
(11, 10, 33),
(12, 1, 15),
(13, 3, 50),
(14, 8, 28),
(15, 5, 20),
(16, 9, 42),
(17, 6, 37),
(18, 2, 19),
(19, 7, 25),
(20, 4, 30),
(1, 5, 40),
(2, 1, 28),
(3, 10, 15),
(4, 3, 22),
(5, 8, 35),
(6, 6, 18),
(7, 2, 25),
(8, 9, 50),
(9, 7, 30),
(10, 4, 45);