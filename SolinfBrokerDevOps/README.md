# Dockerização da API de Autenticação e PostgreSQL

Este repositório contém os arquivos necessários para dockerizar a API de Autenticação e o PostgreSQL, ambos criados por jvlor9510. As imagens foram enviadas para o Docker Hub, permitindo uma fácil implantação e teste local utilizando Kubernetes.

## Docker Hub Repositórios:
- [Imagem API de Autenticação](https://hub.docker.com/repository/docker/jvlr9510/api-autenticacao)
- [Imagem PostgreSQL](https://hub.docker.com/repository/docker/jvlr9510/debeziumpostgres)

## Como Usar com Kubernetes Locais

### Pré-requisitos
- Certifique-se de ter o Kubernetes instalado. Consulte a documentação fornecida na pasta [Kubernetes](./Kubernetes) para obter instruções detalhadas.

### Executando a Aplicação
1. Clone este repositório:

   ```bash
   git clone git@github.com:MobralBroker/SolinfBroker.git 
   ```

2. Acesse a pasta `Kubernetes`:

   ```bash
   cd SolinfBroker/SolinfBrokerDevOps/Kubernetes/
   ```

3. Aplique as configurações para o namespace de desenvolvimento:

   ```bash
   kubectl apply -f namespaces.yml
   ```

4. Acesse as pastas `database_postgresql` e `autenticacao-api`, e execute:

   ```bash
   kubectl apply -f . -n dev
   ```

5. Verifique se os pods estão em execução:

   ```bash
   kubectl get pods -n dev
   ```

## Expondo a API para Testes Externos com Ngrok

[Ngrok](https://ngrok.com/) é uma ferramenta que cria túneis seguros para sua máquina local para a internet. Isso permite que você exponha localmente sua API para que outros desenvolvedores possam testar e validar.

### Instalando Ngrok no Ubuntu
1. Baixe o arquivo compactado do Ngrok:

   ```bash
   wget https://bin.equinox.io/c/4VmDzA7iaHb/ngrok-stable-linux-amd64.tgz
   ```

2. Descompacte o arquivo:

   ```bash
   tar -xzvf ngrok-stable-linux-amd64.tgz
   ```

3. Mova o executável para um diretório no seu caminho (`$PATH`), por exemplo, `/usr/local/bin`:

   ```bash
   sudo mv ngrok /usr/local/bin
   ```

### Uso Básico do Ngrok
1. Inicie sua aplicação localmente (por exemplo, a API de Autenticação):

   ```bash
   kubectl port-forward svc/api-autenticacao 8081:8081 -n dev
   ```

2. Em um novo terminal, execute o Ngrok:

   ```bash
   ngrok http 8081
   ```

3. O Ngrok gerará URLs públicas que você pode compartilhar para que outros possam acessar sua API localmente.

## Documentação Adicional
- [Documentação do Kubernetes](./Kubernetes/README.md)
- [Documentação do Ngrok](https://ngrok.com/docs)
- [Documentação API de Autenticação](https://github.com/jvlr9510/api-autenticacao)
- [Documentação Imagem PostgreSQL](https://hub.docker.com/repository/docker/jvlr9510/debeziumpostgres)

