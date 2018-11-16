
package risk;

import java.awt.Polygon;

public class Country {
    Polygon boundry;
    String name;
    boolean isSelected;
    
    Country() {
        
    }
    
    Country(Polygon _boundry, String _name) {
        boundry = _boundry;
        name = _name;
        isSelected=false;
    }
    
    public Polygon getBoundry()
    { return(boundry); }
    
}