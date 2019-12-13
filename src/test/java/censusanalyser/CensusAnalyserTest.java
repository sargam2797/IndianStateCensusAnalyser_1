package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

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
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,
                    INDIA_STATE_CODE_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingFieldForCensusAnalyser.Parameter.STATE);
            IndiaCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSVS[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianStateCensusData_whenSortedByHighestPopulationState_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,
                    INDIA_STATE_CODE_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingFieldForCensusAnalyser.Parameter.POPULATION);
            IndiaCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCensusData_whenSortedByHighestAreaInSqKm_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,
                    INDIA_STATE_CODE_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingFieldForCensusAnalyser.Parameter.AREA);
            IndiaCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Rajasthan", censusCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianStateCensusData_whenSortedByHighestDensity_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.INDIA);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,
                    INDIA_STATE_CODE_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingFieldForCensusAnalyser.Parameter.DENSITY);
            IndiaCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Bihar", censusCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSStateCensusData_whenSortedByState_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingFieldForCensusAnalyser.Parameter.STATE);
            USCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alabama", censusCSVS[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSStateCensusData_whenSortedByHighestPopulationState_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingFieldForCensusAnalyser.Parameter.POPULATION);
            USCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("California", censusCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSStateCensusData_whenSortedByHighestAreaInSqKm_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingFieldForCensusAnalyser.Parameter.AREA);
            USCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alaska", censusCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSStateCensusData_whenSortedByHighestDensity_ShouldReturnTrue() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser(CensusAnalyser.Country.US);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.sortByParameterCensusData(SortingFieldForCensusAnalyser.Parameter.DENSITY);
            USCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("District of Columbia", censusCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

}
