package chapter05;

/**
 * Deadlock example. Thread 1 will lock resource 1 while thread 2 locks resource 2.
 * Then, thread 1 needs to lock resource 2 and thread 2 resource 1, but both resources are
 * locked, so both threads wait forever. This is a so called deadlock.
 */
public class DeadlockExample {
    public static String resource1 = "resource1";
    public static String resource2 = "resource2";
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized(resource1) {
                System.out.println("Thread " + Thread.currentThread().getId() + " locked resource 1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(resource2) {
                    System.out.println("Thread " + Thread.currentThread().getId() + " locked resource 2");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized(resource2) {
                System.out.println("Thread " + Thread.currentThread().getId() + " locked resource 2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(resource1) {
                    System.out.println("Thread " + Thread.currentThread().getId() + " locked resource 1");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
