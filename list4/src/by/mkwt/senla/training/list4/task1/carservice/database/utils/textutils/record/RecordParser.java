package by.mkwt.senla.training.list4.task1.carservice.database.utils.textutils.record;

public class RecordParser {
    private static final String DELIMITER = "\\|";

    public static String[] getValues(String line) {
        return line.split(DELIMITER);
    }
}
