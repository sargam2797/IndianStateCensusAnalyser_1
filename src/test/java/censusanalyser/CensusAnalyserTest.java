package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CODE_FILE_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test/" +
            "resources/IndiaStateCode.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test" +
            "/resources/USCensusData.csv";

    @Test
    public void givenIndianStateCensusData_whenSortedByState_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingField.Parameter.STATE);
            CensusDAO[] censusCSVS = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCensusData_whenSortedByHighestPopulationState_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingField.Parameter.POPULATION);
            CensusDAO[] censusCSVS = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSVS[28].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCensusData_whenSortedByHighestAreaInSqKm_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingField.Parameter.AREA);
            CensusDAO[] censusCSVS = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Rajasthan", censusCSVS[28].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCensusData_whenSortedByHighestDensity_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingField.Parameter.DENSITY);
            CensusDAO[] censusCSVS = new Gson().fromJson(sortedCensusData, CensusDAO[].class);
            Assert.assertEquals("Bihar", censusCSVS[28].state);
        } catch (CensusAnalyserException e) {
        }
    }

}
