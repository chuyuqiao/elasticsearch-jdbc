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
    SELECT columnList FROM tableRef
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

columnList
:
    nameOperand
    (
        COMMA nameOperand
    )*
;

nameOperand
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
    | left = name op = (STAR | SLASH | MOD | PLUS | SUB) right = name # BinaryName
    | ID collection # AggregationName // Should this support avg(a, b, c) ?
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
    left = identity inToken right = inRightOperandList
;

inToken
:
    IN # inOp
    | NOT IN # notInOp
;

collection
:
    LPAREN identity (COMMA identity)* RPAREN
;

inRightOperandList
:
    inRightOperand
    |LPAREN inRightOperand (COMMA inRightOperand)* RPAREN
;

inRightOperand
:
    identity # constLiteral
    |left = inRightOperand op =
    (
        STAR
        | SLASH
        | MOD
        | PLUS
        | MINUS
    )
    right = inRightOperand # arithmeticLiteral
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
