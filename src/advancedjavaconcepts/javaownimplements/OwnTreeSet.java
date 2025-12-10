package advancedjavaconcepts.javaownimplements;

public class OwnTreeSet {
    public static void main(String[] args) {
        Tree<Integer> rbt = new RedBlackTree<>();
        System.out.println(rbt.isEmpty());
        Node<Integer> node = new Node<>(23);
        node.getColor();
//        rbt.insert(50);
//        System.out.println(rbt.insert(30));
//        rbt.insert(80);
//        rbt.insert(21);
//        rbt.insert(20);
//        rbt.insert(90);
//        rbt.insert(43);
//        40, 20, 60, 35, 10, 30, 50, 70, 25, 65, 15
        rbt.insert(40);
        rbt.insert(20);
        rbt.insert(60);
        rbt.insert(35);
        rbt.insert(10);
        rbt.insert(30);
        rbt.insert(50);
        rbt.insert(70);
        rbt.insert(25);
//        rbt.insert(65);
//        rbt.insert(15);
//
//        System.out.println(rbt.isEmpty());
//        System.out.println();
//        rbt.traverse();
//        System.out.println("Min: " + rbt.getMin());
//        System.out.println("Max: " + rbt.getMax());
//        System.out.println("Size: " + rbt.size());
    }
}
