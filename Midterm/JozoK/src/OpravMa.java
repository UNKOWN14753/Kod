import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

abstract class X implements Comparable<X>{
    static String name = "X";

    public X(){}

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return 5;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int compareTo(X x) {
        return !(this instanceof A && x instanceof B) ? 1 : -1;
    }
}

class A extends X{
    static String name= "A";

    public A(){}

    public String getName() {
        return name;
    }

}

class B extends X{
    static String name = "B";

    public B(){}
}

public  class OpravMa {
    public static boolean search(int[] arr, int element) {
        int bot = 0;
        int top = arr.length-1;
        while (bot <= top) {  //=
            int mid = (bot+top)/2;
            if (arr[mid] == element) return true;
            if (arr[mid] < element) bot = mid+1; else top = mid-1;
        }
        return false;
    }

    public static int[][] jednotkova(int n) {
        int[] vektor = new int[n];  // je vynulovany vdaka Jave
        int[][] matica = new int[n][];
        IntStream.range(0,n).forEach(i -> {
            vektor[i] = 1;
            if (i > 0) vektor[i-1] = 0;
            matica[i] = vektor.clone();
        });
        return matica;
    }


    public static void main(String[] args) {
        System.out.println(search(new int[]{1,3,4,5,6,7}, 8));
        System.out.println(Arrays.deepToString(jednotkova(4)));

        String s1 = "Java";
        String s2 = "java";
        String s3 = null;

        System.out.println(s1 == s2);                   // false
        System.out.println(s1.equalsIgnoreCase(s2));    // true
        System.out.println(s2.equals(s1));              // false
        System.out.println(s2.equals(s3));              // false
        //System.out.println(s3.equals(s2));              // Null Pointer Exception

        System.out.println(List.of(new A(), new B()).size());                           // 2
        System.out.println(new HashSet<>(Arrays.asList(new A(), new B())).size());      // 1

        System.out.println(List.of(X.name, A.name, B.name));                            // [X, A, B]
        System.out.println(List.of(new A().getName(), new B().getName()));              // [A, X]

        System.out.println(new TreeSet<>(Arrays.asList(new A(), new B())).size());                 // 2
        System.out.println(new TreeSet<>(Arrays.asList(new A(), new B())).first().getName());      // A
        System.out.println(new TreeSet<>(Arrays.asList(new A(), new B())).last().getName());       // X
    }
}
