package com.bkav.sdl.core.validator;

import java.util.Collection;

public class ValidateUtils {

    public static double getLength(Object val) {
        if (val == null) return 0;

        if (val instanceof Collection) {
            return ((Collection) val).size();
        }

        if (val instanceof Number) {
            return (double) val;
        }

        if (val instanceof String) {
            return ((String) val).length();
        }

        if (val instanceof Object[]) {
            return ((Object[]) val).length;
        }

        return 0;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }


}
