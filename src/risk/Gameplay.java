
package risk;

public class Gameplay {
    
    RiskMap riskMap = null;
    private Player players[];
    private Player currentPlayer;
    
    Gameplay(Main frame, int numPlayers) {
        riskMap = new RiskMap();
        Window.changeWindow(frame, 280, 60, Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, "Risk - Singleplayer");
        players = new Player[numPlayers];
        for (int i=0;i<numPlayers;i++)
            players[i] = new Player();
        currentPlayer = players[0];
        assignCountries();
        Titlescreen.startedGame();
    }
    
    public void play(Main frame, int x, int y) {
        RiskMap.draw(x, y, frame);
        RiskMap.mouseInCountryFunction(x, y);
    }
    
    public void assignCountries() {
        int randomVal = 0;
        int assignedCountries[] = new int[2];
        for (Country country : RiskMap.getCountryList()) {
            for (int i=0;i<69;i++) {
                randomVal = (int) (Math.random()*2);
                if (randomVal == 0 && assignedCountries[0] < 35) {
                    country.setOwner(players[0]);
                    assignedCountries[0]++;
                }
                else if (assignedCountries[1] < 35) {
                    country.setOwner(players[1]);
                    assignedCountries[1]++;
                }
            }
        }
    }
}
