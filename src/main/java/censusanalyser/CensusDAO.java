package censusanalyser;

public class CensusDAO {

    public String state;
    public String stateCode;
    public int population;
    public double populationDensity;
    public double totalArea;


    public CensusDAO(IndiaCensusCSV csvFileIterator) {
        state = csvFileIterator.state;
        totalArea =csvFileIterator.areaInSqKm;
        populationDensity =csvFileIterator.densityPerSqKm;
        population=csvFileIterator.population;
    }

    public CensusDAO(USCensusCSV censusCSV) {
        state = censusCSV.state;
        stateCode = censusCSV.stateId;
        population = censusCSV.population;
        populationDensity = censusCSV.populationDensity;
        totalArea=censusCSV.totalArea;
    }

    @Override
    public String toString() {
        return "IndiaCensusDAO{" +
                "population=" + population +
                ", densityPerSqKm=" + populationDensity +
                ", areaInSqKm=" + totalArea +
                ", state='" + state + '\'' +
                '}';
    }}
