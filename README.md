# 🚀 Alura's API

## 💻 Sobre o Projeto

O projeto é um sistema técnico para a plataforma Alura, com o objetivo de gerenciar usuários, cursos e matrículas, além da implementação de um sistema de avaliação de cursos.

## 📄 Requisitos do Projeto

1. **Usuários**
    - Cadastro de usuário com os atributos: Nome, Username, Email, Senha, Role e Data de criação.
    - Restrições: Username deve conter apenas caracteres minúsculos, sem numerais e sem espaços. Precisa ser um endereço de e-mail em um formato válido. Pode apenas ter um usuário com o mesmo email/username.
    - Retornar os dados de um usuário cadastrado em nossa plataforma.

2. **Curso**
    - Criação de um curso com os atributos: Nome, Código, Instrutor, Descrição, Status, Data de criação e Data de inativação.
    - Restrições: Pode apenas ter um curso com o mesmo código. O código de um curso deve ser textual, sem espaços, nem caracteres numéricos e nem caracteres especiais, mas pode ser separado por - , exemplo: spring-boot -avancado. Apenas usuários instrutores podem ser autores de um curso. A data de inativação deve ser apenas definida quando o curso estiver desativado.
    - Inativação de um curso.
    - Listagem de cursos.

3. **Matrícula**
    - Matrícula de um usuário em um curso com os atributos: Usuário, Curso e Data de matricula.
    - Restrições: Um usuário não pode matricular-se mais de uma vez em um curso. Podemos apenas nos matricular em cursos ativos.

4. **Avaliação de Cursos**
    - Implementação de um sistema de avaliação de cursos pelos alunos.
    - Notificação automática para os instrutores quando um aluno avaliar o curso com uma nota menor que 6.
    - Relatório com o Net Promoter Score (NPS) de cada curso que tenha mais de quatro matrículas.

## Bônus (não obrigatório)
- Implementação do Spring Security e o acesso baseado em roles.
- Ao invés do H2, usar o mysql e tanto a aplicação, banco de dados e outros serviços necessários devem ser executados no docker.

## 🔧 Tecnologias e Versões

- **Java**: 21
- **Spring Boot**: 3.3.0
- **Maven**
- **Docker**
- **MySQL**
- **Flyway**

## 📦 Dependências

- `Spring Data JPA`
- `Spring Web MVC`
- `Flyway Database Migration`
- `Hibernate Validator (Spring Validation)`
- `Spring Boot Developer Tools`
- `H2 Database Engine`
- `Project Lombok`
- `Spring Boot Test Starter`
- `Spring Doc`
- `MySql`

## 🛠 Como rodar o projeto

Para rodar o projeto, você precisará ter o Docker instalado. Siga as instruções abaixo:

1. Clone o repositório para a sua máquina.
2. Navegue até a pasta do projeto.
3. Execute o comando `docker-compose up`.

Isso iniciará o MySQL e o Flyway, e o projeto estará pronto para ser executado.

## Configurando as Variáveis de Ambiente

### application.properties

1. No diretório raiz do projeto, localize o arquivo `application.example.properties`.
2. Faça uma cópia deste arquivo no mesmo diretório e renomeie a cópia para `application.properties`.
3. Abra o arquivo `application.properties` e substitua os valores das variáveis de ambiente pelos valores correspondentes ao seu ambiente de desenvolvimento.

### env

1. No diretório raiz do projeto, localize a pasta `.env-example`.
2. Faça uma cópia desta pasta no mesmo diretório e renomeie a cópia para `.env`.
3. Abra o arquivo `.env` e substitua os valores das variáveis de ambiente nos arquivos app.env e mysql.env pelos valores correspondentes ao seu ambiente de desenvolvimento. Este arquivo contém variáveis para a aplicação (`app`) e para o MySQL.

## Documentação da API

Para acessar a documentação da API, você precisa primeiro iniciar o projeto. Siga as instruções na seção de como rodar o projeto para saber como fazer.

Depois que o projeto estiver rodando, você pode acessar a documentação da API através do link: `http://localhost:8080/swagger-ui.html`

Substitua `localhost:8080` pelo endereço e porta onde seu aplicativo Spring está sendo executado, se for diferente. Este link levará você à interface do Swagger UI, onde você pode visualizar e interagir com a API documentada.

Se você tiver problemas, verifique sua configuração do SpringDoc e as anotações em seus controladores e modelos de API.



## 🌐 Contato
- lrsolimpio@gmail.com
- [LinkedIn](https://www.linkedin.com/in/larissaolimpio/)
- [Instagran](https://www.instagram.com/dev_larissaolimpio?utm_source=qr&igsh=bzY1bGNqMDl1dTg5)
  
## 📝 Licença

Este projeto está sob a licença MIT.


