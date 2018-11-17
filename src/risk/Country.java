
package risk;

import java.awt.Polygon;
import static risk.Main.g;

public class Country {
    Polygon boundary;
    String name;
    Country selectedCountry;
    
    Country() {
        
    }
    
    Country(Polygon _boundry, String _name) {
        boundary = _boundry;
        name = _name;
    }
    
    public void mouseInCountry() {
        drawBorders();
        if (selectedCountry != this)
            playSoundEffect();
        selectedCountry = this;
    }
    
    private void drawBorders() {
        g.drawPolygon(boundary);
    }
    
    private void playSoundEffect() {
        Titlescreen.getMenuSounds().play("terr_noise.wav");
    }
    
    public Polygon getBoundry()
    { return(boundary); }
    
}