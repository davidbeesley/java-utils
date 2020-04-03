package com.github.davidbeesley.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static String getDateTimeStamp(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public static String getTimeStamp(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }



}
