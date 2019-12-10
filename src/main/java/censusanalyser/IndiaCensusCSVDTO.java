package censusanalyser;

public class IndiaCensusCSVDTO {

    public  String state;
    public  int population;
    public  int areaInSqKm;
    public  int densityPerSqKm;

    public IndiaCensusCSVDTO(IndiaCensusCSV csvIterator) {
        state=csvIterator.state;
        population=csvIterator.population;
        areaInSqKm=csvIterator.areaInSqKm;
        densityPerSqKm=csvIterator.densityPerSqKm;
    }
}
