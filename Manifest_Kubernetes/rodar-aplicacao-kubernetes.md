# Guia de Instalação do SolinfBroker no Kubernetes usando Minikube com Docker Driver 


Este guia fornece instruções passo a passo sobre como instalar o SolinfBroker no Kubernetes usando o Minikube com o driver Docker.

## Pré-requisitos

1. [Instale o Minikube](https://minikube.sigs.k8s.io/docs/start/) em sua máquina.

2. [Instale o kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/) para interagir com o Kubernetes.

3. [Instale o Docker](https://docs.docker.com/get-docker/) em sua máquina.

## Configuração do Minikube

Certifique-se de que o driver do Minikube está configurado para usar o Docker. Abra um terminal e execute:

```bash
minikube config set driver docker
```

## Iniciando o Minikube

Inicie o Minikube usando o comando:

```bash
minikube start
```


### Aplique o manifesto do namespace:

```bash
kubectl apply -f solinfbroker-namespace.yml
```

### Aplique os demais manifestos dentro de cada pasta:

```bash
kubectl apply -f .
```

## Verificando os Pods

Após a aplicação dos manifestos, verifique se os pods foram criados com sucesso:

```bash
kubectl get pods -n solinfbroker
```

## Acessando o SolinfBroker

Uma vez que todos os pods estejam em execução, você pode acessar os serviços expostos. Utilize o comando `minikube service` para obter a URL do serviço desejado.

Exemplo:

```bash
minikube service -n solinfbroker postgresql-solinfbroker-service --url 
```
OU
```bash
minikube service -n autenticacao-solinfbroker-service--url 
```

Copie a URL gerada e acesse-a em seu navegador ou utilize ferramentas como o cURL ou o Postman para interagir com os serviços.

## Parando o Minikube

Quando você terminar de usar o SolinfBroker, você pode parar o Minikube com o seguinte comando:

```bash
minikube stop
```


Observação: Este guia é um exemplo básico e a aplicação está em fase de desenvolvimento. Alguns ajustes podem ser necessários de acordo com as atualizações do projeto.