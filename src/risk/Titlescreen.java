
package risk;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static risk.Risk.g;

public class Titlescreen {
    static private final SoundManager MENU_SOUNDS = new SoundManager();
    static private final Image MAIN_IMAGE = Toolkit.getDefaultToolkit().getImage("./TitleScreenGothic.png");
    static private final Image SETUP_IMAGE = Toolkit.getDefaultToolkit().getImage("./setupscreen.png");
    static private final Image MULTI_IMAGE = Toolkit.getDefaultToolkit().getImage("./multiMenu.png");
    static private final Image MULTI_BACKGROUND_IMAGE = Toolkit.getDefaultToolkit().getImage("./Floating Embers.gif");
    static private Gameplay game;
    static private boolean mainActive = true;
    static private boolean setupActive = false;
    static private boolean instructionsActive = false;
    static private boolean startedGame = false;
    static int timeCount = 0;
    
    static void reset() {
        Window.currentFrame.setSize(Window.MENU_WINDOW_WIDTH, Window.MENU_WINDOW_HEIGHT);
        MENU_SOUNDS.clearSounds();
        MENU_SOUNDS.addSound("titlemusic.wav");
        MENU_SOUNDS.addSound("swordClashTitleScreen.wav");
        MENU_SOUNDS.addSound("multiButtonCheer.wav");
        MENU_SOUNDS.addSound("terr_noise.wav");
        MENU_SOUNDS.loop("titlemusic.wav");
    }
    
    static void titlescreenHandler(Risk frame, int x, int y) throws FontFormatException, IOException {
        if (mainActive)
            mainHandler(x, y, frame);
        else if (setupActive)
            setupHandler(x, y, frame);
        else if (instructionsActive)
            instructionsHandler(x, y, frame);
        else if (startedGame)
            game.drawAndSoundHandler(frame, x, y);
    }
    
    static private void mainHandler(int x, int y, Risk frame) throws FileNotFoundException, FontFormatException, IOException {
        g.drawImage(MAIN_IMAGE,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Viner.ttf"))).deriveFont(Font.PLAIN,20));
        Button.mainHandler(frame, x, y);
    }
    
    static private void setupHandler(int x, int y, Risk frame) throws FileNotFoundException, FontFormatException, IOException {
        g.drawImage(SETUP_IMAGE,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
        Button.setupHandler(x, y);
    }
    
    static private void instructionsHandler(int x, int y, Risk frame)throws FileNotFoundException, FontFormatException, IOException {
        if(Connect.gameStarted()==false) {
            g.drawImage(MULTI_BACKGROUND_IMAGE,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
            g.drawImage(MULTI_IMAGE,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
            Button.instructionsHandler(frame, x, y);
        }
        else { // If connected
            // implement map drawing and gameplay
        }
    }
    
    static public void startGame(Risk frame) {
        game = new Gameplay(frame);
        mainActive = false;
        setupActive = false;
        instructionsActive = false;
        startedGame = true;
    }
    
    static public void mouseClickHandler(Risk frame, int x, int y) {
        Button.mouseClickHandler(frame);
        if (startedGame)
            game.mouseClickHandler(x, y);
    }
    
    static public void keyPressedHandler(String key) {
        if (game != null)
            game.keyPressedHandler(key);
    }
    
    static public void activateMain() {
        mainActive = true;
        setupActive = false;
        instructionsActive = false;
        startedGame = false;
    }
    
    static public void activateSingle() {
        mainActive = false;
        setupActive = true;
        instructionsActive = false;
    }
    
    static public void activateMulti() {
        mainActive = false;
        setupActive = false;
        instructionsActive = true;
    }
    
    static public void startedGame() {
        mainActive = false;
        startedGame = true;
    }
    
    static public boolean gameIsStarted(){
        return startedGame;
    }
    
    static public boolean isActive() {
        return mainActive || setupActive || instructionsActive;
    }
    
    static public boolean isMainActive() {
        return mainActive;
    }
    
    static public boolean isSingleActive() {
        return setupActive;
    }
    
    static public boolean isMultiActive() {
        return instructionsActive;
    }
    
    static SoundManager getMenuSounds() {
        return MENU_SOUNDS;
    }
    
    static Gameplay getGame() {
        return(game);
    }
}
