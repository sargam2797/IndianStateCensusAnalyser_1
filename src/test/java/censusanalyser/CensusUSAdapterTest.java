package censusanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CensusUSAdapterTest {
    private static final String US_CENSUS_CSV_FILE_PATH = "/home/user/CensusAnalyser/CensusAnalyser/src/test" +
            "/resources/USCensusData.csv";

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

}
