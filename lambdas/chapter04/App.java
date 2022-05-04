package chapter04;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import common.Category;
import common.ExampleData;
import common.Product;

public class App {
    public static void main(String[] args) {
        List<Product> products = ExampleData.getProducts();
        Map<Category, List<Product>> categoryToProducts = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));
        System.out.println(categoryToProducts);
        Map<Category, List<String>> categoryToNames = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toList())));
        System.out.println(categoryToNames);
    }
    /**
     * The toMap collector converts a stream into a map. It needs 3 functions
     * for streams that have duplicate entries, the first two are the key and value mappers and
     * the third is the merging function, that decides what to do with entries that have the same key.
     */
    public static void usingToMapCollector() {
        List<Product> products = ExampleData.getProducts();
        Map<Category, BigDecimal> totalPricePerCategory = products.stream()
            .collect(Collectors.toMap(Product::getCategory, Product::getPrice, BigDecimal::add));
        System.out.println(totalPricePerCategory);
    }
    /**
     * Collecting streams seems similar to reducing streams with one key
     * difference. Collecting is the action of turning an immutable collection (stream)
     * into a mutable (for example list), while the reduce is an immutable transformation.
     * This can be also observed from the method references of the two functions:
     * 
     * collect(Supplier<Object> supplier, BiConsumer<Object, ? super Product> accumulator, BiConsumer<Object, Object> combiner)
     * reduce(BigDecimal identity, BiFunction<BigDecimal, ? super Product, BigDecimal> accumulator, BinaryOperator<BigDecimal> combiner)
     * 
     * The collect uses consumers, while the reduce functions, which shows us that the one operation is mutable and
     * the other immutable.
     * 
     * Again here it is worth to notice that the combiner in both functions is needed for the parallel streams.
     * 
     */
    public static void collectingStreams() {
        List<Product> products = ExampleData.getProducts();
        List<String> names = products.stream()
            .collect(ArrayList:: new,
                (list, product) -> list.add(product.getName()),
                List::addAll);
        System.out.println(names);
    }
    /**
     * The three different ways to use the reduce function.
     */
    public static void reducingStreams() {
        List<Product> products = ExampleData.getProducts();
        // reduce version 1
        Optional<BigDecimal> totalPrice = products.stream()
            .map(Product::getPrice)
            .reduce(BigDecimal::add);
        totalPrice.ifPresentOrElse(
            p -> System.out.println("The total price is " + p),
            () -> System.out.println("Total price not foud"));
        // reduce version 2, does not give an optional back because
        // of the initial value
        BigDecimal totalPrice2 = products.stream()
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("The total price is " + totalPrice2);
        // reduce version 3, is used in parallel streams, because it uses
        // a combiner to combine the intermidiate results
        BigDecimal totalPrice3 = products.stream()
            .reduce(BigDecimal.ZERO, (result, product) -> result.add(product.getPrice()), BigDecimal::add);
        System.out.println("The total price is " + totalPrice3);
    }
    /**
     * In a previous chapter we saw some factory methods to create streams.
     * This function demonstrates more ways.
     */
    public static void someMoreFactoryMethodsToCreateStreams() {
        // with generate
        Stream<UUID> uuids = Stream.generate(UUID::randomUUID);
        uuids.limit(10).forEach(System.out::println);
        // with iterate
        Stream<BigInteger> powersOfTwo = Stream.iterate(BigInteger.ONE, n -> n.multiply(BigInteger.TWO));
        powersOfTwo.limit(20).forEach(System.out::println);
        // with the second version of iterate
        Stream.iterate('A', c -> c <= 'Z', n -> (char) (n + 1)).forEach(System.out::print);
        // with a builder
        Stream.Builder<String> builder = Stream.builder();
        builder.add("one");
        builder.add("two");
        builder.add("three");
        Stream<String> numbers = builder.build();
        numbers.forEach(System.out::println);
    }
}
