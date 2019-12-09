package censusanalyser;

public class CsvBuilderException extends Exception {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM,  UNABLE_TO_PARSE
    }

    ExceptionType type;
}
