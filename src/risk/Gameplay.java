
package risk;

public class Gameplay {
    
    static public enum Phase { DEPLOY, ATTACK, FORTIFY }
    private Phase phase;
    private RiskMap riskMap = null;
    private Player players[];
    private Player currentPlayer;
    private Country clickedCountry;
    private Country selectedCountry;
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
        RiskMap.draw(frame, x, y, phase);
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
                if (Country.getSelectedList().isEmpty())
                    selectedCountry = null;
                else
                    selectedCountry = Country.getSelectedList().get(0);
                if (selectedCountry != null)
                System.out.println("You have " + currentPlayer.getDeployableTroops() + " troops left to deploy.\n"
                        + "How many would you like to deploy in " + selectedCountry.getName() + "?");
            }
        }
        else if (selectedCountry != null) {
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
                    if (deployAmount*10+Integer.parseInt(key) <= currentPlayer.getDeployableTroops())
                        deployAmount = deployAmount*10+Integer.parseInt(key);
                    System.out.println("Press enter to deploy " + deployAmount + " troops into " + selectedCountry.getName() + ".");
                    break;
                case "backspace":
                    deployAmount = (int)deployAmount/10;
                    break;
                case "enter":
                    if (deployAmount > 0) {
                        currentPlayer.addTotalTroops(deployAmount);
                        currentPlayer.addDeployableTroops(-deployAmount);
                        selectedCountry.addNumTroops(deployAmount);
                        System.out.println("You have successfully deployed " + deployAmount + " troops into " + selectedCountry.getName() + ".");
                        deployAmount = 0;
                        Country.getSelectedList().clear();
                        if (currentPlayer.getDeployableTroops() <= 0) {
                            System.out.println("No more troops to deploy. Switching turns.");
                            switchTurnHandler();
                        }
                        else
                            System.out.println("You have " + currentPlayer.getDeployableTroops() + " left to deploy.");
                    }
                    break;
                default:
                    break;
            }
        }
    }
    
    private void attackPhaseHandler(int x, int y, String key) {
        if (key.equals("none")) {
            if (clickedCountry.getOwner() == currentPlayer)
                clickedCountry.selectedByClickHandler(phase);
        }
    }
    
    private void fortifyPhaseHandler(int x, int y, String key) {
        
    }
    
    private void deployPhaseInit() {
        phase = Phase.DEPLOY;
        currentPlayer.setDeployableTroops();
        deployAmount = 0;
    }
    
    private void attackPhaseInit() {
        phase = Phase.ATTACK;
    }
    
    private void fortifyPhaseInit() {
        phase = Phase.FORTIFY;
    }
    
    private void switchTurnHandler() {
        for (int i=0;i<players.length;i++) {
            if (currentPlayer == players[i]) {
                if (i == players.length-1) {
                    currentPlayer = players[0];
                    System.out.println("Successfully switched turns. Player " + i + " is now playing.");
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
                    System.out.println("Successfully switched turns. Player " + i + " is now playing.");
                    switch (phase) {
                    case DEPLOY:
                        deployPhaseInit();
                        break;
                    case ATTACK:
                        attackPhaseInit();
                        break;
                    case FORTIFY:
                        fortifyPhaseInit();
                        break;
                    }
                    break;
                }
            }
        }
        Country.switchedTurnHandler(phase);
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
        //System.out.println("Player 1 Troop Count: " + players[0].getTotalTroops() + "   Player 2 Troop Count: " + players[1].getTotalTroops());
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public Phase getPhase() {
        return phase;
    }
}
