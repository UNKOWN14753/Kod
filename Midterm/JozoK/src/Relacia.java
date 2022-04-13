import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record Relacia<A,B>(Set<A> defObor, Set<B> oborHodnot, Set<Dvojica<A,B>> dvojice) {

    public boolean jeZobrazenie(){
        HashSet<A> done = new HashSet<>();
        for (Dvojica<A,B> dvoj: dvojice){
            if (done.contains(dvoj.a())) return false;
            done.add(dvoj.a());
        }
        return true;
    }

    public Map<A,B> zobrazenie(){
        if (jeZobrazenie())
            return dvojice.stream().collect(Collectors.toMap(Dvojica::a, Dvojica::b));
        else return null;
    }

    public boolean jeInjektivne(){
        HashSet<B> done = new HashSet<>();
        for (Dvojica<A,B> dvoj: dvojice){
            if (done.contains(dvoj.b())) return false;
            done.add(dvoj.b());
        }
        return true;
    }

    public boolean jeSurjektivne(){
        Set<B> oborHodnotCopy = new HashSet<>(oborHodnot);
        for (Dvojica<A,B> dvoj: dvojice){
            oborHodnotCopy.remove(dvoj.b());
        }
        return oborHodnotCopy.size() == 0;
    }

    public boolean jeBijektivne(){
        return jeSurjektivne() && jeInjektivne();
    }

    public Relacia<B,A> inverzne(){
        if (jeInjektivne()) {
            return new Relacia<>(oborHodnot(), defObor(), dvojice.stream().map(val -> new Dvojica<>(val.b(), val.a())).collect(Collectors.toSet()));
        } else return null;
    }
}