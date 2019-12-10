package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSvBuilder<T> {
    public Iterator<T> loadParticularCsvFile(Reader reader, Class csvClass) throws CsvBuilderException;
    public List<T> loadParticularCsvFileByList(Reader reader, Class csvClass) throws CsvBuilderException, IOException;
}
