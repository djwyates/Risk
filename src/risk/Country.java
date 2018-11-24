
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
    static private Country[] currentlySelected = new Country[2];// [0] is the owner's [1] is the enemy's
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
    
    Country(Polygon _boundry, String _name, int _centerX, int _centerY) {
        boundary = _boundry;
        name = _name;
        centerX = _centerX;
        centerY = _centerY;
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
                if (currentlySelected[0] != null) {
                    currentlySelected[0].drawBoundary(Color.white);
                    for (Country neighboringCountry : currentlySelected[0].neighboringCountries) {
                        if (neighboringCountry.owner != game.getCurrentPlayer())
                            neighboringCountry.drawBoundary(Color.red);
                    }
                }
                if (currentlySelected[1] != null)
                    currentlySelected[1].drawBoundary(new Color(124, 10, 2));
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
            System.out.println(name + " is null.");
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
                if (game.getCurrentPlayer() == owner) {
                    if (currentlySelected[0] == this) {
                        currentlySelected[0] = null;
                        currentlySelected[1] = null;
                    } else {
                        currentlySelected[0] = this;
                        currentlySelected[1] = null;
                    }
                } else {
                    if (currentlySelected[1] == this)
                        currentlySelected[1] = null;
                    else
                        currentlySelected[1] = this;
                }
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
    
    public boolean isNeighboringEnemy(Country otherCountry) {
        return neighboringCountries.contains(otherCountry) && otherCountry.owner != Titlescreen.getGame().getCurrentPlayer();
    }
}