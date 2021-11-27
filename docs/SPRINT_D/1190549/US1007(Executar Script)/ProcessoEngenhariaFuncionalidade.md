# US1007 - Listener Script 
=======================================


# 1. Requisitos

**US1007** Como Gestor de Projeto, eu pretendo que o sistema seja capaz de executar/interpretar os scripts especificados na linguagem/gramática anteriormente desenvolvida.

- Dado que sou Gestor do Projeto quando acedo aos scripts especificados na linguagem/gramática desenvolvida então pretendo proceder à sua execução. 

A interpretação feita deste requisito foi no sentido de que se pretendia ver finalizado o desenvolvimento da `US1004` do Sprint C.

# 2. Procedimento

## 2.1. Aprimoramento da Gramática Desenvolvida

Inicialmente procederam-se às seguintes modificações ao nível da gramática:

| Descrição Melhoria | Antes | Depois |
| :----------------: | :------: | :-------: |
|Substituição dos parênteses retos nas instruções if por parênteses curvos|`if [condição]` |`if (condição)`|
|Garantir uso de aspas em Strings|`exemplo@email.com`|`"exemplo@email.com"`|
|Garantir operações entre variáveis e valores numéricos (variável-variável, valor-valor, valor-variável, variável-valor) | `valor1 = (4+9)/5*2#2` | `$valor4=($valor2)#($valor4)/3.143;` |
|Garantir múltiplas instruções num bloco If, bem como, Ifs encadeados | `if[valor1>6]-> valor2= 10` | `if(6>$valor1 AND $valor2>7 ){$valor3= 5+1;$vindoFuncao = readFile("precos.xml","preco","234578");if($valor1==$valor2){$valor3 = 10;}}`|
| Definição do token FILE_PATH por forma a permitir especificar o path absoluto da localização do ficheiro .xml | `example.xml` | `sample/example.xml` |
| Permitir alguns caracteres especiais na definição de Strings através da modificação do token WORD (nomeadamente acentos, underscore, pontos de exclamação/interrogação | `[A-Za-z0-9]+` |`[A-Za-z0-9_?!:\u00C0-\u00FF]+`|

## 2.2. Funcionalidades Desenvolvidas

### 2.2.1. Execução do Script de Atividades Automáticas

No âmbito da execução do Script procedeu-se ao desenvolvimento de 5 funcionalidades fundamentais:

#### a.  Substituição das Variáveis pelo seu Valor Real

Através do seguinte excerto da gramática desenvolvida:  

```
variavel:  nome=nome_variavel IGUAL valor=decimal #colocar_decimal_variavel
        |  nome=nome_variavel IGUAL valor=read_file #colocar_funcao_variavel
        ;
```

Através de uma linha no ficheiro de input com o seguinte formato exemplo:

​	`$valor1 = (13+1)/2;`

ou

```
$valor2=readFile("sample\products.xml","product","XYJ456","price");
```

Onde: 

* **$valor1** - representa uma qualquer variável, de acordo com a gramática desenvolvida, uma variável representa-se por um '$' seguido de uma palavra (sendo palavra `[A-Za-z0-9_?!:\u00C0-\u00FF]+` ou seja, podendo conter letras, números, pontos de exclamação/interrogação, underscore, dois pontos ou qualquer tipo de acentos)
  A cada variável pode ser atribuído um qualquer cálculo de acordo com o que será posteriormente evidenciado em 2.2.4 ou inclusivamente o resultado de uma função.

Desenvolveram-se os seguintes métodos:

```java
@Override
public void exitColocar_decimal_variavel(AutomatedScriptParser.Colocar_decimal_variavelContext ctx) {
    try{
        double l = decimalStack.pop();
        variaveis.put(ctx.nome.getText(),l);
    }catch (EmptyStackException e){
        return;
    }
}
```



```java
@Override public void exitColocar_funcao_variavel(AutomatedScriptParser.Colocar_funcao_variavelContext ctx) {
    if(!permissionToExecute) return;
    try{
        double result = decimalStack.pop();
        variaveis.put(ctx.nome.getText(), result);
    }catch(EmptyStackException e){
        return;
    }
}
```

A `decimalStack` consiste numa stack criada para armazenar o valor resultante de cálculos efetuados sobre uma variável ou até atribuições simples para a mesma. 

O contentor `variaveis` consiste num map cuja Key é o nome da variável (obtida através de ctx) e cujo Value é o valor resultante das operações definidas.

De modo geral, o propósito dos métodos é, extraindo da stack o valor resultante da realização das operações, associar no map o nome da variável a este valor.

#### b. Envio de e-mails

 Através do seguinte excerto da gramática desenvolvida:  

```
send_email: 'sendEmail' PARENTESE_CURVO_ABRIR destinatario=EMAIL VIRGULA assunto=string VIRGULA corpo=string PARENTESE_CURVO_FECHAR # sendEmail
        ;
```

Através de uma linha no ficheiro de input com o seguinte formato:

    sendEmail(email,assunto,corpo); 

Onde:

* **email** - representa uma qualquer string que respeite o seguinte regex `'"'[a-zA-Z0-9._%+-]+'@'[a-zA-Z0-9.-]+'.'[a-zA-Z]+'"'`.  
  **Exemplo:** "exemplo@email.com"
* **assunto** - representa uma qualquer string, podendo conter variáveis que serão posteriormente substituídas pelos seus valores  
  **Exemplo:** "exemplo 123 ! ? exemplo_exemplo TesTE {{$var}}"
* **corpo** - representa uma qualquer string, podendo conter variáveis que serão posteriormente substituídas pelos seus valores  
  **Exemplo:** "exemplo 123 ! ? exemplo_exemplo TesTE {{$var}}"

Desenvolveu-se o seguinte método: 

```java
@Override public void exitSendEmail(AutomatedScriptParser.SendEmailContext ctx) {
    EmailHandler emailHandler = new EmailHandler();
    String conteudo = stringStack.pop();
    String assunto = stringStack.pop();
    emailHandler.sendEmail(removerAspas(removerAspas(ctx.destinatario.getText())),
            assunto, conteudo);
}
```

Conforme explicitado anteriormente a `stringStack` representa uma stack onde são armazenadas as Strings cujas variáveis se encontram já substituídas pelo seu respetivo valor.
O método removerAspas consiste num método auxiliar que remove as aspas da String.
Genericamente o método remove da stack as Strings já tratadas, nomeadamente as Strings referentes ao conteúdo e ao assunto do e-mail e envia-as com uma chamada ao método sendEmail().

**NOTA:** O EmailHandler remete para um API anteriormente desenvolvido e que permite o envio do e-mail.

#### c.  Leitura de Ficheiros .xml

Através do seguinte excerto da gramática desenvolvida:  

```a
read_file: 'readFile' PARENTESE_CURVO_ABRIR nomeFicheiro=nome_ficheiro VIRGULA base=string VIRGULA id=string VIRGULA elemento=string PARENTESE_CURVO_FECHAR # readFile
        ;
```

Tem-se a seguinte linha exemplo do ficheiro de input: 

```
$valor2=readFile("sample\products.xml","product","XYJ456","price");
```

Onde:

- **readFile(.) -** Demarca a necessidade de executar uma função que suporte a leitura de um ficheiro .xml
- **"sample\products.xml" -**  Representa o path de um ficheiro, obedece ao regex `[/\\A-Za-z.]+`
- **"product" -**  A tag que se pretende procurar no ficheiro .xml
- **"XYJ456" -**  O id do dado que se pretende consultar
- **"price" -** O atributo específico que se pretende obter

E considerando um ficheiro .xml tal como:

```xml
<products>
    <product>
        <id>XYJ234</id>
        <name>Product Two</name>
        <description>This is the description for product two.</description>
        <price>19.99</price>
    </product>
    <product>
        <id>XYJ456</id>
        <name>Product Three</name>
        <description>This is the description for product three.</description>
        <price>21.99</price>
    </product>
</products>
```

Desenvolveu-se o seguinte método:

```java
@Override public void exitReadFile(AutomatedScriptParser.ReadFileContext ctx) {
    if(!permissionToExecute) return;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        String filePath = removerAspas(ctx.nomeFicheiro.FILE_PATH().getText());
        String id = removerAspas(ctx.id.getText());

        Document doc = db.parse(new File(filePath));
        doc.getDocumentElement().normalize();

        String base = removerAspas(ctx.base.getText());

        NodeList list = doc.getElementsByTagName(base);

        for(int i =0 ; i< list.getLength() ; i++){
            Node node = list.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE ){
                Element obtained = (Element) node;

                String identfier = obtained.getElementsByTagName("id").item(0).getTextContent();

                if(!identfier.equals(id)) continue;

                String elemento = removerAspas(ctx.elemento.getText());

                Node a = obtained.getElementsByTagName(elemento).item(0);
                String desconto = a.getTextContent();
                decimalStack.push(Double.parseDouble(removerAspas(desconto)));
            }

        }
    } catch (ParserConfigurationException | SAXException | IOException | NullPointerException | NumberFormatException e) {
        throw new IllegalArgumentException("O ficheiro nao possui o elemento passado como parametro");
    }

}
```

Inicialmente extrai-se do ficheiro de input o path do ficheiro .xml, a tag a obter e o identificador desse dado.

Obtém-se a lista de Nodes incluídas na tag (correspondente à variável `base`) e itera-se pela mesma até se encontrar a tag relativa ao dado cujo identificador coincide com o extraído. Uma vez obtido o dado pretendido, obtém-se o valor do atributo especificado no ficheiro de input. Por fim, armazena-se o valor obtido na `decimalStack`.

#### d. Cálculos (com / sem recurso a variáveis)

Através do seguinte excerto da gramática desenvolvida:  

```
decimal: '(' dec=decimal ')' # realizarCalculoPrioritario
        | left=decimal op=MUL_DIV right=decimal  # realizarMultiplicaoDivisao
        | left=decimal op=SOMA_SUB right=decimal # realizarSomaSub
        | num=NUM # returnProprioNumero
        | nome=nome_variavel # devolver_valor_variavel
        | num_percentagem=percentagem_num # returnPercentagem
        | MUL_DIV #operr
        | SOMA_SUB #operr
        | decimal MUL_DIV #operr
        | decimal SOMA_SUB #operr
        | MUL_DIV decimal #operr
        | SOMA_SUB decimal #operr
        ;
```

Tem-se a seguinte linha exemplo do ficheiro de input: 

```
$valor1 = (13+1)/2;
```

ou

```
$valor4=($valor2)#($valor4)/ 3.143;
```

Onde:

- **#** - Representa o resto da divisão

Desenvolveram-se os seguintes métodos:

```java
@Override
public void exitRealizarMultiplicaoDivisao(AutomatedScriptParser.RealizarMultiplicaoDivisaoContext ctx) {
    if(!permissionToExecute) return;
    try{
      double right = decimalStack.pop();
      double left = decimalStack.pop();
      double result;
        switch (ctx.op.getText().charAt(0)) {
            case '*':
                result = left * right;
                break;
            case '/':
                result = left / right;
                break;
            default:
                result = left % right;
        }
        decimalStack.push(result);
    }catch (NullPointerException | EmptyStackException e){
        return;
    }
}
```



```java
@Override public void exitRealizarSomaSub(AutomatedScriptParser.RealizarSomaSubContext ctx) {
    if(!permissionToExecute) return;
    try{
        double right = decimalStack.pop();
        double left = decimalStack.pop();
        double result;
        switch (ctx.op.getText().charAt(0)) {
            case '+':
                result = left + right;
                break;
            case '-':
                result = left - right;
                break;
            default:
                result = 0;
        }
        decimalStack.push(result);
    }catch (NullPointerException | EmptyStackException e){
        return;
    }
}
```

Ambos os métodos começam por remover da stack os valores sobre os quais se pretende executar uma operação e posteriormente recorre-se a um switch case para efetuar as diferentes operações.

#### e. Operações Boolean 

Através do seguinte excerto da gramática desenvolvida:  

```
operacao_bool: left=objeto op=OPERADOR_COMPARAR right=objeto # realizarOperacaoBoolean
        ;
```

Tem-se a seguinte linha exemplo do ficheiro de input: 

```
if(10>$valor1 AND $valor2>0){
     $valor3=5+1;
     $vindoFuncao = readFile("sample\products.xml","product","XYJ456","price");
     sendEmail("1190549@isep.ipp.pt" , "depois {{$vindoFuncao}}", "preco {{$vindoFuncao}} euros");
}
```

Desenvolveu-se o seguinte método:

```java
@Override public void exitRealizarOperacaoBoolean(AutomatedScriptParser.RealizarOperacaoBooleanContext ctx) {

    boolean result;
    Double right = obterValor(ctx.right.getText());
    Double left=obterValor(ctx.left.getText());

    String op = ctx.OPERADOR_COMPARAR().toString();

    switch(op){
        case "<":
            result=left.compareTo(right)<0;
            break;
        case "<=":
            result= left.compareTo ( right )<=0;
            break;
        case ">":
            result= left.compareTo ( right )>0;
            break;
        case ">=":
            result= left.compareTo ( right )>=0;
            break;
        case "==":
            result= left.compareTo ( right )==0;
            break;
        case "<>":
            result=left.compareTo ( right )!=0;
            break;
        default:
            throw new IllegalArgumentException ("Illegal operator in boolean expression");
    }
    booleanStack.push(result);

}
```

Inicialmente invoca-se a função obterValor():

```java
private double obterValor(String var){
    try {
        if (!isNomeVariavel(var)) {
            return decimalStack.pop();
        }else {
            return obterValorVariavel(var);
        }
    }catch(EmptyStackException e){
        return 0;
    }
}
```

```java
private Double obterValorVariavel(String nomeVariavel){
    Double result=0.0;
    try{
        result = variaveis.get(nomeVariavel);
        if(result== null) throw new IllegalArgumentException("The mentionted var does not exist");
        if(result== decimalStack.peek()) decimalStack.pop();
    } catch (EmptyStackException e){
    }
    return result;
}
```

Caso se trate de uma variável, os valores `left` e `righ` serão obtidos através do Map `variaveis`, caso contrário serão extraídos da stack `decimalStack`.

Uma vez obtidos os valores de `left` e `righ` recorre-se a um switch case para efetuar as diferentes comparações.

### 2.2.2. Validação do Script de Atividades Automáticas

A validação do Script consistiu em métodos mais simples tais como:

```java
@Override public void enterOperr(AutomatedScriptParser.OperrContext ctx) {
   isValid = false;
}
```

