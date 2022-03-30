package RiesenieJozoK;
record Node(Tree left, int value, Tree right) implements Tree {
    @Override
    public int size() {
        return 1 + ((left == null)?0:left.size()) + ((right == null)?0:right.size());
    }

    @Override
    public int root() {
        return value;
    }

    @Override
    public String toString() {
        return "new Node(" + left + ", " + value + ", " + right + ")";
    }
    // ---- potialto to bol bonus pre vas, kod piste nizsie

    @Override
    public boolean hasFakeLeaf() {

        if (left == null && right == null)
            return true;


        return (left != null && left.hasFakeLeaf()) || (right != null && right.hasFakeLeaf()); // toto doprogramujte
    }

    @Override
    public Tree removeFakeLeaf() {
        if (left == null && right == null)
            return new Leaf(value);

        return new Node(
                left==null?null:left.removeFakeLeaf(),
                value,
                right==null?null:right.removeFakeLeaf()
        );
    }

    public int depth(){

        int leftDepth = left==null?0:left.depth();
        int rightDepth = right==null?0:right.depth();

        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Override
    public boolean isComplete() {

        if ( left != null && right != null) return left.depth() == right.depth();
        return true;
    }
}
