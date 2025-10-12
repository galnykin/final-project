package ru.kupibilet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransferUtils {
    public static int countTransfers(String tripText) {
        Pattern pattern = Pattern.compile("(\\d+) пересад");
        Matcher matcher = pattern.matcher(tripText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 1;
    }
}
