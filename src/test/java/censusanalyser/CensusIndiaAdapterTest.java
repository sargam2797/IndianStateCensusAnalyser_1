package censusanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class censusIndiaAdapterTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATE_CODE_FILE_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test/" +
            "resources/IndiaStateCode.csv";
    private static final String US_CENSUS_CSV_FILE_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test" +
            "/resources/USCensusData.csv";

    @Test
    public void givenIndiaCensusData_returnsExactMapCount() {
        IndiaCensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
        Map<String, CensusDAO> result = null;
        try {
            result = indiaCensusAdapter.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,
                    INDIA_STATE_CODE_FILE_PATH);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(29,result.size());
    }


}
