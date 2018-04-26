grammar QL;

@header {
package antlr.generated;
}

form
    :   'form' id=ID OPEN_BRACKET statement* CLOSE_BRACKET
    ;

statement
    :   text=STRING ID ':' type calculatedValue? ';'?                       #question
    |   'if' '(' expression ')'
                OPEN_BRACKET statement* CLOSE_BRACKET                       #ifStatement
    |   'if' '(' expression ')'
                OPEN_BRACKET ifCase+=statement* CLOSE_BRACKET
                'else' OPEN_BRACKET elseCase+=statement* CLOSE_BRACKET      #ifElseStatement
    ;

expression
    :   STRING                                                              #stringLiteral
    |   NUMBER                                                              #integerLiteral
    |   ID                                                                  #parameter
    |   op=(TRUE|FALSE)                                                     #booleanLiteral
    |   '(' expression ')'                                                  #parameterGroup
    |   '!' expression                                                      #negation
    |   left=expression op=('/'|'*') right=expression                       #mulDiv
    |   left=expression op=('-'|'+') right=expression                       #addSub
    |   left=expression op=('>'|'<'|'=='|'!='|'<='|'>=') right=expression   #comparation
    |   left=expression '&&' right=expression                               #logicalAnd
    |   left=expression '||' right=expression                               #logicalOr
    ;

calculatedValue
    :   '=' value=expression
    ;

type
    : 'boolean'                                                             #booleanType
    | 'integer'                                                             #integerType
    | 'money'                                                               #moneyType
    | 'string'                                                              #stringType
    ;


TRUE: 'TRUE' ;
FALSE: 'FALSE' ;

ID:   [a-zA-Z_]+[a-zA-Z0-9_]*;

STRING: '"' .*? '"';

NUMBER
    :   '-'? ('0'..'9')+ ('.' ('0'..'9')+)?
    ;

WHITESPACE
    :   (' ' | '\t' | '\r'| '\n') -> channel(HIDDEN)
    ;

MULTI_LINE_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

SINGLE_LINE_COMMENT
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;

OPEN_BRACKET :  '{' ;
CLOSE_BRACKET : '}' ;
OPEN_PARENTH :  '(' ;
CLOSE_PARENTH : ')' ;
