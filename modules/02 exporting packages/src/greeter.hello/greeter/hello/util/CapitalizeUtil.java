package greeter.hello.util;

public class CapitalizeUtil {
    public static String capitalize(String message) {
        String first = message.substring(0, 1);
        String rest = message.substring(1);
        return first.toUpperCase() + rest;
    }
}
