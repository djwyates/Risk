
package risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private int totalTroops = 0;
    private int deployableTroops = 0;
    private Color color = null;
    
    Player() {
        color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
    }
    
    // Mutator methods
    static public void transferCountryOwnership(Player previousOwner, Player newOwner, Country country) {
        previousOwner.ownedCountries.remove(country);
        newOwner.ownedCountries.add(country);
        country.setOwner(newOwner);
    }
    
    public void addTerritory(Country country) {
        ownedCountries.add(country);
    }
    
    public void addTotalTroops(int inc) {
        totalTroops += inc;
    }
    
    public void setDeployableTroops() {
        deployableTroops = ownedCountries.size()/3; //todo: add continent bonuses to this integer
    }
    
    // Accessor methods
    public ArrayList<Country> getOwnedCountries() {
        return ownedCountries;
    }
    
    public int getTotalTroops() {
        return totalTroops;
    }
    
    public Color getColor() {
        return color;
    }
}