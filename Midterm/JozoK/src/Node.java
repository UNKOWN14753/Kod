import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public record Node<E>(Node<E> left, E value, Node<E> right) {

    static HashMap<Integer, Integer> levels;

    public Integer najviacVrcholov(){
        levels = new HashMap<>();
        levelsRek(0);
        return levels.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getValue();
    }

    public Integer maximalnaUroven(){
        levels = new HashMap<>();
        levelsRek(0);
        return levels.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }

    public void levelsRek(int level){
        levels.put(level, levels.getOrDefault(level, 0) + 1);
        if (left == null && right == null){
            return;
        }
        if (this.left != null){
            this.left.levelsRek(level + 1);
        }
        if (this.right != null){
            this.right.levelsRek(level + 1);
        }
    }

    public <T> Node<T> map(Function<E,T> f){
        return new Node<> ((left == null) ? null : left.map(f), f.apply(value), (right == null) ? null : right.map(f));
    }

    public Node<E> filter(Predicate<E> p){
        return (p.test(value)) ? new Node<> ((left == null) ? null : left.filter(p), value, (right == null) ? null : right.filter(p)) : null;
    }


    public static void main(String[] args) {
        Node<Integer> t = new Node<>(
                new Node<>(new Node<>(null, 2, null),4,new Node<>(null, 9, new Node<>(null, 10, null))),
                13,
                new Node<>(new Node<>(null, 22, null),27,null));
        System.out.println(t.najviacVrcholov());
        System.out.println(t.maximalnaUroven());
        System.out.println(t.map("*"::repeat));
        System.out.println(t.filter(x -> x % 2 > 0));
    }
}