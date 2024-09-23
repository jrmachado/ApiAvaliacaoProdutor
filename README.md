# Api Avaliação Produtor
# Golden Raspberry Awards API

## Sobre
Esta é uma API RESTful para consultar informações sobre a categoria "Pior Filme" do Golden Raspberry Awards.

## Requisitos
- Java 17
- Maven

## Como Rodar
1. Clone o repositório.
2. Navegue até o diretório do projeto.
3. Execute `mvn spring-boot:run` para iniciar a aplicação.
4. Colocar o arquivo "movielist.csv" neste caminho: "C:/kit/file/". Pois a classe de configuração DataLoader esta apontando para esta caminho com o este nome de arquivo e formato.

## Endpoints
- GET /api/films/maior-periodo: Obtém o produtor com o maior intervalo entre dois prêmios consecutivos.
- GET /api/films/premio-mais-rapido: Obtém o produtor que obteve dois prêmios mais rapidamente.

## Pode ser efeturado também via Swagger
		http://localhost:8080/swagger-ui/index.html#/
