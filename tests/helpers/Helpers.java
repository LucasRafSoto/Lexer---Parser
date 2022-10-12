package tests.helpers;

import java.util.HashMap;
import java.util.StringTokenizer;

import ast.*;
import lexer.Symbol;
import lexer.Token;
import lexer.Tokens;
import tests.ILexer;

public class Helpers {
    private static HashMap<String, Token> testTokens = new HashMap<>();
    static {
        // Reserved words
        testTokens.put("program", new Token(0, 0, Symbol.symbol("program", Tokens.Program), 0));
        testTokens.put("if", new Token(0, 0, Symbol.symbol("if", Tokens.If), 0));
        testTokens.put("then", new Token(0, 0, Symbol.symbol("then", Tokens.Then), 0));
        testTokens.put("else", new Token(0, 0, Symbol.symbol("else", Tokens.Else), 0));
        testTokens.put("while", new Token(0, 0, Symbol.symbol("while", Tokens.While), 0));
        testTokens.put("function", new Token(0, 0, Symbol.symbol("function", Tokens.Function), 0));
        testTokens.put("return", new Token(0, 0, Symbol.symbol("return", Tokens.Return), 0));
        testTokens.put("forall", new Token(0, 0, Symbol.symbol("forall", Tokens.Forall), 0));
        testTokens.put("in", new Token(0, 0, Symbol.symbol("in", Tokens.In), 0));

        testTokens.put("{", new Token(0, 0, Symbol.symbol("{", Tokens.LeftBrace), 0));
        testTokens.put("}", new Token(0, 0, Symbol.symbol("}", Tokens.RightBrace), 0));
        testTokens.put("(", new Token(0, 0, Symbol.symbol("(", Tokens.LeftParen), 0));
        testTokens.put(")", new Token(0, 0, Symbol.symbol(")", Tokens.RightParen), 0));
        testTokens.put("[", new Token(0, 0, Symbol.symbol("[", Tokens.LeftBracket), 0));
        testTokens.put("]", new Token(0, 0, Symbol.symbol("]", Tokens.RightBracket), 0));
        testTokens.put(",", new Token(0, 0, Symbol.symbol(",", Tokens.Comma), 0));
        testTokens.put("..", new Token(0, 0, Symbol.symbol("..", Tokens.Range), 0));

        // Operators
        testTokens.put("=", new Token(0, 0, Symbol.symbol("=", Tokens.Assign), 0));
        testTokens.put("+", new Token(0, 0, Symbol.symbol("+", Tokens.Plus), 0));
        testTokens.put("-", new Token(0, 0, Symbol.symbol("-", Tokens.Minus), 0));
        testTokens.put("|", new Token(0, 0, Symbol.symbol("|", Tokens.Or), 0));
        testTokens.put("&", new Token(0, 0, Symbol.symbol("&", Tokens.And), 0));
        testTokens.put("*", new Token(0, 0, Symbol.symbol("*", Tokens.Multiply), 0));
        testTokens.put("/", new Token(0, 0, Symbol.symbol("/", Tokens.Divide), 0));

        // Relational Operators
        testTokens.put("==", new Token(0, 0, Symbol.symbol("==", Tokens.Equal), 0));
        testTokens.put("!=", new Token(0, 0, Symbol.symbol("!=", Tokens.NotEqual), 0));
        testTokens.put("<", new Token(0, 0, Symbol.symbol("<", Tokens.Less), 0));
        testTokens.put("<=", new Token(0, 0, Symbol.symbol("<=", Tokens.LessEqual), 0));
        testTokens.put(">", new Token(0, 0, Symbol.symbol(">", Tokens.Greater), 0));
        testTokens.put(">=", new Token(0, 0, Symbol.symbol(">=", Tokens.GreaterEqual), 0));

        // Types
        testTokens.put("int", new Token(0, 0, Symbol.symbol("int", Tokens.Int), 0));
        testTokens.put("<int>", new Token(0, 0, Symbol.symbol("42", Tokens.INTeger), 0));
        testTokens.put("boolean", new Token(0, 0, Symbol.symbol("boolean", Tokens.BOOLean), 0));
        testTokens.put("<boolean>", new Token(0, 0, Symbol.symbol("0", Tokens.INTeger), 0));
        testTokens.put("string", new Token(0, 0, Symbol.symbol("string", Tokens.StringType), 0));
        testTokens.put("<string>", new Token(0, 0, Symbol.symbol("This is a string", Tokens.StringLit), 0));
        testTokens.put("scientific", new Token(0, 0, Symbol.symbol("scientific", Tokens.Scientific), 0));
        testTokens.put("<scientific>", new Token(0, 0, Symbol.symbol("1.23e+456", Tokens.ScientificLit), 0));

        // Identifiers
        testTokens.put("<id>", new Token(0, 0, Symbol.symbol("x", Tokens.Identifier), 0));
    }

    public static Token tt(String token) {
        return testTokens.get(token);
    }

    private static HashMap<String, AST> treeMappings = new HashMap<>();
    static {
        treeMappings.put("int", new IntTypeTree());
        treeMappings.put("<int>", new IntTree(Helpers.tt("int")));
        treeMappings.put("boolean", new BoolTypeTree());
        treeMappings.put("<boolean>", new IntTree(Helpers.tt("int")));
        treeMappings.put("string", new StringTypeTree());
        treeMappings.put("<string>", new StringTree(Helpers.tt("string")));
        treeMappings.put("scientific", new ScientificTypeTree());
        treeMappings.put("<scientific>", new ScientificTree(Helpers.tt("scientific")));

    }

    public static AST at(String token) {
        return treeMappings.get(token);
    }

    public static ILexer lexerFromPseudoProgram(String program) throws Exception {
        StringTokenizer tokenizer = new StringTokenizer(program);

        TestLexer lexer = new TestLexer();

        while (tokenizer.hasMoreTokens()) {
            String tokenString = tokenizer.nextToken();
            Token token = Helpers.tt(tokenString);

            if (token == null) {
                // This should only happen if I have created an invalid test case
                throw new Exception(String.format("Unrecognized token: %s", tokenString));
            }

            lexer.addToken(token);
        }

        return lexer;
    }
}
