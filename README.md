# postoDeCombustivel

[Indra]: Desafio para processo seletivo

Posto Combustivel API REST
API REST Para manter os dados do Posto Combustivel.

Created by Diego Rangel
Contact the developer
Apache License Version 2.0

Introdução:

Descrição: Api Rest com importação de arquivo CSV com dados de postos de combustíveis, que contém GRUD de usuários, histórico de Preços e consulta dos dados importados.

Principal funcionalidade: Importar arquivo de combustível CSV contido dentro do projeto e disponibilizar consultas dos dados importados como: Preço, região, postos, etc.

API REST desenvolvida em Spring Boot
						 Mavem
						 Banco de dados H2
						 Banco de dados Mysql (Pré configurado)

Link do banco H2 
http://localhost:8080/h2

Configurações do Banco de dados H2
Configurações estão contidas no arquivo src/main/resources/application.properties
url=jdbc:h2:file:~/test
username=sa
password=
driver-class-name=org.h2.Driver

Aplicação já pronta e configurada para utilizar Mysql conforme Configurações estão contidas no arquivo src/main/resources/application.properties comentadas.


Link para acesso ao swagger
localhost:8080/swagger-ui.html

