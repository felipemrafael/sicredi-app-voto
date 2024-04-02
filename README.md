### APP Votacao Pauta 


## Pré requisitos
- #### docker-compose

## Iniciando a aplicação


### Build da imagem docker e start na aplicação
```
docker-compose buld 
``` 
### Start da aplicação
```
docker-compose up -d
```

# Swagger
http://localhost:8080/swagger-ui/index.html


# Versionamento:
### Atenção no parâmetro no header da requisição :

```
Api-Version = 1
```

### Cadastrar Pauta

```
POST http://localhost:8080/v1/pauta

{
  "titulo": "titulo da pauta"
}
``` 

### Abrir Sessão

```
POST http://localhost:8080/v1/pauta/abrir
{
    "id_pauta": 1,
    "minutos": 4
}
```
### Votar em uma pauta
```
POST http://localhost:8080/v1/votos

{
    "id_pauta": 1,
    "id_cooperado": 1,
    "cpf": "26484951004",
    "voto": "Sim"
}
``` 

### Consultar resultado da pauta
```
GET http://localhost:8080/v1/resultados/{id}
``` 

#### Tarefa Bônus 1 - Integração com sistemas externos
    Foi implementado um servico 'CpfService' no qual realiza a integração com o serviço externo;

#### Tarefa Bônus 2 - Mensageria e filas
    Foi utilizado o Kafka para mensageria:
    O producer ficou responsável por enviar os resultados das votações;
    O consumer ficou responsável por escutar os resultados das votações e apenas logar em console os resultados.

#### Tarefa Bônus 4 - Versionamento da API
    Há varias formas de fazer o versionamento, para este teste foi escolhido pelo header, onde as apis foram construidas com o Header Api-Version = 1 .
    Também pode ser feita via URL ex.: "api/v2/", onde o 'v2' pode ser parametrizado nos properties  .



