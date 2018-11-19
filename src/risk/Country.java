
package risk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;
import static risk.Risk.g;


public class Country {
    static private Image troopEncasementImage = Toolkit.getDefaultToolkit().getImage("./Troop Counter Mark II Final.png");    
    static private Country onMouse;
    static private Country selected;
    private ArrayList<Country> neighboringCountries = new ArrayList<Country>();
    private Player owner;
    private Polygon boundary;
    private String name;
    private int numTroops;
    private int centerX, centerY;
    private boolean isSelected = false;
    
    Country(Polygon _boundry, String _name, int _centerX, int _centerY) {
        numTroops=0;
        isSelected=false;
        boundary = _boundry;
        name = _name;
        centerX = _centerX;
        centerY = _centerY;
    }
    
    public void mouseInCountry() {
        drawBoundary();
        // Plays the sound effect only once
        if (selected != this)
            isSelected = false;
        if (!isSelected)
            playSoundEffect();
        isSelected = true;
        selected = this;
    }
    
    public void drawBoundary() {
        g.setColor(Color.white);
        g.drawPolygon(boundary);
    }
    
    static public void drawAllTroopCounters() {
        for (Country country : RiskMap.getCountryList()) {
            g.drawImage(troopEncasementImage, country.centerX, country.centerY, 51, 51, Window.currentFrame);
            country.drawSoldierCount(0,country.centerX,country.centerY); // 0 is hardcoded; add actual troop amount
        }
    }
    
    public void drawSoldierCount(int soldiers, int x, int y) {
        g.setColor(Color.magenta);
        if(owner != null)
            g.setColor(owner.getColor());
        
        g.setFont (new Font("AMARILLO",Font.BOLD,20));
        g.drawString(""+soldiers, x+19, y+33);
    }
    
    public void drawNameOnMouse(int x, int y) {
        g.setColor(owner.getColor());
        g.setFont (new Font("AMARILLO",Font.BOLD,15));
        g.drawString(Country.getCountryOnMouse().getName(), x, y-5);
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
    
    public void setOwner(Player player) {
        owner = player;
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