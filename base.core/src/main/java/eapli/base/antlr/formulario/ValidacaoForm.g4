grammar ValidacaoForm;

start: 'form' '{' finalResult=form '}' EOF #endResult
     ;
form: form verif=instrucao_form ';' #instRec
    | verif=instrucao_form ';' #inst
    ;
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
inteiro: '(' val=inteiro ')' #opParen
       | left=inteiro op=MUL_DIV right=inteiro #opMD
       | left=inteiro op=SOMA_SUB right=inteiro #opSS
       | 'length' '(' attr=atributo ')' #opLen
       | attr=atributo #opAtr
       | num=NUM #opNum
       | MUL_DIV #operr
       | SOMA_SUB #operr
       | inteiro MUL_DIV #operr
       | inteiro SOMA_SUB #operr
       | MUL_DIV inteiro #operr
       | SOMA_SUB inteiro #operr
       ;
atributo: name=ATRIBUTO #attribute
        ;
regex: reg=STRING
     ;

NUM:'-'?[0-9]+('.'[0-9]+)?;
OPERADOR_BOOL:('<'|'>'|'='|'<='|'>='|'<>');
MUL_DIV:('*'|'/'|'%');
SOMA_SUB:('+'|'-');
ATRIBUTO:[A-Za-z][A-Za-z0-9]*;
STRING:'"' (~[\r\n"] | '""')* '"';

WS:[ \t\r\n]+->skip;

ERR_CHAR:.;










