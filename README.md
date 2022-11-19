<div align="center">
<img style="display: block;margin: 0 auto;" src="/imagem/logo.png" target="_blank"></h2>
</div>

# Projeto Integrador Programa ElaTech

Desenvolvimento de projeto integrado para o programa Elas Tech, uma parceria da PagBank PagSeguro com a Gama Academy.

# Contexto

O objetivo do projeto foi a criação de uma API para o funcionamento do nosso banco, o *ElasBancam*! Nessa API, desenvolvemos métodos para gerenciar clientes e realizar transações entre contas, dentre as funcionalidades criadas estão: cadastro de novos clientes, busca pela lista completa de clientes ativos, realização de transações e a busca de transações por período.

# Regras e validações

Para que todos os métodos criados fossem criados respeitando as nossas regras de negócio, definimos algumas premissas:
- Todo cliente deve ser atrelado a uma conta bancária no momento do seu cadastro
- A conta bancária já deve ser criada com um valor inicial definido, mesmo que 0
- Não pode ser feito o cadastro de pessoa física com CPF ou RG já presentes no sistema
- Não pode ser feito o cadastro de pessoa jurídica com CNPJ ou Inscrição Estadual já presentes no sistema
- Todas as buscas por clientes devem retornar apenas aqueles que estão ativos no sistema


# Endpoints desenvolvidos

**Swagger**

![Swagger](/imagem/swagger.png)

Endpoints
| Verbo | Endpoint | Parâmetro | Body |
|--------|--------------------|-----------------|-----------------|
| PUT | /cliente/pj | N/A | Schema PessoaJuridicaUpdate |
| POST | /cliente/pj | N/A | Schema PessoaJuridica |
| PUT | /cliente/pf | N/A | Schema PessoaFisicaUpdate |
| POST | /cliente/pf | N/A | Schema PessoaFisica |
| PUT | /cliente/delete | {id} | N/A |
| GET | /clientes | {id} | N/A |
| GET | /clientes/todos | N/A | N/A |

| POST | /transacoes | N/A | Schema Transacao|
| GET | /transacoes | id | N/A |
| GET | /transacoes/tipo | {tipoTransacao} | N/A |
| GET | /transacoes/periodo | {dataInicial}/{dataFinal} | N/A |
| GET | /transacoes/conta | id | N/A |

Schemas
PessoaFisica:
![PessoaFisica](/imagem/PessoaFisicaDto.png)



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

