package advancedjavaconcepts.assignmentten;

import advancedjavaconcepts.ColorCode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RestaurantOrder {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            executorService.submit(order);
        }
        executorService.shutdown();
    }
}

class Order implements Runnable {
    String orderId;
    static int id = 1;

    Order() {
        this.orderId = String.format("Order%03d",id++);
    }

    @Override
    public void run() {
        System.out.println(String.format("Chef [%s] preparing Order [%s]",Thread.currentThread().getName(),orderId));
        Thread cooking = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                System.out.print(ColorCode.colored("red","\rCooking " + "\uD83D\uDD25".repeat(i%4)));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(ColorCode.right("Order [" + orderId + "] Ready!"));
        });
        cooking.start();
        try {
            cooking.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}