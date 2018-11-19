
package risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private int totalTroops;
    private int troopsPerTurn;
    private Color color;
    
    Player() {
        totalTroops=50;
        troopsPerTurn=3;
        totalTroops = 50;
        color = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
    }
    
    public void addTerritory(Country country) {
        ownedCountries.add(country);
    }
    
    static public void transferCountry(Player loser,Player winner,Country country) {
        for(Country tempC : loser.ownedCountries) {
            if(tempC == country) {
                winner.ownedCountries.add(country);
                loser.ownedCountries.remove(country);
            }
        }
    }
    
    static public void addTroopsPerTurn(Player player,int numExtra){
        player.troopsPerTurn+=numExtra;
    }
    
    public Color getColor() {
        return color;
    }
}
