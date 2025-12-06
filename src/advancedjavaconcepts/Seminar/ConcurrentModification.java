package advancedjavaconcepts.Seminar;

/*
*   Concurrent Modification Exception
*   What is Concurrent Modification Exception (fails-fast)?
*   Any one face it?
*   relation between modCount and expectedModCount
*
*   cursor -> points to next Element (default = 0)
*   lastRet -> having the value of next() returns (default = -1)
*
*   * * * * not modifies EMC * * * * *
*   ArrayList methods -> add(), remove()
*
*   * * * * modifies EMC * * * * *
*   Iterator -> remove() (Forward Traversal)
*
*   For Each vs Iterator
*
*   * * * * Modifies EMC (ListItr) * * * * *
*   Iterator -> ListIterator
*   ListIterator -> add(), remove() (Both Forward and Reverse Traversal)
*
* */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ConcurrentModification {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(10);
        numbers.add(18);
//
//        Iterator<Integer> itr = numbers.iterator();
//        numbers.add(20);
//        System.out.println(itr.next());
//        System.out.println(itr.next());
//        itr.remove();
//        System.out.println(numbers);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(23);
        list.add(26);
        list.add(68);
//        for (Integer n : list) {
//            list.remove(2);
//        }

//        * * * Normal Indexing * * *
//        for (int i = 0; i < 5; i++) {
//            list.add(12+i);
//            list.remove(0);
//        }
//        System.out.println(list);

//         * * *  Infinite Loops * * *
        ArrayList<Integer> arr2 = new ArrayList<>();
//        arr2.add(1);
//        for (int i = 0; i < arr2.size(); i++) {
//            arr2.add(i);
//            System.out.println(arr2.get(i));
//        }
//        System.out.println(list);


        Iterator<Integer> itr = list.iterator();
        while (itr.hasNext()) {
            if (itr.next()==26)
                itr.remove();
        }
        System.out.println(list);

//        ArrayList<Integer> arr = new ArrayList<>();
//        arr.add(12);
//        arr.add(13);
//        arr.add(135);
//        for (int i: arr) {
//            arr.remove(1);
//        }

        arr2.add(1);
        ListIterator<Integer> listItr = arr2.listIterator();
        System.out.println(arr2);
        System.out.println(listItr.hasNext());
        while (listItr.hasNext()) {
            listItr.next();
            listItr.add(6);
            listItr.remove();
        }
        System.out.println(arr2);
    }
}
