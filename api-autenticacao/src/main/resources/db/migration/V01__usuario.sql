-- Tabela para armazenar informações sobre clientes
CREATE TABLE cliente (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    tipo VARCHAR(2) NOT NULL,
    nome_usuario VARCHAR(50) NOT NULL,
    senha VARCHAR NOT NULL,
    email VARCHAR(50) NOT NULL,
    saldo DOUBLE PRECISION DEFAULT 0.0
);

-- Tabela com as permissões
create table permissoes(
    id BIGSERIAL primary key,
    permissao varchar not null
);

-- Tabela de relacionamento entre os clientes e permissões
create table cliente_permissao(
    id_cliente BIGSERIAL not null,
    id_permissao BIGSERIAL not null,

    foreign key (id_cliente) references cliente(id),
    foreign key (id_permissao) references permissoes(id)
);

-- Tabela para armazenar informações sobre pessoas físicas, com chave estrangeira referenciando a tabela cliente
CREATE TABLE pessoafisica (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    id_cliente BIGSERIAL NOT NULL,
    nome VARCHAR(150) NOT NULL,
    nascimento DATE NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

-- Tabela para armazenar informações sobre pessoas jurídicas, com chave estrangeira referenciando a tabela cliente
CREATE TABLE pessoajuridica (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    cnpj VARCHAR(14) NOT NULL,
    id_cliente BIGSERIAL NOT NULL,
    razao_social VARCHAR(150) NOT NULL,
    nome_fantasia VARCHAR(150) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

-- Insert das Permissoes iniciais do sistema
insert into permissoes(id,permissao) values (1,'ROLE_ADMIN');
insert into permissoes(id,permissao) values (2,'ROLE_USER');