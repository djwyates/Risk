
package risk;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;
import static risk.Main.g;

public class RiskMap {
    
    static private Image mapImage = Toolkit.getDefaultToolkit().getImage("./riskMap.jpg");
    static private ArrayList<Country> countries = null;
    
    
    
    static public void draw(Main frame) {
        g.drawImage(mapImage, 0, 0, Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, frame);
    }
    
    static public String contains(int x, int y) {
        Point point = new Point(x, y);
        for (Country country : countries) {
            if (country.boundry.contains(point))
                return(country.name);
        }
        return("none");
    }
    
    RiskMap() {
        Polygon countryBoundry;
        String countryName;
        Country country;
        
        Country none = new Country();
        
        int x[] = { 31, 30, 86, 125, 125, 89 };
        int y[] = { 180, 81, 69, 93, 170, 168 };
        countryBoundry = new Polygon(x, y, 6);
        countryName = "Alaska";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        countryBoundry = new Polygon();
        countryName = "Greenland";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        countryBoundry = new Polygon();
        countryName = "Mexico";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
    }
}
