
package risk;

import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import static risk.Main.g;

public class RiskMap {
    
    static private Image image;
    static private ArrayList<Country> countries = new ArrayList<Country>();
    
    
    
    static public void draw(Main frame) {
        g.drawImage(image, 0, 0, Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, frame);
    }
    
    static public Country contains(int x, int y) {
        Point point = new Point(x, y);
        for (Country country : countries) {
            if (country != null && country.boundry.contains(point))
                return(country);
        }
        return(null);
    }
         
    RiskMap(Image _image) {
        image = _image;
        Polygon countryBoundry;
        String countryName;
        Country country;
        
        // Initializing countries
        // Alaska
        { int x[] = { 31,30,86,125,125,89,31 };
        int y[] = { 180,81,69,93,170,168,180 };
        countryBoundry = new Polygon(x, y, 7); }
        countryName = "Alaska";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Greenland
        { int x[] = { 412,451,463,487,495,563,574,566,579,457,412 };
        int y[] = { 62,122,185,196,152,114,77,61,0,0,62 };
        countryBoundry = new Polygon(x, y, 11); }
        countryName = "Greenland";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Mexico
        { int x[] = { 159,191,201,216,225,240,278,278,253,237,181,159 };
        int y[] = { 341,349,345,359,354,374,390,397,406,420,401,341 };
        countryBoundry = new Polygon(x, y, 11); }
        countryName = "Mexico";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
    }
}
