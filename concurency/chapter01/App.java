package chapter01;

public class App {
    public static void main(String[] args) {
        theJoinMethodWithTime();
    }
    public static void theJoinMethodWithTime() {
        Thread t1 = new Thread(new SleepingRunnable());
        Thread t2 = new Thread(new SleepingRunnable());
        t1.start();
        try {
            t1.join(2000); // run alone for 2 seconds
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
    /**
     * What if we want the first thread first to finish and then the other to start?
     * For this reason we use the join method.
     */
    public static void theJoinMethod() {
        Thread t1 = new Thread(new SleepingRunnable());
        Thread t2 = new Thread(new SleepingRunnable());
        t1.start();
        try {
            t1.join(); // t1 will start and finish before t2
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
    /**
     * This example demonstrates nicely how those two threads run in parallel.
     */
    public static void runningTwoThreadsInParallel() {
        Thread t1 = new Thread(new SleepingRunnable());
        Thread t2 = new Thread(new SleepingRunnable());
        t1.start();
        t2.start();
    }
    /**
     * A sleeping thread can also be interrupted. If it is not sleeping
     * it will just set the interrupted flag to true. If it is sleeping it will
     * also throw an InterruptedException.
     */
    public static void interruptingAThread() {
        Thread sleepingThread = new Thread(new SleepingRunnable());
        sleepingThread.start();
        sleepingThread.interrupt();
    }
    /**
     * Threads can also sleep. This is generally not a good practice because a sleeping thread
     * can keep your programm running.
     */
    public static void sleepingThread() {
        Thread sleepingThread = new Thread(new SleepingRunnable());
        sleepingThread.start();
    }
    public static void initializingWithACustomRunnable() {
        Thread t = new Thread(new CustomRunnable());
        t.start();
    }
    /**
     * We can extend the Thread class to create custom threads.
     */
    public static void createCustomThreads() {
        CustomThread ct = new CustomThread();
        ct.start();
    }
    /**
     * In order to view how threads run in parallel we print out two thread ids. We will notice
     * they have differant ids, the main thread will have id equals 1 and the other the next free thread id.
     * The first threads are always used by some java processes like the garbage collector.
     * Notice that the thread and main thread may print on different order each time.
     */
    public static void threadsInParallel() {
        Thread t = new Thread(() -> System.out.println("Thread id: " + Thread.currentThread().getId()));
        t.start();
        System.out.println("Main thread id: " + Thread.currentThread().getId());
    }
    /**
     * A thread is initialized with a runnable in order to do something,
     * else it does nothing.
     */
    public static void initializeThreadWithRunnable() {
        Thread t = new Thread(() -> System.out.println("Hello"));
        t.start();
    }
    public static void threadThatDoesNothing() {
        Thread t = new Thread();
        t.start();
    }
}
