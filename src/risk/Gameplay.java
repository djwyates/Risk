
package risk;

public class Gameplay {
    
    static public void assignCountries() {
        for (Country country : RiskMap.getCountryList()) {
            // loop through 0-69
                // choose random # between 0-1
                // if 0 && assignedCountries < 35, assign country to player & increment assignedCountries
                // else assign country to NPC
        }
    }
}
