
package risk;

import java.util.ArrayList;

public class Continent {
    private static Continent continents[]= new Continent[11];
    // instance variables
    private ArrayList<Country> countries = new ArrayList<Country>();
    private String name;
    private int bonus;
    
    Continent(String _name, int _bonus){
        name = _name;
        bonus = _bonus;
    }
    
    static public Continent[] getContinents() {
        return continents;
    }
    
    public ArrayList<Country> getCountries() {
        return countries;
    }
    
    public String getName() {
        return name;
    }
    
    public int getBonus() {
        return bonus;
    }
    
    static public void create() {
        continents[0] = new Continent("Canada", 4);
        continents[0].countries.add(RiskMap.getCountryList().get(8));
        continents[0].countries.add(RiskMap.getCountryList().get(16));
        continents[0].countries.add(RiskMap.getCountryList().get(63));
        continents[0].countries.add(RiskMap.getCountryList().get(65));
        continents[0].countries.add(RiskMap.getCountryList().get(64));
        continents[0].countries.add(RiskMap.getCountryList().get(67));
        
        continents[1] = new Continent("United States", 4);
        continents[1].countries.add(RiskMap.getCountryList().get(4));
        continents[1].countries.add(RiskMap.getCountryList().get(61));
        continents[1].countries.add(RiskMap.getCountryList().get(60));
        continents[1].countries.add(RiskMap.getCountryList().get(62));
        continents[1].countries.add(RiskMap.getCountryList().get(0));
        continents[1].countries.add(RiskMap.getCountryList().get(54));
        
        continents[2] = new Continent("Central America", 3);
        continents[2].countries.add(RiskMap.getCountryList().get(2));
        continents[2].countries.add(RiskMap.getCountryList().get(3));
        continents[2].countries.add(RiskMap.getCountryList().get(7));
        
        continents[3] = new Continent("South America", 4);
        continents[3].countries.add(RiskMap.getCountryList().get(56));
        continents[3].countries.add(RiskMap.getCountryList().get(6));
        continents[3].countries.add(RiskMap.getCountryList().get(57));
        continents[3].countries.add(RiskMap.getCountryList().get(5));
        continents[3].countries.add(RiskMap.getCountryList().get(58));
        continents[3].countries.add(RiskMap.getCountryList().get(59));
        
        continents[4] = new Continent("Africa", 4);
        continents[4].countries.add(RiskMap.getCountryList().get(21));
        continents[4].countries.add(RiskMap.getCountryList().get(23));
        continents[4].countries.add(RiskMap.getCountryList().get(24));
        continents[4].countries.add(RiskMap.getCountryList().get(27));
        continents[4].countries.add(RiskMap.getCountryList().get(26));
        continents[4].countries.add(RiskMap.getCountryList().get(25));
        continents[4].countries.add(RiskMap.getCountryList().get(22));
        continents[4].countries.add(RiskMap.getCountryList().get(66));
        
        continents[5] = new Continent("Europe", 4);
        continents[5].countries.add(RiskMap.getCountryList().get(10));
        continents[5].countries.add(RiskMap.getCountryList().get(12));
        continents[5].countries.add(RiskMap.getCountryList().get(15));
        continents[5].countries.add(RiskMap.getCountryList().get(14));
        continents[5].countries.add(RiskMap.getCountryList().get(17));
        
        continents[6] = new Continent("Middle East", 5);
        continents[6].countries.add(RiskMap.getCountryList().get(28));
        continents[6].countries.add(RiskMap.getCountryList().get(30));
        continents[6].countries.add(RiskMap.getCountryList().get(31));
        continents[6].countries.add(RiskMap.getCountryList().get(29));
        continents[6].countries.add(RiskMap.getCountryList().get(34));
        continents[6].countries.add(RiskMap.getCountryList().get(32));
        continents[6].countries.add(RiskMap.getCountryList().get(33));
        
        continents[7] = new Continent("Russia", 6);
        continents[7].countries.add(RiskMap.getCountryList().get(35));
        continents[7].countries.add(RiskMap.getCountryList().get(36));
        continents[7].countries.add(RiskMap.getCountryList().get(37));
        continents[7].countries.add(RiskMap.getCountryList().get(38));
        continents[7].countries.add(RiskMap.getCountryList().get(39));
        continents[7].countries.add(RiskMap.getCountryList().get(68));
        continents[7].countries.add(RiskMap.getCountryList().get(40));
        continents[7].countries.add(RiskMap.getCountryList().get(41));
        continents[7].countries.add(RiskMap.getCountryList().get(42));
        continents[7].countries.add(RiskMap.getCountryList().get(43));
        continents[7].countries.add(RiskMap.getCountryList().get(69));
        
        continents[8] = new Continent("Scandinavia", 3);
        continents[8].countries.add(RiskMap.getCountryList().get(9));
        continents[8].countries.add(RiskMap.getCountryList().get(1));
        continents[8].countries.add(RiskMap.getCountryList().get(20));
        continents[8].countries.add(RiskMap.getCountryList().get(11));
        
        continents[9] = new Continent("Asia", 6);
        continents[9].countries.add(RiskMap.getCountryList().get(45));
        continents[9].countries.add(RiskMap.getCountryList().get(50));
        continents[9].countries.add(RiskMap.getCountryList().get(49));
        continents[9].countries.add(RiskMap.getCountryList().get(47));
        continents[9].countries.add(RiskMap.getCountryList().get(48));
        continents[9].countries.add(RiskMap.getCountryList().get(46));
        continents[9].countries.add(RiskMap.getCountryList().get(44));
        continents[9].countries.add(RiskMap.getCountryList().get(51));
        continents[9].countries.add(RiskMap.getCountryList().get(52));
        continents[9].countries.add(RiskMap.getCountryList().get(53));
        
        continents[10] = new Continent("Oceania", 3);
        continents[10].countries.add(RiskMap.getCountryList().get(18));
        continents[10].countries.add(RiskMap.getCountryList().get(19));
        continents[10].countries.add(RiskMap.getCountryList().get(17));
        continents[10].countries.add(RiskMap.getCountryList().get(55));
        
        //Checks for null countries in all continent array lists of countries.
        for(Continent x : continents){
            for(Country c : x.countries)
                if(c == null){
                    System.out.println(x.name + " has a null in arraylist");
                }
        }
    }
}
