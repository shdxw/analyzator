package ru.vl.analyze.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//метод для парсинга даты из строки
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
}
