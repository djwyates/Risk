
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
    static private Country[] currentlySelected = new Country[2];
    static private Country onMouse;
    static private Country recentlyHovered;
    // instance variables
    private String name;
    private Polygon boundary;
    private int centerX, centerY;
    private ArrayList<Country> neighboringCountries = new ArrayList<Country>();
    private Player owner;
    private int numTroops = 0;
    private boolean shouldHover = false;
    
    Country(Polygon _boundry, String _name, int _centerX, int _centerY/*, ArrayList<Country> _neighboringCountries*/) { //todo: add arraylist of neighboring countries to constructor
        boundary = _boundry;
        name = _name;
        centerX = _centerX;
        centerY = _centerY;
        //neighboringCountries.addAll(_neighboringCountries);
    }
    
    // Draw methods
    static public void drawBoundaryOnSelectedHandler(Gameplay.Phase phase) {
        Gameplay game = Titlescreen.getGame();
        switch (phase) {
            case DEPLOY:
                for (Country country : currentlySelected) {
                    if (country != null)
                        country.drawBoundary(Color.white);
                }
                break;
            case ATTACK:
                for (Country country : currentlySelected) {
                    if (country == null)
                        continue;
                    country.drawBoundary(Color.white);
                    for (Country neighboringCountry : country.neighboringCountries) {
                        if (neighboringCountry.owner != game.getCurrentPlayer())
                            neighboringCountry.drawBoundary(Color.red);
                    }
                }
                break;
            case FORTIFY:
                break;
        }
    }
    
    static public void drawAllTroopCounters() {
        for (Country country : RiskMap.getCountryList()) {
            g.drawImage(troopEncasementImage, country.centerX, country.centerY, 51, 51, Window.currentFrame);
            country.drawTroopAmount(country.centerX,country.centerY);
        }
    }
    
    public void drawTroopAmount(int x, int y) {
        if(owner != null)
            g.setColor(owner.getColor());
        g.setFont (new Font("AMARILLO",Font.BOLD,20));
        g.drawString(""+numTroops, x+19, y+33);
    }
    
    public void drawNameOnMouse(int x, int y) {
        if (owner == null)
            System.out.println(name);
        g.setColor(owner.getColor());
        g.setFont (new Font("AMARILLO",Font.BOLD,15));
        g.drawString(Country.getCountryOnMouse().getName(), x, y-5);
    }
    
    private void drawBoundary(Color color) {
        g.setColor(color);
        g.drawPolygon(boundary);
    }
    
    // Handler methods
    public void mouseInCountryHandler(Gameplay.Phase phase) {
        selectedByHoverHandler(phase);
        if (shouldHover)
            drawBoundary(Color.white);
        if (shouldHover && recentlyHovered != this)
            Titlescreen.getMenuSounds().play("terr_noise.wav");
        recentlyHovered = this;
    }
    
    private void selectedByHoverHandler(Gameplay.Phase phase) {
        Gameplay game = Titlescreen.getGame();
        switch (phase) {
            case DEPLOY:
                if (owner == game.getCurrentPlayer())
                    shouldHover = true;
                break;
            case ATTACK:
                if (owner == game.getCurrentPlayer())
                    shouldHover = true;
                break;
            case FORTIFY:
                break;
        }
    }
    
    public void selectedByClickHandler(Gameplay.Phase phase) {
        Gameplay game = Titlescreen.getGame();
        switch (phase) {
            case DEPLOY:
                if (currentlySelected[0] == this)
                    currentlySelected[0] = null;
                else
                    currentlySelected[0] = this;
                break;
            case ATTACK:
                if (currentlySelected[0] == this)
                    currentlySelected[0] = null;
                else
                    currentlySelected[0] = this;
                break;
            case FORTIFY:
                break;
        }
    }
    
    static public void switchedTurnHandler(Gameplay.Phase phase) {
        Gameplay game = Titlescreen.getGame();
        switch (phase) {
            case DEPLOY:
                for (Country country : RiskMap.getCountryList())
                    country.shouldHover = false;
                currentlySelected[0] = null;
                break;
            case ATTACK:
                for (Country country : RiskMap.getCountryList())
                    country.shouldHover = false;
                currentlySelected[0] = null;
                break;
            case FORTIFY:
                break;
        }
    }
    
    // Mutator methods
    static public void setCountryOnMouse(int x, int y) {
        onMouse = RiskMap.contains(x, y);
    }
    
    public void addNeighboringCountry(Country neighboringCountry) {
        neighboringCountries.add(neighboringCountry);
    }
    
    public void setOwner(Player _owner) {
        owner = _owner;
    }
    
    public void addNumTroops(int inc) {
        numTroops += inc;
    }
    
    // Accessor methods
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
    
    static public Country[] getSelectedList() {
        return currentlySelected;
    }
    
    public ArrayList<Country> getNeighboringCountries() {
        return neighboringCountries;
    }
    
    public Player getOwner() {
        return owner;
    }
    
    public Polygon getBoundary() {
        return boundary;
    }
    
    public String getName() {
        return name;
    }
}