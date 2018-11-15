
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
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Alaska";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Greenland
        { int x[] = { 412,451,463,487,495,563,574,566,579,457,412 };
        int y[] = { 62,122,185,196,152,114,77,61,0,0,62 };
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Greenland";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Mexico
        { int x[] = { 159,191,201,216,225,240,278,278,253,237,181,159 };
        int y[] = { 341,349,345,359,354,374,390,397,406,420,401,341 };
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Mexico";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // United States
        { int x[] = {157,297,333,393,402,343,344,323,259,243,205,157,146,157};
        int y[] = {237,241,283,251,269,344,381,352,353,368,341,336,280,239};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "United States";
        country = new Country(countryBoundry, countryName);
        countries.add(country); 
        
        // Northern South America
        { int x[] = {323,382,491,521,496,478,418,394,345,319,259,284,314,420,438};
        int y[] = {439,452,515,545,585,632,688,610,625,595,532,483,435,465,492};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Northern South America";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Southern South America
        { int x[] = {416,396,415,385,364,335,323,311,324,356,389,360,362,380};
        int y[] = {690,668,648,608,626,626,597,713,780,811,812,777,747,719};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Southern South America";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
    }
}
