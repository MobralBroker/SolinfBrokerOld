# Composição Docker - PostgreSQL e API de Autenticação

Este é um arquivo `docker-compose.yml` que descreve a composição de dois serviços: PostgreSQL e API de Autenticação.

## Serviço PostgreSQL

### Imagem:
- `jvlr9510/debeziumpostgres:dev`

### Porta:
- Mapeia a porta local `5432` para a porta do contêiner `5432`

### Configurações:
- `POSTGRES_DB`: Nome do banco de dados - "database_master"
- `POSTGRES_USER`: Nome de usuário do PostgreSQL - "root"
- `POSTGRES_PASSWORD`: Senha do usuário do PostgreSQL - "admin"
- `POSTGRES_HOST_AUTH_METHOD`: Método de autenticação do host - "scram-sha-256"

### Comando Adicional:
- Define algumas configurações adicionais do PostgreSQL, como o nível de log lógico.

### Volume:
- `pg_data`: Volume persistente para armazenar os dados do PostgreSQL.

## Serviço API de Autenticação

### Imagem:
- `jvlr9510/api-autenticacao:dev`

### Porta:
- Mapeia a porta local `8081` para a porta do contêiner `8081`

### Dependência:
- Dependente do serviço PostgreSQL para iniciar.

### Configurações:
- `SPRING_DATASOURCE_URL`: URL do banco de dados PostgreSQL.
- `SPRING_DATASOURCE_USERNAME`: Nome de usuário do banco de dados PostgreSQL - "root"
- `SPRING_DATASOURCE_PASSWORD`: Senha do banco de dados PostgreSQL - "admin"
- `SPRING_FLYWAY_BASELINE_ON_MIGRATE`: Configuração Flyway para inicializar a linha de base ao migrar - "true"
- `SPRING_FLYWAY_TABLE`: Tabela usada pelo Flyway para controlar versões - "schema_version_api_autenticacao"
- `SERVER_PORT`: Porta em que a aplicação API de Autenticação será executada - "8081"

### Volume:
- Sem volume específico definido.

## Utilização do Docker Compose:
- Execute `docker compose up -d` para iniciar os serviços conforme as configurações definidas.
- Certifique-se de revisar as imagens usadas e adapte conforme necessário.
- Para mais informações sobre o Docker Compose, consulte a [documentação oficial](https://docs.docker.com/compose/).

Este é um documento destiando ao projeto SolinfBroker da equipe Mobral da Solinftec.
Em fase de testes|Homologação
