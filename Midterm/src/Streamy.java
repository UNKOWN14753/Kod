import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streamy {

    public static IntStream jednotkovaMatica(int n) {
        return IntStream.range(0,n).flatMap(i -> IntStream.range(0,n).map(j -> (i==j)?1:0));
    }
    // ciferný súčet čísla je deliteľný 9
    public static IntStream cifSum9(IntStream vstup) {
        return vstup.filter(n -> Stream.iterate(n, x->x>0, y->y/10).mapToInt(z -> z%10).sum() % 9 == 0);
        //return vstup.filter(x -> x % 9 == 0);
    }
    // číslo obsahuje všetky cifry 1..9, každú práve raz, v ľubovoľnom poradí
    public static IntStream cifry1_9(IntStream vstup) {
        return vstup.filter(n ->
                Stream.iterate(n, x->x>0, y->y/10).map(z -> z%10).sorted().toList().equals(List.of(1,2,3,4,5,6,7,8,9)));
    }
    static Function<Integer, Integer> sucetDelitelov = n -> IntStream.range(1,n).filter(d -> n % d == 0).sum();
    static IntPredicate nedokonale = n -> sucetDelitelov.apply(n) != n;
    public static IntStream spriatelene(IntStream vstup) {
        IntPredicate spriatelene = n -> sucetDelitelov.apply(sucetDelitelov.apply(n)) == n;
        return vstup.filter(spriatelene).filter(nedokonale);
                //.map(sucetDelitelov::apply);
    }

    public static void main(String[] args) {
        System.out.println(jednotkovaMatica(4).boxed().toList());
        var lst = List.of(987654321, 112345678, 82719203, 11, 27);
        System.out.println(cifSum9(IntStream.of(987654321, 112345678, 82719203, 11, 27)).boxed().toList());
        System.out.println(cifry1_9(IntStream.of(987654321, 112345678, 82719203, 123456789, 1234567899, 11, 27)).boxed().toList());
        Function<Integer, Integer> sucetDelitelov = n -> IntStream.range(1,n).filter(d -> n % d == 0).sum();
        IntPredicate spriatelene = n -> sucetDelitelov.apply(sucetDelitelov.apply(n)) == n;
        System.out.println(spriatelene(IntStream.range(0,30_000)).boxed().toList());
    }
}
