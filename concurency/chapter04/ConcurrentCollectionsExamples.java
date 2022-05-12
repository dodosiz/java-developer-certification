package chapter04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConcurrentCollectionsExamples {
    public static void main(String[] args) {
        synchronizedCollectionsExample();
    }
    /**
     * Bellow an example of why collections are not thread safe.
     * We remove a value from a HashMap while looping in it and as a
     * result we get a ConcurrentModificationException
     */
    public static void collectionsAreNotThreadSafe() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("Tom", "Java");
        hashMap.put("Mike", "Python");
        for (String key : hashMap.keySet()) {
            System.out.println("About to remove key " + key);
            hashMap.remove(key);
        }
    }
    /**
     * In order to solve the above problem, we have to use a concurrent
     * collection. In this example it is a ConcurrentHashMap.
     * We could also solve the problem with the synchronized keyword, but a concurrent
     * collection offers better performance.
     */
    public static void concurrentCollectionsToTheRescue() {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("Tom", "Java");
        concurrentMap.put("Mike", "Python");
        for (String key : concurrentMap.keySet()) {
            System.out.println("About to remove key " + key);
            concurrentMap.remove(key);
        }
    }
    /**
     * There are two main interfaces of concurrent collections.
     * The one is the ConcurrentMap and the other the BlockingQueue.
     */
    public static void typesOfConcurrentCollections() {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("Thomas", "PHP");
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.offer("Maria");
    }
    /**
     * The BlockingQueue has methods of the parent Queue interface
     * and also some other concurent mathods.
     */
    public static void blockingQueueExample() {
        BlockingQueue<String> names = new LinkedBlockingQueue<>();

        // methods from Queue interface

        // add names with offer
        names.offer("Maria");
        names.offer("Thomas");
        names.offer("Mark");
        // peek gets the first value without removing it
        System.out.println(names.peek());
        // poll does the same but also removes the value
        System.out.println(names.poll());
        // we can check if the queue contains a value
        System.out.println(names.contains("Thomas"));
        System.out.println(names);

        // special for concurrent queues (timeout)
        try {
            // try to offer for 3 seconds
            names.offer("Alex", 3, TimeUnit.SECONDS);
            // try to poll for 4 seconds
            names.poll(4, TimeUnit.SECONDS);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(names);

    }
    /**
     * The skip list collections other than the concurrent support,
     * don't offer special methods that Set or Map do not have.
     * Other than that, they order the elements to the
     * natural (alphabetical) order.
     */
    public static void skipListExample() {
        Set<String> names = new ConcurrentSkipListSet<>();
        names.add("Mike");
        names.add("Alex");
        names.add("Elias");
        for (String name : names) {
            System.out.println(name);
        }
        Map<String, String> instruments = new ConcurrentSkipListMap<>();
        instruments.put("Mike", "Guitar");
        instruments.put("Alex", "Bass");
        instruments.put("Elias", "Piano");
        for (String key : instruments.keySet()) {
            System.out.println(key + ": " + instruments.get(key));
        }
    }
    /**
     * A CopyOnWrite collection is everytime creating a new copy of the collection
     * whenever this collection gets modified. This has a bad performance for writing
     * and is mostly used for reading.
     */
    public static void copyOnWriteExample() {
        List<String> animals = new CopyOnWriteArrayList<>();
        animals.add("Dog");
        animals.add("Cat");
        animals.add("Donkey");
        // what do you think will happen here?
        for (String animal : animals) {
            animals.add(animal);
        }
        System.out.println(animals);

        Set<String> cities = new CopyOnWriteArraySet<>();
        cities.add("Tokyo");
        cities.add("Berlin");
        cities.add("London");
        // what do you think will happen here?
        for (String city : cities) {
            animals.add(city);
        }
        System.out.println(cities);
    }
    /**
     * Synchronized collections also offer concurrency in collections.
     * Generally, when creating a new collection, create a concurrent
     * collection. This kind of collection is only used for existing collection
     * to use them concurrently.
     * Also, you can only read concurrently not modify.
     */
    public static void synchronizedCollectionsExample() {
        List<String> names = new ArrayList<>();
        names.add("Mike");
        names.add("Markus");
        names.add("Theodor");
        var syncNames = Collections.synchronizedList(names);
        // will also throw a ConcurrentModificationException
        for (String name : syncNames) {
            syncNames.remove(name);
        }
    }
}
