package censusanalyser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class commonCSV<T> implements ICSvBuilder {
    private CSVParser getCsvBean(Reader reader, Class csvClass)  {
        try (CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());){
            return csvParser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterator loadParticularCsvFile(Reader reader, Class csvClass) throws CsvBuilderException {
        return this.getCsvBean(reader,csvClass).iterator();
    }

    @Override
    public List loadParticularCsvFileByList(Reader reader, Class csvClass) throws CsvBuilderException, IOException {
        return this.getCsvBean(reader, csvClass).getRecords();
    }
}
