package advancedjavaconcepts.assignmentnine;

import advancedjavaconcepts.ColorCode;

class Student {
    String studentName;
    int booksRequired;

    Student(String studentName, int booksRequired) {
        this.booksRequired = booksRequired;
        this.studentName = studentName;
    }
}

class LibraryDesk extends Thread {
    LibraryAllocation library;
    Student student;

    LibraryDesk(LibraryAllocation library, Student student) {
        this.library = library;
        this.student = student;
    }

    @Override
    public void run() {
        synchronized (library) {
            if (library.numberOfBooks >= student.booksRequired) {
                library.numberOfBooks -= student.booksRequired;
                System.out.println("I am " + this.getName() + " " + student.studentName + " requries " + student.booksRequired + " book" + (student.booksRequired > 1 ? "s" : "") + ". Now Books count is " + library.numberOfBooks);
            }
            else
                System.out.println("I am " + this.getName() + " " + student.studentName + " requries " + student.booksRequired + " book" + (student.booksRequired > 1 ? "s" : "") + ". Now Books count is " + library.numberOfBooks + ". Sorry! See you next time.");
        }
    }
}

public class LibraryAllocation {
    int numberOfBooks = 20;

    public static void main(String[] args) throws InterruptedException {
        LibraryAllocation libraryAllocation = new LibraryAllocation();
        LibraryDesk libraryDesk = null;
        Student student = null;
        System.out.println(ColorCode.boxDouble("   Welcome to Library Books Sale   "));
        final int[] counter = {5};
        Thread thread = new Thread(() -> {
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.print("\rSale Starts in "+ --counter[0]);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.join();
        System.out.println();

        for (int i = 0; i < 10; i++) {
            student = new Student(""+((char)(i+65)),(int) (Math.random() * 3) + 1);
            libraryDesk = new LibraryDesk(libraryAllocation, student);
            libraryDesk.setName(String.format("%s-%03d",""+(char)(i+65),i));
            libraryDesk.start();
        }
    }
}