package com.readingisgood.getir.helper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Component
public class Utils {

    private static String mon[]={"January", "February", "March","April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public static Timestamp getCurrentTimestamp() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Istanbul"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter(calendar);
    }

    public static Timestamp formatter(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul"));
        return Timestamp.valueOf(format.format(calendar.getTime()));
    }

    public static Date convertStringToDate(String time) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        return format.parse(time);
    }

    public static String convertTimestampToString(Date time) {
        return new SimpleDateFormat("yyyy-MM-dd")
                .format(time);
    }

    public static String getMonth(String val) {
        return mon[Integer.parseInt(val) - 1];
    }
}
