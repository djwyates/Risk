
package risk;

import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gameplay {
    
    static public enum Phase { DEPLOY, ATTACK, FORTIFY }
    static private int numPlayers = 2;
    private Phase phase;
    public static boolean changeToFortify = false;
    private RiskMap riskMap = null; // Purpose of this?
    private Player players[];
    private Player currentPlayer;
    private Country clickedCountry;
    private int deployAmount;
    private int fortifyAmount;
    
    Gameplay(Risk frame) {
        // Handles drawing & window
        riskMap = new RiskMap();
        Window.changeWindow(frame, Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, "Risk - Singleplayer");
        // Handles players
        players = new Player[numPlayers];
        for (int i=0;i<numPlayers;i++){
            players[i] = new Player();
            Button.getPlayerColors().set(i,new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        }
        currentPlayer = players[0];
        // Handles countries
        assignCountries();
        // Handles troops
        assignTroops();
        // Handles game phases
        deployPhaseInit();
        // Handles other classes
        Titlescreen.startedGame();
    }
    
    public static void switchTurnAccessor(){ // The boolean is inneffient, but in order to do the other method the current player needs to be accessible to a private circumstance
        changeToFortify=true;
//      getCurrentPlayer().switchTurnHandler();  
    }
    
    public void drawAndSoundHandler(Risk frame, int x, int y) {
        Country.setCountryOnMouse(x, y);
        try {
            RiskMap.draw(frame, x, y, phase);
        } catch (FontFormatException ex) {
            Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
        }
        RiskMap.mouseInCountryHandler(x, y, phase);
    }
    
    public void mouseClickHandler(int x, int y) {
        Country.setCountryOnMouse(x, y);
        if (Country.getCountryOnMouse() == null)
            return;
        clickedCountry = Country.getCountryOnMouse();
        switch (phase) {
            case DEPLOY:
                deployPhaseHandler(x, y, "none");
                break;
            case ATTACK:
                attackPhaseHandler(x, y, "none");
                break;
            case FORTIFY:
                fortifyPhaseHandler(x, y, "none");
                break;
        }
    }
    
    public void keyPressedHandler(String key) {
        switch (phase) {
            case DEPLOY:
                deployPhaseHandler(0, 0, key);
                break;
            case ATTACK:
                attackPhaseHandler(0, 0, key);
                break;
            case FORTIFY:
                fortifyPhaseHandler(0, 0, key);
                break;
        }
    }
    
    private void deployPhaseHandler(int x, int y, String key) {
        if (key.equals("none"))
            clickedCountry.selectedByClickHandler(phase);
        else if (Country.getSelectedList()[0] != null) {
            switch (key) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    if (deployAmount*10+Integer.parseInt(key) <= currentPlayer.getDeployableTroops()) {
                        deployAmount = deployAmount*10+Integer.parseInt(key);
                        TextLog.addToInput(key);
                    }
                    break;
                case "backspace":
                    deployAmount = (int)deployAmount/10;
                    TextLog.removeOneInput();
                    break;
                case "enter":
                    if (deployAmount > 0) {
                        currentPlayer.addTotalTroops(deployAmount);
                        currentPlayer.addDeployableTroops(-deployAmount);
                        Country.getSelectedList()[0].addNumTroops(deployAmount);
                        TextLog.createStatement("+" + deployAmount + " troops in " + Country.getSelectedList()[0].getName() + ".",Phase.DEPLOY);
                        deployAmount = 0;
                        Country.getSelectedList()[0] = null;
                        if (currentPlayer.getDeployableTroops() <= 0) {
                            TextLog.createStatement("No Troops left.",Phase.DEPLOY);
                            TextLog.createStatement("Switching to attack phase.",null);
                            switchTurnHandler();
                        }
                        else
                            TextLog.createStatement("You have " + currentPlayer.getDeployableTroops() + " troops left to deploy.\n",Phase.DEPLOY);
                    }
                    TextLog.clearInput();
                    break;
                default:
                    break;
            }
        }
    }
    
    private void attackPhaseHandler(int x, int y, String key) {
        if(changeToFortify){
            TextLog.createStatement("Switching to attack phase.",null);
            switchTurnHandler();
        }
        if (key.equals("none")) {
            clickedCountry.selectedByClickHandler(phase);
            if (Country.getSelectedList()[0] != null && Country.getSelectedList()[0].isNeighboringEnemy(clickedCountry)) {
                TextLog.createStatement("Press enter to attack " + clickedCountry.getName(),Phase.ATTACK);
            }
        }
        else if (Country.getSelectedList()[0] != null && Country.getSelectedList()[1] != null) {
            switch (key) {
                case "enter":
                    if (currentPlayer == battleTroops()) { // if current player won
                        //transfers country ownership to the current player from the defending player
                        currentPlayer.addCountry(Country.getSelectedList()[1]);
                        //makes all countries' troop counters normal
                        Country.changeTroopCounter();
                        //transfers all but 1 troop into conquered country
                        Country.getSelectedList()[1].setNumTroops(Country.getSelectedList()[0].getNumTroops()-1);
                        Country.getSelectedList()[0].setNumTroops(1);
                        //selects the conquered country if it has more than 1 troop
                        if (Country.getSelectedList()[1].getNumTroops() > 1)
                            Country.getSelectedList()[0] = Country.getSelectedList()[1];
                        else
                            Country.getSelectedList()[0] = null;
                        Country.getSelectedList()[1] = null;
                    }
                    else { //if current player lost
                        //deselects all countries
                        Country.getSelectedList()[0] = null;
                        Country.getSelectedList()[1] = null;
                        Country.changeTroopCounter();
                    }
                    break;
            }
        }
        
    }
    
    private void fortifyPhaseHandler(int x, int y, String key) {
        if (key.equals("none"))
            clickedCountry.selectedByClickHandler(phase);
        else if (Country.getSelectedList()[0] != null && Country.getSelectedList()[1] != null) {
            switch (key) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    if (fortifyAmount*10+Integer.parseInt(key) <= Country.getSelectedList()[0].getNumTroops()) {
                        fortifyAmount = fortifyAmount*10+Integer.parseInt(key);
                        TextLog.addToInput(key);
                    }
                    break;
                case "backspace":
                    fortifyAmount = (int)fortifyAmount/10;
                    TextLog.removeOneInput();
                    break;
                case "enter":
                    if (fortifyAmount > 0) {
                        Country.getSelectedList()[0].addNumTroops(-fortifyAmount);
                        Country.getSelectedList()[1].addNumTroops(fortifyAmount);
                        TextLog.createStatement("Transfered " + fortifyAmount + " troops to " + Country.getSelectedList()[1].getName(), phase);
                        TextLog.createStatement("Changing turns to the next player.", null);
                    }
                    TextLog.clearInput();
                    break;
            }
        }
    }
    
    private void deployPhaseInit() {
        phase = Phase.DEPLOY;
        currentPlayer.setDeployableTroops();
        TextLog.createStatement("You have " + currentPlayer.getDeployableTroops() + " troops left to deploy.\n",Phase.DEPLOY);
        deployAmount = 0;
    }
    
    private void attackPhaseInit() {
        phase = Phase.ATTACK;
    }
    
    private void fortifyPhaseInit() {
        phase = Phase.FORTIFY;
        fortifyAmount = 0;
    }
    
    private void switchTurnHandler() {
        Country.getSelectedList()[0] = null;
        Country.getSelectedList()[1] = null;
        for (int i=0;i<players.length;i++) {
            if (currentPlayer == players[i]) {
                switch (phase) {
                    case DEPLOY:
                        attackPhaseInit();
                        break;
                    case ATTACK:
                        fortifyPhaseInit();
                        break;
                    case FORTIFY:
                        if (i == players.length-1) {
                            currentPlayer = players[0];
                            TextLog.createStatement("-------Player 1's Turn-------",null);
                        } else {
                            int playerID = i+2;
                            currentPlayer = players[i+1];
                            TextLog.createStatement("-------Player " + playerID + "'s turn------",null);
                        }
                        deployPhaseInit();
                        break;
                }
                break;
            }
        }
        Country.switchedTurnHandler(phase);
    }
    
    private void assignCountries() {
        //todo: fix bug that does not assign a country/countries an owner (null pointer)
        int assignedCountries[] = new int[players.length];
        int countryLimit = (70+players.length-1)/players.length; //rounds up 70/players.length
        int randomVal;
        for (Country country : RiskMap.getCountryList()) {
            randomVal = (int) (Math.random()*players.length);
            for (int i=0;i<players.length;i++) {
                if (randomVal == i && assignedCountries[i] < countryLimit) {
                    country.setOwner(players[i]);
                    players[i].getOwnedCountries().add(country);
                    assignedCountries[i]++;
                    break;
                }
                if (i == players.length-1) {
                    for (int a=0;a<players.length;a++) {
                        if (assignedCountries[a] < countryLimit) {
                            country.setOwner(players[a]);
                            players[a].getOwnedCountries().add(country);
                            assignedCountries[a]++;
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void assignTroops() {
        int troopLimit;
        int randomVal = 0;
        int countryNum = 1;
        switch (players.length) {
            case 2:
                troopLimit = 70;
                break;
            case 3:
                troopLimit = 60;
                break;
            case 4:
                troopLimit = 50;
                break;
            default:
                troopLimit = 40;
                break;
        }
        for (Player player : players) {
            // randomly adds an amount of troops to each of the player's countries
            for (Country country : player.getOwnedCountries()) {
                randomVal = (int) (Math.random()*3+1);
                if (player.getTotalTroops() + randomVal <= troopLimit+1-countryNum) {
                    country.addNumTroops(randomVal);
                    player.addTotalTroops(randomVal);
                } else {
                    country.addNumTroops(1);
                    player.addTotalTroops(1);
                }
                countryNum++;
            }
            // distributes more troops to each player until they reach their limit of troops
            for (Country country : player.getOwnedCountries()) {
                if (player.getTotalTroops() < troopLimit) {
                    country.addNumTroops(1);
                    player.addTotalTroops(1);
                } else
                    break;
            }
            countryNum = 1;
        }
    }
    
    private Player battleTroops() { //returns the winner
        int offensivePlayerRoll=0;
        int defensivePlayerRoll=0;
        if (Country.getSelectedList()[0].getNumTroops() > 1) {
            do {
                if (Country.getSelectedList()[1].getNumTroops() == 0){
                    TextLog.createStatement("Attacker Won!",null);
                    return currentPlayer;
                }
                offensivePlayerRoll = (int)(Math.random()*6+1);
                defensivePlayerRoll = (int)(Math.random()*6+1);
                if (offensivePlayerRoll > defensivePlayerRoll)
                    Country.getSelectedList()[1].addNumTroops(-1);
                else
                    Country.getSelectedList()[0].addNumTroops(-1);
            }
            while(Country.getSelectedList()[0].getNumTroops() > 1);
        }
        TextLog.createStatement("Defender Won!",null);
        return Country.getSelectedList()[1].getOwner();
    }
    
    static public void addNumPlayers(int inc) {
        numPlayers += inc;
    }
    
    static public int getNumPlayers() {
        return numPlayers;
    }
    
    public Phase getPhase() {
        return phase;
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
