package chapter04;

public class GenericsExample<T> {
    public static void main(String[] args) {
        GenericsExample<String> genericsExample = new GenericsExample<>();
        genericsExample.setType("The type");
        System.out.println(genericsExample.getType());
        String[] names = new String[] {"Bob", "Tim"};
        System.out.println(GenericsExample.getFirstElement(names));
    }
    private T type;
    public void setType(T type) {
        this.type = type;
    }
    public T getType() {
        return this.type;
    }
    // generic on method, T is not equal the class generic type T
    public static <T> T getFirstElement(T[] elements) {
        return elements[0];
    }
}
