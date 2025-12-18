package advancedjavaconcepts.advancedjavalearnings;

public class ThreadLearn implements Runnable {
    public static void main(String[] args) {
        Runnable r1 = new ThreadLearn();
        r1.run();
        System.out.println("Hi from " + r1.getClass().getName());
    }

    @Override
    public void run() {
        System.out.println("Hello from Thread!");
    }
}