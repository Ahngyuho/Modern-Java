package lambda;

import java.util.Comparator;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambdas {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("Hello");

        r.run();
        Supplier<String> s = () -> "test";

        System.out.println(s.get());

        IntPredicate intPredicate = (int x) -> x > 5;
//        IntPredicate intPredicate = x -> x > 5;



    }

}
