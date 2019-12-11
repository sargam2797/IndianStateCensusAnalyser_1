package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCsv {
    @CsvBindByName(column = "srNo", required = true)
    public String srNo;

    @CsvBindByName(column = "State Name", required = true)
    public String stateName;

    @CsvBindByName(column = "TIN", required = true)
    public String TIN;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    @Override
    public String toString() {
        return "IndiaStateCodeCsv{" +
                "srNo='" + srNo + '\'' +
                ", stateName='" + stateName + '\'' +
                ", TIN='" + TIN + '\'' +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}
