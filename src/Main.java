import lexer.Token;
import lexer.TokenType;

public class Main {
    public static void main(String[] args) {
//        TokenType type = TokenType.INTEGER_LITERAL;
//        System.out.println(type);
//
//        if (type == TokenType.INTEGER_LITERAL) {
//            System.out.println("integer detected");
//        }

        Token token = new Token(TokenType.INTEGER_LITERAL, "32", 1, 11);

        System.out.println(token);

        System.out.println("type: " + token.getType());
        System.out.println("lexeme: " + token.getLexeme());
        System.out.println("line: " + token.getLine());
        System.out.println("column: " + token.getColumn());
    }
}