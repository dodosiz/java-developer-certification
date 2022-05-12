package greeter.cli;

import java.util.ServiceLoader;

import greeter.api.MessageService;

public class Main {
    public static void main(String[] args) {
        Iterable<MessageService> services = ServiceLoader.load(MessageService.class);
        for (MessageService service : services) {
            System.out.println(service.getMessage());
        }
    }
}
