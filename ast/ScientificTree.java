package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class ScientificTree extends AST {

    private Symbol symbol;

    /**
     * @param token is the Token containing the String representation of the integer
     *              literal; we keep the String rather than converting to an integer
     *              value
     *              so we don't introduce any machine dependencies with respect to
     *              integer
     *              representations
     */
    public ScientificTree(Token token) {
        this.symbol = token.getSymbol();
    }

    public Object accept(ASTVisitor visitor) {
        return visitor.visitScientificTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
