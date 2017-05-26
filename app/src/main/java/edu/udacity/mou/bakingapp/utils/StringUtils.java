package edu.udacity.mou.bakingapp.utils;

/**
 * Created by mou on 25/05/17.
 */

public class StringUtils {
    public static boolean hasContent(String string) {
        return string != null && string.length() > 0;
    }

    public static boolean isEmpty(String string) {
        return !hasContent(string);
    }

}
