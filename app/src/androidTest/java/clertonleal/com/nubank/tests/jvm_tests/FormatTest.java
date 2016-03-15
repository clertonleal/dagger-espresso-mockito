package clertonleal.com.nubank.tests.jvm_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Date;
import java.util.GregorianCalendar;

import clertonleal.com.nubank.util.FormatUtil;

import static junit.framework.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FormatTest {

    public static final long VALUE_10 = 1000L;
    public static final long VALUE_10_20_CENTS = 1020L;
    public static final long VALUE_100 = 10000L;
    public static final long VALUE_1000 = 100000L;
    public static final long VALUE_1500 = 150000L;
    public static final long VALUE_1500_32_CENTS = 150032L;

    public static final Date FEBRUARY_10_2016 = new GregorianCalendar(2016, 1, 10).getTime();

    @Test
    public void test_month_format() {
        assertEquals(FormatUtil.getMonth(FEBRUARY_10_2016), "FEV");
    }

    @Test
    public void test_date_format() {
        assertEquals(FormatUtil.getDate(FEBRUARY_10_2016), "10 DE FEVEREIRO");
    }

    @Test
    public void test_short_date_format() {
        assertEquals(FormatUtil.getShortDate(FEBRUARY_10_2016), "10 FEV");
    }

    @Test
    public void test_monetary_format() {
        assertEquals(FormatUtil.getMonetaryValue(VALUE_10), "R$ 10");
        assertEquals(FormatUtil.getMonetaryValue(VALUE_10_20_CENTS), "R$ 10,2");
        assertEquals(FormatUtil.getMonetaryValue(VALUE_100), "R$ 100");
        assertEquals(FormatUtil.getMonetaryValue(VALUE_1000), "R$ 1.000");
        assertEquals(FormatUtil.getMonetaryValue(VALUE_1500), "R$ 1.500");
        assertEquals(FormatUtil.getMonetaryValue(VALUE_1500_32_CENTS), "R$ 1.500,32");
    }

    @Test
    public void test_short_monetary_format() {
        assertEquals(FormatUtil.getShortMonetaryValue(VALUE_10), "10");
        assertEquals(FormatUtil.getShortMonetaryValue(VALUE_10_20_CENTS), "10,2");
        assertEquals(FormatUtil.getShortMonetaryValue(VALUE_100), "100");
        assertEquals(FormatUtil.getShortMonetaryValue(VALUE_1000), "1.000");
        assertEquals(FormatUtil.getShortMonetaryValue(VALUE_1500), "1.500");
        assertEquals(FormatUtil.getShortMonetaryValue(VALUE_1500_32_CENTS), "1.500,32");
    }

}
