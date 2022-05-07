package chapter01;

/**
 * We can alternatively create custom threads by extending the thread class and
 * overriding the run method.
 */
public class CustomThread extends Thread {
    @Override
    public void run() {
        System.out.println("I am a custom thread");
    }
}
