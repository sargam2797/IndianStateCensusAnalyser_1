package censusanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortingFieldForCensusAnalyser {
    public Map<Parameter, Comparator> sortByFields = new HashMap<>();

    public enum Parameter {
        STATE, POPULATION, AREA, DENSITY;
    }

    public SortingFieldForCensusAnalyser() {
        sortByFields.put(Parameter.DENSITY, Comparator.<CensusDAO,Double>comparing(census ->
                census.populationDensity,Comparator.reverseOrder()));
        sortByFields.put(Parameter.STATE, Comparator.<CensusDAO,String>comparing(census ->
                census.state)) ;
        sortByFields.put(Parameter.POPULATION, Comparator.<CensusDAO,Integer>comparing(census ->
                census.population,Comparator.reverseOrder()));
        sortByFields.put(Parameter.AREA, Comparator.<CensusDAO,Double>comparing(census ->
                census.totalArea,Comparator.reverseOrder()));
    }

    public Comparator getParameter(Parameter fields) {
        Comparator<CensusDAO> comparator = sortByFields.get(fields);
        return comparator;
    }

}
