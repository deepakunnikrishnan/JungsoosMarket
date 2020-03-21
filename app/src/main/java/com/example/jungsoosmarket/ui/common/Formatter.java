package com.example.jungsoosmarket.ui.common;

import java.text.DecimalFormat;

public class Formatter {



    public static String format(float value){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }
}
