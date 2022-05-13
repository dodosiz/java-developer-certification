import java.util.Locale;
import java.util.ResourceBundle;

public class TextBundleExample {
    public static void main(String[] args) {
        Locale usLocale = new Locale("en", "US");
        ResourceBundle textBundleUs = ResourceBundle.getBundle("TextBundle", usLocale);
        System.out.println(textBundleUs.getString("msg1"));
        System.out.println(textBundleUs.getString("msg2"));

        Locale deLocale = new Locale("de", "DE");
        ResourceBundle textBundleDe = ResourceBundle.getBundle("TextBundleDe", deLocale);
        System.out.println(textBundleDe.getString("msg1"));
        System.out.println(textBundleDe.getString("msg2"));
    }
}
