package lexer;
/*
-it is an enumeration that defines all the possible token categories supported.
-this restricts possible token categories,
-improves consistency,
-makes comparisons easier,
-makes the compiler easier to maintain.
 */
public enum TokenType {
    //KEYWORDS
    INT,
    FLOAT,
    STRING,
    IF,
    ELSE,
    WHILE,
    PRINT,

    //IDENTIFIERS
    IDENTIFIER,

    //LITERALS
    INTEGER_LITERAL,
    FLOAT_LITERAL,
    STRING_LITERAL,

    //ARITHMETIC OPERATORS
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,

    //ASSIGNMENT OPERATORS
    ASSIGN,
    PLUS_ASSIGN,
    MINUS_ASSIGN,

    //COMPARISON OPERATORS
    EQUAL,
    NOT_EQUAL,
    GREATER_THAN,
    LESS_THAN,
    GREATER_EQUAL,
    LESS_EQUAL,

    //LOGICAL OPERATORS
    AND,
    OR,
    NOT,

    //DELIMITERS
    OPENING_PARENTHESIS,
    CLOSING_PARENTHESIS,
    OPENING_BRACE,
    CLOSING_BRACE,
    OPENING_BRACKET,
    CLOSING_BRACKET,

    //SEPARATORS
    COMMA,
    SEMICOLON,
    DOT,

    //SPECIAL TOKENS
    EOF,
    UNKNOWN
}



















