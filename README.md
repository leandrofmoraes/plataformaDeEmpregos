# Projeto Plataforma de empregos.


### Descrição:
Solução em Tecnologia da informação: API REST para cadastro de currículos e vagas de emprego.


#### 1 - Dependências:
- [Java 17](https://www.java.com/pt-BR/download/manual.jsp)
- [MySQL](https://www.mysql.com/downloads/)
- [Git](https://git-scm.com/downloads) (opcional)
- [Docker](https://www.docker.com/products/docker-desktop) (opcional)

#### 2 - Inicialização:

Com o git instalado, clone o repositório

```bash
git clone https://github.com/leandrofmoraes/plataformaDeEmpregos.git
```

Caso, não tenha o git instalado, pode baixar o projeto no formato zip e descompactar em uma pasta.

Duas formas de executar a API.

### Usando JRE (Necessário um banco de dados instalado e configurado).

  - Entre na pasta plataformaDeCurriculos
```bash
cd plataformaDeEmpregos
```
  - Execute o comando abaixo: (*substitua localhost pelo ip do banco de dados, assim como o nome do banco, o usuário e a senha*)
```bash
    java -Dspring.profiles.active=prod\
     -DDATASOURCE_URL=jdbc:mysql://localhost:3306/<nome-do-banco>\
     -DDATASOURCE_USERNAME=<usuario-no-banco>\
     -DDATASOURCE_PASSWORD=<sua_senha-no-banco>\
     -jar ./target/plataformaDeEmpregos-0.0.1-SNAPSHOT.jar
```

### Usando o arquivo **docker-compose** (Necessário ter o Docker instalado).
  - Entre na pasta plataformaDeEmpregos
```bash
cd plataformaDeEmpregos
```
- Execute o comando abaixo:
```bash
docker-compose up
```

### 3 - Exemplo de uso da API:

- Usando curl para enviar uma requisição POST para fazer login:
```bash
curl -X POST -H "Content-Type: application/json" -d @../assets/dados_formulario_login_teste.json http://localhost:8080/login #irá devolver um token de acesso
```

- Usando curl para enviar uma requisição POST para cadastrar um currículo:
```bash
curl -X POST -H "Authorization: <substitua-pelo-token-gerado>" -H "Content-Type: application/json" -d @../tests/dados_curriculo_teste_formulario.json http://localhost:8080/curriculo
```

### 4 - Documentação da API:

- Toda a documentação da API está disponível no Swagger, após a inicialização da aplicação, acesse o link abaixo:
```url
http://localhost:8080/swagger-ui/index.html
```
