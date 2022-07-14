## configuração dev
-Dspring.profiles.active=dev  

## docker container
git clone master  

#Backend - VPS

## INFRA
docker build -t fpsoluctionstechs/postgres .  

docker network create --driver bridge fp-network  

### BD
docker run -d --name fp-postgres -e POSTGRES_PASSWORD=r9B9d8Xwp -e PGDATA=/var/lib/postgresql/data/pgdata --network=fp-network --mount type=bind,source=/opt/fpsoluctionstechs/postgres,destination=/var/lib/postgresql/data -p 5432:5432 fpsoluctionstechs/postgres -d

### API
docker build -t fpsoluctionstechs/hortifruit-api .  

docker run --name hortfifruit-api --mount type=bind,source=/opt/fpsoluctionstechs/hortifruit/upload-files,destination=/opt/hortifruit/uploads -e 'spring.profiles.active=prod' -e 'DATABASE_URL=jdbc:postgresql://fp-postgres:5432/postgres' -e 'DATABASE_USERNAME=hortifruit' -e 'DATABASE_PASSWORD=r9B9d8Xwp' --network=fp-network -p 8080:8080 fpsoluctionstechs/hortifruit-api -d

### Comandos
**Acessando o terminal do container**
* docker exec -it hortfifruit-api /bin/sh  