package chapter01;

public class SleepingRunnable implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Current thread id: " + Thread.currentThread().getId() + " i: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // this will set the interrupt flag to true, make sure to include it
                // in catch clauses like this one
                Thread.currentThread().interrupt();
                e.printStackTrace();
                // stop execution after the thread got interrupted
                break;
            }
        }
    }
}
