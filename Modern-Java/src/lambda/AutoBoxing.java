package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class AutoBoxing {
    public static void main(String[] args) {
        Predicate<Integer> predicate = x -> x > 5;
        IntPredicate intPredicate = x -> x > 5;
        predicate.test(4);
        long startTime,endTime;
        startTime = System.currentTimeMillis();

        for(int i=0;i<1000000000;i++){
            predicate.test(i);
//            intPredicate.test(i);
        }

        endTime = System.currentTimeMillis();

//        System.out.println(endTime - startTime);

        IntStream.Builder intBuilder = IntStream.builder();
        startTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<1000000;i++){
            intBuilder.add(i);
//            list.add(i);
        }
        IntStream indexes = intBuilder.build();
//        System.out.println(indexes.sum());
        endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime + "ms");
        System.out.println(list.stream().reduce(0, Integer::sum));
        BiFunction<Integer,Integer,Integer> biFunction = (x,y) -> x + y;
        int sum = 0;
        for(int i=0;i<1000000;i++){
            sum = biFunction.apply(sum, i);
        }
        System.out.println(sum);


    }
}
