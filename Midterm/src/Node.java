import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record Node<E>(Node<E> left, E value, Node<E> right) {

    public Integer najviacVrcholov() {
        var res = new ArrayList<Integer>();
        levels(0, res);
        var y = res.stream().collect(Collectors.groupingBy(x->x, Collectors.counting()));
        return (y.values().stream().max(Comparator.naturalOrder()).orElse(0L)).intValue();
    }
    public Integer maximalnaUroven() {
        var res = new ArrayList<Integer>();
        levels(0, res);
        var y = res.stream().collect(Collectors.groupingBy(x->x, Collectors.counting()));
        var m = (y.values().stream().max(Comparator.naturalOrder()).orElse(0L)).intValue();
        for(Integer k : y.keySet())
            if (y.get(k) == m) return k;
        return 0;
    }

    private void levels(int d, ArrayList<Integer> res) {
        if (left != null) left.levels(d+1, res);
        if (right != null) right.levels(d+1, res);
        res.add(d);
    }

    public <T> Node<T> map(Function<E,T> f) {
        return new Node<>((left != null)?left.map(f):null,
                          f.apply(value),
                          (right != null)?right.map(f):null
                         );
    }
    public Node<E> filter(Predicate<E> p) {
        return (p.test(value))?
                new Node<>((left != null)?left.filter(p):null,
                            value,
                            (right != null)?right.filter(p):null):
                null;
    }


    public static void main(String[] args) {
        Node<Integer> t = new Node<>(
                new Node<>(new Node<>(null, 2, null),4,new Node<>(null, 9, new Node<>(null, 10, null))),
                13,
                new Node<>(new Node<>(null, 22, null),27,null));
        var res = new ArrayList<Integer>();
        t.levels(0, res);
        System.out.println(res);
        System.out.println(t);  // Node[left=Node[left=Node[left=null, value=2, right=null], value=4, right=Node[left=null, value=9, right=Node[left=null, value=10, right=null]]], value=13, right=Node[left=Node[left=null, value=22, right=null], value=27, right=null]]
        System.out.println(t.najviacVrcholov());// 3
        System.out.println(t.maximalnaUroven());// 2
        System.out.println(t.map("*"::repeat)); // Node[left=Node[left=Node[left=null, value=**, right=null], value=****, right=Node[left=null, value=*********, right=Node[left=null, value=**********, right=null]]], value=*************, right=Node[left=Node[left=null, value=**********************, right=null], value=***************************, right=null]]
        System.out.println(t.filter(x -> x % 2 > 0)); // Node[left=null, value=13, right=Node[left=null, value=27, right=null]]
        System.out.println(t.filter(x -> x % 2 == 0)); // null
    }
}
