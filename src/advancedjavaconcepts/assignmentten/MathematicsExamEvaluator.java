package advancedjavaconcepts.assignmentten;

import advancedjavaconcepts.ColorCode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MathematicsExamEvaluator {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exService = Executors.newFixedThreadPool(2);

        ExamEvaluator evaluator = null;
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Student s1 = new Student(""+(char)(65+(int)(Math.random() * 26)));
            evaluator = new ExamEvaluator(s1);
            list.add(exService.submit(evaluator));
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).get());
        }

        exService.shutdown();
    }
}

class ExamEvaluator implements Callable<String> {
    Student student;

    ExamEvaluator(Student student) {
        this.student = student;
    }

    @Override
    public String call() throws Exception {
        Thread loading = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.print(ColorCode.colored("green","\rEvaluating " + ".".repeat(i%3+1)));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println();
        });
        loading.start();
        loading.join();

        student.score = (int) (Math.random() * (100 - 1 + 1));
        String result = String.format("PASS | Student: [%s] | Score: [%03d] Graded by: [%s]", student.name, student.score, Thread.currentThread().getName());
        return switch (student.score / 10) {
            case 10,9 -> ColorCode.GREEN + result;
            case 8 -> ColorCode.BLUE + result;
            case 7 -> ColorCode.YELLOW + result;
            case 6 -> ColorCode.ORANGE + result;
            default -> ColorCode.RED + result.replace("PASS","FAIL");
        } + ColorCode.RESET;
    }
}

class Student {
    String name;
    int score;

    Student(String name) {
        this.name = name;
    }
}