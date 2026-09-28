# DoeFácil

Aplicação full-stack para gerenciamento de ONGs, colaboradores e doações.

## Requisitos

Antes de iniciar, verifique se sua máquina tem instalado:

- Docker
- Docker Compose
- Node.js 20+
- npm
- Java 21
- Maven (opcional, quando for rodar os serviços sem Docker)

## Estrutura do projeto

- `docker/` — configuração do banco e dos serviços com Docker Compose
- `colaboradores/` — microsserviço de colaboradores
- `ongs/` — microsserviço de ONGs
- `doacao/` — microsserviço de doações
- `doe-facil/` — frontend em Vue + Vite

## 1) Clonar o projeto

```bash
git clone <URL_DO_REPOSITORIO>
cd doe_facil
```

## 2) Subir os serviços backend com Docker

Na raiz do projeto:

```bash
docker compose -f docker/docker-compose.yaml up -d --build
```

Isso irá subir:

- PostgreSQL em `localhost:5432` (internamente no container `db_doe_facil`)
- ONGs em `http://localhost:8082`
- Colaboradores em `http://localhost:8081`
- Doações em `http://localhost:8083`

Para verificar se os containers estão ativos:

```bash
docker compose -f docker/docker-compose.yaml ps
```

Para ver logs:

```bash
docker compose -f docker/docker-compose.yaml logs -f
```

## 3) Rodar o frontend

Entre na pasta do frontend:

```bash
cd doe-facil
```

Instale as dependências:

```bash
npm install
```

Se o binário do Vite estiver com problema de permissão, execute:

```bash
chmod +x node_modules/.bin/vite
```

Em seguida, inicie o ambiente de desenvolvimento:

```bash
npm run dev -- --host 0.0.0.0
```

A interface estará disponível em:

```text
http://localhost:5173
```

## 4) Rodar os microsserviços manualmente sem Docker

Se preferir rodar sem Docker, siga os passos abaixo.

### 4.1 ONGs

```bash
cd ongs
./mvnw clean install
./mvnw spring-boot:run
```

A API fica em:

```text
http://localhost:8082
```

### 4.2 Colaboradores

```bash
cd colaboradores
./mvnw clean install
./mvnw spring-boot:run
```

A API fica em:

```text
http://localhost:8081
```

### 4.3 Doações

```bash
cd doacao
./mvnw clean install
./mvnw spring-boot:run
```

A API fica em:

```text
http://localhost:8083
```

## 5) Acesso ao banco PostgreSQL

O banco da aplicação é criado automaticamente pelo Docker Compose. As credenciais default estão em `docker/docker-compose.yaml`:

- Usuário: `devUser`
- Senha: `devPass`
- Banco: `doeFacil`

## 6) Credenciais padrão de acesso

Para testar a aplicação, a documentação antiga indica:

```text
Usuário: 00.134.505/0001-49
Senha: 123456
```

## 7) Observações

- Caso o Vite dê erro `Permission denied`, rode:

```bash
chmod +x node_modules/.bin/vite
```

- Caso o Docker esteja com containers antigos, rode:

```bash
docker compose -f docker/docker-compose.yaml down -v
docker compose -f docker/docker-compose.yaml up -d --build
```

- Para ver logs dos serviços:

```bash
docker compose -f docker/docker-compose.yaml logs -f ongs_srv
```

## 8) Endpoints principais

### ONGs

- `POST /ongs` — criar ONG
- `GET /ongs/cep/{cep}` — consultar CEP
- `GET /ongs/{id}` — buscar ONG por id
- `POST /ongs/login` — login

### Colaboradores

- `POST /colaboradores` — criar colaborador
- `GET /colaboradores/{id}` — buscar colaborador por id

### Doações

- `POST /doacoes` — criar doação
- `GET /doacoes` — listar doações
- `GET /doacoes/{id}` — buscar doação por id

## 9) Troubleshooting

### Docker não inicia

```bash
docker compose -f docker/docker-compose.yaml up --build
```

### Frontend não sobe

```bash
rm -rf node_modules package-lock.json
npm install
npm run dev -- --host 0.0.0.0
```

### Erro de permissão no Vite

```bash
chmod +x node_modules/.bin/vite
```

## 10) Contribuição

Para contribuir com o projeto:

1. Crie uma branch nova
2. Faça suas alterações
3. Teste o fluxo localmente
4. Abra um pull request

---

Se quiser, posso também criar uma versão em português mais enxuta ou incluir comandos específicos de teste para cada microserviço.
