
package risk;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gameplay {
    
    static public enum Phase { DEPLOY, ATTACK, FORTIFY }
    private Phase phase;
    private RiskMap riskMap = null;
    private Player players[];
    private Player currentPlayer;
    private Country clickedCountry;
    private int deployAmount;
    
    Gameplay(Risk frame, int numPlayers) {
        // Handles drawing & window
        riskMap = new RiskMap();
        Window.changeWindow(frame, Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, "Risk - Singleplayer");
        // Handles players
        players = new Player[numPlayers];
        for (int i=0;i<numPlayers;i++)
            players[i] = new Player();
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
        if (key.equals("none")) {
            if (clickedCountry.getOwner() == currentPlayer) {
                clickedCountry.selectedByClickHandler(phase);
            }
        }
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
                        TextLog.createStatement("+" + deployAmount + " troops in " + Country.getSelectedList()[0].getName() + ".");
                        deployAmount = 0;
                        Country.getSelectedList()[0] = null;
                        if (currentPlayer.getDeployableTroops() <= 0) {
                            TextLog.createStatement("0 Troops left. Switching to attack phase.");
                            switchTurnHandler();
                        }
                        else
                            TextLog.createStatement("You have " + currentPlayer.getDeployableTroops() + " troops left to deploy.\n");
                    }
                    TextLog.clearInput();
                    break;
                default:
                    break;
            }
        }
    }
    
    private void attackPhaseHandler(int x, int y, String key) {
        if (key.equals("none")) {
            clickedCountry.selectedByClickHandler(phase);
            if (Country.getSelectedList()[0] != null && Country.getSelectedList()[0].isNeighboringEnemy(clickedCountry)) {
                TextLog.createStatement("How many troops would you");
                TextLog.createStatement("like to attack " + clickedCountry.getName() + " with?");
            }
        }
        else if (Country.getSelectedList()[0] != null && Country.getSelectedList()[1] != null) {
            switch (key) {
                case "1":
                case "2":
                case "3":
                    int troopsLeft = Country.getSelectedList()[0].getNumTroops() - Country.getSelectedList()[1].getNumTroops();
                    if (troopsLeft <= 0) { // if they lost
                        Country.getSelectedList()[0].setNumTroops(1);
                        Country.getSelectedList()[1].setNumTroops(Math.abs(troopsLeft));
                    }
                    else { // if they won
                        Country.getSelectedList()[0].setNumTroops(1);
                        Country.getSelectedList()[1].setNumTroops(troopsLeft);
                        Player.transferCountryOwnership(Country.getSelectedList()[1].getOwner(), currentPlayer, Country.getSelectedList()[1]);
                    }
            }
        }
    }
    
    private void fortifyPhaseHandler(int x, int y, String key) {
        
    }
    
    private void deployPhaseInit() {
        phase = Phase.DEPLOY;
        currentPlayer.setDeployableTroops();
        TextLog.createStatement("You have " + currentPlayer.getDeployableTroops() + " troops left to deploy.\n");
        deployAmount = 0;
    }
    
    private void attackPhaseInit() {
        phase = Phase.ATTACK;
    }
    
    private void fortifyPhaseInit() {
        phase = Phase.FORTIFY;
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
                        deployPhaseInit();
                        break;
                    }
                    break;
                }
                else {
                    currentPlayer = players[i+1];
                    int playerID = i+1;
                    TextLog.createStatement("-------Player " + playerID + "'s turn------");
                    System.out.println(i);
                    switch (phase) {
                    case DEPLOY:
                        deployPhaseInit();
                        break;
                    case ATTACK:
                        attackPhaseInit();
                        break;
                    case FORTIFY:
                        fortifyPhaseInit();
                        if (i == players.length-1) {
                            currentPlayer = players[0];
                            TextLog.createStatement("-------Player 1's Turn-------");
                        } else {
                            currentPlayer = players[i+1];
                            TextLog.createStatement("-------Player " + i+2 + "'s turn------");
                        }
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
        int countryLimit = (int) 70/players.length;
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
                        if (assignedCountries[a] < countryLimit || (RiskMap.getCountryList().indexOf(country) == 68 && a == 0) || (RiskMap.getCountryList().indexOf(country) == 69 && a == 1)) {
                            System.out.println(RiskMap.getCountryList().indexOf(country));
                            country.setOwner(players[a]);
                            players[a].getOwnedCountries().add(country);
                            assignedCountries[a]++;
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
        for (int i=0;i<players.length;i++) {
            // randomly adds an amount of troops to each of the player's countries
            for (Country country : players[i].getOwnedCountries()) {
                randomVal = (int) (Math.random()*3+1);
                if (players[i].getTotalTroops()+randomVal <= troopLimit+1-countryNum) {
                    country.addNumTroops(randomVal);
                    players[i].addTotalTroops(randomVal);
                } else {
                    country.addNumTroops(1);
                    players[i].addTotalTroops(1);
                }
                countryNum++;
            }
            // distributes more troops to each player until they reach their limit of troops
            for (Country country : players[i].getOwnedCountries()) {
                if (players[i].getTotalTroops() < troopLimit) {
                    country.addNumTroops(1);
                    players[i].addTotalTroops(1);
                } else
                    break;
            }
            countryNum = 1;
        }
        //System.out.println("Player 1 Troop Count: " + players[0].getTotalTroops() + "   Player 2 Troop Count: " + players[1].getTotalTroops());
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public Phase getPhase() {
        return phase;
    }
}
