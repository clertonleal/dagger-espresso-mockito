package clertonleal.com.nubank.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatUtil {

    private static final SimpleDateFormat shortDateFormat = new SimpleDateFormat("dd MMM", new Locale("pt", "BR"));
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM", new Locale("pt", "BR"));
    private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", new Locale("pt", "BR"));
    private static final NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    public static String getMonth(Date date) {
        return monthFormat.format(date).toUpperCase();
    }

    public static String getDate(Date date) {
        return dateFormat.format(date).toUpperCase();
    }

    public static String getShortDate(Date date) {
        return shortDateFormat.format(date).toUpperCase();
    }

    public static String getShortMonetaryValue(long value) {
        Double temp = (double) value;
        temp = temp/100;
        return numberFormat.format(temp);
    }

    public static String getMonetaryValue(long value) {
        return numberFormat.getCurrency().getSymbol() + " " + getShortMonetaryValue(value);
    }

}
