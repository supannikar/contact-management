package com.example.web.api.v1.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeFormatUtil {

    public static final String ISO8601_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String ISO8601_DATE_PATTERN = "yyyy-MM-dd";

    public static final String LONG_FORMATTER = "yyyy-MM-dd HH:mm:ss.SSS";

    private DateTimeFormatUtil() {
        // intention, Util should NOT able to instantiate
    }

    public static DateTime fromISO8601String(String dateTimeInString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(ISO8601_DATETIME_PATTERN);
        return DateTime.parse(dateTimeInString, formatter);
    }
    public static DateTime fromISO8601DateString(String dateTimeInString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(ISO8601_DATE_PATTERN);
        return DateTime.parse(dateTimeInString, formatter);
    }

    public static Date dateFromISO8601String(String dateTimeInString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(ISO8601_DATETIME_PATTERN);
        return DateTime.parse(dateTimeInString, formatter).toDate();
    }

    public static String dateToISO8601String(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(ISO8601_DATETIME_PATTERN);
        return dateFormatter.format(date);
    }

    public static String dateToISO8601DateString(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(ISO8601_DATE_PATTERN);
        return dateFormatter.format(date);
    }

    //For Long fomatter zmapfetcher
    public static DateTime fromLongFormatterString(String dateTimeInString){
        DateTimeFormatter formatter = DateTimeFormat.forPattern(LONG_FORMATTER);
        return DateTime.parse(dateTimeInString, formatter);
    }


    public static String toLongFormatterString(DateTime dateTime) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(LONG_FORMATTER);
        return dateFormatter.format(dateTime.toDate());
    }

    public static Date dateFromLongFormatterString(String dateTimeInString) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(LONG_FORMATTER);
        return DateTime.parse(dateTimeInString, formatter).toDate();
    }

    public static String dateToLongFormatterString(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(LONG_FORMATTER);
        return dateFormatter.format(date);
    }

}
