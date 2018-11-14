
package risk;

import java.awt.Polygon;

public class Country {
    Polygon boundry;
    String name;
    
    Country() {
        
    }
    
    Country(Polygon _boundry, String _name) {
        boundry = _boundry;
        name = _name;
    }
    
    public String toString()
    { return(name); }
}