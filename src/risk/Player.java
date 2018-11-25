
package risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private static ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private ArrayList<Continent> ownedContinents = new ArrayList<Continent>();
    private int totalTroops = 0;
    private int deployableTroops = 0;
    private Color color = null;
    
    
    Player() {
        players.add(this);
        for(Color c : Button.getPlayerColors()){
            if(c==null){
                color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
                break;
            }
            else if(Button.getPlayerColors().indexOf(c) == players.indexOf(this)){
                color = c;
                break;
            }
        }
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
    
    public void addDeployableTroops(int inc) {
        deployableTroops += inc;
    }
    
    private void checkOwnedCountries() {
        for (Country country : RiskMap.getCountryList()) {
            if (country.getOwner() == this)
                ownedCountries.add(country);
        }
    }
    
    private void checkOwnedContinents() {
        Continent[] continents = Continent.getContinents();
        ArrayList<Country> countries = new ArrayList<Country>();
        int totalCountryNum;
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
        checkOwnedCountries();
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
    
    public int getDeployableTroops() {
        return deployableTroops;
    }
    
    public Color getColor() {
        return color;
    }
}