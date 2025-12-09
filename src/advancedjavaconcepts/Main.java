package advancedjavaconcepts;

import java.util.*;

class A {
    String text;
    void testing() {
        System.out.println("outer class");
    }
    private class B {
        String test() {
            return "Private inner";
        }
    }
    B b = new B();

    {
        text = b.test();
    }
}

public class Main {
    public static void main(String[] args) {
//        A a = new A();
//        System.out.println(a.text);
//        System.out.println(a);
//
//        ArrayList<Integer> numbers = new ArrayList<>();
//        numbers.add(1);
//        numbers.add(10);
//        numbers.add(18);
//
//        Iterator<Integer> itr = numbers.iterator();
//        numbers.add(20);
//        System.out.println(itr.next());
//        System.out.println(itr.next());
//        itr.remove();
//        System.out.println(numbers);

//        System.out.println(Integer.parseInt("255",16));

//        ArrayList<Integer> arr = new ArrayList<>();
//        arr.add(12);
//        arr.add(13);
//        arr.add(135);
//        for (int i: arr) {
//            arr.remove(1);
//        }
//        ArrayList<Integer> arr2 = new ArrayList<>();
//        arr2.add(1);
//        for (int i = 0; i < arr2.size(); i++) {
//            arr2.add(i);
//            System.out.println(arr2.get(i));
//        }
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(12);
        ts.add(78);
        ts.add(17);
        ts.add(1);
        ts.add(89);
        ts.add(34);
        System.out.println(ts.remove(34));
        System.out.println(ts.contains(34));
        System.out.println(ts.first());
        System.out.println(ts.last());
        System.out.println(ts.higher(89));
        System.out.println(ts.lower(7));
        System.out.println(ts.pollFirst());
        System.out.println(ts.pollLast());
//        System.out.println(ts.ceiling(17));
        System.out.println(Arrays.toString(ts.toArray()));
//        Iterator<Integer> itr = ts.iterator();

        System.out.println(ts.toString());


    }
}
