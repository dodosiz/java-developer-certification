package chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListsExample {
    public static void main(String[] args) {
        ArrayList arrayList1 = new ArrayList();
        // the size does not matter for lists, in oposition with primitive arrays
        ArrayList arrayList2 = new ArrayList(100);
        ArrayList arrayList3 = new ArrayList(arrayList2);
        // since Java 5 we use generics
        ArrayList<String> colors = new ArrayList<>();
        // these are all valid declarations
        List arrayList4 = new ArrayList();
        Collection arrayList5 = new ArrayList();
        // this won't compile
        // ArrayList wrong = new Collection();
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        // overloaded add
        colors.add(1, "orange");
        // available since Java 8
        colors.forEach(System.out::println);
        colors.remove("blue");
        colors.remove(1);
        System.out.println(colors);
        // from list to array
        Object[] colorsObjArray = colors.toArray();
        String[] colorStringArray = colors.toArray(new String[1]);
        String[] shapes = new String[] {"circle", "rectangle"};
        List<String> shapesList = Arrays.asList(shapes);
        System.out.println(shapesList);
        // from array the class will be a subclass of Arrays
        System.out.println(shapesList.getClass().getName());
        System.out.println(colors.getClass().getName());
        // shapesList is immutable, this will throw a runtime exception
        // shapesList.remove(1);
        List<String> shapesList2 = List.of(shapes);
        // shapesList2 is also immutable, this will throw a runtime exception
        // shapesList.remove(1);

        // LinkedLists implement both List and Queue interface
        // LinkedList is more performant than the ArrayList for adding elements (links)
        LinkedList<String> orders = new LinkedList<>();
        // List methods
        orders.add("order 1");
        orders.add("order 2");
        orders.add("order 3");
        System.out.println(orders);
        // Queue methods
        orders.addFirst("order 4");
        orders.addLast("order 5");
        System.out.println(orders);
        orders.removeFirst();
        orders.removeLast();
        System.out.println(orders);

        // sorting lists
        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(5);
        numbers.add(1);
        numbers.add(3);
        numbers.add(9);

        System.out.println(numbers);
        Collections.sort(numbers);
        System.out.println(numbers);

        List<Mountain> mountains = new ArrayList<>();
        mountains.add(new Mountain(8000, "mountain 1"));
        mountains.add(new Mountain(2000, "mountain 2"));
        mountains.add(new Mountain(9000, "mountain 3"));

        // sorting with the Comparable compareTo method
        Collections.sort(mountains);
        mountains.forEach(System.out::println);

        // sorting with an overloaded Comparator
        Collections.sort(mountains, (m1, m2) -> m2.getHeight() - m1.getHeight());
        mountains.forEach(System.out::println);
    }
}
