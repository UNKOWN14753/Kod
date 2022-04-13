public class Rekurzia {

    static long [][] memo;

    public static long origFoo(int a, int b) {  //original
        if (a == 0 && b == 0) return 1;
        else {
            long sum = 0L;
            for(int s = 0; s < a+b; s++)
                for(int i = 0; i <= s; i++)
                    sum += origFoo(i, s-i);  // i + (s-i) je vzdy s...
            return sum;
        }
    }

    public static long foo(int a, int b) {
        if (a == 0 && b == 0) {
            return 1;
        }
        else {
            long sum = 0L;
            for(int s = 0; s < a+b; s++) {
                for (int i = 0; i <= s; i++) {
                    if (memo[i][s - i] != 0) {
                        sum += memo[i][s - i];
                    } else {
                        memo[i][s-i] = foo(i, s-i);
                        sum += memo[i][s - i];
                    }
                }
            }
            return sum;
        }
    }

    public static long foo2(int a, int b) {
        memo[0][0] = 1;
        for(int s = 0; s < a+b; s++) {
            for (int i = 0; i <= s; i++) {
                memo[a][b] += memo[i][s-i];
            }
        }
        return memo[a][b];
    }

    public static long foo3(int a, int b) {
        if (a == 0 && b == 0) return 1;
        if (a == 1 && b == 0) return 1;
        if (a == 0 && b == 1) return 1;
        else {
            if (0 < a){
                return (a+b+1) * foo3(a-1, b);
            }
            return (a+b+1) * foo3(a, b-1);
        }
    }

    public static void main(String[] args) {
        for(int a = 0; a < 5; a++) {
            for (int b = 0; b < 5; b++) {
                memo = new long[a+b+1][a+b+1];
                System.out.println(foo(a, b) == origFoo(a, b));
                System.out.println(foo(a, b) == foo3(a, b));
                System.out.println(foo(a, b) == foo2(a, b));

            }
        }
    }
}
