package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

    @CsvBindByName(column = "State Id", required = true)
    public String stateId;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Population", required = true)
    public int population;

    @CsvBindByName(column = "Population Density", required = true)
    public double populationDensity;

    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;

    public USCensusCSV() {
    }

    public USCensusCSV(String stateId, String state, int population, double populationDensity, double totalArea) {
        this.stateId = stateId;
        this.state = state;
        this.population = population;
        this.populationDensity = populationDensity;
        this.totalArea = totalArea;
    }
}
