# Spring MVC | AngularJs App #

Projeto para disciplina Engenharia Econ�mica

### Insta��o de dependencias ###

 - Java 8
 - Node 0.12 or higher
 - bower
 - maven 3
 - postgres e drivers jdbc


## Instru��es de instala��o

- Depois de clonar o repositorio, instale as dependencias frontend:

    bower install

- criar BD postrgres com nome "engenharia-economica". User owner: postrgres, senha: rcs 
    

E ent�o rode o comando maven:
    mvn clean install tomcat7:run-war -Dspring.profiles.active=development
 


Acesso ao app:

    http://localhost:8080/

    
Usu�rio e senha

    username: test123 / password: Password2


