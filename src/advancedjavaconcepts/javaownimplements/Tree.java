package advancedjavaconcepts.javaownimplements;

public interface Tree <T extends Comparable<T>> {
    Tree<T> insert(T data);

    int size();

    T getMax();

    T getMin();

    boolean isEmpty();

    void traverse();
}
