import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.TimeZone;

public class DateExample {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 3, 10, 20, 15, 5);
        LocalTime localTime = localDateTime.toLocalTime();
        System.out.println("Day: " + localDateTime.getDayOfMonth());
        System.out.println("Month: " + localDateTime.getMonth());
        System.out.println("Hour: " + localTime.getHour());
        System.out.println("Minute: " + localTime.getMinute());

        String pattern = "dd-MMMM-yyyy HH:mm:ss.SSS";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDate = dateTimeFormatter.format(localDateTime);
        System.out.println(formattedDate);

        Locale.setDefault(Locale.FRANCE);
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(dateTimeFormatter2.format(localDateTime));

        Locale.setDefault(Locale.GERMANY);
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(timeZone.getDisplayName(Locale.getDefault()));

        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println("Zoned date time: " + zonedDateTime.toString());
        System.out.println("Day of month: " + zonedDateTime.toLocalDate().getDayOfMonth());

        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        System.out.println("Timestamp: " + timestamp.toString());
    }
}
