# Instalação do Kubernetes no Ubuntu LTS 22.04

Este guia fornece instruções simples para instalar o Kubernetes em um ambiente Ubuntu LTS 22.04.

## Passo 1: Atualizar o sistema e instalar dependências

```bash
sudo apt-get update
sudo apt-get install -y apt-transport-https ca-certificates curl
```

## Passo 2: Adicionar a chave GPG do Kubernetes

```bash
curl -fsSL https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo gpg --dearmor -o /usr/share/keyrings/kubernetes-archive-keyring.gpg
```

## Passo 3: Adicionar o repositório do Kubernetes

```bash
echo "deb [signed-by=/usr/share/keyrings/kubernetes-archive-keyring.gpg] https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee /etc/apt/sources.list.d/kubernetes.list
```

## Passo 4: Atualizar o sistema e instalar as versões específicas do Kubernetes

```bash
sudo apt-get update
sudo apt-get install -y kubelet kubeadm kubectl
```

## Passo 5: Desativar swap

```bash
sudo swapoff -a
```

## Passo 6: Configurações do runtime containerd

```bash
sudo containerd config default | sudo tee /etc/containerd/config.toml
# Alterar "SystemdCgroup: false" para "SystemdCgroup: true" em sudo vim /etc/containerd/config.toml
sudo vim /etc/containerd/config.toml
# Reiniciar serviço do runtime containerd
sudo systemctl restart containerd
```

## Passo 7: Iniciar o cluster Kubernetes

```bash
sudo kubeadm init
```

## Passo 8: Configurar o kubeconfig para o usuário atual

```bash
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

## Passo 9: Instalar o add-on de rede (Weave)

```bash
kubectl apply -f https://github.com/weaveworks/weave/releases/download/v2.8.1/weave-daemonset-k8s.yaml
```

## Passo 10: Aplicar um "Taint" no nó master (opcional)

```bash
kubectl taint nodes --all node-role.kubernetes.io/control-plane-
```

Consulte a [documentação oficial do Kubernetes](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/), para informações detalhadas.
