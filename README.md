# postoDeCombustivel

[Indra]: Desafio para processo seletivo

Posto Combustivel API REST
API REST Para manter os dados do Posto Combustivel.

Created by Diego Rangel
Contact the developer
Apache License Version 2.0

Introdução:

Descrição: Api Rest com importação de arquivo CSV com dados de postos de combustíveis, que contém GRUD de usuários, histórico de Preços e consulta dos dados importados.

Principal funcionalidade: 
Implementado recurso para CRUD de usuários
Implementado recurso para CRUD de histórico de preço de combustível
Implementado recurso para importação de csv (Arquivo dentro do projeto disponibilizado pelo desafio)
Implementado recurso que retorne a média de preço de combustível com base no nome do município
Implementado recurso que retorne todas as informações importadas por sigla da região
Implementado recurso que retorne os dados agrupados por distribuidora
Implementado recurso que retorne os dados agrupados pela data da coleta
Implementado recurso que retorne o valor médio do valor da compra e do valor da venda por município
Implementado recurso que retorne o valor médio do valor da compra e do valor da venda por bandeira

API REST desenvolvida em Spring Boot
						 Mavem
						 Banco de dados H2
						 Banco de dados Mysql (Pré configurado)
						 
Link do repositório no Git: https://github.com/diegorangelsoares/apipostocombustivel

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

