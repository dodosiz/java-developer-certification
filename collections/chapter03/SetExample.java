package chapter03;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExample {
    public static void main(String[] args) {
        // HashSet uses hashCode method for adding elements
        Set<Integer> primeNumbers1 = new HashSet<>();
        // TreeSet uses the compareTo method for adding elements
        Set<Integer> primeNumbers2 = new TreeSet<>();

        primeNumbers1.add(71);
        primeNumbers1.add(61);
        primeNumbers1.add(41);
        primeNumbers1.add(1);

        primeNumbers2.add(71);
        primeNumbers2.add(61);
        primeNumbers2.add(41);
        primeNumbers2.add(1);

        primeNumbers1.forEach(System.out::println);
        System.out.println("--------");
        // TreeSet will sort the elements automatically
        primeNumbers2.forEach(System.out::println);

        // add will return false if the element exists
        System.out.println(primeNumbers1.add(5));
        System.out.println(primeNumbers1.add(5));

        // sets do not offer indexing, this will throw a compile error
        // primeNumbers1.get();
    }
}
