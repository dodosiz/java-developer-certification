package chapter01;

import java.util.concurrent.Callable;

/**
 * Another way of executing tasks in threads is through a callable.
 * The thread does not get initialized with a callable in the constructor,
 * in oposition with runnables. The callable is called with an executor service.
 * Another difference is that a callable returns a value and is also designed to support
 * checked exceptions (introduced in Java 5).
 */
public class CustomCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("I am a custom callable");
        return 3;
    }
}
