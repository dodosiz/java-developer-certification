package chapter01;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import common.Category;
import common.ExampleData;
import common.Product;

public class App {
    public static void main(String[] args) {
        methodReferences();
    }
    /**
     * This function demonstrates the four posible uses of method references.
     */
    public static void methodReferences() {
        List<Product> products = ExampleData.getProducts();
        // static method
        products.removeIf(App::isExpensive);
        // instance method on a specific object (out is an object)
        products.forEach(System.out::println);
        // instance method by refering the class (like in static)
        products.stream().map(Product::getName).forEach(System.out::println);
        // by refering to a contructor (it helps with the factory design pattern)
        ProductFactory productFactory = Product::new;
        Product pizza = productFactory.create(Category.FOOD, "pizza", new BigDecimal("8.00"));
        System.out.println(pizza);
    }
    interface ProductFactory {
        Product create(Category category, String name, BigDecimal price);
    }
    public static boolean isExpensive(Product product) {
        return product.getPrice().compareTo(BigDecimal.valueOf(5)) > 0;
    }
    /**
     * Exceptions in lambdas can not be caught by the enclosing scope.
     */
    public static void exceptionsInLambdas() {
        List<Product> products = ExampleData.getProducts();
        try (FileWriter fileWriter = new FileWriter("products.txt")) {
            for (Product product : products) {
                fileWriter.write(product.toString() + "\n");
            }
            products.forEach(product -> {
                // will throw a compile error because the checked exception can not
                // be caught from the enclosing scope
                // fileWriter.write(product.toString());
            });
            fileWriter.write("---------------------------------\n");
            products.forEach(product -> {
                // instead we need to use try catch inside the lambda
                // which makes it verbose
                try {
                    fileWriter.write(product.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("An exception occured: " + e.getMessage());
        }
    }
    /**
     * Example of the scope of this in lambdas, it is the same as the
     * this keyword of the enclosing class, the same applies to the super keyword.
     */
    public static void scopeOfThis() {
        ThisScope thisScope = new ThisScope();
        thisScope.printMessage();
    }
    static class ThisScope {
        String message = "hello";
        public void printMessage() {
            Runnable runnable = () -> System.out.println(this.message);
            new Thread(runnable).start();
        }
    }
    /**
     * An example of why to avoid mutations and side effects in
     * a lambda expression.
     */
    public static void avoidMutationsInLambdas() {
        List<Product> products = ExampleData.getProducts();
        List<Product> cheapProducts = new ArrayList<>();
        BigDecimal limit = new BigDecimal("4.00");
        ProductFilter cheapProductFilter = p -> p.getPrice().compareTo(limit) < 0;
        // this may throw a concurrent modification exception
        products.parallelStream().forEach(product -> {
            if (cheapProductFilter.accept(product)) {
                cheapProducts.add(product);
            }
        });
        cheapProducts.forEach(System.out::println);
    }
    interface ProductFilter {
        boolean accept(Product product);
    }
    /**
     * Runs the print products function.
     */
    public static void runPrintProducts() {
        List<Product> products = ExampleData.getProducts();
        BigDecimal priceLimit = new BigDecimal("5.00");
        // priceLimit has to be final or effectively final to be used in a lambda
        // priceLimit = new BigDecimal("3.00"); // compile error
        ProductFilter productFilter = p -> p.getPrice().compareTo(priceLimit) < 0;
        printProducts(products, productFilter);
    }
    /**
     * Print products based on a predicate.
     */
    public static void printProducts(List<Product> products, ProductFilter productFilter) {
        for (Product product : products) {
            if (productFilter.accept(product)) {
                System.out.println(product);
            }
        }
    }
    /**
     * Sorting using an anonymous class (not recommended).
     */
    public static void sortWithAnonymousClass() {
        List<Product> products = ExampleData.getProducts();
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getPrice().compareTo(p2.getPrice());
            }
        });
        for (Product product : products) {
            System.out.println(product);
        }
    }
    /**
     * Sorting using a lambda expression.
     */
    public static void sortWithLambdaExpression() {
        List<Product> products = ExampleData.getProducts();
        products.sort((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()));
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
