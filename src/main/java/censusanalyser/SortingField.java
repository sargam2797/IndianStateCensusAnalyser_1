package censusanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortingField {
    static Map<Parameter, Comparator> sortByFields = new HashMap<>();

    enum Parameter {
        STATE, POPULATION, AREA, DENSITY;
    }

    public static Comparator getParameter(Parameter fields) {
        sortByFields.put(Parameter.DENSITY, Comparator.<CensusDAO,Double>comparing(census ->
                census.populationDensity));
        sortByFields.put(Parameter.STATE, Comparator.<CensusDAO,String>comparing(census ->
                census.state)) ;
        sortByFields.put(Parameter.POPULATION, Comparator.<CensusDAO,Integer>comparing(census ->
                census.population));
        sortByFields.put(Parameter.AREA, Comparator.<CensusDAO,Double>comparing(census ->
                census.totalArea));
        Comparator<CensusDAO> comparator = sortByFields.get(fields);
        return comparator;
    }

}
