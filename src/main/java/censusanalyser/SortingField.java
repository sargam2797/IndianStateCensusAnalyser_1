package censusanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortingField {
    static Map<SortingField.Parameter, Comparator> sortParameterComparator = new HashMap<>();

    enum Parameter {
        STATE, POPULATION, AREA, DENSITY;
    }

    public static Comparator getParameter(SortingField.Parameter parameter) {
        sortParameterComparator.put(Parameter.DENSITY, Comparator.<IndiaCensusDAO,Integer>comparing(census ->
                census.densityPerSqKm));
        sortParameterComparator.put(Parameter.STATE, Comparator.<IndiaCensusDAO,String>comparing(census ->
                census.state)) ;
        sortParameterComparator.put(Parameter.POPULATION, Comparator.<IndiaCensusDAO,Integer>comparing(census ->
                census.population));
        sortParameterComparator.put(Parameter.AREA, Comparator.<IndiaCensusDAO,Integer>comparing(census ->
                census.areaInSqKm));
        Comparator<IndiaCensusDAO> comparator = sortParameterComparator.get(parameter);
        return comparator;
    }

}
