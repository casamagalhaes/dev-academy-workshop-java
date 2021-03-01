![Image](imagens/devacademy1.svg)



## Desenvolvendo APIs REST com Java



![Image](imagens/techs.png)



### Facilitadores:
Note: Essas descrições são parcialmente baseado na realidade...



#### Pablo Monteiro
- Java dev há 200 anos
- Trocou o chapéu de vaqueiro e a botina pelo teclado e mouse
- Hoje é Dev Leader na Casa Magalhães e um dos veteranos no time do Varejofacil.



#### Giva
- Programou o portão da caverna de tesouros de Alibabá. 
- A chave criptografada "Abra-te sésamo" é de autoria dele. 
- Atualmente arquiteto do Varejofacil, é também conhecido como **"O Oráculo do Varejo"**.



#### Daniel Chaves
- Quando criança, mamãe bateu na cabeça dele e disse: _"Esse menino vai ser programador Java"_. 
- Em 2005 a profecia se concretizou e hoje é um arquiteto phodão do Varejofacil.



<div style="float: left; width: 35%"> 

![Image](imagens/cronograma.png)

</div>
<div style="float: right; width: 40%;">

<div style="text-align:left">

<h6> Dia 1 </h6>
<span style="font-size: 17px"> 

 - Conceitos 
    - HTTP
    - API
    - REST
 
 - Preparando Ambiente
 
 - Hello API - `/hello`

 - Mão na massa: API CRUD (parte 1)

</span>

***

<h6> Dia 2 </h6>

<span style="font-size: 17px"> 

- Mão na massa: API CRUD (Parte 2)

- Validações
 
- Testes

- Documentação 
    - Swagger

</span>
</div>
</div>



## Conceitos



![Image](imagens/http.png)
Note:
- basicamente é o protocolo de comunicação que rege a maioria das interações na web
- bastante usado na arquitetura cliente-servidor
- se comunica usando mensagens



- _Hipertext Transfer Protocol_ (HTTP)
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
- Accept-Headers
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



![Image](imagens/api.png)
Note: 
- Por que APIs? Pra quê servem?
    - Hoje em dia as aplicações sendo consumidas por diversos meios (web, smartphones, etc)
    - Muitos sistemas buscam se integrar, compartilhar/consumir informações
- As APIs vieram pra faciliar esse meio campo
- Criando uma forma de integração fluida



- _Application Programming Interface_ (API)
- Conjunto de instruções e padrões de programação que servem para **fornecer dados e informações** relevantes de uma determinada aplicação
Note:
- Em português, Interface de Programação de Aplicações
- Contrato para que outras aplicações se integrem com outra aplicação



### Benefícios
- Segurança na troca de informação
- Redução de volume de dados
Note:
- Só se exibe o que se quer exibir (Segurança)
- Envia dados específicos de acordo com a solicitação (Dados)



### Exemplos de APIs
- PagSeguro, PayPal

- AWS, Azure

- LinkedIn, Twitter, Facebook, Google

- Booking

Note: 
- Empresas que disponibilizam API para que aplicações consigam se integrar aos seus sistemas e usar os serviços.



### Evolução API
![Image](imagens/api-history.png)
Note: 
- EDI de 1948 com 1a implementação nos anos 1970, aeroporto de Londres; 
- RPC dos anos 1970 com 1a implementação em 1982, depois XEROX, SUN...
- CORBA (EAI), nos anos 1990, é uma das primeiras formas de API moderna, por permitir sistemas com diferentes implementações se comunicar, sem forçar uma alteração na sua implementação e estrutura
- SOA, com o advento do XML as integrações ficaram mais focadas em webservices.
- Microservices foi uma variação que entrou em cena quando SOA passou a ter predominância no mercado. Nos anos 2010s as Web APIs não só se tornaram relevantes mas dominaram o mercado como padrão para integração de apicações e serviços.
- Agora que já vimos boa parte da base da comunicação existente na wbe, vamos partir agora pra uma abstração de arquitetura, onde nos permitirá criar projetos bem definidos e viabilizar uma melhor comunicação entre aplicações.



![Image](imagens/rest-fake-2.png)
Note:
- ops! Não esse tipo de rest(descanso)



![Image](imagens/rest.png)
Note:
- Não é uma biblioteca. Não é uma tecnologia, um framework
- É basicamente um conjunto de boas práticas pra construção de aplicações Web.



- _Representational State Transfer_ (REST)

- Conjunto de princípios e definições para criar interfaces bem definidas

- "Padrão" da arquitetura de software para interação de aplicações, usando múltiplos _Web services_.

- Tem como principal pilar o protocolo HTTP
Note:
- Transferência de Estado Representacional
- Permite comunicação entre aplicações



### Benefícios:
- Facilidade de execução
- Alto aproveitamento da infraestrutura da web
- Flexibilidade
Note:
- Flexível para escolher de formato que melhor se adequa. Ex: json, xml, html, etc



### Mais sobre REST
- _Web resources_
- Conjunto de operações (GET, POST, PUT, DELETE, PATCH, CONNECT, OPTIONS, TRACE)
Note:
- Web Resource ou Resource: identificação da funcionalidade/recurso



### REST X RESTful?
- O divisor de águas são os princípios!!
Note:
- Alguns mais xiitas só consideram uma API RESTful se tiverem os 6 princípios implementados



### RESTful
- Princípios:
    - _Cliente - servidor_
    - _Stateless_
    - _Cacheable_
    - Sistema em Camadas (_Layered system_)
    - Código sob demanda
Note:
- São 6 os requisitos que definem uma arquitetura RESTful. 
- *Cliente-servidor*: deixa bem claro o que faz parte do cliente e o que faz parte do servidor. EX: cliente não precisa saber como os dados são salvos
- *stateless*: cada comunicação é independente, ou seja, ela precisa mandar toda a informação necessária; 
- *cacheability*: deve facilitar o cache do cliente (capacidade de fornecer info para guardar/expirar respostas); 
    - suporte a camadas de sistema (proxy, load balancer não deve interferir na comunicação);
- *Sistema de Camadas*: uso de camadas pra facilitar a escalabilidade, confiabilidade e segurança
- *Código sobre demanda*: enviar apenas as informações do recurso de acordo com o solicitado. (O servidor pode eventualmente enviar código para o cliente executar...)



### E o 6 princípio?
- Princípio (Cont.)
    - Interfaces bem definidas
        - Identificação de recurso (`/pessoas`)
        - Manipulação de recursos por suas representações
        - Mensagens auto descritivas
        - HATEOAS
Note:
- *Interfaces bem definicas*: forma de identificar os recursos. É devolvida com a mensagem que outras rodas sobre o recurso, pra conseguir mais informações
    - Identificação de recurso nas requisições (`/pessoas`)
    - Manipulação de recursos por suas representações
    - Mensagens auto descritivas
    - HATEOAS: recursos bem identificados; com a resposta de um get eu consigo fazer um post ou patch; headers (text/json), http status, ajudam a descrever as ações; Hypermedia as the engine of application state - assim como usuario acessando uma pagina as mensagens contem "links" para navegar pelos recursos



### Exemplo HATEOAS
- Request
```log
GET /pessoas/423
Accept: text/json
```
- Response
```log
HTTP 200 - OK
Content-Type: application/json
Content-Length: ...
{
    pessoaId: 423,
    nome: "Fulano de Tal",
    idade: 25,
    links: {
        cnh: "/pessoas/423/cnh",
        endereco: "/pessoas/423/endereco"
    }
}
```

Note: 
- Resposta textual (HTML, XML, JSON) e status HTTP. 
- HATEOAS concede links para navegação e se comporta conforme o estado.




![Image](imagens/maos-a-obra.png)
Note:
- Visto um pouco da teoria que envolve o desenvolvimento de uma API REST, vamos praticar um pouco!



![Image](imagens/springboot-no-bg.png)
- Spring Initializr (https://start.spring.io/)
    - Configuração
    - Geração do projeto
Note: 
- o que é Spring Initializr?
- o que representa os campos
- etc



![Image](imagens/visualstudio.png)
- Importar projeto
    - Open _'path do projeto'_
- Importar plugins
    - Java Extension Pack
    - Lombok
- Run
    - Menu Terminal, abrir novo **Terminal**
              ./gradlew bootRun 
    - Acessar: _localhost:8080_



![Image](imagens/erro-primeira-pagina.png)
Note:
- Página de erro de recurso não encontrado (Status 404)
- Antes de corrigir, vamos falar um pouco sobre um padrão de desenvolvimento que usaremos pra dividir nossa aplicação em camadas.



![Image](imagens/mvc.png)
Note: 
- Padrão que divide projeto em camadas
- Model:
    - camada principal, tudo gira em torno dela
    - escrita, leitura, validações, etc
- View: camada de interação com usuário
    - no uso de API não temos necessariamente essa camada
    - nos nossos exemplos, podemos dizer que ela é o Postman/browser
- Controller: responsável por se comunicar com a view e receber e enviar respostas para as msgs recebidas



#### Criando primeiro controller
```java
@RestController
public class HelloController {

    @RequestMapping(method = RequestMethod.GET, 
                      path = "/hello")
    public String hello() {
        return "Hello, World!";
    }

}
```



![Image](imagens/postman.png)
- Testando aplicação



### Recebendo Dados
- Query String (**/pessoa?id=1**)
```
...
void metodo(@RequestParam Long id){...}
...
```
- URI (**/pessoa/1**)
```
...
void metodo(@PathVariable Long id){...}
...
```



### Controller Raiz Quadrada
```
@RequestMapping(method = RequestMethod.GET, 
                  path = "/raiz-quadrada/{numero}")
    public Double calculaRaizQuadrada(@PathVariable Long numero) {
        return Math.sqrt(numero.doubleValue());
    }
```
Note: 
- path variable tem que ter o mesmo nome da variável
- Math.sqrt usado pra nos ajudar a realizar esse cáculo.



### Exemplo Resultado
```
GET /raiz-quadrada/{number} (number=9)

200 - OK
Content-Type: text/plain

Resultado: 3
```



### Desafio 1:
- Criar rota da raiz quadrada usando String Query
        localhost:8080/raiz-quadrada/query-string?numero=9



### Explorando cenários
- API distribuidora de bebidas
- Usando dados em memória
Note:
- Criar controller Bebidas
- Criar lista pra armazenar dados dados fake



### Cenários
- Buscar todas as bebidas (GET)
- Gravar uma nova bebida (POST)
- Remover uma bebida (DELETE)
- Atualizar uma bebida (PUT)



### Desafio 2
- Criar pesquisa de bebida por parte do nome (Comece com)
    - Ex:
            /bebidas/a
    - Resultado:
            aguardente
            agua



### Iniciando o Projeto
- `git clone projeto-hands-on` 



### Validação
- 


### Testes
- Rest Assured



### Outros
- Erros comuns (falso positivo) 
    - Ex. HTTP Status 200 e no body msg erro);
- Falar sobre versionamento de APIs 
    - Versão do modelo;



### Swagger
- O que é?
- Como fazer?


### Desafio CM
- O que é?
- Cronograma
    - Desenvolvimento: 03/03 - 04/03
    - Análise: 05/03 - 07/03
    - Resultado: 08/03



### Dúvidas?



## Cabou-se