# Configuração do Ambiente Kubernetes - PostgreSQL e API de Autenticação

Este guia fornece instruções para configurar um ambiente Kubernetes para os serviços PostgreSQL e API de Autenticação.

## Passo 1: Criar Namespaces

Aplique as configurações do manifesto namespaces.yml, use o comando:

```bash
kubectl apply -f namespaces.yml
```

Isso definirá as namespaces necessárias para o ambiente.

## Passo 2: Configurar PostgreSQL

1. Navegue até o diretório `database_postgresql`:

```bash
cd database_postgresql
```

2. Aplique as configurações no namespace `dev`:

```bash
kubectl apply -f . -n dev
```

Isso configurará o PostgreSQL no namespace `dev`.

## Passo 3: Configurar API de Autenticação

1. Navegue até o diretório `autenticacao-api`:

```bash
cd autenticacao-api
```

2. Aplique as configurações no namespace `dev`:

```bash
kubectl apply -f . -n dev
```

Isso configurará a API de Autenticação no namespace `dev`.

## Conclusão

Após a execução desses comandos, os serviços PostgreSQL e API de Autenticação estarão configurados e prontos para serem utilizados no ambiente Kubernetes. Certifique-se de que tudo foi aplicado corretamente e a aplicação está em execução.
