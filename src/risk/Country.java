
package risk;

import java.awt.Polygon;
import static risk.Main.g;

public class Country {
    static private Country currentCountry;
    Polygon boundary;
    String name;
    boolean isSelected;
    
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
    
    public Polygon getBoundry()
    { return(boundary); }
    
}