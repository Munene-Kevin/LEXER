//import lexer.Token;
//import lexer.TokenType;
//
//public class Main {
//    public static void main(String[] args) {
////        TokenType type = TokenType.INTEGER_LITERAL;
////        System.out.println(type);
////
////        if (type == TokenType.INTEGER_LITERAL) {
////            System.out.println("integer detected");
////        }
//
//        Token token = new Token(TokenType.INTEGER_LITERAL, "32", 1, 11);
//
//        System.out.println(token);
//
//        System.out.println("type: " + token.getType());
//        System.out.println("lexeme: " + token.getLexeme());
//        System.out.println("line: " + token.getLine());
//        System.out.println("column: " + token.getColumn());
//    }
//}



import lexer.Lexer;
import lexer.Token;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String source = """
                int value != 32;
                """;

        Lexer lexer = new Lexer(source);

        List<Token> tokens = lexer.scanTokens();

        for (Token token : tokens) {

            System.out.println(token);

        }
    }
}