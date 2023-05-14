package com.hnt.dental.util;

import java.security.SecureRandom;
import java.util.Random;

public class CaptchaUtils {

    private CaptchaUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final char[] LOWER_CASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] NUMBERS = "0123456789".toCharArray();
    private static final char[] ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final Random rand = new SecureRandom();

    public static String getCaptcha(int length) {
        char[] captcha = new char[length];

        captcha[0] = LOWER_CASE[rand.nextInt(LOWER_CASE.length)];
        captcha[1] = UPPER_CASE[rand.nextInt(UPPER_CASE.length)];
        captcha[2] = NUMBERS[rand.nextInt(NUMBERS.length)];

        for (int i = 3; i < length; i++) {
            captcha[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        return getString(captcha);
    }

    private static String getString(char[] captcha) {
        for (int i = 0; i < captcha.length; i++) {
            int randomPosition = CaptchaUtils.rand.nextInt(captcha.length);
            char temp = captcha[i];
            captcha[i] = captcha[randomPosition];
            captcha[randomPosition] = temp;
        }
        return new String(captcha);
    }
}