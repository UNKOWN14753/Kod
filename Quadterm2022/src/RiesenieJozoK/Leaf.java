package RiesenieJozoK;
record Leaf(int value) implements Tree {
    @Override
    public int size() {
        return 1;
    }
    @Override
    public int root() {
        return value;
    }
    @Override
    public String toString() {
        return "new Leaf(" + value + ")";
    }
    // ---- potialto to bol bonus pre vas, kod piste nizsie
    @Override
    public boolean hasFakeLeaf() {
        return false;  // toto doprogramujte
    }

    @Override
    public Tree removeFakeLeaf() {
        return new Leaf(value); // toto doprogramujte
    }

    public int depth(){
        return 1;
    }
    @Override
    public boolean isComplete() {
        return true; // toto doprogramujte
    }
}