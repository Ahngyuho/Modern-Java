package lambda;

import java.util.ArrayList;
import java.util.List;

public class FilteringApples {


    public static void main(String[] args) {
        List<Apple> inventory = List.of(new Apple(80, Color.GREEN), new Apple(90, Color.RED), new Apple(120, Color.GREEN));

        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        System.out.println(greenApples);

        List<Apple> greenAndHeavyApples = filterApplesByColorAndWeight(inventory, Color.GREEN, 90);
        System.out.println(greenAndHeavyApples);

        ApplePredicate appleColorPredicate = new AppleColorPredicate();
        List<Apple> greenApples2 = filter(inventory, appleColorPredicate);
        System.out.println(greenApples2);

        ApplePredicate appleWeightPredicate = new AppleWeightPredicate();
        List<Apple> heavyApples = filter(inventory, appleWeightPredicate);
        System.out.println(heavyApples);

        ApplePredicate appleColorAndWeight = new AppleWeightAndColorPredicate();
        List<Apple> greenAndHeavyApples2 = filter(inventory, appleColorAndWeight);
        System.out.println(greenAndHeavyApples2);

        List<Apple> redApples = filter(inventory, new ApplePredicate() {
            public boolean test(Apple apple) {
                return Color.RED == apple.getColor();
            }
        });

        List<Apple> redAndHeavyApples = filter(inventory, apple -> apple.getWeight() > 80 && apple.getColor() == Color.RED);

    }



    private static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(apple.getColor() == color) {
                result.add(apple);
            }
        }

        return result;
    }

    private static List<Apple> filterApplesByColorAndWeight(List<Apple> inventory, Color color, int weight) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if (apple.getColor() == color && apple.getWeight() > weight) {
                result.add(apple);
            }
        }

        return result;
    }


    private static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for(Apple apple : inventory){
            if (p.test(apple)) result.add(apple);
        }

        return result;
    }




    public interface ApplePredicate{
        boolean test(Apple apple);
    }

    enum Color{
        GREEN, RED
    }

    public static class Apple{
        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color){
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }
    }

    static class AppleWeightPredicate implements ApplePredicate{
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 90;
        }
    }

    static class AppleColorPredicate implements ApplePredicate{
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN;
        }

    }

    static class AppleWeightAndColorPredicate implements ApplePredicate{
        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN && apple.getWeight() > 90;
        }
    }
}
