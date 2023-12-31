# Manual de Configuração - Autenticação SolinfBroker

Este manual fornece instruções sobre como configurar o serviço de autenticação do SolinfBroker no Kubernetes.

## Deployment

O arquivo `autenticacao-solinfbroker-deployment.yml` contém a definição do Deployment para o serviço de autenticação.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: autenticacao-solinfbroker
  labels:
    app: autenticacao-solinfbroker
spec:
  replicas: 4
  selector:
    matchLabels:
      app: autenticacao-solinfbroker
  template:
    metadata:
      labels:
        app: autenticacao-solinfbroker
    spec:
      containers:
      - name: autenticacao-solinfbroker
        image: jvlr9510/jvlr9510-api-auth:1.0
        ports:
        - containerPort: 8081
```

**Instruções:**
1. Este Deployment define quatro réplicas do serviço de autenticação.
2. O contêiner usa a imagem `jvlr9510/jvlr9510-api-auth:1.0`.
3. O serviço é exposto na porta `8081`.

## Service

O arquivo `autenticacao-solinfbroker-service.yml` contém a definição do Service para o serviço de autenticação.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: autenticacao-solinfbroker-service
  labels:
    app: autenticacao-solinfbroker
spec:
  selector:
    app: autenticacao-solinfbroker
  ports:
    - protocol: TCP
      port: 8081
      targetPort: 8081
  type: NodePort
```

**Instruções:**
1. Este Service seleciona os pods rotulados como `app: autenticacao-solinfbroker`.
2. O serviço é exposto na porta `8081` e direciona o tráfego para o mesmo na porta `8081` dos pods.

**Observações:**
- Certifique-se de que as imagens e as portas estejam configuradas conforme necessário para o seu ambiente.

Este é um guia básico, e pode ser necessário ajustar a configuração conforme necessário, dependendo dos requisitos específicos do projeto.
