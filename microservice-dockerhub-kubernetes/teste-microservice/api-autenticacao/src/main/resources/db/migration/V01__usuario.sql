create table usuario(
    id BIGSERIAL primary key unique not null,
    login TEXT not null unique,
    password TEXT not null
);

create table permissoes(
    id BIGSERIAL primary key,
    permissao varchar not null
);

create table usuario_permissao(
    id_usuario BIGINT not null,
    id_permissao BIGINT not null,

    foreign key (id_usuario) references usuario(id),
    foreign key (id_permissao) references permissoes(id)
);

insert into permissoes(id,permissao) values (1,'ROLE_ADMIN');
insert into permissoes(id,permissao) values (2,'ROLE_USER');