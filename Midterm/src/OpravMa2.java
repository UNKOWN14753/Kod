import java.util.Arrays;
import java.util.stream.IntStream;

public class OpravMa2 {

    public static int[][] jednotkova(int n) {
        int[] vektor = new int[n];  // je vynulovany vdaka Jave
        int[][] matica = new int[n][];
        IntStream.range(0,n).forEach(i -> {
            vektor[i] = 1;
            matica[i] = vektor;
        });
        return matica;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(jednotkova(4)));
    }
}
