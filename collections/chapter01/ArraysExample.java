package chapter01;

import java.util.Arrays;

public class ArraysExample {
    public static void main(String[] args) {
        int[] uninitialized;
        // won't compile
        // System.out.println(uninitialized);
        int[] ids = new int[10];
        System.out.println(ids); // address of ids
        System.out.println(ids[0]); // 0
        String[] uninitializedNames = new String[10];
        System.out.println(uninitializedNames[0]); // null
        String[] instruments = new String[] {"guitar", "bass", "piano"};
        System.out.println(instruments[0]); // Tom
        // all valid declarations
        int[] ids1;
        int [] ids2;
        int ids3[];
        int ids4 [];
        // for multiple declaration keep the [] near the type
        int[] ids5, ids6, ids7;
        System.out.println(instruments.length);
        // i <= ids.length will get an ArrayIndexOutOfBoundsException
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i * i;
            System.out.println(ids[i]);
        }
        for (String intrument : instruments) {
            System.out.println(intrument);
        }
        // the primitive arrays don't have helper methods, they only offer index accesor and length
        // so we will use the Arrays class
        Arrays.sort(instruments);
        System.out.println(Arrays.toString(instruments));
        int[] fibNumbers = new int[] {0, 1, 5, 2, 3, 1, 8, 13};
        // in unsorted arrays the result will be unpredictable (negative number)
        System.out.println(Arrays.binarySearch(fibNumbers, 3));
        // we have to sort first
        Arrays.sort(fibNumbers);
        System.out.println(Arrays.binarySearch(fibNumbers, 3));
        // multidimensional
        int[][] ids8 = new int[3][10];
        String[][] cities = new String[][] {
            {"Athens", "Tokyo"},
            {"Berlin", "London", "Amsterdam"},
            {"Paris"}
        };
        for (String[] row : cities) {
            for (String city : row) {
                System.out.println(city);
            }
        }
        // this will just print the memory location of each subarray
        System.out.println(Arrays.toString(cities));
    }
}
