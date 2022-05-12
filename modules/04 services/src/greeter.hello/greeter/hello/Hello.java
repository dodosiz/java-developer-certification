package greeter.hello;

import greeter.api.MessageService;

public class Hello implements MessageService {
    public String getMessage() {
        return "Hello";
    }
}
