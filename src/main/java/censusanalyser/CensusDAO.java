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
        stateCode = censusCSV.stateId;
        state = censusCSV.state;
        population = censusCSV.population;
        populationDensity = censusCSV.populationDensity;
        totalArea=censusCSV.totalArea;
    }

    public Object getCensusDTO(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.US))
            return new USCensusCSV(stateCode,state,population,populationDensity,totalArea);
        return new IndiaCensusCSV(state,(int)totalArea,(int)populationDensity,population);
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
