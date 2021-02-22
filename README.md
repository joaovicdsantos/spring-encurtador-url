<h1>Spring-Encurtador-Url <img src="https://img.shields.io/badge/Java-11-007396.svg?&style=for-the-badge&logo=java&logoColor=white"/> 
<img src="https://img.shields.io/badge/Spring-v2.4.3-6DB33F.svg?&style=for-the-badge&logo=spring&logoColor=white"/></h1>

Um simples encurtador de url feito em Java para por em prática meus conhecimentos sobre Spring obtidos no treinamento Gama Academy.

### Uso da API
#### Registro de Links
Para realizar o cadastro de um novo link na api do encurtador é muito simples. Basta enviar o link para `/api/v1/links` no formato de Json. Seguindo o seguinte modelo:
```json
{
  "link": "https://github.com"
}
```
Dessa forma o link será cadastro em nossos servidores e será retornada o link encurtado juntamente com o contador de acessos (que por padrão inicia-se em 0):
```json
{
  "id": 1,
  "link": "https://github.com",
  "linkEncurtado": "$PREFIX/seu_hash",
  "acessos": 0
}
```
__Nota:__ Vale destacar que se um link enviado pelo consumidor da API já estiver cadastrado, a mesma faz o retorno do registro já salvo poupando de alguns possíveis problemas.
#### Obtenção de Links
Os links já cadastrados podem ser obtidos através de seus respectivos __IDs__, __link__ ou __linkEncurtado__.
##### Via ID
- `/api/v1/links/{id}` `GET`
##### Via Links
- `/api/v1/links/filtro?link={link_desejado}` `GET`
#### Remoção
- `/api/v1/links/{id}` `DELETE`
