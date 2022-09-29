package com.trendit.common.util;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isValidKeyword(String keyword) {
        return Pattern.matches("^[가-힣a-zA-Z]*$",keyword);
    }

}
