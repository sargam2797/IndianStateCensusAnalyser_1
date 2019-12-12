package censusanalyser;

import com.CSVBuilderFactory;
import com.CsvBuilderException;
import com.ICSvBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class censusLoader {

    public  <T> Map loadCensusData(String filepath, Class<T> censusCsvClass ) throws CensusAnalyserException {
        Map<String,CensusDAO> censusMap =null;
        try (Reader reader = Files.newBufferedReader(Paths.get(filepath))) {
            ICSvBuilder csvBuilder = CSVBuilderFactory.createCsvBuilder();
            Iterator<T> censusCSVIterator = csvBuilder.getFileByIterator(reader,
                    censusCsvClass);
            Iterable<T> csvIterable = () -> censusCSVIterator;
            if (censusCsvClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(),false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            }
            if (censusCsvClass.getName().equals("censusanalyser.USCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(),false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            }
            return censusMap;
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

}
