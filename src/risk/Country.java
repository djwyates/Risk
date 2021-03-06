
package risk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;
import static risk.Risk.g;


public class Country {
    private Image troopEncasementImage = Toolkit.getDefaultToolkit().getImage("./images/troopCounter.png");
    static private Country[] currentlySelected = new Country[2];// [0] is the owner's [1] is the enemy's // change this to encompass more than two players!
    static private Country onMouse;
    static private Country recentlyHovered;
    // instance variables
    private final String NAME;
    private final Polygon BOUNDARY;
    private final int CENTER_X, CENTER_Y;
    private ArrayList<Country> neighboringCountries = new ArrayList<Country>();
    private Player owner;
    private int numTroops = 0;
    private boolean shouldHover = false;

    Country(Polygon _boundry, String _name, int _centerX, int _centerY) {
        BOUNDARY = _boundry;
        NAME = _name;
        CENTER_X = _centerX;
        CENTER_Y = _centerY;
    }

    // Draw methods
    static public void drawBoundaryOnSelected(Gameplay.Phase phase) {
        switch (phase) {
            case DEPLOY:
                for (Country country : currentlySelected) {
                    if (country != null){
                        country.drawBoundary(Color.white);
                    }
                }
                break;
            case ATTACK:
                if (currentlySelected[0] != null) {
                    currentlySelected[0].drawBoundary(Color.white);
                    for (Country neighboringCountry : currentlySelected[0].neighboringCountries) {
                        if (neighboringCountry.owner != Titlescreen.getGame().getCurrentPlayer()){
                            neighboringCountry.drawBoundary(Color.red);
                        }
                    }
                }
                if (currentlySelected[1] != null) {
                    currentlySelected[1].drawBoundary(new Color(255, 124, 0));
                }
                break;
            case FORTIFY:
                if (currentlySelected[0] != null)
                    currentlySelected[0].drawBoundary(Color.white);
                if (currentlySelected[1] != null)
                    currentlySelected[1].drawBoundary(Color.blue);
                break;
        }
    }

    static public void drawAllTroopCounters() {
        for (Country country : RiskMap.getCountryList()) {
            g.drawImage(country.troopEncasementImage, country.CENTER_X, country.CENTER_Y, 51, 51, Window.currentFrame);
            country.drawTroopAmount(country.CENTER_X,country.CENTER_Y);
        }
    }

    public void drawTroopAmount(int x, int y) {
        g.setColor(owner.getColor());
        g.setFont (new Font("AMARILLO",Font.BOLD,20));
        if(numTroops<10)
        g.drawString(""+numTroops, x+19, y+33);
        else
        g.drawString(""+numTroops, x+14, y+33);
    }

    public static void resetAllTroopCounters(){
        for(Country country : RiskMap.getCountryList())
            country.troopEncasementImage = Toolkit.getDefaultToolkit().getImage("./images/troopCounter.png");
    }

    public void drawNameOnMouse(int x, int y) {
        if (owner == null)
            System.out.println(NAME + " is null.");
        g.setColor(owner.getColor());
        g.setFont (new Font("AMARILLO",Font.BOLD,15));
        g.drawString(Country.getCountryOnMouse().getName(), x, y-5);
    }

    private void drawBoundary(Color color) {
        g.setColor(color);
        g.drawPolygon(BOUNDARY);
    }

    // Handler methods
    public void mouseInCountryHandler(Gameplay.Phase phase) {
        selectedByHoverHandler(phase);
        if (shouldHover) {
            switch (phase) {
                case DEPLOY:
                    drawBoundary(Color.white);
                    break;
                case ATTACK:
                    drawBoundary(Color.white);
                    break;
                case FORTIFY:
                    if (currentlySelected[0] != null && this != currentlySelected[0])
                        drawBoundary(Color.blue);
                    else
                        drawBoundary(Color.white);
            }
            if (recentlyHovered != this)
                Titlescreen.getMenuSounds().play("./sounds/tap.wav");
        }
        recentlyHovered = this;
    }

    private void selectedByHoverHandler(Gameplay.Phase phase) {
        switch (phase) {
            case DEPLOY:
                if (owner == Titlescreen.getGame().getCurrentPlayer())
                    shouldHover = true;
                else
                    shouldHover = false;
                break;
            case ATTACK:
                if (owner == Titlescreen.getGame().getCurrentPlayer() && numTroops > 1)
                    shouldHover = true;
                else
                    shouldHover = false;
                break;
            case FORTIFY:
                if (owner == Titlescreen.getGame().getCurrentPlayer() && (currentlySelected[0] != null || (currentlySelected[0] == null && numTroops > 1)))
                    shouldHover = true;
                else
                    shouldHover = false;
                break;
        }
    }

    public void selectedByClickHandler(Gameplay.Phase phase) {
        switch (phase) {
            case DEPLOY:
                if (Titlescreen.getGame().getCurrentPlayer() != owner)
                    return;
                if (currentlySelected[0] == this)
                    currentlySelected[0] = null;
                else
                    currentlySelected[0] = this;
                break;
            case ATTACK:
                if (currentlySelected[1] != null)
                    currentlySelected[1].troopEncasementImage = Toolkit.getDefaultToolkit().getImage("./images/troopCounter.png");
                if (Titlescreen.getGame().getCurrentPlayer() == owner) {
                    if (currentlySelected[0] == this) { //deselects your country
                        currentlySelected[0] = null;
                        currentlySelected[1] = null;
                    } else if (numTroops > 1) { //selects your country
                        currentlySelected[0] = this;
                        currentlySelected[1] = null;
                    }
                } else {
                    if (currentlySelected[1] == this) { //deselects attackable enemy country
                        currentlySelected[1] = null;
                    } else if (currentlySelected[0] != null && currentlySelected[0].isNeighboringEnemy(this)) { //selects attackable enemy country
                        currentlySelected[1] = this;
                        troopEncasementImage = Toolkit.getDefaultToolkit().getImage("./images/attackButton.png");
                    }
                }
                break;
            case FORTIFY:
                if (Titlescreen.getGame().getCurrentPlayer() != owner)
                    return;
                if (currentlySelected[0] == this) {
                    currentlySelected[0] = null;
                    currentlySelected[1] = null;
                    TextLog.clearInput();
                    Titlescreen.getGame().setFortifyAmount(0);
                } else if (currentlySelected[0] == null) {
                    if (numTroops > 1)
                        currentlySelected[0] = this;
                } else if (currentlySelected[1] == this) {
                    currentlySelected[1] = null;
                } else {
                    currentlySelected[1] = this;
                }
                break;
        }
    }

    static public void switchedTurnHandler(Gameplay.Phase phase) {
        switch (phase) {
            case DEPLOY:
                currentlySelected[0] = null;
                currentlySelected[1] = null;
                break;
            case ATTACK:
                currentlySelected[0] = null;
                currentlySelected[1] = null;
                break;
            case FORTIFY:
                resetAllTroopCounters();
                currentlySelected[0] = null;
                currentlySelected[1] = null;
                break;
        }
    }

    // Mutator methods
    static public void setCountryOnMouse(int x, int y) {
        onMouse = RiskMap.containsPoint(x, y);
    }

    public void addNeighboringCountry(Country neighboringCountry) {
        neighboringCountries.add(neighboringCountry);
    }

    public void setOwner(Player _owner) {
        owner = _owner;
    }

    public void setNumTroops(int _numTroops) {
        numTroops = _numTroops;
    }

    public void addNumTroops(int inc) {
        numTroops += inc;
    }

    // Accessor methods
    static Country getCountry (String name) {
        for(Country country : RiskMap.getCountryList()) {
            if(country.NAME.equalsIgnoreCase(name))
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
        return BOUNDARY;
    }

    public String getName() {
        return NAME;
    }

    public int getNumTroops() {
        return numTroops;
    }

    public boolean isNeighboringEnemy(Country otherCountry) {
        return neighboringCountries.contains(otherCountry) && otherCountry.owner != Titlescreen.getGame().getCurrentPlayer();
    }
}
