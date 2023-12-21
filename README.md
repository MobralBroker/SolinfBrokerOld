# SolinfBroker
Project Broker Solinftec microservices 

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
