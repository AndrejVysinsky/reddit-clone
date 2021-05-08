package com.example.RedditClone.helpers;

import java.util.Calendar;
import java.util.Date;

public class DateFormatter
{
    public static String GetTimeSinceCreation(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int createYear = calendar.get(Calendar.YEAR);
        int createMonth = calendar.get(Calendar.MONTH);
        int createDay = calendar.get(Calendar.DAY_OF_MONTH);
        int createHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int createMinute = calendar.get(Calendar.MINUTE);

        Date dateNow = new Date(System.currentTimeMillis());
        calendar.setTime(dateNow);

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int currentMinute = calendar.get(Calendar.MINUTE);

        if (currentYear > createYear)
        {
            int yearDifference = currentYear - createYear;

            return GetFormattedCreatedMessage(yearDifference, "year");
        }

        if (currentMonth > createMonth)
        {
            int monthDifference = currentMonth - createMonth;

            return GetFormattedCreatedMessage(monthDifference, "month");
        }

        if (currentDay > createDay)
        {
            int dayDifference = currentDay - createDay;

            return GetFormattedCreatedMessage(dayDifference, "day");
        }

        if (currentHour > createHour)
        {
            int hourDifference = currentHour - createHour;

            return GetFormattedCreatedMessage(hourDifference, "hour");
        }

        if (currentMinute > createMinute)
        {
            int minuteDifference = currentMinute - createMinute;

            return GetFormattedCreatedMessage(minuteDifference, "minute");
        }

        return "less than a minute ago";
    }

    private static String GetFormattedCreatedMessage(int timeDifference, String timeMessage)
    {
        if (timeDifference > 1)
        {
            return timeDifference + " " + timeMessage + "s ago";
        }
        else
        {
            return timeDifference + " " + timeMessage + " ago";
        }
    }
}
