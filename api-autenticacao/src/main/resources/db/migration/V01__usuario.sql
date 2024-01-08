CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY NOT NULL,
    tipo VARCHAR(2) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    saldo DOUBLE PRECISION DEFAULT 0.0
);

create table permissoes(
    id SERIAL primary key,
    permissao varchar not null
);

create table cliente_permissao(
    id_cliente SERIAL not null,
    id_permissao SERIAL not null,

    foreign key (id_cliente) references cliente(id_cliente),
    foreign key (id_permissao) references permissoes(id)
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


insert into permissoes(id,permissao) values (1,'ROLE_ADMIN');
insert into permissoes(id,permissao) values (2,'ROLE_USER');