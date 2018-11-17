
package risk;

import java.awt.Polygon;
import static risk.Main.g;

public class Country {
    static private Country currentCountry;
    Player owner;
    Polygon boundary;
    String name;
    boolean isSelected;
    int numTroops;
    
    Country(Polygon _boundry, String _name) {
        numTroops=0;
        isSelected=false;
        boundary = _boundry;
        name = _name;
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
    
    public Polygon getBoundry()
    { return(boundary); }
    
}