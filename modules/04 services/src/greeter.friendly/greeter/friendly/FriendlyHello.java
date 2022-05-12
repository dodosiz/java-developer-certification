package greeter.friendly;

import greeter.api.MessageService;

public class FriendlyHello implements MessageService {
    public String getMessage() {
        return "Hello friend";
    }
}
