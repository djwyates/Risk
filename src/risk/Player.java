
package risk;

import java.util.ArrayList;

public class Player {
    static private Player[] players;
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private int numTroops = 0;
    private int numContinents = 0;
    
    Player() {
        
    }
    
    public void addTerritory(Country country) {
        ownedCountries.add(country);
    }
}
