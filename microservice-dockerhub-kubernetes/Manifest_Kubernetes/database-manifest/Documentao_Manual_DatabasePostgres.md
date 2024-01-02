# Manual de Configuração - Database SolinfBroker

Este manual fornece instruções detalhadas sobre como configurar o banco de dados do SolinfBroker no Kubernetes usando os arquivos YAML fornecidos.

## Deployment (`database-solinfbroker-deployment.yml`)

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: database-solinfbroker
  namespace: solinfbroker
spec:
  replicas: 4
  selector:
    matchLabels:
      app: database-solinfbroker
  template:
    metadata:
      labels:
        app: database-solinfbroker
    spec:
      containers:
      - name: database-solinfbroker
        image: postgres
        env:
        - name: POSTGRES_PASSWORD
          value: "root"
        ports:
        - containerPort: 5432
        volumeMounts:
        - name: postgres-data-volume
          mountPath: /var/lib/postgresql/data
        - name: initdb
          mountPath: /docker-entrypoint-initdb.d
      volumes:
      - name: postgres-data-volume
        persistentVolumeClaim:
          claimName: postgres-data-pvc
      - name: initdb
        configMap:
          name: postgres-init-script
```

**Instruções:**
1. Este Deployment define quatro réplicas do banco de dados PostgreSQL.
2. A senha do PostgreSQL é configurada como "root".
3. O banco de dados é exposto na porta `5432`.
4. Os dados persistentes do banco de dados são montados em um Persistent Volume Claim (PVC) chamado `postgres-data-pvc`.
5. Um volume chamado `initdb` é montado no diretório `/docker-entrypoint-initdb.d` para executar scripts de inicialização.

## Service (`database-solinfbroker-service.yml`)

```yaml
apiVersion: v1
kind: Service
metadata:
  name: database-solinfbroker-service
  namespace: solinfbroker
spec:
  selector:
    app: database-solinfbroker
  ports:
  - protocol: TCP
    port: 5432
    targetPort: 5432
  type: NodePort
```

**Instruções:**
1. Este Service seleciona os pods rotulados como `app: database-solinfbroker`.
2. O serviço é exposto na porta `5432` e direciona o tráfego para o mesmo na porta `5432` dos pods.

## PersistentVolumeClaim (`postgres-data-pvc.yml`)

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: postgres-data-pvc
  namespace: solinfbroker
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi
```

**Instruções:**
1. Este Persistent Volume Claim (PVC) define uma solicitação de armazenamento de 1 gigabyte e acessa os modos ReadWriteOnce.

## PersistentVolume (`postgres-data-pv.yml`)

```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: postgres-data-pv
  namespace: solinfbroker
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: ./postgres-data
```

**Instruções:**
1. Este Persistent Volume (PV) define uma capacidade de armazenamento de 1 gigabyte e acessa os modos ReadWriteOnce.
2. Os dados do PV são armazenados localmente no host no diretório `./postgres-data`. Certifique-se de que este diretório exista no host.

**Observações:**
- Certifique-se de que as imagens e as portas estejam configuradas conforme necessário para o seu ambiente.
- Este é um guia básico, e pode ser necessário ajustar a configuração conforme necessário, dependendo dos requisitos específicos do projeto.
