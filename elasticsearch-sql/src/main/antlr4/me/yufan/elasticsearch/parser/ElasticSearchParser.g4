parser grammar ElasticSearchParser;

options {
    tokenVocab = ElasticSearchLexer;
}

prog
:
    (
        selectOperation
        | deleteOperation
    )
    SEMI ? EOF
;

selectOperation
:
    SELECT columList FROM tableRef
    (
        whereClause
    )?
    (
        groupClause
    )?
    (
        orderClause
    )?
    (
        limitClause
    )?
;

deleteOperation
:
    DELETE FROM tableRef
    (
        whereClause
    )?
;

columList
:
    nameOprand
    (
        COMMA nameOprand
    )*
;

nameOprand
:
    (
        tableName = ID DOT
    )? columName = name
    (
        AS alias = ID
    )?
;

name
:
    LPAREN name RPAREN # LRName
    | DISTINCT columnName = name #distinct
    | left = name op = (STAR | SLASH | MOD) right = name # MulName
    | left = name op = (PLUS | SUB) right = name # AddName
    | ID LPAREN columnName = name RPAREN # AggregationName
    | identity # columnName
;

identity
:
    ID # idEle
    | INT # intEle
    | FLOAT # floatEle
    | STRING # stringEle
;

boolExpr
:
    LPAREN boolExpr RPAREN # lrExpr
    | left = boolExpr EQ right = boolExpr # eqOpr
    | left = boolExpr GT right = boolExpr # gtOpr
    | left = boolExpr LT right = boolExpr # ltOpr
    | left = boolExpr GTEQ right = boolExpr #gteqOpr
    | left = boolExpr LTEQ right = boolExpr #lteqOpr
    | left = boolExpr BANGEQ right = boolExpr # notEqOpr
    | left = boolExpr AND right = boolExpr # andOpr
    | left = boolExpr OR right = boolExpr # orOpr
    | left = boolExpr BETWEEN right = boolExpr # betweenExpr
    | inExpr # inBooleanExpr
    | name # nameOpr
;

inExpr
:
    left = identity in_or_not_in right = inRightOprandList
;

in_or_not_in
:
    IN # inOp
    | NOT IN # notInOp
;

inRightOprandList
:
    inRightOprand
    |LPAREN inRightOprand (COMMA inRightOprand)* RPAREN
;

inRightOprand
:
    const_literal # constLiteral
    |left = inRightOprand op =
    (
        STAR
        | SLASH
        | MOD
        | PLUS
        | MINUS
    )
    right = inRightOprand # arithmeticLiteral
;

const_literal
:
    INT # intLiteral
    | FLOAT # floatLiteral
    | STRING # stringLiteral
;

tableRef
:
    tableName = ID
    (
        AS alias = ID
    )?
;

whereClause
:
    WHERE boolExpr // Should some default query restrict need to be added here ?
;

groupClause
:
    GROUP BY name
    (
        COMMA name
    )*
;

orderClause
:
    ORDER BY order
    (
        COMMA order
    )*
;

order
:
    name type =
    (
        ASC
        | DESC
    )?
;

limitClause
:
    LIMIT
    (
        offset = INT
    )? resultCount = INT
;
