package chapter02;

public class App {
    private static int counter = 0;
    private static Object lock = new Object();
    public static void main(String[] args) {
        incrementValueInThreadsSynchronizedWithObject();
    }
    /**
     * Incrementing a value in threads can lead to unpredictable results.
     * In this case the time one thread reads the value, another thread increases it
     * and the value each thread reads is not the updated one, so the counter never
     * gets to 10. It would get to 10 if we comment out the before print statement,
     * as the calculation will come faster, but either way the result is unpredictable.
     */
    public static void incrementValueInThreads() {
        System.out.println("Without the synchronized keyword:");
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> incrementCounter());
            t.start();
        }
    }
    /**
     * The solution for that is the synchronized keyword, we can add this in methods
     * or blocks (with an object).
     */
    public static void incrementValueInThreadsSynchronized() {
        System.out.println("With the synchronized keyword:");
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> incrementCounterSync());
            t.start();
        }
    }
     /**
     * With can achieve the same effect by using a synchronized block with an object.
     * This can help in performance as we keep smaller parts of our code synchronized.
     * Somethimes you can see instead of an object the value this.
     */
    public static void incrementValueInThreadsSynchronizedWithObject() {
        System.out.println("With the synchronized object:");
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> incrementCounterSyncWithObject());
            t.start();
        }
    }
    public synchronized static void incrementCounterSyncWithObject() {
        synchronized (lock) { // if not static we could use this instead of lock
            int current = counter;
            // comment out the next line and run again
            System.out.println("Before: " + counter + " Current thread: " + Thread.currentThread().getId());
            counter = current + 1;
            System.out.println("After: " + counter + " Current thread: " + Thread.currentThread().getId());
        }
    }
    public synchronized static void incrementCounterSync() {
        int current = counter;
        // comment out the next line and run again
        System.out.println("Before: " + counter + " Current thread: " + Thread.currentThread().getId());
        counter = current + 1;
        System.out.println("After: " + counter + " Current thread: " + Thread.currentThread().getId());
    }
    public static void incrementCounter() {
        int current = counter;
        // comment out the next line and run again
        System.out.println("Before: " + counter + " Current thread: " + Thread.currentThread().getId());
        counter = current + 1;
        System.out.println("After: " + counter + " Current thread: " + Thread.currentThread().getId());
    }
}
