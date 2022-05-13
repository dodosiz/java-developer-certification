import java.util.Locale;
import static java.util.Locale.ITALIAN;
import static java.util.Locale.CANADA;

public class LocaleExample {
    public static void main(String[] args) {
        // the default locale
        Locale locale = Locale.getDefault();
        System.out.println("Default locale");
        displayLocaleAttributes(locale);

        // We can pass up to three arguments in the Locale contructor
        // language, country and variant
        Locale locale2 = new Locale("fr");
        Locale locale3 = new Locale("fr", "FR");
        Locale locale4 = new Locale("fr", "FR", "WIN");
        System.out.println("Printing locale2");
        displayLocaleAttributes(locale2);
        System.out.println("Printing locale3");
        displayLocaleAttributes(locale3);
        System.out.println("Printing locale4");
        displayLocaleAttributes(locale4);

        // create locale using a builder
        Locale locale5 = new Locale.Builder()
            .setLanguage("en").setRegion("US").build();
        System.out.println("Printing locale5");
        displayLocaleAttributes(locale5);

        // there are also predefined locale constants
        System.out.println("Printing locale constants");
        System.out.println(ITALIAN.getLanguage());
        System.out.println(CANADA.getCountry());
    }
    public static void displayLocaleAttributes(Locale locale) {
        System.out.println("*** Displaying locale attributes***");
        System.out.println("Display Country: " + locale.getDisplayCountry());
        System.out.println("Display Language: " + locale.getDisplayLanguage());
        System.out.println("Display Name: " + locale.getDisplayName());
        System.out.println("Display Variant: " + locale.getDisplayVariant());
        System.out.println("Language Code: " + locale.getLanguage());
        System.out.println("Country Code: " + locale.getCountry());
        System.out.println("Variant Code: " + locale.getVariant());
        System.out.println("***********************");
    }
}