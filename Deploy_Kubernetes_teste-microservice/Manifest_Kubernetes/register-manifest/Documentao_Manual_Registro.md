# Manual de Configuração - Registro SolinfBroker

Este manual fornece instruções detalhadas sobre como configurar o serviço de registro do SolinfBroker no Kubernetes usando os arquivos YAML fornecidos.

## Deployment (`registro-solinfbroker-deployment.yml`)

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: registro-solinfbroker
  namespace: solinfbroker
spec:
  replicas: 4
  selector:
    matchLabels:
      app: registro-solinfbroker
  template:
    metadata:
      labels:
        app: registro-solinfbroker
    spec:
      containers:
      - name: registro-solinfbroker
        image: jvlr9510/jvlr9510-registro-solinfbroker:1.0
        ports:
        - containerPort: 8761
```

**Instruções:**
1. Este Deployment define quatro réplicas do serviço de registro SolinfBroker.
2. A imagem do registro é definida como `jvlr9510/jvlr9510-registro-solinfbroker:1.0`.
3. O serviço de registro é exposto na porta `8761` no container.

## Service (`registro-solinfbroker-service.yml`)

```yaml
apiVersion: v1
kind: Service
metadata:
  name: registro-solinfbroker-service
spec:
  selector:
    app: registro-solinfbroker
  ports:
    - protocol: TCP
      port: 8761
      targetPort: 8761
  type: NodePort
```

**Instruções:**
1. Este Service seleciona os pods rotulados como `app: registro-solinfbroker`.
2. O serviço é exposto na porta `8761` e direciona o tráfego para o mesmo na porta `8761` dos pods.

**Observações:**
- Certifique-se de que as imagens e as portas estejam configuradas conforme necessário para o seu ambiente.
- Este é um guia básico, e pode ser necessário ajustar a configuração conforme necessário, dependendo dos requisitos específicos do projeto.
