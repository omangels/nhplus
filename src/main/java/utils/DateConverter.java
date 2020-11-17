package utils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The Date converter because a string representation is too bothersome.
 *
 */
public class DateConverter {
    /**
     * Converts a string to a date
     * @param date
     * @return LocalDate
     *
     */
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        LocalDate result = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
        return result;
    }
    /**
     * Converts a string to a LocalTime Element
     * @param time
     * @return LocalTime
     *
     */
    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        LocalTime result = LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
        return result;
    }
}
