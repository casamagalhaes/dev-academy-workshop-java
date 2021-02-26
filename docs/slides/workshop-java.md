![Image](imagens/devacademy1.svg)



## Desenvolvendo APIs REST com Java



![Image](imagens/techs.png)



### Facilitadores:
Note: Essas descrições são parcialmente baseado na realidade...



#### Pablo Monteiro
- Java dev há 200 anos, trocou o chapéu de vaqueiro e a botina pelo teclado e mouse. Hoje é Dev leader na Casa Magalhães e um dos veteranos no time do Varejofacil.



#### Giva
- Programou o portão da caverna de tesouros de Alibabá. A chave criptografada "Abra-te sésamo" é de autoria dele. Atualmente arquiteto do Varejofacil, é também conhecido como "O Oráculo do varejo".



#### Daniel Chaves
- Quando criança, mamãe bateu na cabeça dele e disse: "Esse menino vai ser programador Java". Em 2005 a profecia se concretizou e hoje é arquiteto do Varejofacil.



<div style="float: left; width: 35%"> 

![Image](imagens/cronograma.png)

</div>
<div style="float: right; width: 40%;">

<div style="text-align:left">

<h6> Dia 1 </h6>
<span style="font-size: 17px"> 

 - Conceitos API e REST
 
 - Iniciando com Spring Boot
 
 - Hello API - `/hello`

 - Mão na massa: API CRUD

</span>

***

<h6> Dia 2 </h6>

<span style="font-size: 17px"> 

 - Validações
 
 - Testes

 - Documantação (Swagger)

 - Querying (Overview)
  - FiQL
  - GraphQL
  - Paginação


</span>
</div>
</div>



## Conceitos



![Image](imagens/api.png)
Note: 
- Falar das conexões
- Exemplos de APIs



### API

- _Application Programming Interface_
- Conjunto de instruções e padrões de programação que servem para *fornecer dados e informações* relevantes de uma determinada aplicação
- Vantagens
- Exemplos: (PIX)
Note:
- Outras coisas de APIs 



### Evolução API
![Image](imagens/api-history.png)
Note: 
- EDI de 1948 com 1a implementação nos anos 1970, aeroporto de Londres; 
- RPC dos anos 1970 com 1a implementação em 1982, depois XEROX, SUN...
- CORBA (EAI), nos anos 1990, é uma das primeiras formas de API moderna, por permitir sistemas com diferentes implementações se comunicar, sem forçar uma alteração na sua implementação e estrutura
- SOA, com o advento do XML as integrações ficaram mais focadas em webservices.
- Microservices foi uma variação que entrou em cena quando SOA passou a ter predominância no mercado. Nos anos 2010s as Web APIs não só se tornaram relevantes mas dominaram o mercado como padrão para integração de apicações e serviços.




![Image](imagens/http.png)
Note:
- basicamente é o protocolo de comunicação que rege a maioria das interações na web
- se comunica usando mensagens
- bastante usado na arquitetura cliente-servidor



### HTTP
- _Hipertext Transfer Protocol_
- Projetado para permitir elementos conectados em rede estabelecer comunicação cliente-servidor
- Protocolo de camada de aplicação projetado dentro do framework da suite de protocolos de internet (TCP/IP)
- Baseado em requisição - resposta (Request/Response)



![Image](imagens/client-server.png)
Note:
- request(client) <-> resposta(server)
- composição: (header | body) (pedaço da msg)
- informações da msg status/metodos, etc



### Headers
- Possui informações que serão utilizadas pelo servidor identificar e responder as mensagens de forma adequada
***
### Body 
- Onde os dados são enviados para o servidor. 
    - EX: dados de um formulário para gravação
Note: 
- Content-Language: quais linguagens estão disponíveis (en-US, pt-BR)
- Content-Type: indica o tipo de dados que deseja receber (text/html, application/json)
- Método HTTP (GET, POST, DELETE)
- Status, no caso das respostas (200, 201, 404, 403, 500)



### Status
- Feedback do servidor

- Classes:
<div style="font-size:28px">
<ul>
<li>
    <b>1xx</b> - Até agora tudo bem, mas tá rolando ainda... <span style="color: blue">(Processamento)</span>
</li>
<li>
    <b>2XX</b> - Deu certo, mah! <span style="color: green">(Confirmação)</span>
</li>
<li>
    <b>3XX</b> - Pergunta no posto Ipiranga... <span style="color: gray">(Redirecionamento)</span>
</li>
<li>
    <b>4XX</b> - Deu bom não! Aí eh contigo... <span style="color: purple">(Erro Cliente)</span>
</li>
<li>
    <b>5XX</b> - Deu ruim. Mal minha... <span style="color: red">(Erro servidor)</span>
</li>
 </ul>
</div>
Note: 
- Forma do servidor informar como a requisição foi processada
- 100 - Tudo correu bem até então (informação parcial). Pode continuar; 102 - Processando;
- 200 - OK; 201 - Created; 202 - Accepted; 
- 301 - Moved permanently;
- 400 - Bad request; 401 - Unauthorized; 404 - Not Found;
- 500 - Internal server error; 503 - Service unavailable.



### Métodos (Verbos)
- Indicam ação a ser executada
- Mais usados:
<div style="font-size:28px">
<ul>
<li>
    <span style="color: blue">GET</span>    - Obter informação do(s) recurso(s)
</li>
<li>
    <span style="color: blue">POST</span>   - Processar a informação contida na requisição
</li>
<li>
    <span style="color: blue">PUT</span>    - Alterar o estado do recurso para o estado contido na requisição
</li>
<li>
    <span style="color: blue">DELETE</span> - Delete né =]
</li>
</ul>
</div>
- Outros:
<div style="font-size:28px">
<ul>
<li>
    <span style="color: blue">PATCH</span>  - Alterar estado do recurso com requisição parcial
</li>
<li>
    <span style="color: blue">HEAD</span>   - Similar ao `GET`, mas não tem _response body_
</li>
<li>
    <span style="color: blue">OPTIONS</span>- Retorna dados de quais outros métodos e operações os servidor suporta
</li>
</ul>
</div>

Note: 
- Esses são muito usados
- Menos usados mas muito útis. 
- Tem mais... TRACE - rastreio de rede, retorna o q enviado / CONNECT - usado pra iniciar comunicação de duas vias com o recurso
referencia: https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Methods



### Exemplos
- `GET`
    - www.livraria.com/livros?descricao=bonecos
    - www.livraria.com/livros/100
    - www.livraria.com/livros
- `POST`
    - www.livraria.com/livros 
        > <span style="font-size: 20px; color: gray">data: {id: 101, descricao: "A volta dos que não foram"} </span>
- `DELETE`: 
    - www.livraria.com/livros/100
Note:
- Agora que já vimos boa parte da base da comunicação existente na wbe, vamos partir agora pra uma abstração de arquitetura, onde nos permitirá criar projetos bem definidos e viabilizar uma melhor comunicação entre aplicações.



![Image](imagens/rest-fake-2.png)
Note:
- ops! Não esse tipo de rest(descanso)



![Image](imagens/rest.png)



- _Representational State Transfer_ (REST)

- Conjunto de princípios e definições para criar interfaces bem definidas

- "Padrão" da arquitetura de software para interação de aplicações, usando múltiplos _Web services_.
Note:
- Transferência de Estado Representacional
- Permite comunicação entre aplicações



- Benefícios:
    - facilidade de execução
    - alto aproveitamento da infraestrutura da web
    - flexibilidade
Note:
- escolha de formato que melhor se adequa. Ex: json, xml, html, etc




- _Web resources_
- Textual (JSON, XML, HTML)
- Conjunto de operações (GET, POST, PUT, DELETE, PATCH, CONNECT, OPTIONS, TRACE)
Note:
- Web Resource ou Resource: identificação da funcionalidade/recurso




### E RESTful?
- _Client-server_
- _Stateless_
- _Cacheable_
- _Layered system_
- Código sob demanda (opcional)
Note: Seis requisitos definem uma arquitetura RESTful. Cliente-servidor; stateless; cacheability (capacidade de fornecer info para guardar/expirar respostas); suporte a camadas de sistema (proxy, load balancer não deve interferir na comunicação); O servidor pode eventualmente enviar código para o cliente executar...



### Nera 6? Falta um...



#### Interface uniforme
- Identificação de recurso nas requisições (`/pessoas`)
- Manipulação de recursos por suas representações
- Mensagens auto descritivas
- HATEOAS
Note: recursos bem identificados; com a resposta de um get eu consigo fazer um post ou patch; headers (text/json), http status, ajudam a descrever as ações; Hypermedia as the engine of application state - assim como usuario acessando uma pagina as mensagens contem "links" para navegar pelos recursos



```log
GET /pessoas/423
Accept: text/json
```
Note: Recurso (Pessoas)



```log
HTTP 200 - OK
Content-Type: text/json
Content-Length: ...
{
    pessoaId: 423,
    nome: "Fulano de Tal",
    idade: 25,
    links: {
        cnh: "/pessoas/423/cnh"
    }
}
```
Note: Resposta textual (HTML, XML, JSON) e status HTTP. HATEOAS concede links para navegação e se comporta conforme o estado.



![Image](imagens/maos-a-obra.png)



![Image](imagens/springboot-no-bg.png)
- Spring Initializr
    - Configuração
    - Geração do projeto
Note: 
- o que é Spring Initializr?
- o que representa os campos
- etc



![Image](imagens/visualstudio.png)
- Importar projeto
- Plugins
```
Java Extension Pack
```
- Importar dependências
```
./gradlew build
```
- Run
```
localhost:8080
```



### !Antes... Sobre MVC



#### Criando primeiro controller
```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

}
```




![Image](imagens/postman.png)
- Testando aplicação



### Recebendo dados na URL
- Query String 
    - <span style="background-color: gray; color: white">@RequestParameter</span>

- URI 
    - <span style="background-color: gray; color: white">@PathVariable</span>
Note:
- QueryString são os parâmetros passados na URI em formato de query.



### Calculando Raiz Quadrada
```java
    @RequestMapping(method = RequestMethod.GET, 
                      path = "/raiz-quadrada/{numero}")
    public Long calculaRaizQuadrada(@PathVariable Long numero) {
        return numero * numero;
    }
```
***
```java
    @RequestMapping(method = RequestMethod.GET, 
                      path = "/raiz-quadrada-qs")
    public Long calculaRaizQuadradaQS(@RequestParam Long numero) {
        return numero * numero;
    }
```



### Enviando dados - <span style="color: blue">POST</span>
- 



### !Notas
- Erros comuns (falso positivo ex. HTTP Status 200 e no body msg erro);
- Falar sobre versionamento de APIs (tb versão do modelo);



### !Endpoint de Teste
```
GET /sqr-root/{number} (number=9)

200 - OK
Content-Type: text/plain

Resultado: 3
```



### Explorando cenários
- Criar estrutura fake pra testar mockando* dados
Note:
- Criar modelo entidade
- Criar método que retorne uma lista fake
- Explicar o que é MOCK

//Exemplo1

GET /sqr-root/:number

200 - OK

Content-Type: text/plain

422

//Exemplo2

/frutas

List<String> frutasArray = new ArrayList();

POST /frutas
Content-Type: text/plain

maçã

public void add(@RequestBody String fruta) {
	frutasArray.add(fruta);
}

GET /frutas

public List<String> get() {
	return frutasArray;
}



### Consultando entidade
- GET
    - Ajustar método pra retornar lista fake



### Gravando entidade
- POST
    - Gravar entidade na lista fake



### Removendo entidade
- DELETE
    - Remover entidade da lista fake



### Iniciando o Projeto
- `git clone projeto-hands-on` 



### !Usando testes automatizados
- Rest Assured



## Cabou-se