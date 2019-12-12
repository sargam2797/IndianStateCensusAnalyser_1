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
        sortByFields.put(Parameter.DENSITY, Comparator.<IndiaCensusDAO,Integer>comparing(census ->
                census.densityPerSqKm));
        sortByFields.put(Parameter.STATE, Comparator.<IndiaCensusDAO,String>comparing(census ->
                census.state)) ;
        sortByFields.put(Parameter.POPULATION, Comparator.<IndiaCensusDAO,Integer>comparing(census ->
                census.population));
        sortByFields.put(Parameter.AREA, Comparator.<IndiaCensusDAO,Integer>comparing(census ->
                census.areaInSqKm));
        Comparator<IndiaCensusDAO> comparator = sortByFields.get(fields);
        return comparator;
    }

}
