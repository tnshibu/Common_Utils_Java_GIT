package com.vypeensoft.util;

import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class FormatUtil {
    //=========================================================================================
    public static String formatNumberWithComma(int i) {
        DecimalFormat decimalFormatter = new DecimalFormat("###,###,000");
        return decimalFormatter.format(i);
    }
    //=========================================================================================
    public static String formatNumberWithComma(Long i) {
        DecimalFormat decimalFormatter = new DecimalFormat("###,###,000");
        return decimalFormatter.format(i);
    }
    //=========================================================================================
    //=========================================================================================
    //=========================================================================================
}
