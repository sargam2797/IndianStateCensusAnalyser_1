package censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCsvBuilder<T> implements ICSvBuilder{
    public Iterator<T> loadParticularCsvFile(Reader reader, Class csvClass) throws CsvBuilderException {
        return this.getCsvBean(reader,csvClass).iterator();
    }

    @Override
    public List loadParticularCsvFileByList(Reader reader, Class csvClass) throws CsvBuilderException {
        return this.getCsvBean(reader,csvClass).parse();
    }

    private CsvToBean<T> getCsvBean(Reader reader, Class csvClass) throws CsvBuilderException {
        try {
            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CsvBuilderException(e.getMessage(),
                    CsvBuilderException.ExceptionType.UNABLE_TO_PARSE);

        }
    }
}
