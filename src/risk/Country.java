
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
    static private Country hovered;
    static private ArrayList<Country> selected = new ArrayList<Country>();
    private ArrayList<Country> neighboringCountries = new ArrayList<Country>();
    private Player owner;
    private Polygon boundary;
    private String name;
    private int numTroops;
    private int centerX, centerY;
    private boolean isSelected = false;
    
    Country(Polygon _boundry, String _name, int _centerX, int _centerY) {
        numTroops = 0;
        boundary = _boundry;
        name = _name;
        centerX = _centerX;
        centerY = _centerY;
    }
    
    static public void drawBoundaryOnSelected() {
        for (Country country : selected)
            country.drawBoundary();
    }
    
    static public void drawAllSoldierCounters() {
        for (Country country : RiskMap.getCountryList()) {
            g.drawImage(troopEncasementImage, country.centerX, country.centerY, 51, 51, Window.currentFrame);
            country.drawSoldierNumber(0,country.centerX,country.centerY); // 0 is hardcoded; add actual troop amount
        }
    }
    
    public void drawSoldierNumber(int soldiers, int x, int y) {
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
    
    private void drawBoundary() {
        g.setColor(Color.white);
        g.drawPolygon(boundary);
    }
    
    public void selectedHandler(Gameplay.Phase phase){
        switch (phase) {
            case DEPLOY:
                if (selected.contains(this))
                    selected.clear();
                else {
                    selected.clear();
                    selected.add(this);
                }
                break;
            case ATTACK:
                break;
            case FORTIFY:
                break;
        }
    }
    
    public void mouseInCountryHandler() {
        drawBoundary();
        if (hovered != this)
            Titlescreen.getMenuSounds().play("terr_noise.wav");
        hovered = this;
    }
    
    static public void setCountryOnMouse(int x, int y) {
        onMouse = RiskMap.contains(x, y);
    }
    
    static Country getCountry (String name) {
        for(Country country : RiskMap.getCountryList()) {
            if(country.name.equalsIgnoreCase(name))
                return country;
        }
        return null;
    }
    
    static public Country getCountryOnMouse() {
        return onMouse;
    }
    
    static public ArrayList<Country> getSelectedList() {
        return selected;
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