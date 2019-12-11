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
    Map<String,IndiaCensusDAO> censusMap =null;

    public CensusAnalyser() {
        this.censusMap = new HashMap<>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ;
            ICSvBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getFileByIterator(reader,
                    IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .forEach(indiaCensusCSV -> censusMap.put(indiaCensusCSV.state, new IndiaCensusDAO(indiaCensusCSV)));
            return censusMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.ISSUE_RELATED_TO_FILE);
        }
    }

    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSvBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<IndiaStateCodeCsv> stateCSVIterator = csvBuilder.getFileByIterator(reader,
                    IndiaStateCodeCsv.class);
            int count = 0;
           while (stateCSVIterator.hasNext()) {
               count++;
               IndiaStateCodeCsv stateCodeCsv = stateCSVIterator.next();
               IndiaCensusDAO censusDAO =  censusMap.get(stateCodeCsv.stateName);
               if (censusDAO == null)
                   continue;
               censusDAO.stateCode = stateCodeCsv.stateCode;
           }
            return count;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public String sortByParameterCensusData(SortingField.Parameter parameter) throws CensusAnalyserException {
        exception();
        Comparator<IndiaCensusDAO> censusComparator = SortingField.getParameter(parameter);
        List<IndiaCensusDAO> censusDAOS = censusMap.values().stream().collect(Collectors.toList());
        this.sort(censusDAOS,censusComparator);
        String sortStateCensusToJson = new Gson().toJson(censusDAOS);
        return sortStateCensusToJson;
    }

    private void exception() throws CensusAnalyserException {
        if (censusMap == null || censusMap.size() == 0) {
            throw  new CensusAnalyserException("no census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
    }

    private void sort(List<IndiaCensusDAO> censusDAOS, Comparator<IndiaCensusDAO> censusComparator) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                IndiaCensusDAO tempObj1 = censusDAOS.get(j);
                IndiaCensusDAO tempObj2 = censusDAOS.get(j + 1);
                if ( censusComparator.compare(tempObj1, tempObj2) > 0) {
                    censusDAOS.set(j, tempObj2);
                    censusDAOS.set(j + 1, tempObj1);
                }
            }
        }
    }

}

