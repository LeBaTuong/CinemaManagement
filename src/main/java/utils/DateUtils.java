package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    static DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static LocalDate parseDate(String strDate){
        try {
            return LocalDate.parse(strDate, dateTimeFormatter);
        }catch (DateTimeParseException dateTimeParseException){
            dateTimeParseException.printStackTrace();
        }
        return null;
    }
    public static String fomatLocalDate(LocalDate localDate){
        try {
            return dateTimeFormatter.format(localDate);
        }catch (DateTimeParseException dateTimeParseException){
            dateTimeParseException.printStackTrace();
        }
        return null;
    }
}