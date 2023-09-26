package eu.brickpics.maurice.data;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDateTime getFromString(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    public static String getFromLocalDateTime(LocalDateTime localDateTime) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localDateTime);
    }
}
