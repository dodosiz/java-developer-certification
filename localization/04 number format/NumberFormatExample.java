import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatExample {
    public static void main(String[] args) {
        double number = 432947.324234;
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("en", "US"));
        numberFormat.setMaximumFractionDigits(2);
        System.out.println(numberFormat.format(number));
        NumberFormat numberFormat2 = NumberFormat.getInstance(new Locale("fr", "FR"));
        System.out.println(numberFormat2.format(number));
    }
}
