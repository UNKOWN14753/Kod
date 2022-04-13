import java.util.List;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streamy {

    public static IntStream jednotkovaMatica(int n){
        return IntStream.range(0, n).flatMap(x -> IntStream.range(0, n).map(y -> x == y ? 1 : 0));
    }

    public static IntStream cifSum9(IntStream vstup){
        return vstup.filter(x -> x % 9 == 0); // :)))
        //return vstup.filter(x -> String.valueOf(x).chars().map(Character::getNumericValue).sum() % 9 == 0);
    }

    public static IntStream cifry1_9(IntStream vstup){
        return vstup.filter(x -> String.valueOf(x).chars().boxed().collect(Collectors.toSet()).size() == 9 &&
                String.valueOf(x).chars().boxed().toList().size() == 9);
    }

    public static IntStream delitele (int n){
        return IntStream.range(1, n).filter(x -> n % x == 0);
    }

    public static Function<Integer, Integer> sucetDelitelov = x -> IntStream.range(1, x).filter(y -> x % y == 0).sum();

    static IntPredicate dokonale = x -> delitele(x).sum() == x;

    public static IntStream spriatelene(IntStream vstup){
        IntPredicate friends = x -> sucetDelitelov.apply(sucetDelitelov.apply(x)) == x;
        return vstup.filter(friends).filter(x -> sucetDelitelov.apply(x) != x);
    }

    public static void main(String[] args) {
        jednotkovaMatica(4).forEach(System.out::println);
        System.out.println("------");
        cifSum9(IntStream.range(1, 100)).forEach(System.out::println);
        System.out.println("------");
        cifry1_9(IntStream.range(123456789, 133456789)).forEach(System.out::println);
        System.out.println("------");
        IntStream.range(1, 100).filter(dokonale).forEach(System.out::println);
        System.out.println("------");
        spriatelene(IntStream.range(0,30_000)).forEach(System.out::println);
    }
}
