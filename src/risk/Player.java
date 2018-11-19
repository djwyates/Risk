
package risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private int totalTroops = 0;
    private int deployableTroops = 0;
    private Color color = null;
    
    Player() {
        totalTroops = 50;
        color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
    }
    
    static public void transferCountryOwnership(Player previousOwner, Player newOwner, Country country) {
        previousOwner.ownedCountries.remove(country);
        newOwner.ownedCountries.add(country);
    }
    
    public void addTerritory(Country country) {
        ownedCountries.add(country);
    }
    
    public void setDeployableTroops() {
        deployableTroops = ownedCountries.size()/3;
    }
    
    public Color getColor() {
        return color;
    }
}