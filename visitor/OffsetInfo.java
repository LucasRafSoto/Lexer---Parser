package visitor;

public class OffsetInfo {

    private int depth;
    private int offset;

    public OffsetInfo(int givenDepth, int givenOffset) {
        depth = givenDepth;
        offset = givenOffset;
    }

    public int getDepth() {
        return depth;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int newOffset) {
        offset = newOffset;
    }
}
