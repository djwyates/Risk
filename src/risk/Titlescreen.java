
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
import static risk.Main.g;
import java.net.*;

public class Titlescreen {
    static private boolean mainActive;
    static private boolean singleActive;
    static private boolean multiActive;
    static private boolean drawnBoard;
    static private Image mainImage;
    static private Image multiImage;
    static private Image emberImage;
    static private SoundManager menuSounds = null;
    static private int fontSize;
    static int timeCount;
    
    
    static void reset(){
        Window.currentFrame.setSize(Window.MENU_WINDOW_WIDTH, Window.MENU_WINDOW_HEIGHT);
        mainActive = true;
        singleActive = false;
        multiActive = false;
        drawnBoard = false;
        mainImage = Toolkit.getDefaultToolkit().getImage("./TitleScreenGothic.png");
        multiImage = Toolkit.getDefaultToolkit().getImage("./multiMenu.png");
        emberImage = Toolkit.getDefaultToolkit().getImage("./Floating Embers.gif");
        menuSounds = new SoundManager();
        menuSounds.addSound("titlemusic.wav");
        menuSounds.addSound("swordClashTitleScreen.wav");
        menuSounds.addSound("multiButtonCheer.wav");
        menuSounds.addSound("terr_noise.wav");
        menuSounds.loop("titlemusic.wav");
        fontSize = 20;
        timeCount=0;
    }
    static void titlescreenHandler(int mousePos [],Main frame) throws FontFormatException, IOException {
        // Array of mouse position separated
        int x = mousePos[0];
        int y = mousePos[1];
        if (mainActive)
        { mainHandler(x, y, frame); }
        else if (singleActive)
        { singleHandler(x, y, frame); }
        else if (multiActive)
        { multiHandler(x, y, frame); }
    }
    
    static private void mainHandler(int x, int y, Main frame) throws FileNotFoundException, FontFormatException, IOException {
        // Draw
        g.drawImage(mainImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
        Button.drawMute(frame, 760,760);
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Viner.ttf"))).deriveFont(Font.PLAIN,fontSize));
        
        Button.mainHandler(x, y);
    }
    
    static private void singleHandler(int x, int y, Main frame) {
        if(!drawnBoard) {
            RiskMap riskMap = new RiskMap();
            Window.changeWindow(frame, 280, 60, Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, "Risk - Singleplayer");
            mainActive = false;
            drawnBoard = true;
        }
        RiskMap.draw(x, y, frame);
        RiskMap.mouseInCountryFunction(x, y);
    }
    
    static private void multiHandler(int x, int y, Main frame)throws FileNotFoundException, FontFormatException, IOException {
        if(Connect.gameStarted()==false) {
            g.drawImage(emberImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
            g.drawImage(multiImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
            Button.drawMute(frame, 760, 760);
            try {
                g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,45));
                g.setColor(Color.black);
                g.drawString(InetAddress.getLocalHost().getHostAddress(), 266, 495);
                g.drawString(Connect.getHost(), 266, 585);
                g.setColor(Color.red);
                g.drawString(InetAddress.getLocalHost().getHostAddress(), 261, 490);
                g.drawString(Connect.getHost(), 261, 580);
            }
            catch (UnknownHostException e)
            { e.printStackTrace(); }

            Button.multiHandler(x, y);
        }
        else { // If connected
            if(!drawnBoard) {
                Window.addWindow(Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, "Risk - Multiplayer");
                RiskMap riskMap = new RiskMap();
                frame.dispose();
                mainActive = false;
                drawnBoard = true;
            }
            
            if(RiskMap.contains(x, y)!=null)
                g.drawString(RiskMap.contains(x, y).name, x, y-15);
            
            RiskMap.draw(x, y, frame);
        }
    }
    
    static public void activateMain() {
        mainActive = true;
        singleActive = false;
        multiActive = false;
        drawnBoard = false;
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
}
