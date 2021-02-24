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



### API

- _Application Programming Interface_
- Conjunto de instruções e padrões de programação que servem para *fornecer dados e informações* relevantes de uma determinada aplicação
- Vantagens
- Exemplos: (PIX)



### Evolução API
![Image](imagens/api-history.png)
Note: 
- EDI de 1948 com 1a implementação nos anos 1970, aeroporto de Londres; 
- RPC dos anos 1970 com 1a implementação em 1982, depois XEROX, SUN...
- CORBA (EAI), nos anos 1990, é uma das primeiras formas de API moderna, por permitir sistemas com diferentes implementações se comunicar, sem forçar uma alteração na sua implementação e estrutura
- SOA, com o advento do XML as integrações ficaram mais focadas em webservices.
- Microservices foi uma variação que entrou em cena quando SOA passou a ter predominância no mercado. Nos anos 2010s as Web APIs não só se tornaram relevantes mas dominaram o mercado como padrão para integração de apicações e serviços.




### HTTP
- _Hipertext Transfer Protocol_
- Projetado para permitir elementos conectados em rede estabelecer comunicação cliente-servidor
- Protocolo de camada de aplicação projetado dentro do framework da suite de protocolos de internet (TCP/IP)
- Baseado em requisição - resposta (Request/Response)



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



#### HTTP _Methods_
- `GET`    - Obter informação do(s) recurso(s)
- `POST`   - Processar a informação contida na requisição
- `PUT`    - Alterar o estado do recurso para o estado contido na requisição
- `DELETE` - Delete né =]
Note: Esses são muito usados



#### HTTP _Methods_
- `PATCH`  - Alterar estado do recurso com requisição parcial
- `HEAD`   - Similar ao `GET` mas não tem _response body_
- `OPTIONS`- Retorna dados de quais outros métodos e operações os servidor suporta
Note: menos usados mas muito útis. Tem mais... TRACE - rastreio de rede, retorna o q enviado / CONNECT - usado pra iniciar comunicação de duas vias com o recurso



#### HTTP _Status_
- 1xx - Resposta informativa parcial
- 2XX - Deu certo, chapa.
- 3XX - Sei não, pergunta ali...
- 4XX - Deu bom não, mas eh contigo...
- 5XX - Deu ruim. Mal minha...
Note: 100 - Continue; 102 - Processando; 200 - OK; 201 - Created; 202 - Accepted; 301 - Moved permanently; 400 - Bad request; 401 - Unauthorized; 404 - Not Found; 500 - Internal server error; 503 - Service unavailable.



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