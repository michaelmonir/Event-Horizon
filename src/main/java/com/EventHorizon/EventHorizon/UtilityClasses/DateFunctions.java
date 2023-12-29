package com.EventHorizon.EventHorizon.UtilityClasses;

import java.util.Date;

public class DateFunctions
{
    public static boolean isDateAfterNow(Date date)
    {
        Date now = new Date();
        Date halfHourBeforeNow = new Date(now.getTime() - 30 * 60 * 1000);
        return date.after(halfHourBeforeNow);
    }

    public static boolean isDateBeforeNow(Date date)
    {
        Date now = new Date();
        Date halfHourBeforeNow = new Date(now.getTime() - 30 * 60 * 1000);
        return date.before(halfHourBeforeNow);
    }

    public static Date getCurrentDate()
    {
        return new Date();
    }

    public static Date getYesterDaysDate()
    {
        return new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
    }
}
