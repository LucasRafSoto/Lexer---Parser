package visitor;

import java.util.HashMap;
import ast.AST;

public class OffsetVisitor extends ASTVisitor {

    int[] offset;
    int depth = 0;
    int parentOffset;
    int calculatedOffsetX;
    HashMap<AST, OffsetInfo> offsetMap = new HashMap<AST, OffsetInfo>();

    public OffsetVisitor() {
        offset = new int[100];

    }

    public void offset(AST t) {
        int kids = t.kidCount();
        depth++;
        visitKids(t);
        depth--;
        if (offsetMap.containsKey(t)) {
            int currentOffset = offsetMap.get(t).getOffset();
            int updateOffset = (calculatedOffsetX + currentOffset);

            try {
                if (updateOffset <= (offset[offsetMap.get(t).getDepth()]) - 2) {
                    offsetMap.get(t).setOffset(offset[offsetMap.get(t).getDepth()]);
                }
                offsetMap.get(t).setOffset(updateOffset);

            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } else if (kids != 0) {
            parentOffset = 0;
            for (int i = 1; i <= kids; i++) {
                try {
                    parentOffset += offsetMap.get(t.getKid(i)).getOffset();
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }
            parentOffset = (parentOffset / t.kidCount());

            // for possible shifting
            if (parentOffset < offset[depth]) {

                OffsetInfo info = new OffsetInfo(depth, offset[depth]);
                offsetMap.put(t, info);
                calculatedOffsetX = (offset[depth] - parentOffset);
                depth++;
                visitKids(t);
                depth--;
                offset[depth] += 2;
                parentOffset = 0;
            } else {
                OffsetInfo info = new OffsetInfo(depth, parentOffset);
                offsetMap.put(t, info);
                offset[depth] = parentOffset;
                offset[depth] += 2;
            }
        } else {
            OffsetInfo info = new OffsetInfo(depth, offset[depth]);
            offsetMap.put(t, info);
            offset[depth] += 2;
        }

    }

    public int getCountAtDepth(int level) {
        return offset[level];
    }

    public int getMaxDepth() {
        for (int i = 0; i < offset.length; i++) {
            if (offset[i] == 0) {
                return i;
            }
        }
        return 0;
    }

    public HashMap<AST, OffsetInfo> getOffsetMap() {
        return offsetMap;
    }

    public Object visitProgramTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitBlockTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitFunctionDeclTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitCallTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitDeclTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitIntTypeTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitBoolTypeTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitStringTypeTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitScientificTypeTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitFormalsTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitActualArgsTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitIfTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitWhileTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitReturnTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitAssignTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitIntTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitStringTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitScientificTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitIdTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitRelOpTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitAddOpTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitMultOpTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitForAllTree(AST t) {
        offset(t);
        return null;
    }

    public Object visitRangeExpTree(AST t) {
        offset(t);
        return null;
    }
}
