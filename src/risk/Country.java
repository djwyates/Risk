
package risk;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import static risk.Main.g;

public class Country {
    static private Country onMouse;
    static private Country selected;
    private ArrayList<Country> neighboringCountries = new ArrayList<Country>();
    private Player owner;
    private Polygon boundary;
    private String name;
    private int numTroops;
    private int centerX, centerY;
    private boolean isSelected = false;
    
    Country(Polygon _boundry, String _name) {
        numTroops=0;
        isSelected=false;
        boundary = _boundry;
        name = _name;
    }
    
    public void setOwner(Player player) {
        owner = player;
    }
    
    public void mouseInCountry() {
        drawBoundary();
        if (selected != this) {
            isSelected = false;
        }
        if (!isSelected)
            playSoundEffect();
        isSelected = true;
        selected = this;
    }
    
    public void drawBoundary() {
        g.drawPolygon(boundary);
    }
    
    public void drawSoldierCount(int soldierCount) {
        g.setColor(owner.getColor());
        g.drawString("" + soldierCount, centerX, centerY);
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
    
    static Country getCountry (String name) {
        for(Country country : RiskMap.getCountryList()) {
            if(country.name.equalsIgnoreCase(name))
                return country;
        }
        return null;
    }
    
    static public void setCountryOnMouse(int x, int y) {
        onMouse = RiskMap.contains(x, y);
    }
    
    static public Country getCountryOnMouse() {
        return onMouse;
    }
    
    public Player getOwner() {
        return owner;
    }
    
    public Polygon getBoundary() {
        return(boundary);
    }
    
    public String getName() {
        return name;
    }
}