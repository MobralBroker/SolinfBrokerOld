-- Tabelas base para o sistema SolinfBroker
-- Tabela para armazenar informações sobre clientes
CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY NOT NULL,
    tipo VARCHAR(2) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    saldo DOUBLE PRECISION NOT NULL
);

-- Tabela para armazenar informações sobre pessoas físicas, com chave estrangeira referenciando a tabela cliente
CREATE TABLE pessoafisica (
    id_pessoafisica SERIAL PRIMARY KEY NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    id_cliente INT NOT NULL,
    nome VARCHAR(150) NOT NULL,
    nascimento DATE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- Tabela para armazenar informações sobre pessoas jurídicas, com chave estrangeira referenciando a tabela cliente
CREATE TABLE pessoajuridica (
    id_pessoajuridica SERIAL PRIMARY KEY NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    id_cliente INT NOT NULL,
    razao_social VARCHAR(150) NOT NULL,
    nome_fantasia VARCHAR(150) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- Tabela para armazenar informações sobre ordens de compra, com chave estrangeira referenciando a tabela cliente
CREATE TABLE ordem_compra (
    id_compra SERIAL PRIMARY KEY NOT NULL,
    id_cliente INT NOT NULL,
    valor_compra DOUBLE PRECISION NOT NULL,
    data_compra DATE NOT NULL,
    quantidade INT NOT NULL,
    status VARCHAR(3) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- Tabela para armazenar informações sobre ordens de venda, com chave estrangeira referenciando a tabela cliente
CREATE TABLE ordem_venda (
    id_venda SERIAL PRIMARY KEY NOT NULL,
    id_cliente INT NOT NULL,
    valor_venda DOUBLE PRECISION NOT NULL,
    data_venda DATE NOT NULL,
    quantidade INT NOT NULL,
    status VARCHAR(3) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
);

-- Tabela para armazenar informações sobre empresas
CREATE TABLE empresa (
    id_empresa SERIAL PRIMARY KEY NOT NULL,
    razao_social VARCHAR(150) NOT NULL,
    nome_fantasia VARCHAR(150) NOT NULL,
    cnpj VARCHAR(14) NOT NULL
);

-- Tabela para armazenar informações sobre ativos, com chave estrangeira referenciando a tabela empresa
CREATE TABLE ativo (
    id_ativo SERIAL PRIMARY KEY NOT NULL,
    id_empresa INT NOT NULL,
    sigla VARCHAR(10) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    atualizacao DATE NOT NULL,
    quantidades_papeis INT NOT NULL,
    valor_max DOUBLE PRECISION NOT NULL,
    valor_min DOUBLE PRECISION NOT NULL,
    valor DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa)
);

-- Tabela para armazenar histórico de preços de ativos, com chave estrangeira referenciando a tabela ativo
CREATE TABLE IF NOT EXISTS historico_preco (
    id SERIAL PRIMARY KEY NOT NULL,
    id_ativo INT NOT NULL,
    data_valor DATE NOT NULL,
    valor_do_ativo DOUBLE PRECISION NOT NULL,
    FOREIGN KEY (id_ativo) REFERENCES ativo(id_ativo)
);

-- Tabela para armazenar informações sobre a carteira de um cliente, com chaves estrangeiras referenciando as tabelas cliente e ativo
CREATE TABLE carteira (
    id_carteira SERIAL PRIMARY KEY NOT NULL,
    id_cliente INT NOT NULL,
    id_ativo INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_ativo) REFERENCES ativo(id_ativo)
);

-- Trigger de Log para gerar Histório de Preços
-- Função que é executada após uma atualização na tabela ativo, registrando o histórico de preços
CREATE OR REPLACE FUNCTION after_update_ativo()
RETURNS TRIGGER AS $$
BEGIN
    -- Verifica se o valor da coluna "valor" foi alterado
    IF NEW.valor <> OLD.valor THEN
        -- Insere um novo registro na tabela historico_preco
        INSERT INTO historico_preco (id_ativo, data_valor, valor_do_ativo)
        VALUES (OLD.id_ativo, CURRENT_DATE, OLD.valor);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger associada à função, executada após uma atualização na tabela ativo
CREATE TRIGGER after_update_ativo
AFTER UPDATE ON ativo
FOR EACH ROW
EXECUTE FUNCTION after_update_ativo();