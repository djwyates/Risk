
package risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private ArrayList<Continent> ownedContinents = new ArrayList<Continent>();
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
        deployableTroops = ownedCountries.size()/3 + getContinentsTroopValue();
    }
    
    private void checkOwnedContinents() {
        Continent[] continents = Continent.getContinents();
        ArrayList<Country> countries = new ArrayList<Country>();
        int totalCountryNum = 0;
        for (int i=0;i<continents.length;i++) {
            countries = continents[i].getCountries();
            totalCountryNum = countries.size();
            for (int a=0;a<totalCountryNum;a++) {
                if (countries.get(a).getOwner() != this)
                    break;
                else if (a == totalCountryNum)
                    ownedContinents.add(continents[i]);
            }
        }
    }
    
    // Accessor methods
    private int getContinentsTroopValue() {
        checkOwnedContinents();
        int value = 0;
        for (Continent continent : ownedContinents) {
            value += continent.getBonus();
        }
        return value;
    }
    
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