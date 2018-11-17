
package risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private int numTroops = 0;
    private int numContinents = 0;
    private Color color = null;
    
    Player() {
        numTroops = 50;
        color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
    }
    
    public void addTerritory(Country country) {
        ownedCountries.add(country);
    }
}
