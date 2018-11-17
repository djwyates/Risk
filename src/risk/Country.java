
package risk;

import java.awt.Polygon;
import static risk.Main.g;

public class Country {
    static private Country currentCountry = null;
    boolean isSelected = false;
    Polygon boundary = null;
    String name = null;
    Player owner = null;
    
    Country() {
        
    }
    
    Country(Polygon _boundry, String _name) {
        boundary = _boundry;
        name = _name;
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