package advancedjavaconcepts.javaownimplements;

public class OwnTreeSet {
    public static void main(String[] args) {
        Tree<Integer> rbt = new RedBlackTree<>();
        System.out.println(rbt.isEmpty());
//        rbt.insert(30);
//        rbt.insert(30);
//        System.out.println(rbt.insert(30));
//        rbt.insert(80);
//        rbt.insert(21);
//        rbt.insert(20);
//        rbt.insert(90);
//        rbt.insert(43);
//        40, 20, 60, 35, 10, 30, 50, 70, 25, 65, 15
//        rbt.insert(40);
//        rbt.insert(20);
//        rbt.insert(60);
//        rbt.insert(35);
//        rbt.insert(10);
//        rbt.insert(30);
//        rbt.insert(50);
//        rbt.insert(70);
//        rbt.insert(25);
//        rbt.insert(65);
//        rbt.insert(15);
//
//        System.out.println(rbt.isEmpty());
//        System.out.println();
//        rbt.traverse();
//        System.out.println("Min: " + rbt.getMin());
//        System.out.println("Max: " + rbt.getMax());
//        System.out.println("Size: " + rbt.size());

//        rbt.remove(30);
//        rbt.remove(400);
//        rbt.remove(40);
//        rbt.remove(70);
//        rbt.remove(50);
//        System.out.println();
//        rbt.traverse();

//        rbt.insert(40);
//        rbt.insert(20);
//        rbt.insert(60);
//        rbt.insert(10);
//        rbt.insert(30);
//        rbt.insert(50);
//        rbt.insert(70);
//
//        rbt.remove(30);
//        rbt.remove(50);

//        rbt.insert(30);
//        rbt.insert(15);
//        rbt.insert(40);
//        rbt.insert(10);
//        rbt.insert(18);
//        rbt.insert(35);
//        rbt.insert(45);
//        rbt.insert(17);
//
//        rbt.remove(10);
//        rbt.remove(40);

//        rbt.insert(50);
//        rbt.insert(25);
//        rbt.insert(75);
//        rbt.insert(12);
//        rbt.insert(37);
//        rbt.insert(62);
//        rbt.insert(87);
//        rbt.insert(6);
//        rbt.insert(18);
//
//        rbt.remove(6);
//        rbt.remove(75);

//        rbt.insert(10);
//        rbt.insert(20);
//        rbt.insert(30);
//        rbt.insert(15);
//        rbt.insert(25);
//        rbt.insert(5);
//        rbt.insert(1);
//
//        rbt.remove(25);
//        rbt.remove(1);

//        rbt.insert(100);
//        rbt.insert(50);
//        rbt.insert(150);
//        rbt.insert(25);
//        rbt.insert(75);
//        rbt.insert(125);
//        rbt.insert(175);
//        rbt.insert(60);
//        rbt.insert(80);
//
//        rbt.remove(75);
//        rbt.remove(25);
//        rbt.remove(150);

        rbt.insert(20);
        rbt.insert(10);
        rbt.insert(30);
        rbt.insert(5);
        rbt.insert(15);
        rbt.insert(25);
        rbt.insert(35);
        rbt.insert(27);
        rbt.insert(33);

        rbt.remove(5);
        rbt.remove(27);

        rbt.traverse();
    }
}
