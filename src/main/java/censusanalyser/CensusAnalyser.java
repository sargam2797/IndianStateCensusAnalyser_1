package censusanalyser;

import com.CSVBuilderFactory;
import com.CsvBuilderException;
import com.ICSvBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    Map<String,CensusDAO> censusMap =null;

    public CensusAnalyser() { }

    public int loadIndiaCensusData(String... csvFilePath) throws CensusAnalyserException {
        censusMap = new censusLoader().loadCensusData(IndiaCensusCSV.class,csvFilePath);
        return censusMap.size();
    }

    public int loadUsCensusData(String... usCensusCsvFilePath) throws CensusAnalyserException {
        censusMap = new censusLoader().loadCensusData(USCensusCSV.class, usCensusCsvFilePath);
        return censusMap.size();
    }

    public String sortByParameterCensusData(SortingField.Parameter parameter) throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw  new CensusAnalyserException("no census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<CensusDAO> censusComparator = SortingField.getParameter(parameter);
        List<CensusDAO> censusDAOS = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS,censusComparator);
        String sortStateCensusToJson = new Gson().toJson(censusDAOS);
        return sortStateCensusToJson;
    }

    private void sort(List<CensusDAO> censusDAOS, Comparator<CensusDAO> censusComparator) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                CensusDAO tempObj1 = censusDAOS.get(j);
                CensusDAO tempObj2 = censusDAOS.get(j + 1);
                if ( censusComparator.compare(tempObj1, tempObj2) > 0) {
                    censusDAOS.set(j, tempObj2);
                    censusDAOS.set(j + 1, tempObj1);
                }
            }
        }
    }

}

