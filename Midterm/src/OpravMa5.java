import java.util.*;

abstract class X implements Comparable<X>{
    static String name = "X";
    String getName() { return name;}

    @Override
    public int hashCode() {
        return 1984;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int compareTo(X o) {
        return (this instanceof A && o instanceof B)? -1:1;
    }
}
class A extends X {
    static String name = "A";
    String getName() { return name;}
}
class B extends X {
    static String name = "B";
}

public class OpravMa5 {

    public static void main(String[] args) {
        System.out.println(List.of(new A(), new B()).size());                           // 2
        System.out.println(new HashSet<>(Arrays.asList(new A(), new B())).size());      // 1

        System.out.println(List.of(X.name, A.name, B.name));                            // [X, A, B]
        System.out.println(List.of(new A().getName(), new B().getName()));              // [A, X]

        System.out.println(new TreeSet<>(Arrays.asList(new A(), new B())).size());                 // 2
        System.out.println(new TreeSet<>(Arrays.asList(new A(), new B())).first().getName());      // A
        System.out.println(new TreeSet<>(Arrays.asList(new A(), new B())).last().getName());       // X
    }
}
