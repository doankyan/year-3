/**
 * Student name: Doan Ky An
 * Student ID: 1410160
 */
grammar MC;

@lexer::header{
    package mc.parser;
}

@lexer::members{
@Override
public Token emit() {
    switch (getType()) {
    case UNCLOSE_STRING:
        Token result = super.emit();
        // you'll need to define this method
        throw new UncloseString(result.getText());

    case ILLEGAL_ESCAPE:
        result = super.emit();
        throw new IllegalEscape(result.getText());

    case ERROR_CHAR:
        result = super.emit();
        throw new ErrorToken(result.getText());

    default:
        return super.emit();
    }
}
}

@parser::header{
    package mc.parser;
}

options{
    language=Java;
}

program : decl+ EOF;

decl: varDecl | funcDecl ; // | funcDecl

listID: termID( COMMA termID )* ;

termID: ID | ID LSB INTLIT RSB ;

funcDecl: mctype ID LB listPara RB blockStmt ;

listPara: (non_listPara)? ;

non_listPara: paraDecl (COMMA paraDecl)* ;

paraDecl: paraDecl1 | paraDecl2 ;

paraDecl1: primitiveType ID ;

paraDecl2: primitiveType ID LSB RSB ;

blockStmt: LP listCmd RP ;

listCmd: listVarDecl listStmt;

listVarDecl: varDecl*;

listStmt: stmt* ;

stmt: ifStmt
    | callStmt
    | dowhileStmt
    | forStmt
    | breakStmt
    | continueStmt
    | returnStmt
    | expStmt
    | blockStmt ;
ifStmt: IF LB exp RB stmt (ELSE stmt)? ;


dowhileStmt: DO listStmt WHILE exp SEMI;

forStmt: FOR LB exp SEMI exp SEMI exp RB stmt;

breakStmt: BREAK SEMI;

callStmt: funccall SEMI ;

continueStmt: CONTINUE SEMI ;

returnStmt: RETURN exp? SEMI ;

expStmt: exp SEMI;

varDecl: primitiveType listID SEMI ;

mctype: primitiveType | voidType | arrayPointerType ;

primitiveType: BOOLEAN | INT | FLOAT | STRING ;

voidType: VOID ;

arrayPointerType: (BOOLEAN | INT | FLOAT | STRING)  LSB RSB ;

listExp: expList? ;

expList: exp (COMMA exp)*;

exp: term1 ASSIGN exp
    | term1 ;
term1: term1 LOR term2
    | term2 ;
term2: term2 LAND term3
    | term3 ;
term3: term4 (EQ | NOTEQ) term4
    | term4 ;
term4: term5 (LESS | LESSEQ | GREAT | GREATEQ) term5
    | term5 ;
term5: term5 (SUB | ADD) term6
    | term6 ;
term6: term6 (DIV | MUL | MOD) term7
    | term7 ;
term7: (LNOT|SUB) term7
     | term8 ;
term8: term9 LSB exp RSB
    | term9 ;
term9: INTLIT
    | FLOATLIT
    | BOOLIT
    | STRINGLIT
    | funccall
    | ID
    | LB exp RB ;
funccall: ID LB listExp RB ;
//comment
Comment: (BLOCK_COMMENT | LINE_COMMENT) -> skip ;
fragment
BLOCK_COMMENT
    : '/*' .*? ('*/' | EOF);
fragment
LINE_COMMENT
    : '//' ~[\r\n]* ;

//Do khong nhan nen dat tren keyword
BOOLIT: TRUE | FALSE ;

BOOLEAN: 'boolean' ;
BREAK: 'break' ;
CONTINUE: 'continue' ;
ELSE: 'else' ;
FOR: 'for' ;
FLOAT: 'float' ;
IF: 'if' ;
INT: 'int' ;
RETURN: 'return' ;
VOID: 'void' ;
DO: 'do' ;
WHILE: 'while' ;
TRUE: 'true' ;
FALSE: 'false' ;
STRING: 'string' ;




FLOATLIT: Floatnumbers '.' Floatnumbers? ExponentPart?
    | '.' Floatnumbers ExponentPart?
    | Floatnumbers ExponentPart ;

fragment
Floatnumbers: Digit+ ;
fragment
ExponentPart: [eE] [+-]? Floatnumbers ;
fragment
StartDigit: [1-9] ;
fragment
Digit: [0-9] ;
fragment
ZeroDigit: '0' ;

INTLIT: Digit+ ;


STRINGLIT: '"' StringCharacters* '"' {setText(getText().substring(1, getText().length()-1));} ;

fragment
StringCharacters:  ~[\\\r\n"] | ESC;

fragment
ESC: '\\' [bfrnt\'"\\] ;

ID: NameStartChar NameChar* ;

fragment
NameStartChar
    : [a-zA-Z]
    | '_' ;
fragment
NameChar
    : NameStartChar
    | [0-9] ;



ADD: '+' ;
MUL: '*' ;
LNOT: '!' ;
LOR: '||' ;
NOTEQ: '!=' ;
LESS: '<' ;
LESSEQ: '<=' ;
ASSIGN: '=' ;
SUB: '-' ;
DIV: '/' ;
MOD: '%' ;
LAND: '&&' ;
EQ: '==' ;
GREAT: '>' ;
GREATEQ: '>=' ;

COMMA: ',' ;

SEMI: ';' ;

LSB: '[';

RSB: ']';

LB: '(' ;

RB: ')' ;

LP: '{';

RP: '}';

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines

UNCLOSE_STRING: '"'(~[\r\n"\\] |'\\b' | '\\f' | '\\r' | '\\n' | '\\t' | '\\"' |'\\\\')*  ;

ILLEGAL_ESCAPE: '"' ((~[\r\n"\\] |'\\b' | '\\f' | '\\r' | '\\n' | '\\t' | '\\"' |'\\\\')*  '\\' ~[bfrnt"\\] (~[\r\n"\\] |'\\b' | '\\f' | '\\r' | '\\n' | '\\t' | '\\"' |'\\\\')*)+  '"';

ERROR_CHAR: .;