## configuração dev
-Dspring.profiles.active=dev  

## docker container
git clone master  

docker build -f container.dockerfile -t fpsoluctiontechs/hortifruit-api .  

### Opções de volumes

#1 mapea uma pasta da máquina atual para o container  
docker run -p 8080:8080 --name hortfifruit-api --mount type=bind,source=/opt/hortifruit-upload-files,destination=/opt/hortifruit/uploads fpsoluctiontechs/hortifruit-api

#2 cria um volume que poderá ser utilizado quando recriado o novo container  
docker run -p 8080:8080 --name hortfifruit-api --mount source=hortfruit-api-volume,destination=/opt/hortifruit/uploads fpsoluctiontechs/hortifruit-api

docker exec -it hortfifruit-api /bin/sh  