package censusanalyser;

public class CsvBuilderException extends Exception {
    public enum ExceptionType {
        CENSUS_FILE_PROBLEM,  UNABLE_TO_PARSE
    } ExceptionType type;

    public CsvBuilderException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

}
