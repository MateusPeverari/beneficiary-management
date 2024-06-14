# Beneficiary Management API

## Descrição

Este projeto é uma API para gerenciar beneficiários e seus documentos, desenvolvida utilizando a arquitetura hexagonal. A API expõe endpoints para executar as seguintes operações:

- Cadastrar um beneficiário junto com seus documentos;
- Listar todos os beneficiários cadastrados;
- Listar todos os documentos de um beneficiário a partir de seu id;
- Atualizar os dados cadastrais de um beneficiário;
- Remover um beneficiário.

## Funcionalidades

### Endpoints

### Cadastrar um Beneficiário
- **URL:** `/beneficiaries`
- **Método:** POST
- **Descrição:** Cadastra um novo beneficiário junto com seus documentos.
- **Request Body:**
    ```json
    {
        "nome": "string",
        "telefone": "string",
        "dataNascimento": "string (date)",
        "documentos": [
            {
                "tipoDocumento": "string",
                "descricao": "string"
            }
        ]
    }
    ```
- **Responses:**
    - `201`: Beneficiário cadastrado com sucesso
    - `400`: Requisição inválida
    - `400`: Entidade não processável

## Listar Todos os Beneficiários
- **URL:** /beneficiaries
- **Método:** GET
- **Descrição:** Lista todos os beneficiários cadastrados.
- **Responses:**
  - `201`: Beneficiário cadastrado com sucesso
  - `400`: Requisição inválida
  - `400`: Entidade não processável

## Listar Documentos de um Beneficiário
- **URL:** /beneficiaries/{beneficiaryId}/documents
- **Método:** GET
- **Descrição:** Lista todos os documentos de um beneficiário a partir de seu id.
- **Responses:**
  - `201`: Beneficiário cadastrado com sucesso
  - `400`: Requisição inválida
  - `400`: Entidade não processável

## Atualizar Dados de um Beneficiário
- **URL:** /beneficiaries/{beneficiaryId}
- **Método:** PUT
- **Descrição:** Atualiza os dados cadastrais de um beneficiário.
- **Request Body:**
  ```json
  {
    "nome": "string",
    "telefone": "string",
    "dataNascimento": "string (date)",
    "documentos": [
      {
        "tipoDocumento": "string",
        "descricao": "string"
      }
    ]
  }

- **Responses:**
  - `201`: Beneficiário cadastrado com sucesso
  - `400`: Requisição inválida
  - `400`: Entidade não processável

# Remover um Beneficiário
- **URL:** /beneficiaries/{beneficiaryId}
- **Método:** DELETE
- **Descrição:** Remove um beneficiário.
- **Responses:**
  - `201`: Beneficiário cadastrado com sucesso
  - `400`: Requisição inválida
  - `400`: Entidade não processável

# Arquitetura Hexagonal
O projeto foi desenvolvido utilizando a Arquitetura Hexagonal (também conhecida como Arquitetura de Portas e Adaptadores), que promove uma separação clara entre a lógica de negócios e a infraestrutura.

## Benefícios da Arquitetura Hexagonal
- **Isolamento da Lógica de Negócios:** Facilita a manutenção e evolução da aplicação ao isolar a lógica de negócios dos detalhes de implementação.
- **Testabilidade:** Torna os componentes da aplicação mais fáceis de testar de forma isolada.
- **Flexibilidade:** Permite trocar componentes de infraestrutura (como bancos de dados, serviços externos) com impacto mínimo no código da lógica de negócios.
- **Facilidade de Integração:** Facilita a integração com diferentes interfaces de usuário e sistemas externos através de adaptadores.

# Como Executar o Projeto
Siga os passos abaixo para executar o projeto:

## Clonar o Repositório:
```bash
git clone https://github.com/MateusPeverari/beneficiary-management.git
```

## Executar o Maven Clean Install:
```bash
mvn clean install
```
## Executar o Projeto na IDE:
1. Abra o projeto em sua IDE preferida (IntelliJ IDEA, Eclipse, etc.).
2. Execute a aplicação a partir da classe principal.

## Acessar o Swagger UI:
Após iniciar o projeto, acesse a URL abaixo para visualizar e interagir com a documentação da API (Swagger UI):
[http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)