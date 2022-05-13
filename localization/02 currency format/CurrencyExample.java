import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CurrencyExample {
    public static void main(String[] args) {
        Locale defaultLocale = Locale.getDefault();
        Currency defaultCurrency = Currency.getInstance(defaultLocale);
        System.out.println("Display name: " + defaultCurrency.getDisplayName());
        System.out.println("Symbol: " + defaultCurrency.getSymbol());

        Locale usLocale = new Locale("en", "US");
        Currency usCurrency = Currency.getInstance(usLocale);
        System.out.println("Display name: " + usCurrency.getDisplayName());
        System.out.println("Symbol: " + usCurrency.getSymbol());

        double amount = 392.34;
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(usLocale);
        System.out.println(numberFormat.format(amount));
    }
}
