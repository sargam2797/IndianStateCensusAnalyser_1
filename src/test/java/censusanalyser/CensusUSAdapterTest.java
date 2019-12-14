package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class CensusUSAdapterTest {
    private static final String US_CENSUS_CSV_FILE_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test" +
            "/resources/USCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test" +
            "/resources/USCensusData1.csv";
    private static final String US_CENSUS_CSV_FILE_PATH_WRONG_EXT = "/home/user/CensusAnalyser/CensusAnalyser/src/test" +
            "/resources/USCensusData.txt";
    private static final String US_CENSUS_CSV_DELIMITER_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test/" +
            "resources/USCensusDataDelimiter.csv";
    private static final String US_CENSUS_CSV_HEADER_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test/" +
            "resources/USCensusDataHeader.csv";

    @Test
    public void givenUSCensusData_returnsExactMapCount() {
        USCensusAdapter usCensusAdapter =  new USCensusAdapter();
        Map<String, CensusDAO> result = null;
        try {
            result = usCensusAdapter.loadCensusData(CensusAnalyser.Country.US, US_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(51,result.size());
    }

    @Test
    public void givenUSCensusData_WithWrongFile_ShouldThrowException() {
        try {
            USCensusAdapter usCensusAdapter =  new USCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            usCensusAdapter.loadCensusData(CensusAnalyser.Country.US,WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenUSCensusData_WithCorrectFileButWrongExtension_ShouldThrowException() {
        USCensusAdapter usCensusAdapter =  new USCensusAdapter();
        try {
            usCensusAdapter.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH_WRONG_EXT);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIncorrectDelimiterInUSCensusData_ShouldThrowException() {
        USCensusAdapter usCensusAdapter =  new USCensusAdapter();
        try {
            usCensusAdapter.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_DELIMITER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_RELATED_TO_FILE, e.type);
        }
    }

    @Test
    public void givenIncorrectHeaderInUSCensusData_ShouldThrowException() {
        USCensusAdapter usCensusAdapter = new USCensusAdapter();
        try {
            usCensusAdapter.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_HEADER_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_RELATED_TO_FILE, e.type);
        }
    }
}
