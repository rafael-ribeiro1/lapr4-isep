# US1007 - Formulário

=======================================


# 1. Requisitos

**US1007** Como Gestor de Projeto, eu pretendo que o sistema seja capaz de executar/interpretar os scripts especificados na linguagem/gramática anteriormente desenvolvida.

- Dado que sou Gestor do Projeto quando acedo aos scripts especificados na linguagem/gramática desenvolvida então pretendo proceder à sua execução. 

A interpretação feita deste requisito foi no sentido de que se pretendia ver finalizado o desenvolvimento da `US1004` do Sprint C.

# 2. Procedimento

## 2.1. Funcionalidades Desenvolvidas

### 2.1.1. Execução do Formulário

No âmbito da execução do Script procedeu-se ao desenvolvimento de 2 funcionalidades fundamentais:

#### a.  Condições Booleanas e Validações

Através do seguinte excerto da gramática desenvolvida:  

```
instrucao_form: condicao=cond_verificacao '->' valid=validacoes #opVerifyInstrucaoForm
              ;

cond_verificacao: left=cond_verificacao 'AND' right=cond_verificacao #opAndVerif
                | left=cond_verificacao 'OR' right=cond_verificacao #opOrVerif
                | opBoolStack=operacao_bool #opBoolVerif
                ;
validacoes: validStack=validacoes logicStack=validacao #opValidRec
          | logicStack=validacao #opValid
          ;
          
validacao: '[' operacoes_logicas ']'
         ;
         
operacoes_logicas: left=operacoes_logicas 'AND' right=operacoes_logicas #opAndLogic
                 | left=operacoes_logicas 'OR' right=operacoes_logicas #opOrLogic
                 | opBoolStack=operacao_bool #opBoolLogic
                 | funcao_bool #funcBool
                 | instrucao_form #instForm
                 ;
                 
operacao_bool: left=operacao_bool op=OPERADOR_BOOL right=operacao_bool #opBool
             | 'str' '(' attr=atributo ')' #opBoolAtr
             | val=inteiro #opInt
             | str=STRING  #opStr
             ;
```

Através de uma linha no ficheiro de input com o seguinte formato exemplo:

```
CampoA <> 90 -> [20 == (5*4) AND 3 < 5+7];
```

Onde: 

* **CampoA** - representa um atributo do formulário com um valor associado;

* **<>** - diferente

* **->** - indica o início da secção de validações

* **[ ]** - limitam uma validação

  **Nota:** Se **[ ]** forem usadas de forma encadeada serão tratadas como um AND, isto é, todas as validações devem ser true para que a condição se verifique

#### b. Funções e Obtenção de Respostas relativas a Campos

 Através do seguinte excerto da gramática desenvolvida:  

```
funcao_bool: 'isEmpty' '(' attr=atributo ')' #empty
           | 'isNotEmpty' '(' attr=atributo ')' #notEmpty
           | 'match' '(' attr=atributo ',' reg=regex ')'#match
           | 'notMatch' '(' attr=atributo ',' reg=regex ')'#notMatch
           ;
operacao_bool: left=operacao_bool op=OPERADOR_BOOL right=operacao_bool #opBool
             | 'str' '(' attr=atributo ')' #opBoolAtr
             | val=inteiro #opInt
             | str=STRING  #opStr
             ;
```

Através de uma linha no ficheiro de input com o seguinte formato:

    CampoA < 100 -> [length(CampoB) > 9+5 AND isEmpty(CampoC)][str(CampoA) > (5*4) AND AND match(CampoA, "[A-Za-z]")];
    

Onde:

* **length** - representa uma função que retorna o tamanho do valor associado à variável entre parênteses
* **isEmpty** - representa uma função que retorna false se o valor da variável entre parênteses for vazio, caso contrário retorna true
* **str** - representa uma função que converte o valor da variável entre parênteses para texto
* **match** - representa uma função que compara o primeiro parâmetro (o atributo) com um determinado regex (segundo parâmetro) e retorna true ou false consoante o resultado

### 2.1.2. Validação do Formulário

 Através do seguinte excerto da gramática desenvolvida:  

```
atributo: name=ATRIBUTO #attribute
```

Foi desenvolvido também o listener de validação, para validar o script, tanto sintaticamente/lexicamente como também os atributos do formulário usados no script.