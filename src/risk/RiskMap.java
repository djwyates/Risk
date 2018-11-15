
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
    
    static public void fillBorders() {
        for (Country country : countries)
            g.drawPolygon(country.getBoundry());
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
        { int x[] = { 161,185,206,213,224,243,283,281,271,287,278,184,159 };
        int y[] = { 336,346,342,355,351,391,388,404,409,452,465,401,338 };
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
        
        // Southern South America
        { int x[] = {297,333,377,405,424,376,346,338,304};
        int y[] = {395,391,414,418,427,431,427,404,398};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Carribean Islands";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Western Canada
        { int x[] = {249,249,209,126,129,157};
        int y[] = {235,132,87,94,174,236};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Western Canada";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Iceland
        { int x[] = {574,619,621,576,560,564};
        int y[] = {144,156,179,185,168,145};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Iceland";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Iceland
        { int x[] = {574,619,621,576,560,564};
        int y[] = {144,156,179,185,168,145};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Iceland";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // England
        { int x[] = {579,597,584,541,518,561};
        int y[] = {213,282,305,305,268,210};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "England";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Svalbard
        { int x[] = {621,654,696,713,616};
        int y[] = {42,109,91,28,27};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Svalbard";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Northern Europe
        { int x[] = {669,675,734,750,733,648,649,614,658,661};
        int y[] = {218,243,240,288,310,328,312,293,252,222};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Northern Europe";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Western Europe
        { int x[] = {539,539,588,614,612,640,648,642,613,594,567,588,587};
          int y[] = {369,410,436,403,387,361,328,313,295,316,320,345,368};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Western Europe";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Southern Europe
        { int x[] = {683,664,643,648,729,759,731,732,686};
          int y[] = {423,410,356,329,308,344,395,420,419};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Southern Europe";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Ukraine 
        { int x[] = {750,777,782,791,788,816,816,785,749,736,751,716,748};
          int y[] = {203,257,250,263,280,289,315,348,341,310,289,218,199};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Ukraine";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Eastern Canada 
        { int x[] = {217,215,292,374,416,432,434,461,476,409,398,335,303,253,254,218};
          int y[] = {92,36,47,61,95,143,177,209,243,275,248,274,234,233,127,90};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Eastern Canada";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
        
        // Australia
        { int x[] = {1153,1202,1253,1258,1240,1226,1199,1180,1155,1123,1084,1057,1083,1119,1137,1159,1177};
          int y[] = {649,645,721,762,796,824,804,789,771,775,788,715,698,662,661,646,653};
        countryBoundry = new Polygon(x, y, x.length); }
        countryName = "Australia";
        country = new Country(countryBoundry, countryName);
        countries.add(country);
    }
}
