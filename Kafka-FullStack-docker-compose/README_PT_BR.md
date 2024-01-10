# Configuração do Docker Compose para a Plataforma Kafka

[![Status das Ações](https://github.com/conduktor/kafka-stack-docker-compose/workflows/CI/badge.svg)](https://github.com/conduktor/kafka-stack-docker-compose/actions)

# Um projeto de código aberto por [![Conduktor.io](https://www.conduktor.io/images/logo.svg)](https://conduktor.io/)

Este projeto é patrocinado por [Conduktor.io](https://www.conduktor.io/), uma interface gráfica de usuário para o Apache Kafka.

Uma vez que você tenha iniciado seu cluster, você pode usar o Conduktor para gerenciá-lo facilmente. Basta conectar em `localhost:9092`. Se estiver no Mac ou Windows e quiser se conectar de outro contêiner, use `host.docker.internal:29092`.

# kafka-stack-docker-compose

Isso replica o mais próximo possível as configurações reais de implantação, onde você tem seus servidores ZooKeeper e Kafka realmente distintos entre si. Isso resolve todas as complicações de rede que surgem com o Docker e o Docker Compose, sendo compatível entre plataformas.

**ATUALIZAÇÃO**: Não são mais necessárias alterações no arquivo `/etc/hosts`. Explicações em: https://rmoff.net/2018/08/02/kafka-listeners-explained/

## Versão do Stack

- Conduktor Plataform: latest
- Zookeeper version: 3.6.3 (Confluent latest)
- Kafka version: 3.3.0 (Confluent latest)
- Kafka Schema Registry: Confluent latest
- Proxy de Rest do Kafka: Confluent latest
- Kafka Connect: Confluent latest
- ksqlDB Server: Confluent latest
- Zoonavigator: 1.1.1

Para uma ferramenta de interface do usuário para acessar seu cluster Kafka local, use a versão gratuita do [Conduktor](https://www.conduktor.io/get-started)

# Requisitos

O Kafka será exposto em `127.0.0.1` ou `DOCKER_HOST_IP` se definido no ambiente. (Provavelmente você não precisa defini-lo se não estiver usando o Docker-Toolbox)

## Docker-Toolbox
O Docker toolbox está [descontinuado](https://github.com/docker-archive/toolbox) e não é mantido há vários anos. Não podemos garantir que esse conjunto funcionará com o Docker Toolbox, mas se você quiser tentar mesmo assim, exporte seu ambiente antes de iniciar o conjunto:
```
export DOCKER_HOST_IP=192.168.99.100
```
(seu IP de máquina Docker geralmente é `192.168.99.100`)

## Suporte Apple M1
A plataforma Confluent suporta o Apple M1 (ARM64) desde a versão `7.2.0`! Basicamente, este conjunto funcionará sem problemas. 

Se você quiser rebaixar a versão da plataforma Confluent, existem duas maneiras:

1. Adicione `platform: linux/amd64`. Ele funcionará, pois o Docker é capaz de emular instruções AMD64.
2. Versões anteriores foram [construídas](https://github.com/arm64-compat/confluent-platform) para ARM64 pela comunidade. Se você quiser usá-la, basta alterar a imagem no arquivo .yml correspondente. Como não é uma imagem oficial, use-a por sua própria conta e risco. 

## Stack Completo

Para facilitar sua jornada com o Kafka, conecte-se a [localhost:8080](http://localhost:8080/)

login: `admin@admin.io`
senha: `admin`

- Conduktor Plataform : `$DOCKER_HOST_IP:8080`
- Single Zookeeper: `$DOCKER_HOST_IP:2181`
- Single kafka: `$DOCKER_HOST_IP:9092`
- Kafka Schema Registry: `$DOCKER_HOST_IP:8081`
- Kafka Rest Proxy: `$DOCKER_HOST_IP:8082`
- Kafka Connect: `$DOCKER_HOST_IP:8083`
- KSQL Server: `$DOCKER_HOST_IP:8088`
- (experimental) JMX port at `$DOCKER_HOST_IP:9001`

 Execute com:
 ```
 docker compose -f full-stack.yml up
 docker compose -f full-stack.yml down
 ```
** Observação: se você não conseguir se conectar a [localhost:8080](http://localhost:8080/), execute `docker compose -f full-stack.yml build` para reconstruir as mapeamentos de porta.

## Único Zookeeper / Único Kafka

Esta configuração atende à maioria dos requisitos de desenvolvimento.

- Zookeeper estará disponível em `$DOCKER_HOST_IP:2181`
- Kafka estará disponível em `$DOCKER_HOST_IP:9092`
- (experimental) Porta JMX em `$DOCKER_HOST_IP:9999`

Execute com:
```
docker compose -f zk-single-kafka-single.yml up
docker compose -f zk-single-kafka-single.yml down
```

## Único Zookeeper / Múltiplos Kafka

Se você quiser ter três corretores e experimentar com a replicação/falta de tolerância do Kafka.

- Zookeeper estará disponível em `$DOCKER_HOST_IP:2181`
- Kafka estará disponível em `$DOCKER_HOST_IP:9092,$DOCKER_HOST_IP:9093,$DOCKER_HOST_IP:9094`

Execute com:
```
docker compose -f zk-single-kafka-multiple.yml up
docker compose -f zk-single-kafka-multiple.yml down
```

## Múltiplos Zookeeper / Único Kafka

Se você quiser ter três nós do Zookeeper e experimentar com a tolerância a falhas do Zookeeper.

- Zookeeper estará disponível em `$DOCKER_HOST_IP:2181,$DOCKER_HOST_IP:2182,$DOCKER_HOST_IP:2183`
- Kafka estará disponível em `$DOCKER_HOST_IP:9092`
- (experimental) Porta JMX em `$DOCKER_HOST_IP:9999`

Execute com:
```
docker compose -f zk-multiple-kafka-single.yml up
docker compose -f zk-multiple-kafka-single.yml down
```


## Múltiplos Zookeeper / Múltiplos Kafka

Se você quiser ter três nós do Zookeeper e três corretores do Kafka para experimentar com a configuração de produção.

- Zookeeper estará disponível em `$DOCKER_HOST_IP:2181,$DOCKER_HOST_IP:2182,$DOCKER_HOST_IP:2183`
- Kafka estará disponível em `$DOCKER_HOST_IP:9092,$DOCKER_HOST_IP:9093,$DOCKER_HOST_IP:9094`

Execute com:
```
docker compose -f zk-m
