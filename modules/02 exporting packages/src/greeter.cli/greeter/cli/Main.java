package greeter.cli;

import greeter.hello.service.HelloMessageService;

public class Main {
    public static void main(String[] args) {
        HelloMessageService helloMessageService = new HelloMessageService();
        String message = helloMessageService.getMessage();
        System.out.println(message + " from a module!");
    }
}