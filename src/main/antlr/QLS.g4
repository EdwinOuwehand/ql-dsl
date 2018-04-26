grammar QLS;

@header {
package antlr.generated;
}

stylesheet
    :   'stylesheet' id=ID OPEN_BRACKET page+ CLOSE_BRACKET
    ;

page: 'page' ID OPEN_BRACKET segment+ defaultStatement* CLOSE_BRACKET;

section: 'section' id=STRING OPEN_BRACKET segment+ defaultStatement* CLOSE_BRACKET
       | 'section' id=STRING segment
       ;

segment: question
       | section
       ;

defaultStatement: defaultStyleStatement
                | defaultWidgetStatement
                ;

defaultWidgetStatement: 'default' type widget ;
defaultStyleStatement: 'default' type style ;

question: 'question' id=ID widget?
        | 'question' id=ID style?
        ;

widget: 'widget' widgetType;

widgetType: 'radio' (OPEN_PARENTH yes=STRING',' no=STRING CLOSE_PARENTH)?                     # radioType
          | 'checkbox' (OPEN_PARENTH yes=STRING CLOSE_PARENTH)?                               # checkboxType
          | 'dropdown' (OPEN_PARENTH yes=STRING',' no=STRING CLOSE_PARENTH)?                  # dropdownType
          | 'slider' OPEN_PARENTH start=NUMBER',' end=NUMBER',' step=NUMBER CLOSE_PARENTH     # sliderType
          | 'text'                                                                            # textType
          | 'spinbox'                                                                         # spinboxType
          ;

type
    : 'boolean'                                                             #booleanType
    | 'integer'                                                             #integerType
    | 'money'                                                               #moneyType
    | 'string'                                                              #stringType
    ;

style: OPEN_BRACKET styleProperty+ widget? CLOSE_BRACKET;

styleProperty: 'fontsize:' NUMBER #fontSizeProperty
             | 'font:' STRING     #fontProperty
             | 'width:' NUMBER    #widthProperty
             | 'color:' COLOR     #colorProperty
             ;

value: STRING # stringValue
     | NUMBER # numberValue
     | COLOR  # colorValue
     ;


ID:   [a-zA-Z_]+[a-zA-Z0-9_]*;

STRING: '"' .*? '"';

COLOR : '#' ('0'..'9' | 'a'..'f')+;

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
