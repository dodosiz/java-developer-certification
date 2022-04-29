package chapter03;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import common.Category;
import common.ExampleData;
import common.Product;

public class App {
    public static void main(String[] args) {
        collectingStreams();
    }
    public static void collectingStreams() {
        // collect as a list
        List<String> productNames = ExampleData.getProducts().stream()
            .map(p -> p.getName())
            .collect(Collectors.toList());
        System.out.println(productNames);
        // collect as a string
        String categories = ExampleData.getProducts().stream()
            .map(p -> p.getCategory())
            .distinct()
            .map(c -> c.toString())
            .collect(Collectors.joining(", "));
        System.out.println(categories);
    }
    /**
     * This method demonstrates searching in streams.
     * Notice that findFirst, findAny and anyMatch are short circuit
     * operators, while the other two are not.
     */
    public static void searchInStreams() {
        List<Product> products = ExampleData.getProducts();
        Optional<Product> officeProduct = products.stream()
            .filter(p -> p.getCategory().equals(Category.OFFICE))
            .findFirst();
        officeProduct.ifPresent(System.out::println);
        // in this case findFirst and findAny will give the same result because
        // we have an ArrayList (preserves order of elements). If we had a
        // HashSet, which does not preserve an order, we would have different results
        Optional<Product> anyOfficeProduct = products.stream()
            .filter(p -> p.getCategory().equals(Category.OFFICE))
            .findAny();
        anyOfficeProduct.ifPresent(System.out::println);
        // find if any
        boolean officeProductFound = products.stream()
            .anyMatch(p -> p.getCategory().equals(Category.OFFICE));
        System.out.println("Office product found? " + officeProductFound);
        // find if all
        boolean allProductsAreCheap = products.stream()
            .allMatch(p -> p.getPrice().compareTo(new BigDecimal("10.0")) < 0);
        System.out.println("All products are cheap? " + allProductsAreCheap);
        // find if none matches
        boolean allProductsAreExpensive = products.stream()
            .noneMatch(p -> p.getPrice().compareTo(new BigDecimal("10.0")) < 0);
        System.out.println("All products are expensive? " + allProductsAreExpensive);
    }
    /**
     * Filter and transform streams using filter, map and flatMap.
     */
    public static void filterAndTransformStreams() {
        List<Product> products = ExampleData.getProducts();
        // filter
        products.stream()
            .filter(p -> p.getCategory().equals(Category.CLEANING))
            .forEach(System.out::println);
        // transform one to one
        products.stream()
            .map(p -> String.format("The price of %s is %.2f $.", p.getName(), p.getPrice()))
            .forEach(System.out::println);
        // transform one to many
        Pattern spaces = Pattern.compile("\\s+");
        products.stream()
            .flatMap(p -> spaces.splitAsStream(p.getName()))
            .forEach(System.out::println);
    }
    /**
     * This function demonstrates different ways to create streams.
     */
    public static void differentWaysToCreateAStream() {
        // stream from collection
        List<Product> products = ExampleData.getProducts();
        Stream<Product> stream1 = products.stream();
        stream1.forEach(System.out::println);
        // from array
        String[] numbers = new String[]{"one", "two", "three"};
        Stream<String> stream2 = Arrays.stream(numbers);
        stream2.forEach(System.out::println);
        // with a factory method
        Stream<String> stream3 = Stream.of("one", "two", "three");
        stream3.forEach(System.out::println);
        // creates a stream from a single element that can be null
        Stream<String> stream4 = Stream.ofNullable("four");
        stream4.forEach(System.out::println);
        // we can also create an empty stream
        Stream<?> stream5 = Stream.empty();
        stream5.forEach(System.out::println);
        // from other libraries that produce streams
        IntStream dice = new Random().ints(1, 7);
        System.out.println(dice.findFirst().getAsInt());
    }
    /**
     * Only after excecuting a terminal operation the stream runs.
     */
    public static void streamsAreLazy() {
        List<Product> products = ExampleData.getProducts();
        Stream<Product> stream = products.stream()
            .map(product -> {
                System.out.println(product.getName());
                return product;
            });
        
        // if you comment the following line you won't see no output
        stream.forEach(p -> {});
    }
}
