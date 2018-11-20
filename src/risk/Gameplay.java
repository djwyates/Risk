
package risk;

import java.awt.Image;

public class Gameplay {
    
    static public enum Phase { DEPLOY, ATTACK, FORTIFY }
    private Phase phase;
    private RiskMap riskMap = null;
    private Player players[];
    private Player currentPlayer;
    
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
        phase = Phase.DEPLOY;
        deployPhaseInit();
        // Handles other classes
        Titlescreen.startedGame();
    }
    
    public void drawAndSoundHandler(Risk frame, int x, int y) {
        Country.setCountryOnMouse(x, y);
        RiskMap.draw(frame, x, y);
        RiskMap.mouseInCountryHandler(x, y, phase);
    }
    
    public void mouseClickHandler(int x, int y) {
        Country.setCountryOnMouse(x, y);
        switch (phase) {
            case DEPLOY:
                deployPhaseHandler();
                break;
            case ATTACK:
                attackPhaseHandler();
                break;
            case FORTIFY:
                fortifyPhaseHandler();
                break;
        }
    }
    
    public void keyPressedHandler(String key) {
        switch (phase) {
            case DEPLOY:
                deployPhaseHandler();
                break;
            case ATTACK:
                attackPhaseHandler();
                break;
            case FORTIFY:
                fortifyPhaseHandler();
                break;
        }
    }
    
    private void deployPhaseHandler() {
        Country country = Country.getCountryOnMouse();
        if (country != null && country.getOwner() == currentPlayer) {
            country.selectedByClickHandler(phase);
            System.out.println("You have " + currentPlayer.getDeployableTroops() + " troops left to deploy.\n"
                    + "How many would you like to deploy here?");
        }
    }
    
    private void attackPhaseHandler() {
        
    }
    
    private void fortifyPhaseHandler() {
        
    }
    
    private void deployPhaseInit() {
        currentPlayer.setDeployableTroops();
    }
    
    private void assignCountries() {
        int randomVal = 0;
        int assignedCountries[] = new int[2];
        for (Country country : RiskMap.getCountryList()) {
            randomVal = (int) (Math.random()*2);
            if (randomVal == 0 && assignedCountries[0] < 35) {
                country.setOwner(players[0]);
                players[0].getOwnedCountries().add(country);
                assignedCountries[0]++;
            }
            else if (assignedCountries[1] < 35) {
                country.setOwner(players[1]);
                players[1].getOwnedCountries().add(country);
                assignedCountries[1]++;
            }
            else {
                country.setOwner(players[0]);
                players[0].getOwnedCountries().add(country);
            }
        }
    }
    
    private void assignTroops() {
        int troopLimit = 80;
        int randomVal = 0;
        int countryNum = 1;
        // randomly adds an amount to troops to each player's territories
        for (Country country : players[0].getOwnedCountries()) {
            randomVal = (int) (Math.random()*3+1);
                if (players[0].getTotalTroops()+randomVal <= troopLimit+1-countryNum) {
                    country.addNumTroops(randomVal);
                    players[0].addTotalTroops(randomVal);
                } else {
                    country.addNumTroops(1);
                    players[0].addTotalTroops(1);
                }
                countryNum++;
        }
        countryNum = 1;
        for (Country country : players[1].getOwnedCountries()) {
            randomVal = (int) (Math.random()*3+1);
                if (players[1].getTotalTroops()+randomVal <= troopLimit+1-countryNum) {
                    country.addNumTroops(randomVal);
                    players[1].addTotalTroops(randomVal);
                } else {
                    country.addNumTroops(1);
                    players[1].addTotalTroops(1);
                }
                countryNum++;
        }
        // distributes more troops to each player until they reach their limit of troops
        for (Country country : players[0].getOwnedCountries()) {
            if (players[0].getTotalTroops() < troopLimit) {
                country.addNumTroops(1);
                players[0].addTotalTroops(1);
            } else
                break;
        }
        for (Country country : players[1].getOwnedCountries()) {
            if (players[1].getTotalTroops() < troopLimit) {
                country.addNumTroops(1);
                players[1].addTotalTroops(1);
            } else
                break;
        }
        System.out.println("Player 1 Troop Count: " + players[0].getTotalTroops() + "   Player 2 Troop Count: " + players[1].getTotalTroops());
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public Phase getPhase() {
        return phase;
    }
}
