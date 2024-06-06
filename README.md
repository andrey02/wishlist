# Wishlist Project

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/yourusername/wishlist)

Este é um projeto de API de Wishlist usando Spring Boot, MongoDB e Docker. A API permite adicionar, remover e listar produtos de uma wishlist de clientes.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.0
- MongoDB
- Maven
- Docker e Docker Compose
- JUnit e Mockito para testes unitários
- Cucumber para testes de comportamento

## Estrutura do Projeto
```sh
wishlist/ 
├── Dockerfile 
├── docker-compose.yml 
├── pom.xml 
└── src/ 
    ├── main/ 
    │   ├── java/com/test/wishlist/ 
    │   └── resources/ 
    └── test/ 
        ├── java/com/test/wishlist/ 
        └── resources/ 
            └── features/ 
```

## Instalação e Configuração

1. **Clone o repositório**:

    ```sh
    git clone https://github.com/andrey02/wishlist.git
    cd wishlist
    ```

2. **Configuração do MongoDB**:
    - O `docker-compose.yml` já está configurado para subir um container do MongoDB na porta 27017 com credenciais padrão. Certifique-se de que estas portas estejam livres na sua máquina.

3. **Configuração do Docker**:

   Certifique-se de ter o Docker e Docker Compose instalados na sua máquina. Você pode seguir as instruções de instalação aqui:
   - [Docker](https://docs.docker.com/get-docker/)
   - [Docker Compose](https://docs.docker.com/compose/install/)

## Construindo e Executando a Aplicação

1. **Construir e Rodar a Aplicação**:

    ```sh
    docker-compose up --build
    ```

    A aplicação estará disponível em `http://localhost:8080`.

2. **Parar e Remover os Containers**:

    ```sh
    docker-compose down
    ```

3. **Se preferir rodar apontando para uma instancia do MongoDB local**:
    
    ```sh
        spring.data.mongodb.uri=mongodb://root:example@127.0.0.1:27017/wishlist-db?authSource=admin
     ```
    alterar a propriedade spring.data.mongodb.uri= do arquivo 'application.properties' como no exemplo acima.
## Endpoints da API

### Adicionar Produto à Wishlist

- **URL**: `/wishlist/add`
- **Método**: `POST`
- **Corpo da Requisição**:
    ```json
    {
        "clientId": "client1",
        "productId": "prod1"
    }
    ```
- **Resposta**:
    ```json
    {
        "id": "string",
        "clientId": "client1",
        "productIds": ["prod1"]
    }
    ```

### Remover Produto da Wishlist

- **URL**: `/wishlist/remove`
- **Método**: `DELETE`
- **Corpo da Requisição**:
    ```json
    {
        "clientId": "client1",
        "productId": "prod1"
    }
    ```
- **Resposta**:
    ```json
    {
        "id": "string",
        "clientId": "client1",
        "productIds": []
    }
    ```

### Obter Wishlist por ID do Cliente

- **URL**: `/wishlist/{clientId}`
- **Método**: `GET`
- **Resposta**:
    ```json
    {
        "id": "string",
        "clientId": "client1",
        "productIds": ["prod1", "prod2"]

    }
    ```

### Verificar se um Produto está na Wishlist

- **URL**: `/wishlist/contains`
- **Método**: `POST`
- **Corpo da Requisição**:
    ```json
    {
        "clientId": "client1",
        "productId": "prod1"
    }
    ```
- **Resposta**:
    ```json
    true
    ```

Collection [Wishlist.postman_collection.json]() disponibilizado na raiz do projeto.

Feito por [andrey02](https://github.com/andrey02)