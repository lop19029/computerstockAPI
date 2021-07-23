<h2>Digital Innovation One Lab - Desenvolvendo testes unitarios para um API REST</h2>

Desenvolvimento de testes unitarios ustilizando ferramentas como JUnit, Mockito e Hamcrest.

Neste lab baixei e implementei um projeto de API REST para criar um API de gerenciamento de computadores, onde o gerente que for utilziar o API pode registrar o
modelo, marca, stock maximo, stock atual, e tipo de cada computador . Foram aplicados testes unitarios para
cada funcionalidade do API (inserir, listar, consultar individualmente, e excluir computadores do stock). Também foi implementada a funcinalidade "increment" (PATCH) utilizando arquitetura TDD.

Para executar o projeto no terminal, digite o seguinte comando:

mvn spring-boot:run 

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

http://localhost:8080/api/v1/computers

Creditos:

[Facilitador do Lab - Rodrigo Peleias](https://github.com/rpeleias)

[Digital Innovation One](https://web.digitalinnovation.one/labs)