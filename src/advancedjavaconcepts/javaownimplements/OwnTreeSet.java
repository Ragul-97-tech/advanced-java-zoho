package advancedjavaconcepts.javaownimplements;

public class OwnTreeSet {
    public static void main(String[] args) {
        Tree<Integer> rbt = new RedBlackTree<>();
        System.out.println(rbt.isEmpty());
        rbt.insert(50);
//        System.out.println(rbt.insert(30));
        rbt.insert(80);
//        rbt.insert(21);
//        rbt.insert(20);
//        rbt.insert(90);
//        rbt.insert(43);

//        System.out.println(rbt.isEmpty());
//
//        rbt.traverse();
//        System.out.println("Min: " + rbt.getMin());
//        System.out.println("Max: " + rbt.getMax());
//        System.out.println("Size: " + rbt.size());
    }
}
