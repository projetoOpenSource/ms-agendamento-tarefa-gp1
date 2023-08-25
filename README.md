# ms-agendamento-tarefa -- Microservices em Springboot

## Sumário

1. [Tecnologias Utilizadas]
2. [ Configuração ]
3. [ Build e Deploy - Ambiente de desenvolvimento (Localhost) ]
4. [ Build e Deploy - Ambiente de Homologação (Api Portal) ]
5. [ Arquitetura ]
6. [ Actuator ]
7. [ Arquitetura ]

### 1. Tecnologias Utilizadas

- Java (JDK 17)
- Spring Boot (Running with Spring Boot v2.7.5, Spring v5.3.23)
- JPA
- Hibernate
- Lombok
- Swagger
- Model Mapper
- Mysql (MySQL 5.7)

### 2. Configuração

##### Instale

* [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/download/) ou [Spring Tools Suite](https://spring.io/tools)
* [Java JDK 17](https://openjdk.java.net/projects/jdk/17/);
* [Maven](https://maven.apache.org/download.cgi);
* [Git](https://git-scm.com/downloads)
* [Postman Agent](https://www.postman.com/downloads/);

### 3. Build e Deploy - Ambiente de desenvolvimento

##### SpringBoot

1. Faça um git clone para o branch desejado;
2. Abra a IDE escolhida (Spring Tool Suite ou IntelliJ)
3. Importe o projeto
4. Realize o build
    1. Aperte o botão com o símbolo de um martelo

    - ou

    2. Digite no terminal mvn package -U
5. Execute o projeto
    1. Aperte o botão com o símbolo de play

    - ou

    2. Digite no terminal mvn exec:java

### 4. Build e Deploy - Ambiente de homologação

##### Git

1. git status
2. git add < arq >
3. git tag - TAG OPCIONAL
4. git tag < last_tag+1 > - TAG OPCIONAL
5. git commit -m < message >
6. git push
7. git push origin < last_tag+1 > - TAG OPCIONAL

##### Swagger

1. Localhost
    - Acesse o swagger por meio do link:
        - http://localhost:8080/ms-agendamento-tarefa/swagger-ui/index.html
2. API Sever
    - Acesse o swagger por meio do link: