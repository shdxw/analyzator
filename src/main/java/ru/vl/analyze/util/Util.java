package ru.vl.analyze.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/mm/yyyy:hh:mm:ss");
    public static Date toDate(String strDate) {
        try {
            strDate = strDate.substring(1, strDate.length());
            Date date = FORMATTER.parse(strDate);
            return date;
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(Util.toDate("[14/06/2017:16:47:02 +1000]"));
    }
}
