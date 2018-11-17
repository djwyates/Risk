
package risk;

import java.util.ArrayList;

public class Continent {
    static Continent continents[]= new Continent[11];
    String name;
    ArrayList<Country> contCountries;
    int bonus;
    
    Continent(){
        name="";
        contCountries= new ArrayList<Country>();
        bonus=0;
    }
    
    static void create(){
        continents[0] = new Continent();
        continents[0].name="Canada";
        continents[0].bonus = 4;
        continents[0].contCountries.add(Country.getCountry("North West Canada"));
        continents[0].contCountries.add(Country.getCountry("Ontario"));
        continents[0].contCountries.add(Country.getCountry("Alberta"));
        continents[0].contCountries.add(Country.getCountry("Nunavut"));
        continents[0].contCountries.add(Country.getCountry("Quebec"));
        continents[0].contCountries.add(Country.getCountry("Buffalo Islands"));
        
        continents[1] = new Continent();
        continents[1].name="United States";
        continents[1].bonus = 4;
        continents[1].contCountries.add(Country.getCountry("Western United States"));
        continents[1].contCountries.add(Country.getCountry("Midwestern United States"));
        continents[1].contCountries.add(Country.getCountry("Southern United States"));
        continents[1].contCountries.add(Country.getCountry("Northern United States"));
        continents[1].contCountries.add(Country.getCountry("Alaska"));
        continents[1].contCountries.add(Country.getCountry("Hawaii"));
        
        continents[2] = new Continent();
        continents[2].name="Central America";
        continents[2].bonus = 3;
        continents[2].contCountries.add(Country.getCountry("Mexico"));
        continents[2].contCountries.add(Country.getCountry("Guatamala"));
        continents[2].contCountries.add(Country.getCountry("Caribbean Islands"));
        
        continents[3] = new Continent();
        continents[3].name="South America";
        continents[3].bonus = 4;
        continents[3].contCountries.add(Country.getCountry("Venezuela"));
        continents[3].contCountries.add(Country.getCountry("Peru"));
        continents[3].contCountries.add(Country.getCountry("Bolivia"));
        continents[3].contCountries.add(Country.getCountry("Brazil"));
        continents[3].contCountries.add(Country.getCountry("Alaska"));
        continents[3].contCountries.add(Country.getCountry("Argentina"));
        continents[3].contCountries.add(Country.getCountry("Falkland Islands"));
        
        continents[4] = new Continent();
        continents[4].name="Africa";
        continents[4].bonus = 4;
        continents[4].contCountries.add(Country.getCountry("Algeria"));
        continents[4].contCountries.add(Country.getCountry("North Africa"));
        continents[4].contCountries.add(Country.getCountry("Congo"));
        continents[4].contCountries.add(Country.getCountry("Sudan"));
        continents[4].contCountries.add(Country.getCountry("Eastern Africa"));
        continents[4].contCountries.add(Country.getCountry("South Africa"));
        continents[4].contCountries.add(Country.getCountry("Libya"));
        continents[4].contCountries.add(Country.getCountry("Madagascar"));
        
        continents[5] = new Continent();
        continents[5].name="Europe";
        continents[5].bonus = 4;
        continents[5].contCountries.add(Country.getCountry("British Isles"));
        continents[5].contCountries.add(Country.getCountry("Northern Europe"));
        continents[5].contCountries.add(Country.getCountry("Ukraine"));
        continents[5].contCountries.add(Country.getCountry("Southern Europe"));
        continents[5].contCountries.add(Country.getCountry("Western Europe"));
        
        continents[6] = new Continent();
        continents[6].name="Middle East";
        continents[6].bonus = 5;
        continents[6].contCountries.add(Country.getCountry("Egypt"));
        continents[6].contCountries.add(Country.getCountry("Iraq"));
        continents[6].contCountries.add(Country.getCountry("Iran"));
        continents[6].contCountries.add(Country.getCountry("Saudi Arabia"));
        continents[6].contCountries.add(Country.getCountry("Turkey"));
        continents[6].contCountries.add(Country.getCountry("Afghanistan"));
        continents[6].contCountries.add(Country.getCountry("Kazakhstan"));
        
        continents[7] = new Continent();
        continents[7].name="Russia";
        continents[7].bonus = 6;
        continents[7].contCountries.add(Country.getCountry("Volga"));
        continents[7].contCountries.add(Country.getCountry("Northern Russia"));
        continents[7].contCountries.add(Country.getCountry("Novada Zendya"));
        continents[7].contCountries.add(Country.getCountry("Urd"));
        continents[7].contCountries.add(Country.getCountry("Siberia"));
        continents[7].contCountries.add(Country.getCountry("Soverya"));
        continents[7].contCountries.add(Country.getCountry("Yakutta"));
        continents[7].contCountries.add(Country.getCountry("Irkatsk"));
        continents[7].contCountries.add(Country.getCountry("Amer"));
        continents[7].contCountries.add(Country.getCountry("Kamchakta"));
        continents[7].contCountries.add(Country.getCountry("Novashelkye"));
        
        continents[8] = new Continent();
        continents[8].name="Scandinavia";
        continents[8].bonus = 3;
        continents[8].contCountries.add(Country.getCountry("Iceland"));
        continents[8].contCountries.add(Country.getCountry("Greenland"));
        continents[8].contCountries.add(Country.getCountry("Sweden"));
        continents[8].contCountries.add(Country.getCountry("Svalbard"));
        
        continents[9] = new Continent();
        continents[9].name="Asia";
        continents[9].bonus = 6;
        continents[9].contCountries.add(Country.getCountry("China"));
        continents[9].contCountries.add(Country.getCountry("Thailand"));
        continents[9].contCountries.add(Country.getCountry("India"));
        continents[9].contCountries.add(Country.getCountry("Xijang"));
        continents[9].contCountries.add(Country.getCountry("Xiajano"));
        continents[9].contCountries.add(Country.getCountry("Mongolia"));
        continents[9].contCountries.add(Country.getCountry("Japan"));
        continents[9].contCountries.add(Country.getCountry("Philippines"));
        continents[9].contCountries.add(Country.getCountry("Indonesia"));
        continents[9].contCountries.add(Country.getCountry("New Guinea"));
        
        continents[10] = new Continent();
        continents[10].name="Oceania";
        continents[10].bonus = 3;
        continents[10].contCountries.add(Country.getCountry("Queensland"));
        continents[10].contCountries.add(Country.getCountry("Victoria"));
        continents[10].contCountries.add(Country.getCountry("Western Australia"));
        continents[10].contCountries.add(Country.getCountry("New Zealand"));
        
        //Checks for null countries in all continent array lists of countries.
        for(Continent x : continents){
            for(Country c : x.contCountries)
                if(c == null){
                    System.out.println(x.name + " has a null in arraylist");
                }
        }
        
    }
}
