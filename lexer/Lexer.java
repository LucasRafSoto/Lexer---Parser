package lexer;

import java.io.FileNotFoundException;
import lexer.readers.IReader;
import lexer.readers.SourceReader;
import tests.ILexer;

/**
 * The Lexer class is responsible for scanning the source file
 * which is a stream of characters and returning a stream of
 * tokens; each token object will contain the string (or access
 * to the string) that describes the token along with an
 * indication of its location in the source program to be used
 * for error reporting; we are tracking line numbers; white spaces
 * are space, tab, newlines
 */
public class Lexer implements ILexer {
    private boolean atEOF = false;
    // next character to process
    private char ch;
    private IReader source;

    // positions in line of current token
    private int startPosition, endPosition;
    private static int lineNumber;

    /**
     * Lexer constructor
     * 
     * @param sourceFile is the name of the File to read the program source from
     */
    public Lexer(String sourceFile) throws Exception {
        // init token table
        new TokenType();
        source = new SourceReader(sourceFile);
        ch = source.read();
    }

    public Lexer(IReader reader) throws Exception {
        // init token table
        new TokenType();
        this.source = reader;
        ch = source.read();
    }

    public Token errorHandling() {
        System.out.println(
                "******** illegal character: " + ch + "< \n at line: " + source.getLineno() + " Position: "
                        + source.getPosition());
        atEOF = true;
        return nextToken();
    }

    /**
     * newIdTokens are either ids or reserved words; new id's will be inserted
     * in the symbol table with an indication that they are id's
     * 
     * @param id            is the String just scanned - it's either an id or
     *                      reserved word
     * @param startPosition is the column in the source file where the token begins
     * @param endPosition   is the column in the source file where the token ends
     * @return the Token; either an id or one for the reserved words
     */
    public Token newIdToken(String id, int startPosition, int endPosition, int lineNumber) {
        try {
            return new Token(
                    startPosition,
                    endPosition,
                    Symbol.symbol(id, Tokens.Identifier),
                    source.getLineno());
        } catch (Exception e) {
            return nextToken();
        }
    }

    // All possible reserved tokens below
    public Token newInToken(String id, int startPosition, int endPosition, int lineNumber) {
        return new Token(
                startPosition,
                endPosition,
                Symbol.symbol(id, Tokens.In),
                source.getLineno());
    }

    public Token newForAllToken(String id, int startPosition, int endPosition, int lineNumber) {
        return new Token(
                startPosition,
                endPosition,
                Symbol.symbol(id, Tokens.Forall),
                source.getLineno());
    }

    /**
     * number tokens are inserted in the symbol table; we don't convert the
     * numeric strings to numbers until we load the bytecodes for interpreting;
     * this ensures that any machine numeric dependencies are deferred
     * until we actually run the program; i.e. the numeric constraints of the
     * hardware used to compile the source program are not used
     * 
     * @param number        is the int String just scanned
     * @param startPosition is the column in the source file where the int begins
     * @param endPosition   is the column in the source file where the int ends
     * @return the int Token
     */
    public Token newNumberToken(String number, int startPosition, int endPosition, int lineNumber) {
        return new Token(
                startPosition,
                endPosition,
                Symbol.symbol(number, Tokens.INTeger),
                source.getLineno());
    }

    public Token newScientificLitToken(String number, int startPosition, int endPosition, int lineNumber) {
        return new Token(startPosition, endPosition, Symbol.symbol(number, Tokens.ScientificLit), source.getLineno());
    }

    public Token newStringLitToken(String number, int startPosition, int endPosition, int lineNumber) {
        return new Token(startPosition, endPosition, Symbol.symbol(number, Tokens.StringLit), source.getLineno());
    }

    /**
     * build the token for operators (+ -) or separators (parens, braces)
     * filter out comments which begin with two slashes
     * 
     * @param tokenString   is the String representing the token
     * @param startPosition is the column in the source file where the token begins
     * @param endPosition   is the column in the source file where the token ends
     * @return the Token just found
     */
    public Token makeToken(String tokenString, int startPosition, int endPosition, int lineNumber) {
        // filter comments
        if (tokenString.equals("//")) {
            try {
                int oldLine = source.getLineno();
                do {
                    ch = source.read();
                } while (oldLine == source.getLineno());
            } catch (Exception e) {
                atEOF = true;
            }

            return nextToken();
        }

        // ensure it's a valid token
        Symbol symbol = Symbol.symbol(tokenString, Tokens.BogusToken);

        if (symbol == null) {
            errorHandling();
        }

        return new Token(startPosition, endPosition, symbol, source.getLineno());
    }

    /**
     * @return the next Token found in the source file
     */
    public Token nextToken() {

        // ch is always the next char to process
        if (atEOF) {
            if (source != null) {
                source.close();
                source = null;
            }

            return null;
        }

        try {
            // scan past whitespace
            while (Character.isWhitespace(ch)) {
                ch = source.read();
            }
        } catch (Exception e) {
            atEOF = true;
            return nextToken();
        }

        startPosition = source.getPosition();
        endPosition = startPosition - 1;

        if (ch == '"') {
            // return tokens for String words
            String string = "";
            int lineNumber = source.getLineno();
            int tempLineNum = source.getLineno();
            boolean stringLit = true;

            try {
                endPosition++;
                ch = source.read();
            } catch (Exception e) {
                errorHandling();
            }

            // concatenating all till closing quotation else error
            try {
                do {
                    if (tempLineNum != lineNumber) {
                        stringLit = false;
                        break;
                    }
                    endPosition++;
                    string += ch;
                    ch = source.read();
                    tempLineNum = source.getLineno();
                } while (ch != '"' && stringLit == true);
            } catch (Exception e) {
                System.out.println("***** Missing Closing Quotations *****");
                errorHandling();
            }
            endPosition++;
            try {
                ch = source.read();
            } catch (Exception e) {
            }

            // Returning String Lit Token
            return newStringLitToken(string, startPosition, endPosition, source.getLineno());
        }

        if (Character.isJavaIdentifierStart(ch)) {
            // return tokens for ids and reserved words
            String id = "";

            try {
                do {
                    endPosition++;
                    id += ch;
                    ch = source.read();
                } while (Character.isJavaIdentifierPart(ch));
            } catch (Exception e) {
                atEOF = true;
            }

            // Determining Reserved Words
            if (id.equals("in")) {
                return newInToken(id, startPosition, endPosition, source.getLineno());
            } else if (id.equals("forall")) {
                return newForAllToken(id, startPosition, endPosition, source.getLineno());
            } else {
                return newIdToken(id, startPosition, endPosition, source.getLineno());
            }
        }

        if (Character.isDigit(ch)) {
            // return number tokens
            String number = "";
            Boolean isScientificLit = false;

            // Adding first digit to string
            endPosition++;
            number += ch;
            try {
                ch = source.read();
            } catch (Exception e) {
                atEOF = true;
            }
            try {
                // Branching to either number or ScientificLit
                if (Character.isDigit(ch)) {
                    do {
                        endPosition++;
                        number += ch;
                        ch = source.read();
                    } while (Character.isDigit(ch));
                    if (!(Character.isDigit(ch))) {
                        errorHandling();
                    }
                }
                // ScientificLit
                // "."
                else if (ch == '.') {
                    endPosition++;
                    number += ch;
                    ch = source.read();
                    int digits = 0;
                    // Numbers after "." and error handling for ScientificLit
                    do {
                        digits++;
                        endPosition++;
                        number += ch;
                        if (digits < 1 || digits > 2) {
                            errorHandling();
                        }
                        ch = source.read();
                    } while (Character.isDigit(ch));
                    // Must be followed by an e or E
                    if (Character.toLowerCase(ch) != 'e') {
                        errorHandling();
                    } else {
                        endPosition++;
                        number += ch;
                        ch = source.read();
                    }
                    // Next up either + or -
                    if (ch != '+' && ch != '-') {
                        System.out.println("HELLLOOOOOO");
                        errorHandling();
                    } else {
                        endPosition++;
                        number += ch;
                        ch = source.read();
                        if (!(Character.isDigit(ch))) {
                            errorHandling();
                        }
                        do {
                            endPosition++;
                            number += ch;
                            ch = source.read();
                            digits++;
                        } while (Character.isDigit(ch));
                        isScientificLit = true;
                    }
                }
            } catch (Exception e) {
                errorHandling();
            }
            // Return statements depending on the Token type
            if (isScientificLit) {
                return newScientificLitToken(number, startPosition, endPosition, source.getLineno());
            } else {
                return newNumberToken(number, startPosition, endPosition, source.getLineno());
            }
        }

        // At this point the only tokens to check for are one or two
        // characters; we must also check for comments that begin with
        // 2 slashes
        String charOld = "" + ch;
        String op = charOld;
        Symbol sym;
        try {
            endPosition++;
            ch = source.read();
            op += ch;

            // check if valid 2 char operator; if it's not in the symbol
            // table then don't insert it since we really have a one char
            // token
            sym = Symbol.symbol(op, Tokens.BogusToken);
            if (sym == null) {
                // it must be a one char token
                return makeToken(charOld, startPosition, endPosition, source.getLineno());
            }

            endPosition++;
            ch = source.read();

            return makeToken(op, startPosition, endPosition, source.getLineno());
        } catch (Exception e) {
            /* no-op */ }

        atEOF = true;
        if (startPosition == endPosition) {
            op = charOld;
        }

        return makeToken(op, startPosition, endPosition, source.getLineno());
    }

    public static void main(String args[]) {
        Token token;

        try {
            Lexer lex = new Lexer(args[0]);
            lineNumber = lex.source.getLineno();
            token = lex.nextToken();
        } catch (FileNotFoundException e) {
            System.out.println("> java lexer.Lexer ");
            System.out.println("usage: java lexer.Lexer filename.x");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("File Error");
        }
    }

}