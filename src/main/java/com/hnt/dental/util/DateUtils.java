package com.hnt.dental.util;

import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.hnt.dental.constant.Regex.*;


public class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static final String HH_MM_SS = "HH:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static String parseString(LocalTime localTime, String format) {
        return localTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static String parseString(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDate parseLocalDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(YYYY_MM_DD));
    }

    public static LocalDateTime parseLocalDateTime(String year, String month, String day,
                                                   String hour, String minute, String second) {
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),
                Integer.parseInt(hour), Integer.parseInt(minute), Integer.parseInt(second)
        );
    }

    public static boolean isValidDate(String year, String month, String day) {
        if ((ObjectUtils.isEmpty(year) || ObjectUtils.isEmpty(month) || ObjectUtils.isEmpty(day) ||
                !year.matches(YEAR_REGEX) || !month.matches(MONTH_REGEX) || !day.matches(DAY_REGEX))) {
            return false;
        }

        try {
            LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidDate(String year, String month) {
        return ObjectUtils.isNotEmpty(year) && ObjectUtils.isNotEmpty(month) &&
                year.matches(YEAR_REGEX) && month.matches(MONTH_REGEX);
    }

    public static int lastDayOfMonth(String year, String month) {
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1).lengthOfMonth();
    }

    public static String stringifyTime(LocalDate date, String hour, String minute, String second) {
        return date + "T" + hour + ":" + minute + ":" + second + "+09:00";
    }
}