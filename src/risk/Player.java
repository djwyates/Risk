
package risk;

import java.util.ArrayList;

public class Player {
    static private Player[] players;
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private int totalTroops;
    private int troopsPerTurn;
    
    Player() {
        totalTroops=50;
        troopsPerTurn=3;
    }
    
    public void addTerritory(Country country) {ownedCountries.add(country);}
    
    static void transferCountry(Player loser,Player winner,Country country){
        for(Country tempC : loser.ownedCountries){
            if(tempC == country){
                winner.ownedCountries.add(country);
                loser.ownedCountries.remove(country);
            }
        }
    }
    
    static void addTroopsPerTurn(Player player,int numExtra){
        player.troopsPerTurn+=numExtra;
    }
}
