package censusanalyser;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<IndiaCensusCSV> censusCSVList =null;
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSvBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            censusCSVList = csvBuilder.loadParticularCsvFileByList(reader,
                    IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSvBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.loadParticularCsvFile(reader,
                    IndiaStateCodeCsv.class);
            return (this.getCount(censusCSVIterator));
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    public <T> int getCount(Iterator<T> censusCSVIterator) {
        Iterable<T> csvIterable = () -> censusCSVIterator;
        int count = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return count;
    }

    public String sortByStateCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0) {
            throw  new CensusAnalyserException("no census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusCSVList,censusComparator);
        String sortStateCensusToJson = new Gson().toJson(censusCSVList);
        return sortStateCensusToJson;
    }

    private void sort(List<IndiaCensusCSV> censusCSVList, Comparator<IndiaCensusCSV> censusComparator) {
        for (int i = 0; i < censusCSVList.size() - 1; i++) {
            for (int j = 0; j < censusCSVList.size() - i - 1; j++) {
                IndiaCensusCSV tempObj1 = censusCSVList.get(j);
                IndiaCensusCSV tempObj2 = censusCSVList.get(j + 1);
                if ( censusComparator.compare(tempObj1, tempObj2) > 0) {
                    censusCSVList.set(j, tempObj2);
                    censusCSVList.set(j + 1, tempObj1);
                }
            }
        }
    }

}

