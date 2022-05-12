package greeter.hello.service;

import greeter.hello.util.CapitalizeUtil;

public class HelloMessageService {
    public String getMessage() {
        return CapitalizeUtil.capitalize("hello");
    }
}
