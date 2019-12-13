package censusanalyser;

import com.google.gson.Gson;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    public enum Country {
        INDIA, US
    }

    private Country country;

    public CensusAnalyser(Country country) {
        this.country = country;
    }

    Map<String, CensusDAO> censusMap = null;

    public int loadCensusData(Country country, String... csvFilePath) throws CensusAnalyserException {
        CensusAdapter censusAdapter = LoadCensusDataFactory.getCensusObject(country);
        censusMap = censusAdapter.loadCensusData(country, csvFilePath);
        return censusMap.size();
    }

    public String sortByParameterCensusData(SortingFieldForCensusAnalyser.Parameter... parameter) throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw new CensusAnalyserException("no census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator=null;
        if (parameter.length == 2)
            censusComparator = new SortingFieldForCensusAnalyser()
                    .getParameter(parameter[0])
                    .thenComparing(new SortingFieldForCensusAnalyser()
                            .getParameter(parameter[1]));
            censusComparator = new SortingFieldForCensusAnalyser()
                    .getParameter(parameter[0]);
        ArrayList censusDTO = censusMap.values().stream()
                .sorted(censusComparator)
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortStateCensusToJson = new Gson().toJson(censusDTO);
        return sortStateCensusToJson;
        }

}

