
package risk;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static risk.Risk.g;
import java.net.*;

public class Titlescreen {
    static private Gameplay game;
    static private boolean mainActive, singleActive, multiActive, startedGame;
    static private Image mainImage, multiImage, multiBackgroundImage;
    static private SoundManager menuSounds = null;
    static int timeCount;
    
    static void reset(){
        Window.currentFrame.setSize(Window.MENU_WINDOW_WIDTH, Window.MENU_WINDOW_HEIGHT);
        mainActive = true;
        singleActive = false;
        multiActive = false;
        startedGame = false;
        mainImage = Toolkit.getDefaultToolkit().getImage("./TitleScreenGothic.png");
        multiImage = Toolkit.getDefaultToolkit().getImage("./multiMenu.png");
        multiBackgroundImage = Toolkit.getDefaultToolkit().getImage("./Floating Embers.gif");
        menuSounds = new SoundManager();
        menuSounds.addSound("titlemusic.wav");
        menuSounds.addSound("swordClashTitleScreen.wav");
        menuSounds.addSound("multiButtonCheer.wav");
        menuSounds.addSound("terr_noise.wav");
        menuSounds.loop("titlemusic.wav");
        timeCount = 0;
    }
    
    static void titlescreenHandler(int x, int y, Risk frame) throws FontFormatException, IOException {
        if (mainActive)
        { mainHandler(x, y, frame); }
        else if (singleActive)
        { singleHandler(x, y, frame); }
        else if (multiActive)
        { multiHandler(x, y, frame); }
    }
    
    static private void mainHandler(int x, int y, Risk frame) throws FileNotFoundException, FontFormatException, IOException {
        g.drawImage(mainImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Viner.ttf"))).deriveFont(Font.PLAIN,20));
        Button.mainHandler(frame, x, y);
    }
    
    static private void singleHandler(int x, int y, Risk frame) {
        if(!startedGame)
            game = new Gameplay(frame, 4);
        game.drawAndSoundHandler(frame, x, y);
    }
    
    static private void multiHandler(int x, int y, Risk frame)throws FileNotFoundException, FontFormatException, IOException {
        if(Connect.gameStarted()==false) {
            g.drawImage(multiBackgroundImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
            g.drawImage(multiImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
            Button.multiHandler(frame, x, y);
        }
        else { // If connected
            // implement map drawing and gameplay
        }
    }
    
    static public void mouseClickHandler(Risk frame, int x, int y) {
        Button.mouseClickHandler(Window.currentFrame);
        if (game != null)
            game.mouseClickHandler(x, y);
    }
    
    static public void keyPressedHandler(String key) {
        if (game != null)
            game.keyPressedHandler(key);
    }
    
    static public void activateMain() {
        mainActive = true;
        singleActive = false;
        multiActive = false;
        startedGame = false;
    }
    
    static public void activateSingle() {
        mainActive = false;
        singleActive = true;
        multiActive = false;
    }
    
    static public void activateMulti() {
        mainActive = false;
        singleActive = false;
        multiActive = true;
    }
    
    static public void startedGame() {
        mainActive = false;
        startedGame = true;
    }
    static public boolean gameIsStarted(){
        return startedGame;
    }
    static public boolean isActive()
    { return mainActive || singleActive || multiActive; }
    
    static public boolean isMainActive()
    { return mainActive; }
    
    static public boolean isSingleActive()
    { return singleActive; }
    
    static public boolean isMultiActive()
    { return multiActive; }
    
    static SoundManager getMenuSounds()
    { return menuSounds; }
    
    static Gameplay getGame()
    { return(game); }
}
