# Pedido Hexagonal

Projeto de exemplo usando **Arquitetura Hexagonal** (Ports & Adapters) com **Spring Boot**, **Kafka** e **PostgreSQL**.

## Problema que o projeto resolve

Em sistemas modernos, é comum ter regras de negócio complexas que não devem depender de tecnologias externas.  
Este projeto resolve o problema de:
- Separar regras de negócio da infraestrutura (banco de dados, mensagens e API).
- Permitir comunicação assíncrona com outros sistemas via Kafka.
- Manter persistência consistente no PostgreSQL sem acoplamento direto com a lógica de negócio.

---

## Tecnologias

- Java 17
- Spring Boot
- Spring Kafka
- Spring Data JPA
- PostgreSQL
- Apache Kafka
- Docker / Docker Compose
- Maven

---
## Estrutura do projeto
```
    pedido-hexagonal
    │
    ├─ adapters
    │ ├─ in/ -> Controllers, DTOs, Mappers
    │ └─ out/ -> Kafka Producers/Consumers
    │
    ├─ application/ -> Services, UseCases
    ├─ domain/ -> Entidades, Repositórios
    ├─ infra/
    │ └─ persistence/ -> Adapters de persistência
    └─ resources/
    └─ application.yml/properties
```

## Desafios enfrentados e como resolvi

1. **Integração Kafka + Spring Boot**
    - Problema: O consumidor Kafka não recebia mensagens, e havia erros de DNS (`UnknownHostException: kafka`).
    - Solução: Ajustei `KAFKA_ADVERTISED_LISTENERS` no Docker Compose e usei `localhost:9092` no Spring Boot para comunicação local.

2. **Configuração do PostgreSQL**
    - Problema: Erros de EntityManager e Hibernate ao iniciar.
    - Solução: Adicionei `@Id` na entidade `Pedido` e configurei o `dialect` do Hibernate corretamente.

3. **Serialização de mensagens Kafka**
    - Problema: Mensagens enviadas como `String` sem estrutura dificultavam consumo.
    - Solução: Usei `ObjectMapper` para enviar mensagens em JSON, mantendo consistência.

4. **Injeção de dependências Spring**
    - Problema: `BeanCreationException` em `KafkaTemplate` e produtores Kafka.
    - Solução: Criei configuração explícita em `ProdutorKafkaConfig` com `@Bean` e `ProducerFactory`.

---

## O que aprendi com o processo

- Como implementar **Arquitetura Hexagonal** na prática, separando domínio, aplicação e infraestrutura.
- Configurar **Kafka e Spring Boot**, incluindo tópicos, produtores e consumidores.
- Configurar **PostgreSQL com Spring Data JPA**, incluindo `ddl-auto`, dialect e entidades.
- Desenvolver **microserviço** que persiste dados e publica eventos assincronamente.
- Depurar problemas de rede e configuração em containers Docker (Kafka, Zookeeper, PostgreSQL).
- Estruturar um projeto para escalabilidade e manutenção futura, aplicando boas práticas de design.

---