import java.util.Stack;

public class Rekurzia {
    public static long foo(int a, int b) {
        if (a == 0 && b == 0) return 1;
        else {
            long sum = 0L;
            for(int s = 0; s < a+b; s++)
                for(int i = 0; i <= s; i++)
                    sum += foo(i, s-i);  // i + (s-i) je vzdy s...
            return sum;
        }
    }
    static final int MAX = 40;
    static long[][] mem = new long[MAX][MAX];
    static {
        mem[0][0] = 1;
    }

    public static long foo1(int a, int b) {
        if (mem[a][b] > 0) return mem[a][b];
        long sum = 0L;
        for (int s = 0; s < a + b; s++)
            for (int i = 0; i <= s; i++)
                sum += foo1(i, s-i);
        mem[a][b] = sum;
        return sum;
    }

    public static long foo2(int a, int b) {
        Stack<Integer> stack = new Stack<>();
        stack.push(100*a+b);
        long sum = 0L;
        while (!stack.empty()) {
            Integer x = stack.pop();
            int aa = x / 100;
            int bb = x % 100;
            if (aa == 0 && bb == 0) sum++;
            else
                for (int s = 0; s < aa + bb; s++)
                    for (int i = 0; i <= s; i++)
                        stack.push(100 * i + (s - i));
        }
        return sum;
    }

    public static long foo3(int a, int b) {
        if (a+b < 2) return 1;
        else if (a > 0) return (a+b+1)* foo3(a-1,b);
        else return (a+b+1)* foo3(a,b-1);
    }
    public static long foo4(int a, int b) {
        long fact = 1;
        for (int i = 3; i < (a + 1) + (b + 1); i++) {
            fact *= i;
            System.out.println(i + " " + fact);
        }
        return fact;
    }

    public static void main(String[] args) {
        int MAX = 6;
//        for(int i = 0; i<MAX; i++) {
//            for(int j = 0; j<MAX; j++)
//                System.out.print(foo(i,j) + ",\t");
//            System.out.println();
//        }
//        System.out.println(foo(6,6));

//        for(int i = 0; i<MAX; i++) {
//            for(int j = 0; j<MAX; j++)
//                System.out.print(foo1(i,j) + ",\t");
//            System.out.println();
//        }
//        System.out.println(foo1(10  ,10));


        for(int i = 0; i<MAX; i++) {
            for(int j = 0; j<MAX; j++)
                System.out.print(foo2(i,j) + ",\t");
            System.out.println();
        }
//        System.out.println(foo2(10  ,10));



        for(int i = 0; i<6; i++) {
            for(int j = 0; j<6; j++)
                System.out.print(foo3(i,j) + ",\t");
            System.out.println();
        }
        System.out.println(foo3(10,10));
        System.out.println(foo4(10,10));
        System.out.println(Long.MAX_VALUE);
        long fact = 1L;
        for(int i= 1; i < 22; i++) {
            fact *=i;
            System.out.println(i+"!="+fact);
        }
        System.out.println(foo4(9,9));
        System.out.println(foo4(9,10));
//        System.out.println(foo3(11,11));


    }
}
