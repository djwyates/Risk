
package risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private int totalTroops;
    Color color;
    
    Player() {
        totalTroops = 50;
        color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
    }
    
    public void addTerritory(Country country) {ownedCountries.add(country);}
    
    static void transferCountry(Player loser,Player winner,Country country) {
        for(Country tempC : loser.ownedCountries) {
            if(tempC == country) {
                winner.ownedCountries.add(country);
                loser.ownedCountries.remove(country);
            }
        }
    }
}
