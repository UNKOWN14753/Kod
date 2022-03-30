package RiesenieJozoK;
import java.util.Arrays;

public class Queens implements Comparable<Queens>{
    int[] riesenie;     // !!! tento riadok NEMENTE, pouziva ho testovac

    @Override
    public String toString() {
        return "Queens{" +
                "riesenie=" + Arrays.toString(riesenie) +
                '}';
    }

    public Queens(int[] riesenie) {     // dodefinujte podla zadania
        this.riesenie = riesenie.clone();
    }
    public Queens verticalFlip() {    // dodefinujte podla zadania
        int[] newA = new int[this.riesenie.length];
        int counter = 0;
        for(int i = riesenie.length-1; i >= 0; i--){
            newA[counter] = this.riesenie[i];
            counter++;
        }
        return new Queens(newA);
    }
    public Queens horizontalFlip() {      // dodefinujte podla zadania
        int[] newA = new int[this.riesenie.length];
        for(int i = 0; i <this.riesenie.length; i++){
            newA[i] = this.riesenie.length-1-this.riesenie[i];
        }
        return new Queens(newA);
    }
    public Queens clockwise90() {       // dodefinujte podla zadania
        int[] newA = new int[this.riesenie.length];
        for(int i = 0; i <this.riesenie.length; i++){
            newA[this.riesenie.length-this.riesenie[i]-1] = i ;
        }
        return new Queens(newA.clone());
    }
    public static void main(String[] args) {
        Queens q = new Queens(new int[]{0,1,2,3,4});    // maly priklad, 5 dam na hlavnej uhlopriecke
        System.out.println(q);
        System.out.println(q.horizontalFlip());
        System.out.println(q.clockwise90());
    }

    @Override
    public int compareTo(Queens o) {
        for (int i = 0; i < riesenie.length; i++){
            if (this.riesenie[i] != o.riesenie[i]){
                return riesenie[i] < o.riesenie[i] ? -1 : 1;
            }
        }
        return 0;
    }
}
