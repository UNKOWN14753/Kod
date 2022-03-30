package RiesenieJozoK;

import java.util.Random;

sealed interface Tree permits Node, Leaf {
    int size();
    int root();

    boolean hasFakeLeaf();  // fake leaf je Node(null, value, null), funkcia vrati true, ak strom obsahuje takyto uzol
    Tree removeFakeLeaf();  // vrati kopiu stromu, kde kazdy fake leaf Node(null, value, null) je nahradeny Leaf(value)

    // predpokladajte, ze strom NEOBSAHUJE ziadne fake leafs
    // strom nazyvame uplny, ak vzdialenost kazdeho listu (Leaf) od korena stromu je rovnaka
    boolean isComplete();   // vrati true, ak strom je uplnym stromom

    // vrchol na indexe i ma synov na indexe 2*i+1, 2*i+2, ak take indexy existuju
    // koren je na indexe 0
    // k polu heapArray vytvorte zodpovedajuci strom
    static Tree heapifyHelp(int i, int[] heapArray){
        if (i >= heapArray.length) return null;

        if (i*2 + 1>= heapArray.length)
            return new Leaf(heapArray[i]);
        else {
            Tree left = heapifyHelp(2*i +1, heapArray);
            Tree right = heapifyHelp(2*i+2, heapArray);
            return new Node(left, heapArray[i], right);
        }

    }

    static Tree heapify(int[] heapArray) {

        return heapifyHelp(0, heapArray); // toto doprogramujte
    }
    //----
    public static void main(String[] args) {
        Tree[]t = { // zopar konstant
                new Node(null, 2, null),
                new Node(new Node(null, 2, null), 4, null),
                new Node(null, 6, null),
                new Node(null, 8, new Node(null, 6, null)),
                new Node(null, 10, new Node(null, 8, new Node(null, 6, null))),
                new Node(null, 12, new Node(null, 10, new Node(null, 8, new Node(null, 6, null)))),
                new Node(new Node(null, 12, new Node(null, 10, new Node(null, 8, new Node(null, 6, null)))), 14, new Node(null, 12, new Node(null, 10, new Node(null, 8, new Node(null, 6, null))))),
                new Node(null, 16, null),
                new Node(new Node(null, 16, null), 18, new Node(null, 16, null)),
        };
    }

    int depth();
}