package chapter02;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import common.Category;
import common.ExampleData;
import common.Product;

public class App {
    public static void main(String[] args) {
        usePredicateComposition();
    }
    /**
     * Uses functional interfaces to categorize and print products into categories.
     */
    public static void categorizeAndPrint() {
        List<Product> products = ExampleData.getProducts();
        HashMap<Category, List<Product>> productsByCategory = new HashMap<>();
        for (Product product : products) {
            productsByCategory.computeIfAbsent(product.getCategory(), c -> new ArrayList<>()).add(product);
        }
        productsByCategory.forEach((category, pds) -> {
            System.out.println(String.format("Category %s:", category));
            pds.forEach(product -> System.out.println(String.format("- %s", product.getName())));
        });
    }
    /**
     * Example with predicate composition.
     */
    public static void usePredicateComposition() {
        List<Product> products = ExampleData.getProducts();
        Predicate<Product> isFood = p -> p.getCategory().equals(Category.FOOD);
        Predicate<Product> isCheap = p -> p.getPrice().compareTo(new BigDecimal("5.00")) < 0;
        Predicate<Product> isFoodAndCheap = isFood.and(isCheap); // composition
        products.stream().filter(isFoodAndCheap).findFirst()
            .ifPresentOrElse(p -> System.out.println(p), () -> System.out.println("No product found."));
    }
    /**
     * Example with a functional interface (Predicate) to search for
     * a product, then use a Consumer and a Runnable to print the results.
     * Also demonstrate function composition.
     */
    public static void usePredicate() {
        List<Product> products = ExampleData.getProducts();
        String name = "Spaghetti";
        Function<Product, BigDecimal> productToPrice = Product::getPrice;
        Function<BigDecimal, String> priceToMessage = p -> String.format("Product %s costs %.2f $.", name, p);
        // function composition
        Function<Product, String> productToMessage = productToPrice.andThen(priceToMessage);
        // alternative with compose (notice oposite direction)
        // Function<Product, String> productToMessage = priceToMessage.compose(productToPrice);
        // Predicate
        findProduct(products, p -> p.getName().equals(name))
        .map(productToMessage)
        .ifPresentOrElse(
            // Consumer
            System.out::println,
            // Runnable
            () -> System.out.println(String.format("Product %s not found.", name))
        );
    }
    public static Optional<Product> findProduct(List<Product> products, Predicate<Product> predicate) {
        for (Product product : products) {
            if (predicate.test(product)) {
                return Optional.of(product);
            }
        }
        return Optional.empty();
    }
}
