package advancedjavaconcepts.assignmenttwo;

import java.util.Arrays;

class Student implements Cloneable {
    String studentId;
    String studentName;
    Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\nStudent Name: " + studentName;
    }
}

public class LearnAboutArrays {
    public static void main(String[] args) {
        int[] numbers = {34, 78, 23, 56, 90, 12};
        int[] numbers2 = {12, 23, 34, 56, 78, 90};
//        1. Sort an array and print the smallest & largest element
        System.out.println("1. Sort an array and print the smallest & largest element");
        Arrays.sort(numbers);
        System.out.println("Smallest Value: " + numbers[0] + "\nLargest Values: " + numbers[numbers.length - 1]);

//        2. Check if two arrays are equal
        System.out.println("2. Check if two arrays are equal");
        System.out.println(Arrays.equals(numbers, numbers2));

//        3. Copy an array into a new array of bigger size
        System.out.println("3. Copy an array into a new array of bigger size");
        System.out.println(Arrays.toString(Arrays.copyOf(numbers, 10)));

//        4. Fill an entire array with a given number
        System.out.println("4. Fill an entire array with a given number");
        Arrays.fill(numbers2,5);
        System.out.println(Arrays.toString(numbers2));

//        5. Fill half of an array with one value and the other half with a different value
        System.out.println("5. Fill half of an array with one value and the other half with a different value");
        Arrays.fill(numbers2, 0, numbers2.length/2, 3);
        System.out.println(Arrays.toString(numbers2));

//        6. Use binary search to find an element in an array.
        System.out.println("6. Use binary search to find an element in an array.");
        System.out.println(Arrays.binarySearch(numbers, 23));

//        7. Convert an array to a string
        System.out.println("7. Convert an array to a string");
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(numbers2));

//        8. Copy a specific portion of an array and print it
        System.out.println("8. Copy a specific portion of an array and print it");
        System.out.println(Arrays.toString(Arrays.copyOfRange(numbers, 0, numbers.length/2)));

//        9. Check if an array is sorted
        System.out.println("9. Check if an array is sorted");
        int[] cloned = numbers2.clone();
        Arrays.sort(cloned);
        System.out.println(Arrays.equals(numbers2, cloned));

//        10. Convert a 2D array to string
        System.out.println("10. Convert a 2D array to string");
        int[][] numberCollections = {{19, 67, 23, 56}, {23, 56, 42}, {12, 56}, {23}};
        System.out.println(Arrays.deepToString(numberCollections));

//        11. Check if two arrays are identical using hashCode
        System.out.println("11. Check if two arrays are identical using hashCode");
        System.out.println(Arrays.hashCode(numbers) == Arrays.hashCode(numbers2));

//        12. Verify if the first half and second half of an array contain the same data using hashCode method
        System.out.println("12. Verify if the first half and second half of an array contain the same data using hashCode method");
        int[] firstHalf = Arrays.copyOfRange(numbers, 0, numbers.length/2);
        int[] lastHalf = Arrays.copyOfRange(numbers, numbers.length/2, numbers.length);
        System.out.println(Arrays.hashCode(firstHalf) == Arrays.hashCode(lastHalf));

//        13. Clone Student Object and print the values inside it
        System.out.println("13. Clone Student Object and print the values inside it");
        Student s1 = new Student("zsttk-421", "Ragul");
        try {
            Student s2 = (Student) s1.clone();
            System.out.println("Original Object:\n" + s1 + "\n");
            System.out.println("Cloned Object:\n" + s2);
            System.out.println(s1.hashCode() + " " + s2.hashCode());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
}