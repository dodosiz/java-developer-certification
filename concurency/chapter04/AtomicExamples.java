package chapter04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExamples {
    // we can also have AtomicLong and AtomicBoolean
    private static AtomicInteger ai = new AtomicInteger(0);
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> System.out.println(ai.incrementAndGet()));
        }
        executorService.shutdown();
    }
}
