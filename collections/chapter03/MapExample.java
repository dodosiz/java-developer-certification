package chapter03;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapExample {
    public static void main(String[] args) {
        Map<Integer, String> countries = new HashMap<>();
        countries.put(840, "USA");
        countries.put(484, "MEX");
        countries.put(124, "CAN");

        System.out.println(countries.get(840));

        countries.remove(840);
        System.out.println(countries.keySet());

        // this will override CAN
        countries.put(124, "ABC");
        System.out.println(countries.values());

        // these are also allowed operations
        countries.put(-1, null);
        countries.put(null, null);
        System.out.println(countries);

        Map<Integer, String> countries2 = new TreeMap<>();
        countries2.put(840, "USA");
        countries2.put(484, "MEX");
        countries2.put(124, "CAN");

        // a TreeMap will sort the keys
        System.out.println(countries2.keySet());
        // null values are allowed
        countries2.put(20, null);
        System.out.println(countries2);
        // null key are not allowed (NullPointerException)
        // countries2.put(null, null);
    }
}
