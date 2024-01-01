FROM postgres

# Configurar variáveis de ambiente, se necessário
ENV POSTGRES_PASSWORD=root

# Copiar o script SQL para o diretório de inicialização
COPY init.sql /docker-entrypoint-initdb.d/init.sql