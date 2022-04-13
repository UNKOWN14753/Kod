import java.util.*;
import java.util.stream.Collectors;

record Dvojica<A,B>(A a, B b) {
    @Override
    public String toString() { return a + "->" + b;  }
}
public record Relacia<A,B>(Set<A> defObor, Set<B>oborHodnot, Set<Dvojica<A,B>> dvojice) {
    // žiaden prvok množiny A sa nezobrazuje na dva rôzne prvky množiny B
    public boolean jeZobrazenie() {
        var y = dvojice.stream().collect(Collectors.groupingBy(Dvojica::a, Collectors.counting()));
        var m = y.values().stream().max(Long::compare).orElse(0L);
        return m < 2;
    }
    // ak je relácia zobrazením, potom vráti reprezentáciu pomocou mapy, inak null
    public Map<A,B> zobrazenie() {
        if (jeZobrazenie())
            return dvojice.stream().collect(Collectors.toMap(Dvojica::a, Dvojica::b));
        else return null;
    }
    // každý prvok cieľovej množiny B je obrazom *najviac jedného prvku* z množiny A.
    public boolean jeInjektivne() {
        if (jeZobrazenie()) {
            var y = dvojice.stream().collect(Collectors.toMap(Dvojica::a, Dvojica::b)).values();
            return new HashSet<>(y).size() == y.size();
        } else return false;
    }
    // každý prvok cieľovej množiny B je obrazom *aspoň nejakého prvku* z množiny A
    public boolean jeSurjektivne() {
        if (jeZobrazenie()) {
            var y = dvojice.stream().collect(Collectors.toMap(Dvojica::a, Dvojica::b));
            return y.values().size() == oborHodnot.size();
        } else return false;
    }
    // každý prvok cieľovej množiny B je obrazom *práve jedného prvku* z množiny A
    public boolean jeBijektivne() {
        return jeSurjektivne() && jeInjektivne();
    }
    // ak je zobrazenie injektívne, existuje k nemu inverzné, inak null
    public Relacia<B,A> inverzne() {
        if (jeInjektivne()) {
            return new Relacia<>(oborHodnot(), defObor(),
                    dvojice.stream().map(d->new Dvojica<>(d.b(), d.a())).collect(Collectors.toSet()));
        } else return null;
    }
    public <C> Relacia<A,C> skladanie(Relacia<B,C> druheZobrazenie) {
        return null;
    }

    public static void main(String[] args) {
        //var r1 = new Relacia<Integer,Character>(Set.of(1,2,3), Set.of('A','B'), Set.of(new Dvojica(1,'A'), new Dvojica(2,'B')));
        //var r2 = new Relacia<Integer,Character>(Set.of(1,2), Set.of('A','B'), Set.of(new Dvojica(1,'B'), new Dvojica(2,'A')));

        var obr1_L = new Relacia<Integer,Character>(Set.of(1,2,4), Set.of('J','A','V','E'), Set.of(new Dvojica(1,'J'), new Dvojica(2,'E'), new Dvojica(4,'A')));
        var obr1_R = new Relacia<Integer,Character>(Set.of(1,2,4), Set.of('J','A','V','E'), Set.of(new Dvojica(1,'J'), new Dvojica(4,'E'), new Dvojica(4,'A')));

        var obr2_L = new Relacia<Integer,Character>(Set.of(1,2,4,7), Set.of('J','A','V','E'), Set.of(new Dvojica(1,'J'), new Dvojica(2,'E'), new Dvojica(4,'A')));
        var obr2_R = new Relacia<Integer,Character>(Set.of(1,2,4,7), Set.of('J','A','V','E'), Set.of(new Dvojica(1,'J'), new Dvojica(2,'A'), new Dvojica(4,'A')));

        var obr3_L = new Relacia<Integer,Character>(Set.of(1,2,4,7), Set.of('J','A','V','E'), Set.of(new Dvojica(1,'J'), new Dvojica(2,'E'), new Dvojica(4,'A'), new Dvojica(7,'V')));
        var obr3_R = new Relacia<Integer,Character>(Set.of(1,2,4,7), Set.of('J','A','V','E'), Set.of(new Dvojica(1,'J'), new Dvojica(2,'A'), new Dvojica(4,'E'), new Dvojica(7,'E')));

        var obr4_L = new Relacia<Integer,Character>(Set.of(1,2,4,7), Set.of('J','A','V','E'), Set.of(new Dvojica(1,'J'), new Dvojica(2,'V'), new Dvojica(4,'A'), new Dvojica(7,'E')));
        var obr4_R = new Relacia<Integer,Character>(Set.of(1,2,4,7), Set.of('J','A','V','E'), Set.of(new Dvojica(1,'J'), new Dvojica(2,'A'), new Dvojica(4,'E'), new Dvojica(7,'A')));

        var obr5 = new Relacia<Integer,Character>(Set.of(1,2,4,7), Set.of('J','A','V','E'), Set.of(new Dvojica(1,'J'), new Dvojica(2,'E'), new Dvojica(4,'A')));

        var tests = List.of(obr1_L, obr1_R, obr2_L, obr2_R, obr3_L, obr3_R, obr4_L, obr4_R, obr5);
        tests.forEach(r -> {
                    System.out.println(r);
                    System.out.println("zobrazenie: " + r.jeZobrazenie() + ", " +
                    "injektivne: " + r.jeInjektivne() + ", " +
                    "surjektivne: " + r.jeSurjektivne() + ", " +
                    "bijektivne: " + r.jeBijektivne());
                }
                );
        System.out.println(obr5.inverzne());
    }
}
