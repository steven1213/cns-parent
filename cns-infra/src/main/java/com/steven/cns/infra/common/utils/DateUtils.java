package com.steven.cns.infra.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author steven.cao
 */
public final class DateUtils {

    private DateUtils() {
    }

    public static Date add(int times, int field) {
        return add(times, field, false);
    }

    public static Date add(int times, int field, boolean dayEnd) {
        return add(new Date(), times, field, dayEnd);
    }

    public static Date add(Date date, int times, int field) {
        return add(date, times, field, false);
    }

    public static Date add(Date date, int times, int field, boolean dayEnd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, times);
        if (dayEnd) {
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        }
        return calendar.getTime();
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


}
