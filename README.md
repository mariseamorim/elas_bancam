<div align="center">
<img style="display: block;margin: 0 auto;" src="/imagem/logo.png" target="_blank"></h2>
</div>

# Projeto Integrador Programa ElaTech

Desenvolvimento de projeto integrado para o programa elas tech parceria da Gama Academy com a PagBank.

# Contexto

Nossa equipe "ElasBancam" foi escolhida para ser responsável pelo desenvolvimento de um sistema que gerencie clientes e transações.

# Proposta

Desenvolver Api Rest que permite cadastro de novos clientes, incluindo dados pessoais e
dados para contato. Também possibilita a realização de transferências de um cliente para outro gravando o
histórico de transações. No histórico de transações permite a busca de resultados registrados nas transações realizadas.

# Regras e validações

- O cliente deve ser atrelado a uma conta bancária;
- A conta bancária deve ter um valor inicial;

# Métodos esperados

**Swagger**

![Swagger](/imagem/swagger.png)

Endpoints
| Verbo | Endpoint | Parâmetro | Body |
|--------|--------------------|-----------------|-----------------|
| GET | /clientes/{id} | id | N/A |
| PUT | /clientes/{id} | id | Schema Clientes |
| DELETE | /clientes/{id} | id | N/A |
| POST | /clientes | N/A | Schema Clientes |
| GET | /clientes/todos | N/A | N/A |
| POST | /transacoes | N/A | Schema Transacao|
| GET | /transacoes/tipo | tipo | N/A |
| GET | /transacoes/periodo | N/A | Schema DataTransacao |
| GET | /transacoes/conta | id | N/A |
| GET | /transacoes | id | N/A |

# Stack utilizada

- Java 19 com module path
- Spring Boot 2.7.5
- Swagger
- JUnit 5 com AssertJ e Mockito
- Maven
- MySql

# Gerenciador de projetos Utilizado

- [Trello](https://trello.com/)
  ![Quadro Trello](/imagem/trello.png)

# Rodando localmente

---TO DO

# Autoras

- [Ana Flavia](https://github.com/anaflxvia)
- [Camila Belota](https://github.com/camilabelota)
- [Chrislanne Oliveira](https://github.com/Chrislanneo)
- [Laise France](https://github.com/LaiFrance)
- [Laura Neves](https://github.com/lmtfn)
- [Nathaly Barreto](https://github.com/nathalybarreto)
- [Marise Amorim](https://github.com/nathalybarreto)

# Referências

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Java](https://docs.oracle.com/en/java/)
- [Java Tutorial W3 School](https://www.w3schools.com/java/default.asp)

