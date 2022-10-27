package codegen;

/**
 * NumOpcode class used for bytecodes with a number op field
 * e.g. lit 5
 */
public class NumOpcode extends Code {
    int num;

    public NumOpcode(Codes.ByteCodes code, int num2) {
        super(code);
        num = num2;
    }

    int getNum() {
        return num;
    }

    public String toString() {
        return super.toString() + " " + num;
    }

    public void print() {
        System.out.println(toString());
    }
}
