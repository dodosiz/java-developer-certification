package chapter02;

// notice the diference of Comparable and Comparator
public class Mountain implements Comparable<Mountain> {
    private int height;
    private String name;
    public Mountain(int height, String name) {
        this.height = height;
        this.name = name;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int compareTo(Mountain o) {
        return this.height - o.height;
    }
    public String toString() {
        return this.name;
    }
}
