package com.morzhanov.boilerplate.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.inject.Inject;

public class DateUtils {

    @Inject
    public DateUtils() {

    }

    public int getAgeFromBirthday(String birthday) {
        Date currentDate = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        Date birthDate = null;

        try {
            birthDate = format.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();

            return 0;
        }

        Calendar cal1 = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        cal1.setTime(birthDate);
        cal2.setTime(currentDate);
        int year = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);

        int currentDay = cal2.get(Calendar.DAY_OF_MONTH);
        int birthDay = cal1.get(Calendar.DAY_OF_MONTH);

        if ((cal2.get(Calendar.MONTH) < cal1.get(Calendar.MONTH))) {
            year = --year;
        } else if ((cal2.get(Calendar.MONTH) == cal1.get(Calendar.MONTH))) {
            if (currentDay < birthDay) {
                year = --year;
            }
        }

        return year;
    }

    public boolean isNextDayOrLater(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
            return true;
        }
        if (c1.get(Calendar.MONTH) > c2.get(Calendar.MONTH)) {
            return true;
        }
        if (c1.get(Calendar.DAY_OF_MONTH) > c2.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }
}
