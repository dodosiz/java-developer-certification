package chapter01;

public class RiddleThread extends Thread {
    public void run() {
        System.out.println("Running the run method.");
        System.out.println("Current thread id: " + Thread.currentThread().getId());
    }
    public static void main(String[] args) {
        RiddleThread rt = new RiddleThread();
        rt.run(); // what you think this will print out?
        System.out.println("Current thread id in main: " + Thread.currentThread().getId());
    }
}
