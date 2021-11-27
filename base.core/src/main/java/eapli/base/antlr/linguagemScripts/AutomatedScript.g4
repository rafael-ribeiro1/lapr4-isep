grammar AutomatedScript;


start: SCRIPT CHAVETA_ENTRADA script CHAVETA_FECHAR
       | EOF
        ;

script: script instrucao_script # resetExecucao
        | instrucao_script # resetExecucao
        ;

instrucao_script: instrucoes_condicao #instrucao_condicao
        | funcao PONTO_VIRGULA #do_funcao
        | atribuir_variavel PONTO_VIRGULA #atribuir_variavel_rule
        | funcao # missing_ponto_virgula
        | atribuir_variavel # missing_ponto_virgula
        ;

instrucoes_condicao: IF cond=cond_verificacoes CHAVETA_ENTRADA condicoes=script CHAVETA_FECHAR #condicaoIf
        ;

cond_verificacoes: PARENTESE_CURVO_ABRIR cond=cond_verificacao PARENTESE_CURVO_FECHAR     # verificarCondicaoIf
        ;

cond_verificacao: '('cond=cond_verificacao ')' # prioridade
        | left=cond_verificacao AND right=cond_verificacao # andCondition
        | left=cond_verificacao OR right=cond_verificacao # orCondition
        | bool=operacao_bool # operacaoBoolean
        ;

operacao_bool: left=objeto op=OPERADOR_COMPARAR right=objeto # realizarOperacaoBoolean
        ;

funcao: send_email
        | read_file
        ;

send_email: 'sendEmail' PARENTESE_CURVO_ABRIR destinatario=EMAIL VIRGULA assunto=string VIRGULA corpo=string PARENTESE_CURVO_FECHAR # sendEmail
        ;

read_file: 'readFile' PARENTESE_CURVO_ABRIR nomeFicheiro=nome_ficheiro VIRGULA base=string VIRGULA id=string VIRGULA elemento=string PARENTESE_CURVO_FECHAR # readFile
        ;

string:
    ASPAS cont=content ASPAS #processarString
    | content #missingAspasError
    ;

content: cont=content word=WORD #skip
        | cont=content out=output #checkContent
        | out=output #checkContent
        | word=WORD #skip
        | cont=content word=NUM #skip
        | word=NUM #skip
        ;

output:   CHAVETA_ENTRADA CHAVETA_ENTRADA nome=nome_variavel CHAVETA_FECHAR CHAVETA_FECHAR #realizarOutput
        ;

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


objeto: dec=decimal #calculo_decimal
       | var=variavel #calculo_variavel
       ;

percentagem_num: PERCENTAGEM num=NUM
        ;


nome_ficheiro: ASPAS path=FILE_PATH ASPAS
        ;

atribuir_variavel: var=variavel #definirNovaVariavel;

variavel:  nome=nome_variavel IGUAL valor=decimal #colocar_decimal_variavel
        |  nome=nome_variavel IGUAL valor=read_file #colocar_funcao_variavel
        ;

nome_variavel: DOLLAR word=WORD;


NUM:'-'?[0-9]+('.'[0-9]+)?;
PERCENTAGEM:'%'
           ;
SOMA_SUB: '+'
    | '-'
    ;


MUL_DIV: '*'
    | BARRA
    | '#' // isto é resto
    ;

BARRA: '/';
SCRIPT: 'SCRIPT';
AND: 'AND';
OR: 'OR';
IGUAL: '=';
EMAIL: '"'[a-zA-Z0-9._%+-]+'@'[a-zA-Z0-9.-]+'.'[a-zA-Z]+'"';
OPERADOR_COMPARAR: '=='| '<>' | '<' | '>' | '<=' | '>=';
IF: 'if';
WORD: [A-Za-z0-9_?!:\u00C0-\u00FF]+;
VIRGULA: ',';
DOLLAR: '$';
ASPAS:'"';
CHAVETA_ENTRADA: '{';
CHAVETA_FECHAR: '}';
PARENTESE_CURVO_ABRIR: '(';
PARENTESE_CURVO_FECHAR: ')';
PONTO_VIRGULA: ';';
XML:'.xml';
FILE_PATH:[/\\A-Za-z.]+;
WS : [ \t\r\n]+->skip ;
ERR_CHAR:.;

// ver o any que esta no tutorial de antlr