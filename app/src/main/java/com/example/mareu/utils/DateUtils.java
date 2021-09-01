package com.example.mareu.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mohamed GHERBAL (pour OC) on 20/08/2021
 */
public class DateUtils {

    public static Date stringToDate(String formattedDate) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(formattedDate);
        return date;
    }
}
