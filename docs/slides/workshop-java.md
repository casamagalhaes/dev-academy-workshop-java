![Image](imagens/devacademy.png)



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
 
 - Modelos e pesistência

 - API CRUD

</span>

***

<h6> Dia 2 </h6>

<span style="font-size: 17px"> 

 - Validações

 - Querying
  - FiQL
  - GraphQL
  - Paginação

 - Testes

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



![Image](imagens/http.png)
Note:
- basicamente é o protocolo de comunicação que rege a maioria das interações na web
- usado na comunicação cliente-servidor
- comunicação usando mensagens
- request(client) <-> resposta(server)
- composição: (header | body) (pedaço da msg)
- informações da msg status/metodos, etc



### Headers
- 



### Body 



### Status
- Feedback do servidor

- Classes:
<div style="font-size:28px">
<ul>
<li>
    <b>1xx</b> - Até agora tudo bem, mas tá rolando ainda... <span style="color: blue">(Processamento)</span>
</li>
<li>
    <b>2XX</b> - Deu certo, chapa! <span style="color: green">(Confirmação)</span>
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
- 100 - Tudo correu bem até então (informação parcial). Pode continuar; 102 - Processando; 200 - OK; 201 - Created; 202 - Accepted; 301 - Moved permanently; 400 - Bad request; 401 - Unauthorized; 404 - Not Found; 500 - Internal server error; 503 - Service unavailable.



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
<li>
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
<li>
</ul>
</div>

Note: Esses são muito usados
Menos usados mas muito útis. Tem mais... TRACE - rastreio de rede, retorna o q enviado / CONNECT - usado pra iniciar comunicação de duas vias com o recurso
referencia: https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Methods



### REST
- _Representational State Transfer_
- "Padrão" da arquitetura de software para interação de aplicações, usando múltiplos _Web services_.
- _Web resources_
- Textual (JSON, XML, HTML)
- Conjunto de operações (GET, POST, PUT, DELETE, PATCH, CONNECT, OPTIONS, TRACE)



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



#### Teste de Exemplo de código
```java
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal Principal principal) {
        return "Hello, " + principal.getName() + "!";
    }

}
```



## Cabou-se