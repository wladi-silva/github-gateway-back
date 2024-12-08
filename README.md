[![GitHub Projects](https://img.shields.io/badge/Projeto-GitHub%20Projects-FF5733)](https://github.com/users/wladi-silva/projects/1) [![Frontend](https://img.shields.io/badge/Frontend-GitHub%20Gateway%20Front-FFB400)](https://github.com/wladi-silva/github-gateway-front)

## Github Gateway

Este projeto fornece uma API que acessa informações de usuários e repositórios do GitHub. 
Utilizando o Spring Boot e Feign Client para a comunicação com a API do GitHub.

### Requisitos

* **Java 17 ou superior**
* **Maven 3.0.5 ou superior**
* **Acesso à API do GitHub (necessário um token de autenticação)**

#### Como Conseguir o Token do GitHub

1. Acesse o [GitHub](https://github.com) e faça login.
2. No menu superior, clique na foto de perfil e selecione **Settings**.
3. Em **Developer settings**, vá para **Personal access tokens**.
4. Clique em **Generate new token**, selecione as permissões (como **repo**) e gere o token.
5. Copie o token gerado e guarde-o em um local seguro, pois não será mostrado novamente.

Após isso, configure o token no seu projeto conforme necessário.

## Como Executar Localmente

### Clone o repositório

Siga os passos abaixo para rodar o projeto em seu ambiente local.

```bash
git clone https://github.com/wladi-silva/github-gateway-back.git
cd github-gateway-back
```

### Configuração do Token do GitHub

Antes de rodar o projeto, você deve configurar o token de autenticação do GitHub.   
Adicione o token no arquivo **application.properties**

```properties
github.token=seu-token-do-github
```

### Iniciar a Aplicação

Para iniciar a aplicação, execute o seguinte comando Maven:

```bash
mvn spring-boot:run
```

## Rotas da API Gateway

São disponibilizados dois principais endpoints:

```java
@GetMapping("/{username}")
public ResponseEntity<User> getUser(@PathVariable String username) {
    User user = githubClient.getUser(username);
    return ResponseEntity.ok(user);
}

@GetMapping("/{username}/repos")
public ResponseEntity<List<Repository>> getUserRepositories(@PathVariable String username) {
    List<Repository> repositories = githubClient.getUserRepositories(username);
    if (repositories.isEmpty()) {
        throw new GithubEmptyRepositoriesException();
    }
    return ResponseEntity.ok(repositories);
}
```

#### Exemplos de requisições

```bash
GET http://localhost:8080/api/github/wladi-silva
```
```bash
GET http://localhost:8080/api/github/wladi-silva/repos
```

#### Exemplos de respostas

```json
    "login": "wladi-silva",
    "url": "https://api.github.com/users/wladi-silva"
```
```json
    "name": "microservice-architecture",
    "url": "https://api.github.com/repos/wladi-silva/microservice-architecture",
    "description": "Arquitetura de microsserviços com Spring Cloud"
```

## Exceções

O projeto trata de exceções personalizadas e de falhas de comunicação com o GitHub.

```java
@ExceptionHandler(GithubEmptyRepositoriesException.class)
public ResponseEntity<ErrorResponse> githubEmptyRepositoriesExceptionHandler(GithubEmptyRepositoriesException exception) {
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
}

@ExceptionHandler(FeignException.class)
public ResponseEntity<ErrorResponse> feignExceptionNotFoundHandler(FeignException exception) {
  ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Not found in Github");
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
}

@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception exception) {
  ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
}
```

#### Exemplos de respostas

```json
    "status": "NOT_FOUND",
    "message": "Not found repositories in Github"
```
```json
    "status": "NOT_FOUND",
    "message": "Not found in Github"
```

## Estrutura de Pastas

A estrutura de pastas do projeto é organizada da seguinte forma:

* **clients**: Contém os arquivos responsáveis pela comunicação com a API do GitHub utilizando o Feign.
* **configurations**: Contém a configuração do Feign, como o interceptador para incluir o token de autenticação.
* **controllers**: Contém os controladores da aplicação que expõem os endpoints REST.
* **exceptions**: Contém as exceções personalizadas utilizadas no projeto.
* **handlers**: Responsável por tratar exceções e gerar as respostas apropriadas.
* **models**: Contém as classes de modelo que representam os dados da API, como `User` e `Repository`.


