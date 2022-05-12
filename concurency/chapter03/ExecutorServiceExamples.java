package chapter03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorServiceExamples {
    // provides only one thread
    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    // provides a fixed amount of threads, play with the number of threads to see how concurrent the tasks are beeing run
    private static ExecutorService fixedThreadExecutor = Executors.newFixedThreadPool(3);
    // creates new threads depending on the number of incoming tasks
    private static ExecutorService cachedThreadExecutor = Executors.newCachedThreadPool();
    private static ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(50);
    public static void main(String[] args) {
        scheduleWithFixedDelay();
    }
    /**
     * We can use executor services to run tasks on threads. The proccess will always consist of 3 steps:
     * - initialize the executor service
     * - execute tasks
     * - shutdown the executor service
     */
    public static void executeWithSingleThreadExecutor() {
        // we use the execute method with a Runnable, notice that the thread id will always be the same (single thread)
        singleThreadExecutor.execute(() -> System.out.println("1: " + Math.random() + " id: " + Thread.currentThread().getId()));
        singleThreadExecutor.execute(() -> System.out.println("2: " + Math.random() + " id: " + Thread.currentThread().getId()));
        singleThreadExecutor.execute(() -> System.out.println("3: " + Math.random() + " id: " + Thread.currentThread().getId()));
        singleThreadExecutor.execute(() -> System.out.println("4: " + Math.random() + " id: " + Thread.currentThread().getId()));
        // always remember to shutdown the executor
        singleThreadExecutor.shutdown();
    }
    /**
     * There is another option with the single thread executor service to run a task,
     * the invokeAny method. It takes as argument a list of callables.
     * Because this is a single thread executor service it will always print 1, because
     * the tasks are executed in the order they come in and the 1 is the first task.
     */
    public static void executeWithInvokeAny() {
        List<Callable<Integer>> callables = new ArrayList<>();
        callables.add(() -> 1);
        callables.add(() -> 2);
        callables.add(() -> 3);
        callables.add(() -> 4);
        try {
            System.out.println(singleThreadExecutor.invokeAny(callables));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            singleThreadExecutor.shutdown();
        }
    }
    /**
     * The invokeAll is similar, except it won't throw an ExecutionException, because the other
     * tasks are not canceled and also it will return a future for every task.
     */
    public static void executeWithInvokeAll() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        List<Callable<Integer>> callables = new ArrayList<>();
        callables.add(() -> 1);
        callables.add(() -> 2);
        callables.add(() -> 3);
        callables.add(() -> 4);
        try {
            System.out.println(singleThreadExecutor.invokeAll(callables));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            singleThreadExecutor.shutdown();
        }
    }
    /**
     * The method submit of the executor service takes a callable and returns a future.
     * We can use the methods isDone and isCancelled to check the future and finally use
     * the get method to get it.
     */
    public static void submittingTaks() {
        Future<Double> future = getRandom(1, singleThreadExecutor);
        // we can see in the output that this tasks will be executed in the order they come (single thread)
        getRandom(2, singleThreadExecutor);
        getRandom(3, singleThreadExecutor);
        getRandom(4, singleThreadExecutor);
        // bad way because it reserves our thread the whole time but ok as an example
        while (!future.isDone()) {
            if (future.isCancelled()) {
                System.out.println("Future has been cancelled");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        singleThreadExecutor.shutdown();
    }
    /**
     * We will use a thread pool of fixed threads and see in the output
     * which thread gets everytime the task.
     */
    public static void runningWithFixedThreads() {
        for (int i = 0; i < 100; i++) {
            getRandom(i, fixedThreadExecutor);
        }
        fixedThreadExecutor.shutdown();
    }
    /**
     * The cached executor will create how many tasks it needs.
     */
    public static void runningWithCachedThreads() {
        for (int i = 0; i < 100; i++) {
            getRandom(i, cachedThreadExecutor);
        }
        cachedThreadExecutor.shutdown();
    }
    public static Future<Double> getRandom(int i, ExecutorService executorService) {
        return executorService.submit(() -> {
            Thread.sleep((int) (Math.random() * 200));
            System.out.println(i + " id: " + Thread.currentThread().getId());
            return Math.random();
        });
    }
    /**
     * Example of using a scheduled executor and then getting the future result.
     */
    public static void scheduleATask() {
        Future<Double> doubleFuture = scheduledExecutor.schedule(() -> {
            Thread.sleep((int) (Math.random() * 200));
            System.out.println("id: " + Thread.currentThread().getId());
            return Math.random();
        }, 1000, TimeUnit.MILLISECONDS);
        try {
            // will wait until the future is there
            System.out.println(doubleFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        scheduledExecutor.shutdown();
    }
    /**
     * We can also get the future in a specified time and if it is not there
     * it will throw a TimeoutException.
     */
    public static void timeoutExeptionWithFuture() {
        Future<Double> doubleFuture = scheduledExecutor.schedule(() -> {
            Thread.sleep((int) (Math.random() * 200));
            System.out.println("id: " + Thread.currentThread().getId());
            return Math.random();
        }, 1000, TimeUnit.MILLISECONDS);
        try {
            System.out.println(doubleFuture.get(100, TimeUnit.MILLISECONDS));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Timeout occured");
            doubleFuture.cancel(false);
        }
        if (doubleFuture.isCancelled()) {
            System.out.println("Future got canceled.");
        }
        // a canceled future is technically done
        if (doubleFuture.isDone()) {
            System.out.println("Future is done.");
        }
        scheduledExecutor.shutdown();
    }
    /**
     * We can also schedule tasks with a fixed delay, they will be executed at a fixed delay every time.
     */
    public static void scheduleWithFixedDelay() {
        scheduledExecutor.scheduleWithFixedDelay(
            () -> System.out.println("id: " + Thread.currentThread().getId()), 1000, 500, TimeUnit.MILLISECONDS);
        try {
            scheduledExecutor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
