package advancedjavaconcepts.javaownimplements;

public interface Tree <T extends Comparable<T>> extends iterator<T> {
    boolean insert(T data);

    boolean remove(T data);

    int size();

    T getMax();

    T getMin();

    boolean isEmpty();

    boolean contains(T node);

    void traverse();
}
