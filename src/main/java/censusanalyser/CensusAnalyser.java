package censusanalyser;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<IndiaCensusCSVDAO> censusList =null;

    public CensusAnalyser() {
        this.censusList = new ArrayList<IndiaCensusCSVDAO>();
    }

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
          ;  ICSvBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.loadParticularCsvFile(reader,
                    IndiaCensusCSV.class);
            while (censusCSVIterator.hasNext()) {
                this.censusList.add(new IndiaCensusCSVDAO(censusCSVIterator.next()));
            }
            return censusList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CsvBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.DELIMITER_OR_HEADER_PROBLEM);
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
        if (censusList == null || censusList.size() == 0) {
            throw  new CensusAnalyserException("no census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSVDAO> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortStateCensusToJson = new Gson().toJson(censusList);
        return sortStateCensusToJson;
    }

    private void sort(Comparator<IndiaCensusCSVDAO> censusComparator) {
        for (int i = 0; i < censusList.size() - 1; i++) {
            for (int j = 0; j < censusList.size() - i - 1; j++) {
                IndiaCensusCSVDAO tempObj1 = censusList.get(j);
                IndiaCensusCSVDAO tempObj2 = censusList.get(j + 1);
                if ( censusComparator.compare(tempObj1, tempObj2) > 0) {
                    censusList.set(j, tempObj2);
                    censusList.set(j + 1, tempObj1);
                }
            }
        }
    }

}

