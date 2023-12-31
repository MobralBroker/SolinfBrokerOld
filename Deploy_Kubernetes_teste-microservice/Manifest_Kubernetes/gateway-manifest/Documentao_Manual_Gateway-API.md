# Manual de Configuração - Gateway SolinfBroker

Este manual fornece instruções detalhadas sobre como configurar o gateway do SolinfBroker no Kubernetes usando os arquivos YAML fornecidos.

## Deployment (`gateway-solinfbroker-deployment.yml`)

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: gateway-solinfbroker
  namespace: solinfbroker
spec:
  replicas: 4
  selector:
    matchLabels:
      app: gateway-solinfbroker
  template:
    metadata:
      labels:
        app: gateway-solinfbroker
    spec:
      containers:
      - name: gateway-solinfbroker
        image: jvlr9510/jvlr9510-api-gateway:1.0
        ports:
        - containerPort: 8080
```

**Instruções:**
1. Este Deployment define quatro réplicas do serviço de gateway SolinfBroker.
2. A imagem do gateway é definida como `jvlr9510/jvlr9510-api-gateway:1.0`.
3. O gateway é exposto na porta `8080`.

## Service (`gateway-solinfbroker-service.yml`)

```yaml
apiVersion: v1
kind: Service
metadata:
  name: gateway-solinfbroker-service
  namespace: solinfbroker
spec:
  selector:
    app: gateway-solinfbroker
  ports:
    - protocol: TCP
      port: 8080
      targetPort: 8080
  type: NodePort
```

**Instruções:**
1. Este Service seleciona os pods rotulados como `app: gateway-solinfbroker`.
2. O serviço é exposto na porta `8080` e direciona o tráfego para o mesmo na porta `8080` dos pods.

**Observações:**
- Certifique-se de que as imagens e as portas estejam configuradas conforme necessário para o seu ambiente.
- Este é um guia básico, e pode ser necessário ajustar a configuração conforme necessário, dependendo dos requisitos específicos do projeto.
