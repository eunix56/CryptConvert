package com.example.eunice.cryptconvert.internal.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StringUtils {


    public static String formatValue(Double value) {
        String arr[] = {"", "K", "M", "B", "T", "P", "E"};

        int index = 0;
        double count = value;
        while ((count/1000) >= 1){
            count = count/1000;
            index++;
        }

        if (index > arr.length - 1){
            NumberFormat format = new DecimalFormat("0.###E0");
            return String.format("%s", format.format(value));
        }

        NumberFormat numberFormat = new DecimalFormat("###.###");
        return String.format("%s%s", numberFormat.format(count), arr[index]);
    }

    public static String formatValueWithComma(Double value) {
        NumberFormat format = new DecimalFormat("###,###,###.##");
        return String.format("%s", format.format(value));
    }
}
