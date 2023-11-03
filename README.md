# Sistema de Tarefas

Este é um projeto guiado realizado pela Rockseat. O projeto é um sistema de todolist, no qual é possível cadastrar, coletar e atualizar . Além disso, o sistema possui autenticação por usuário e password.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Security
- Spring JPA
- Banco de dados H2 (em memória)

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

- Cadastro dos usuários: É possível criar novos usuários.

- Cadastro de tarefas: Os usuários podem cadastrar as tarefas no sistema, com as informações necessárias.

- Atualização de tarefas: É possível atualizar as informações da tarefa, não sendo necessário preencher as informações repetidas no body da requisição.

- Coleta das tarefas: O usuário pode coletar as tarefas que ele criou, é necessário que autentificação do user e do password é necessária. 

## Como Executar o Projeto

1. Certifique-se de que você possui o Java instalado em sua máquina.

2. Clone este repositório para o seu ambiente de desenvolvimento:

3. Navegue até o diretório do projeto:

4. O aplicativo estará disponível em `http://localhost:8080`. A porta utilizada poderá ser outra, dependendo da sua configuração local.

## Endpoints da API

O sistema oferece os seguintes endpoints da API:

- `POST /user/`: Cria um novo usuário.

- `POST /tasks/`: Os usuários cadastrados no sistema, poderão criar uma nova tarefa. *

- `PUT /tasks/{id}`: Atualiza uma tarefa com base no ID e verifica se usuário pode alterar esta tarefa, apenas o usuário que criou a tarefa pode altera-lá. *

- `GET /tasks/`: Obtém a lista de todas as tarefas que o usuário criou. *

*** É necessário autenticar o usuário e password na hora da requisição, Auth -> Basic Auth.

## Contribuição

Este é um projeto inicial de um estudante, e qualquer contribuição é bem-vinda. Se você quiser contribuir com novos recursos, correções de bugs ou melhorias na documentação, fique à vontade para criar um "pull request" neste repositório.

## Licença

Este projeto é distribuído sob a licença MIT.

---

Se tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato.

