
package risk;

import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;
import static risk.Main.g;


public class Country {
    static private Image troopCounter = Toolkit.getDefaultToolkit().getImage("./Troop Counter Mark II.png");
    static private Country currentCountry;
    ArrayList<Country> neighboringCountries = new ArrayList<Country>();
    Player owner;
    Polygon boundary;
    String name;
    boolean isSelected;
    int numTroops;
    private int centerX, centerY;
    
    Country(Polygon _boundry, String _name, int _centerX, int _centerY) {
        numTroops=0;
        isSelected=false;
        boundary = _boundry;
        name = _name;
        centerX = _centerX;
        centerY = _centerY;
    }
    
    static Country getCountry (String name){
        for(Country tempCountry : RiskMap.getCountryList()){
            if(tempCountry.name==name)
                return tempCountry;
        }
        System.out.println("Returning null");
        return null;
    }
    
    public void mouseInCountry() {
        drawBorders();
        if (currentCountry != this)
            isSelected = false;
        if (!isSelected)
            playSoundEffect();
        isSelected = true;
        currentCountry = this;
    }
    
    private void drawBorders() {
        g.drawPolygon(boundary);
    }
    static public void drawAllTroopCounter() {
        for (Country country : RiskMap.getCountryList()) {
            g.drawImage(troopCounter, country.centerX, country.centerY, 51, 51, Window.currentFrame);
        }
    }
    
    private void playSoundEffect() {
        int i = 0;
        if(isSelected) {
            i++;
            if(i>1)
                isSelected=false;
        }
        if(isSelected)
            g.drawPolygon(boundary);
        Titlescreen.getMenuSounds().play("terr_noise.wav");
    }
    
    public void setOwner(Player player) {
        owner = player;
    }
    
    public void drawSoldierCount(int soldiers){
        g.setColor(owner.getColor());
        g.drawString(""+soldiers,centerX, centerY);
    }
    
    public Polygon getBoundry()
    { return(boundary); }
    
    
}