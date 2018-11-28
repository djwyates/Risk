
package risk;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static Color blank = new Color(0,0,0,0);
    private ArrayList<Country> ownedCountries = new ArrayList<Country>();
    private ArrayList<Continent> ownedContinents = new ArrayList<Continent>();
    private int totalTroops;
    private int deployableTroops;
    private Color color;
    
    
    Player() {
        players.add(this);
        for(Color c : Button.getPlayerColors()){
            if(c==blank && Button.getPlayerColors().indexOf(c) == players.indexOf(this)){
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
    public void addCountry(Country country) {
        ownedCountries.add(country);
        country.getOwner().ownedCountries.remove(country);
        country.setOwner(this);
    }
    
    public void addTerritory(Country country) {
        ownedCountries.add(country);
    }
    
    public void addTotalTroops(int inc) {
        totalTroops += inc;
    }
    
    public void setDeployableTroops() {
        deployableTroops = ownedCountries.size()/3 + getContinentsTroopValue();
        if (deployableTroops < 3)
            deployableTroops = 3;
//        if(ownedCountries.size()/3 > 3)
//            deployableTroops = ownedCountries.size()/3 + getContinentsTroopValue();
//        else
//            deployableTroops = 3 + getContinentsTroopValue();
    }
    
    public void addDeployableTroops(int inc) {
        deployableTroops += inc;
    }
    
    private void refreshOwnedCountries() {
        ownedCountries.clear();
        for (Country country : RiskMap.getCountryList()) {
            if (country.getOwner() == this)
                ownedCountries.add(country);
        }
    }
    
    private void checkOwnedContinents() {
        ownedContinents.clear();
        for (int i=0;i<Continent.getContinents().length;i++) {
            for (int a=0;a<Continent.getContinents()[i].getCountries().size();a++) {
                if (Continent.getContinents()[i].getCountries().get(a).getOwner() != this)
                    break;
                else if (a == Continent.getContinents()[i].getCountries().size()-1)
                    ownedContinents.add(Continent.getContinents()[i]);
            }
        }
    }
    
    // Accessor methods
    public void checkWin() {
        for (Country country : ownedCountries) {
            if (country.getOwner() != this)
                return;
        }
//        System.out.println("A PLAYER HAS WON!");
//        Titlescreen.getGame().victoryAchieved = true;
//        Window.changeWindow(Window.currentFrame, 1370, 912, "Victory!");
    }
    
    private int getContinentsTroopValue() {
        refreshOwnedCountries();
        checkOwnedContinents();
        int value = 0;
        for (Continent continent : ownedContinents)
            value += continent.getBonus();
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
    
    static Color getBlankColor(){
        return blank;
    }
}