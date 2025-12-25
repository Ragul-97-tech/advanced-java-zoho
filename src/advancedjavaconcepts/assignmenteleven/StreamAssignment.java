package advancedjavaconcepts.assignmenteleven;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAssignment {
    public static void main(String[] args) {
        System.out.println("---- 1. The Printer ----");
        List<String> list = new ArrayList<>();
        list.add("John");
        list.add("Jack");
        list.add("Jane");
        list.forEach(ele -> System.out.print(ele + (list.get(list.size() - 1) == ele ? "\n" : ", ")));

        System.out.println("---- 2. The Map Looper ----");

        Map<String, Integer> map = new HashMap<>();
        map.put("Apples", 10);
        map.put("Bananas", 20);
        map.put("Oranges", 5);
        map.forEach((key,value) -> System.out.println(String.format("We have %d %s", value, key)));

        System.out.println("---- 3. The Filter ----");

        List<Integer> integerList = new ArrayList<>();
        integerList.add(10);
        integerList.add(55);
        integerList.add(23);
        integerList.add(8);
        integerList.add(90);

        System.out.println(integerList.stream().filter(ele -> ele > 20).collect(Collectors.toList()));

        System.out.println("---- 4. The Generator ----");

        System.out.println(Arrays.toString(Stream.generate(() -> {
            return (int) (Math.random() * 100);
        }).limit(5).toArray()));

        System.out.println("---- 5. The Transformer ----");

        list.clear();
        list.add("java");
        list.add("is");
        list.add("cool");

        System.out.println(list.stream().map(ele -> ele.toUpperCase()).collect(Collectors.toList()));

        System.out.println("---- 6. The Salary Adjuster ----");

        map.clear();
        map.put("Alice",50000);
        map.put("Bob",60000);

        map.replaceAll((key, value) -> value+=value/10);
        System.out.println(map);
    }
}
