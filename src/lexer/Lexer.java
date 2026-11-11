package lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
this is responsible for reading source code, recognizing lexemes, classifying them and generating tokens.
OUTPUT: sequence of Token objects thus stored in a collection List.

-methods to implement and their responsibility;;

1. scanTokens()
    - coordinates the scanning process and returns tokens.
2. scanToken()
    - examines the current character and identifies its category.
3. advance()
    - consumes the current character and moves forward.
4. peek()
    - looks at the current character without consuming it.
5. peekNext()
    - looks one character ahead.
6. match()
    - checks whether the next character matches an expected character.
7. isAtEnd()
    - determines whether the source has been completely scanned.
8. isDigit()
    - checks whether a character is numeric.
9. isAlpha()
    - checks whether a character can start an identifier.
10. isAlphaNumeric()
    - checks whether a character is valid within an identifier.
11.  skipWhitespace()
    - handles spaces, tabs and newlines.
12. identifier()
    - recognizes identifiers and keywords.
13. number()
    - recognizes integer and floating-point literals.
14. string()
    - recognizes string literals.
15. addToken()
    - creates and stores a Token object.
 */
public class Lexer {
    //source and token storage
    private final String source; //complete source code being analyzed.
    private final List<Token> tokens = new ArrayList<>(); //collection of recognized tokens are stored here.

    //scanning positions
    private int start = 0; //starting index of the lexeme currently being scanned.
    private int current = 0; //index of the character currently being examined.

    //source location tracking
    private int line = 1; //current source-code line.
    private int column = 1; //current source-code column.
    private int startLine = 1;
    private int startColumn = 1;

    //KEYWORD mapping
    private static final Map<String, TokenType> keywords = new HashMap<>();

    static {
        keywords.put("int", TokenType.INT);
        keywords.put("float", TokenType.FLOAT);
        keywords.put("string", TokenType.STRING);
        keywords.put("if", TokenType.IF);
        keywords.put("else", TokenType.ELSE);
        keywords.put("while", TokenType.WHILE);
        keywords.put("print", TokenType.PRINT);
    }

    //constructor
    public Lexer(String source) {
        this.source = source;
    }

    //main scanning method
    public List<Token> scanTokens() {
        while(!isAtEnd()) {
            start = current;
            startLine = line;
            startColumn = column;

            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", line, column));
        return tokens;
    }

    //identify current character
    private void scanToken() {
        char c = advance();

        switch(c) {
            //single-character tokens
            case '(' -> addToken(TokenType.OPENING_PARENTHESIS);
            case ')' -> addToken(TokenType.CLOSING_PARENTHESIS);

            case '{' -> addToken(TokenType.OPENING_BRACE);
            case '}' -> addToken(TokenType.CLOSING_BRACE);

            case '[' -> addToken(TokenType.OPENING_BRACKET);
            case ']' -> addToken(TokenType.CLOSING_BRACKET);

            case ',' -> addToken(TokenType.COMMA);
            case ';' -> addToken(TokenType.SEMICOLON);
            case '.' -> addToken(TokenType.DOT);

            //arithmetic operators
            case '*' -> addToken(TokenType.MULTIPLY);
            case '+' -> addToken(match('=') ? TokenType.PLUS_ASSIGN : TokenType.PLUS);
            case '-' -> addToken(match('=') ? TokenType.MINUS_ASSIGN : TokenType.MINUS);

            //division and comments
            case '/' -> {
                if (match('/')) {
                    //single-line comment
                    while(peek() != '\n' && !isAtEnd()) {
                        advance();
                    }
                } else if(match('*')) {
                    //multi-line comment
                    while(!isAtEnd()) {
                        if (peek() == '*' && peekNext() == '/') {
                            advance();
                            advance();
                            return;
                        }
                        advance();
                    }
                    throw error("Unterminated block comment!");
                } else {
                    addToken(TokenType.DIVIDE);
                }
            }

            //assignment and comparison
            case '=' -> addToken(match('=') ? TokenType.EQUAL : TokenType.ASSIGN);
            case '!' -> addToken(match('=') ? TokenType.NOT_EQUAL : TokenType.NOT);
            case '>' -> addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER_THAN);
            case '<' -> addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS_THAN);

            //logical operators
            case '&' -> {
                if(match('&')) {
                    addToken(TokenType.AND);
                } else {
                    throw error ("Unexpected character '&'!");
                }
            }

            case '|' -> {
                if (match('|')) {
                    addToken(TokenType.OR);
                } else {
                    throw error("Unexpected character '|'!");
                }
            }

            //whitespace
            case ' ', '\r', '\t', '\n' -> {
                //ignore whitespace(s)
            }

            //string literals
            case '"' -> string();

            //numbers
            default -> {
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                } else {
                    throw error("Unexpected character '" + c + "'!");
                }
            }
        }
    }

    //string recognition
    private void string() {
        while(peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') {
                throw error("Unterminated string literal!");
            }
            advance();
        }

        if (isAtEnd()) {
            throw error("Unterminated string literal!");
        }

        //consume closing quotation mark
        advance();

        addToken(TokenType.STRING_LITERAL);
    }

    //character classification
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    //number recognition
    private void number() {
        while(isDigit(peek())) {
            advance();
        }

        //check for decimal part
        if (peek() == '.' && isDigit(peekNext())) {
            advance();

            while(isDigit(peek())) {
                advance();
            }

            addToken(TokenType.FLOAT_LITERAL);
        } else {
            addToken(TokenType.INTEGER_LITERAL);
        }
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }
    //identifier and keyword recognition
    private void identifier() {
        while(isAlphaNumeric(peek())) {
            advance();
        }
        String text = source.substring(start, current);

        TokenType type = keywords.get(text);

        if (type == null) {
            type = TokenType.IDENTIFIER;
        }

        addToken(type);
    }

    //token creation
    private void addToken(TokenType type) {
        String lexeme = source.substring(start, current);

        tokens.add(new Token(type, lexeme, startLine, startColumn));
    }

    //character scanning
    private char advance() {
        char c = source.charAt(current++);

        if (c == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }
        return c;
    }

    //look ahead
    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(current);
    }

    private char peekNext() {
        if (current + 1 >= source.length()) {
            return '\0';
        }
        return source.charAt(current + 1);
    }

    //character matching
    private boolean match(char expected) {
        if (isAtEnd()) {
            return false;
        }

        if (source.charAt(current) != expected) {
            return false;
        }
        advance();

        return true;
    }

    //End-Of-File(EOF) check
    private boolean isAtEnd() {
        return current >= source.length();
    }

    //error reporting
    private IllegalArgumentException error(String message) {
        return new IllegalArgumentException(
                "Lexical Error at line " + startLine + ", column " + startColumn + ": " + message
        );
    }
}



























