package censusanalyser;

import censusanalyser.CensusAnalyser.Country;

public class LoadCensusDataProvider {

    public static CensusAdapter getCensusObject(Country country) {
        if (country.equals(country.US)) {
         return new USCensusAdapter();
        }
        return new IndiaCensusAdapter();
    }
}
