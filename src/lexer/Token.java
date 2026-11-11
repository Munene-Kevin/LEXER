package lexer;
/*
1. Token Type:
    - represents the category of the token.
2. Lexeme:
    - stores the actual characters recognized from the source code.
3. Line Number:
    - stores the line where the token appears in the source code.
4. Column Number:
    - stores the position where a token begins on its source line.

- we use 1-based line and column numbering.
- the lexer will be responsible for tracking these positions.
 */
public class Token {
    private final TokenType type;
    private final String lexeme;
    private final int line;
    private final int column;

    public Token(TokenType type, String lexeme, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public TokenType getType() {
        return this.type;
    }

    public String getLexeme() {
        return this.lexeme;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    @Override
    public String toString() {
        return String.format(
                "Token{type=%s, lexeme='%s', line=%d,column=%d}",
                type,
                lexeme,
                line,
                column
        );
    }
}






















