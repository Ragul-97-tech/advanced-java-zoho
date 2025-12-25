package advancedjavaconcepts.advancedjavalearnings;

import java.util.*;

public class SpliteratorLearning {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add(""+(char)(65+i));
        }
        System.out.println("Initial List Array: " + list);
        Spliterator<String> secondHalf = list.spliterator();
        Spliterator<String> firstHalf;
        for (int i = 0; i < 3; i++) {
            System.out.println("Second Half Separation: ");
            firstHalf = secondHalf.trySplit();
            firstHalf.forEachRemaining(ele -> {
                System.out.print(ele + " ");
            });
            System.out.println("\n* * * * * * * * * *");
            firstHalf = secondHalf.trySplit();
            System.out.println("First Half Separation: ");
            firstHalf.forEachRemaining(ele -> {
                System.out.print(ele + " ");
            });
            System.out.println("\n* * * * * * * * * *");
        }
    }
}
