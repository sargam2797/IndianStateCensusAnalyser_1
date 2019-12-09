package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            Iterator<IndiaCensusCSV> censusCSVIterator = this.loadParticularCsvFile(reader,IndiaCensusCSV.class);
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
            return (this.getCount(censusCSVIterator));
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            Iterator<IndiaCensusCSV> censusCSVIterator = this.loadParticularCsvFile(reader,IndiaStateCodeCsv.class);
            Iterable<IndiaCensusCSV> csvIterable = () -> censusCSVIterator;
           return (this.getCount(censusCSVIterator));
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
    public <T> Iterator<T> loadParticularCsvFile(Reader reader, Class csvClass) throws CensusAnalyserException {
        try {
            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<T> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
        } catch (IllegalStateException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
    public <T> int getCount(Iterator<T> censusCSVIterator) {
        Iterable<T> csvIterable = () -> censusCSVIterator;
        int count = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
        return count;
    }
}
