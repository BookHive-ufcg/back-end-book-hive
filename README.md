# back-end-book-hive

Este projeto consiste no back-end da aplicação bookhive.

## Pré requisitos

[Java](https://www.oracle.com/br/java/technologies/downloads/)

[Docker Compose](https://docs.docker.com/compose/)

[Banco de dados local Postgres](https://www.edivaldobrito.com.br/dbeaver-no-ubuntu-e-derivados/)


##
Rodando a aplicação via docker:

No diretório root, execute o comando:

```
docker build -t bookhive-app .
```

o primeiro build é demorado, levando cerca de 3 a 4 minutos, depois que ele for concluído, execute:

```
docker run -p 8686:8686 bookhive-app
```
json de cadastro
{  "firstName": "string",   "lastName": "string",   "username": "string",   "dateOfBirth": "2024-12-09T15:16:51.081Z",   "password": "string" }












##

Caso seja aprimeira vez, entre na pasta database-docker e execute

```
Docker compose up --build
```

caso contrário: 

```
Docker compose up
```

Depois disso, vamos utilizar a ferramenta Dbeaver para subir noso banco de dados local. 

Inicie uma nova conexão
![alt text](images-readme/image.png)

Escolha o banco de dados postgreSQL
![alt text](images-readme/image-1.png)

Utilize as seguintes configurações
![alt text](images-readme/image-3.png)

Teste a conexão 
![alt text](images-readme/image-4.png)

Adicione um novo script para criação de tabelas
![alt text](images-readme/image-5.png)

Depois Execute o script para criação de tabelas
![alt text](images-readme/image-6.png)

O script para criação pode ser encontrado aqui:
[Script]()

Depois suba a aplicação na IDE de sua preferência.

A documentação sobre os endpoits pode ser encontrada no seguinte link : 

[http://localhost:8080/swagger-ui.html#](http://localhost:8080/swagger-ui.html#)

para por no campo de cadastrar user
{   "firstName": "string",   "lastName": "string",   "username": "string",   "dateOfBirth": "2024-12-09T15:16:51.081Z",   "password": "string" }