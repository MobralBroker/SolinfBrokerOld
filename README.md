# SolinfBroker
É um HomeBroker para fazer compras e vendas de ações das empresas cadastradas. Possui um ambiente de gestão da Empresa e de usuários. E possui uma dashboard para compra e venda de papeis.

### Casos de Uso
Para visualizar o casos de uso, precisa abrir este site: https://draw.io e importar o arquivo Diagramas1.1.drawio.


## FEATURES

 ### Administrador:

#### Usuario Admin

* Login
  * Gestão de Empresa
    * [GET POST] da empresa
    * [DELETE] da empresa (validar se não possui papeis em mercado)
    * [PUT] da empresa (desativar a empresa)
    * [GET] [GRAFICO] histórico dos Preços
    * X [PUT] add ou remover papeis
    * X [GET] [GRAFICO] Maiores Volumes
    * X Model: (valorMercado; )
  
  * Gestão de Usuário
    * [GET POST PUT DELETE] usuarios
    * [GET] Listar as ações que possui
    * [GET] Listar o Histórico de compra e venda
    * [PUT] Add dinheiro ao caixa
    * [PUT] Sacar Dinheiro do Caixa pessoal
	
### Cliente:
* Usuario
    * [PUT] Atualizar Informações Pessoais
    * [PUT] Add dinheiro ao caixa
    * [PUT] Sacar Dinheiro do Caixa pessoal
	
* DashBoard
    * [GET] Listar as ações que possui
    * [GET] Listar o Histórico de compra e venda
    * [GET] Listar as açoes do mercado
    * [POST] Criar Ordem de Venda e de Compra
    * [PUT] Cancelar Ordem Enviada
    * [GET] Listar o gráfico da são selecionada
    
* Empresa
    * [GET] Detalhar informações da empresa s
[PUT] Add dinheiro ao caixa
[PUT] Sacar Dinheiro do Caixa pessoal
DashBoard

[GET] Listar as ações que possui
[GET] Listar o Histórico de compra e venda
[GET] Listar as açoes do mercado
[POST] Criar Ordem de Venda e de Compra
[PUT] Cancelar Ordem Enviada
[GET] Listar o gráfico da são selecionada
Empresa

[GET] Detalhar informações da empresa

# Banco de Dados do Projeto

A estrutura do banco de dados do projeto est  organizada na pasta "database". O script principal "database.sql", que cria as tabelas necess rias para o funcionamento do sistema.

## Diagramas do Banco de Dados (\diagrams)

Na pasta "diagrams", irá encontrar  o diagrama de entidade e relacionamento, que fornece uma visao geral da estrutura do banco de dados. E também uma aba detalhada, especificando a finalidade de cada campo no banco de dados.

## Scripts do Banco de Dados (\scripts)

### Populaao de Dados

- **Population.sql:** Este script é destinado a popular as tabelas de cliente, pessoa física, pessoa jurídica, empresa, ativo e carteira. Note que esses dados sao apenas para fins de teste.

- **Population Orders.sql:** Este script preenche as tabelas de ordens de compra e venda, utilizado para cen rios de teste.

### Views e Procedures

- **View Cliente.sql:** Contém o script para uma view que valida se um cliente é pessoa física ou jurídica, trazendo o resultado da busca.

- **Procedure Cliente.sql:** Este script gera uma procedure que possibilita buscas por CPF, CNPJ e ID, validando se a entidade é uma pessoa física ou jurídica durante a busca.

## Observaoes

Durante a execução dos scripts, É importante verificar se estao sendo inseridas informações dependentes corretamente. Por exemplo, É necessário inserir informações em "pessoafisica" e "pessoajuridica" antes de incluir um cliente no sistema.