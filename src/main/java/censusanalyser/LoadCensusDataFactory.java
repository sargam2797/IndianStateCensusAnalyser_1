package censusanalyser;

import censusanalyser.CensusAnalyser.Country;

public class LoadCensusDataFactory {

    public static Object getCensusObject(Country country) {
        if (country.equals(country.US)) {
         return new USCensusAdapter();
        }
        return new IndiaCensusAdapter();
    }
}
