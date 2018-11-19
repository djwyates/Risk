
package risk;

public class Gameplay {
    
    static private enum Phase { DEPLOY, ATTACK, FORTIFY }
    Phase phase;
    RiskMap riskMap = null;
    private Player players[];
    private Player currentPlayer;
    
    Gameplay(Risk frame, int numPlayers) {
        // Handles drawing & window
        riskMap = new RiskMap();
        Window.changeWindow(frame, Window.USER_SCREEN_WIDTH/6, Window.USER_SCREEN_HEIGHT/20, Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, "Risk - Singleplayer");
        // Handles players
        players = new Player[numPlayers];
        for (int i=0;i<numPlayers;i++)
            players[i] = new Player();
        currentPlayer = players[0];
        // Handles countries
        assignCountries();
        // Handles game phases
        phase = Phase.DEPLOY;
        // Handles other classes
        Titlescreen.startedGame();
    }
    
    public void drawAndSoundHandler(Risk frame, int x, int y) {
        Country.setCountryOnMouse(x, y);
        RiskMap.draw(frame, x, y);
        RiskMap.mouseInCountryHandler(x, y);
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
    
    private void deployPhaseHandler() {
        Country country = Country.getCountryOnMouse();
        if (country != null && country.getOwner() == currentPlayer) {
            
        }
    }
    
    private void attackPhaseHandler() {
        
    }
    
    private void fortifyPhaseHandler() {
        
    }
    
    private void assignCountries() {
        int randomVal = 0;
        int assignedCountries[] = new int[2];
        for (Country country : RiskMap.getCountryList()) {
            randomVal = (int) (Math.random()*2);
            if (randomVal == 0 && assignedCountries[0] < 35) {
                country.setOwner(players[0]);
                assignedCountries[0]++;
            }
            else if (assignedCountries[1] < 35) {
                country.setOwner(players[1]);
                assignedCountries[1]++;
            }
            else {
                country.setOwner(players[0]);
            }
        }
    }
}
