package com.hnt.dental.constant;

public class Regex {

    private Regex() {
        throw new IllegalStateException("Constant class");
    }

    public static final String YEAR_REGEX = "^(19|20)\\d{2}$";
    public static final String MONTH_REGEX = "^(0[1-9]|1[0-2])$";
    public static final String DAY_REGEX = "^(0[1-9]|[12][0-9]|3[01])$";
}