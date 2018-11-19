
package risk;

import java.util.ArrayList;

public class Continent {
    static Continent continents[]= new Continent[11];
    ArrayList<Country> countries = new ArrayList<Country>();
    String name;
    int bonus;
    
    Continent(String _name, int _bonus){
        name = _name;
        bonus = _bonus;
    }
    
    static void create() {
        continents[0] = new Continent("Canada", 4);
        continents[0].countries.add(Country.getCountry("North West Canada"));
        continents[0].countries.add(Country.getCountry("Ontario"));
        continents[0].countries.add(Country.getCountry("Alberta"));
        continents[0].countries.add(Country.getCountry("Nunavut"));
        continents[0].countries.add(Country.getCountry("Quebec"));
        continents[0].countries.add(Country.getCountry("Buffalo Islands"));
        
        continents[1] = new Continent("United States", 4);
        continents[1].countries.add(Country.getCountry("Western United States"));
        continents[1].countries.add(Country.getCountry("Midwestern United States"));
        continents[1].countries.add(Country.getCountry("Southern United States"));
        continents[1].countries.add(Country.getCountry("Northern United States"));
        continents[1].countries.add(Country.getCountry("Alaska"));
        continents[1].countries.add(Country.getCountry("Hawaii"));
        
        continents[2] = new Continent("Central America", 3);
        continents[2].countries.add(Country.getCountry("Mexico"));
        continents[2].countries.add(Country.getCountry("Guatamala"));
        continents[2].countries.add(Country.getCountry("Caribbean Islands"));
        
        continents[3] = new Continent("South America", 4);
        continents[3].countries.add(Country.getCountry("Venezuela"));
        continents[3].countries.add(Country.getCountry("Peru"));
        continents[3].countries.add(Country.getCountry("Bolivia"));
        continents[3].countries.add(Country.getCountry("Brazil"));
        continents[3].countries.add(Country.getCountry("Argentina"));
        continents[3].countries.add(Country.getCountry("Falkland Islands"));
        
        continents[4] = new Continent("Africa", 4);
        continents[4].countries.add(Country.getCountry("Algeria"));
        continents[4].countries.add(Country.getCountry("North Africa"));
        continents[4].countries.add(Country.getCountry("Congo"));
        continents[4].countries.add(Country.getCountry("Sudan"));
        continents[4].countries.add(Country.getCountry("Eastern Africa"));
        continents[4].countries.add(Country.getCountry("South Africa"));
        continents[4].countries.add(Country.getCountry("Libya"));
        continents[4].countries.add(Country.getCountry("Madagascar"));
        
        continents[5] = new Continent("Europe", 4);
        continents[5].countries.add(Country.getCountry("British Isles"));
        continents[5].countries.add(Country.getCountry("Northern Europe"));
        continents[5].countries.add(Country.getCountry("Ukraine"));
        continents[5].countries.add(Country.getCountry("Southern Europe"));
        continents[5].countries.add(Country.getCountry("Western Europe"));
        
        continents[6] = new Continent("Middle East", 5);
        continents[6].countries.add(Country.getCountry("Egypt"));
        continents[6].countries.add(Country.getCountry("Iraq"));
        continents[6].countries.add(Country.getCountry("Iran"));
        continents[6].countries.add(Country.getCountry("Saudi Arabia"));
        continents[6].countries.add(Country.getCountry("Turkey"));
        continents[6].countries.add(Country.getCountry("Afghanistan"));
        continents[6].countries.add(Country.getCountry("Kazakhstan"));
        
        continents[7] = new Continent("Russia", 6);
        continents[7].countries.add(Country.getCountry("Volga"));
        continents[7].countries.add(Country.getCountry("Northern Russia"));
        continents[7].countries.add(Country.getCountry("Novada Zendya"));
        continents[7].countries.add(Country.getCountry("Urd"));
        continents[7].countries.add(Country.getCountry("Siberia"));
        continents[7].countries.add(Country.getCountry("Soverya"));
        continents[7].countries.add(Country.getCountry("Yakutta"));
        continents[7].countries.add(Country.getCountry("Irkatsk"));
        continents[7].countries.add(Country.getCountry("Amer"));
        continents[7].countries.add(Country.getCountry("Kamchakta"));
        continents[7].countries.add(Country.getCountry("Novashelkye"));
        
        continents[8] = new Continent("Scandinavia", 3);
        continents[8].countries.add(Country.getCountry("Iceland"));
        continents[8].countries.add(Country.getCountry("Greenland"));
        continents[8].countries.add(Country.getCountry("Sweden"));
        continents[8].countries.add(Country.getCountry("Svalbard"));
        
        continents[9] = new Continent("Asia", 6);
        continents[9].countries.add(Country.getCountry("China"));
        continents[9].countries.add(Country.getCountry("Thailand"));
        continents[9].countries.add(Country.getCountry("India"));
        continents[9].countries.add(Country.getCountry("Xijang"));
        continents[9].countries.add(Country.getCountry("Xiajano"));
        continents[9].countries.add(Country.getCountry("Mongolia"));
        continents[9].countries.add(Country.getCountry("Japan"));
        continents[9].countries.add(Country.getCountry("Philippines"));
        continents[9].countries.add(Country.getCountry("Indonesia"));
        continents[9].countries.add(Country.getCountry("New Guinea"));
        
        continents[10] = new Continent("Oceania", 3);
        continents[10].countries.add(Country.getCountry("Queensland"));
        continents[10].countries.add(Country.getCountry("Victoria"));
        continents[10].countries.add(Country.getCountry("Western Australia"));
        continents[10].countries.add(Country.getCountry("New Zealand"));
        
        //Checks for null countries in all continent array lists of countries.
        for(Continent x : continents){
            for(Country c : x.countries)
                if(c == null){
                    System.out.println(x.name + " has a null in arraylist");
                }
        }
        
    }
}
