# Spring Boot Microservices with CQRS & Event Sourcing

O objetivo deste projeto é construir uma aplicação utilizando micro-serviços atendendo os princípios de design em termos de melhores práticas, incluindo CQRS, Event Sourcing e DDD.

## Os tópicos abordados nesse projeto são:

 - CQRS (Separação de operações de comando e consulta).
 - Event Sourcing (Garantir que todos os eventos gerados na aplicação sejam armazenados em uma sequência para posterior consulta).
 - DDD (Codificação centralizada nas entidades de domínio (Usuários e Contas bancárias)
 - Padrão arquitetural REST
 - Deploy utilizando docker containers
 - Servidor de autorização utilizando OAuth 2.0
 - Utilização de API Gateway para service discovery.

## Tecnologias utilizadas:

 - SpringBoot 2.4.5
 - Java 11 (OpenJDK)
 - Spring Cloud Starter Gateway
 - Maven
 - Intellij Idea
 - Axon Framework
 - MongoDB
 - MySQL Server
 - Adminer
 - Docker (Para rodas as aplicações como bancos e Axon server).

## Arquitetura do projeto:

![image](https://user-images.githubusercontent.com/15165746/121761137-79e09080-cb04-11eb-9b69-0d0bbdd3417d.png)


**User-Domain**

![image](https://user-images.githubusercontent.com/15165746/121761090-4998f200-cb04-11eb-84e3-37267a3696a4.png)


**Bank-Account Domain**

![Component+Diagram+-+Bank+Account+Microservices](https://user-images.githubusercontent.com/15165746/121761036-135b7280-cb04-11eb-890e-84e566e252ab.jpg)

**Para executar o projeto primeiro execute o comando:**

```shell script
mvn clean install
```
Nos projetos:

 - api-gateway
 - bank-account
 - user-management

Após isso faça a construção de cada uma das imagens para os respectivos projetos:
```shell script
 - bankacc.cmd.api -> docker build -t bankacc-cmd-api .
 - bankacc.query.api -> docker build -t bankacc-query-api .
 - user.cmd.api -> docker build -t user-cmd-api .
 - user.query.api -> docker build -t user-query-api .
 - user.oauth2.0 -> docker build -t oauth2-service .
 - api-gateway -> docker build -t api-gateway .
 ```

Após gerado as imagens basta executar o comando:

```shell script
docker-compose up -d dentro do diretório docker/springbank
 ```


  
