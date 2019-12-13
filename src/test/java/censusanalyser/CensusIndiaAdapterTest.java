package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class CensusIndiaAdapterTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CODE_FILE_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test/" +
            "resources/IndiaStateCode.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH_INVALID_EXT = "./src/test/resources/IndiaStateCensusData.txt";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_CENSUS_CSV_EMPTY_FILE_PATH = "";

    @Test
    public void givenIndiaCensusData_returnsExactMapCount() {
        IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
        Map result = null;
        try {
             result = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,
                     INDIA_STATE_CODE_FILE_PATH);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(29,result.size());
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            IndiaCensusAdapter indiaCensusAdapter =  new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithCorrectFileButWrongExtension_ShouldThrowException() {
        IndiaCensusAdapter indiaCensusAdapter =  new IndiaCensusAdapter();
        try {
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH_INVALID_EXT);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIncorrectDelimiterInIndiaCensusData_ShouldThrowException() {
        IndiaCensusAdapter indiaCensusAdapter =  new IndiaCensusAdapter();
        try {
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_RELATED_TO_FILE, e.type);
        }
    }

    @Test
    public void givenIncorrectHeaderInIndiaCensusData_ShouldThrowException() {
        IndiaCensusAdapter indiaCensusAdapter =  new IndiaCensusAdapter();
        try {
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_RELATED_TO_FILE, e.type);
        }
    }

    @Test
    public void givenEmptyCsvFile_ShouldThrowException() {
        IndiaCensusAdapter indiaCensusAdapter =  new IndiaCensusAdapter();
        try {
            indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_EMPTY_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_RELATED_TO_FILE, e.type);
        }
    }

}
