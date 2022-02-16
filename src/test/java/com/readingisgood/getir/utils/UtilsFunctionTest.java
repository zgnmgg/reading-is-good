package com.readingisgood.getir.utils;

import com.readingisgood.getir.helper.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsFunctionTest {

    @Test
    public void getCurrentTimestampTest() {
        Timestamp timestamp = Utils.getCurrentTimestamp();
        assertThat(timestamp).isNotNull();
    }

    @Test
    public void convertStringToDateTest() throws ParseException {
        Date date = Utils.convertStringToDate("11-11-2022 11:11:11");
        assertThat(date).isNotNull();
    }

    @Test
    public void formatterTest() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Istanbul"));
        //Thursday, October 4, 2012
        calendar.setTimeInMillis(Long.parseLong("1349333576093"));

        Timestamp t = Utils.formatter(calendar);

        Assertions.assertEquals(t.getDay(), 4);
        Assertions.assertEquals(t.getMonth(), 9);
        Assertions.assertNotEquals(t.getYear(), 2021);
    }

    @Test
    public void convertTimestampToStringTest() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Istanbul"));
        //Thursday, October 4, 2012
        calendar.setTimeInMillis(Long.parseLong("1349333576093"));

        String t = Utils.convertTimestampToString(calendar.getTime());

        Assertions.assertEquals("2012-10-04", t);
    }

    @Test
    public void getMonthTest() {
       String month = Utils.getMonth("3");

        Assertions.assertEquals("March", month);
    }
}
