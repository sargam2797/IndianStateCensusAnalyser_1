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

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        censusMap = new censusLoader().loadCensusData(csvFilePath,IndiaCensusCSV.class);
        return censusMap.size();
    }

    public int loadUsCensusData(String usCensusCsvEmptyFilePath) throws CensusAnalyserException {
        censusMap = new censusLoader().loadCensusData(usCensusCsvEmptyFilePath,USCensusCSV.class);
        return censusMap.size();
    }

    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSvBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<IndiaStateCodeCsv> stateCSVIterator = csvBuilder.getFileByIterator(reader,
                    IndiaStateCodeCsv.class);
            Iterable<IndiaStateCodeCsv> csvIterable = () -> stateCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .filter(csvState -> censusMap.get(csvState.stateName) != null)
                    .forEach(csvState -> censusMap.get(csvState.stateName).stateCode = csvState.stateCode);
            return censusMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
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

