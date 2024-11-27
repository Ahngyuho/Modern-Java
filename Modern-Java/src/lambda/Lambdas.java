package lambda;

import java.util.Comparator;

public class Lambdas {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello");

        r.run();

        Comparator<Integer> comp = (FilteringApples.Apple a1, FilteringApples.Apple a2) -> a1.getWeight().compare(a2.getWeight());
    }

}
