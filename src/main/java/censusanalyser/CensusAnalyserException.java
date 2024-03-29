package censusanalyser;

public class CensusAnalyserException extends Exception {

    public enum ExceptionType {
            CENSUS_FILE_PROBLEM,  INCORRECT_COUNTRY, ISSUE_RELATED_TO_FILE, NO_CENSUS_DATA,ONLY_ONE_FILE;
    }

    ExceptionType type;

    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
