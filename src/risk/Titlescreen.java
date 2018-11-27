
package risk;

import java.awt.*;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static risk.Risk.g;

public class Titlescreen {
    static private boolean InstuctionsConnected ;
    static private final SoundManager MENU_SOUNDS = new SoundManager();
    static private final Image MAIN_IMAGE = Toolkit.getDefaultToolkit().getImage("./TitleScreenGothic.png");
    static private final Image SETUP_IMAGE = Toolkit.getDefaultToolkit().getImage("./setupscreen.png");
    static private final Image INSTRUCTIONS_IMAGE = Toolkit.getDefaultToolkit().getImage("./multiMenu.png");
    static private final Image INSTRUCTIONS_BACKGROUND_IMAGE = Toolkit.getDefaultToolkit().getImage("./Floating Embers.gif");
    static private Gameplay game;
    static private SoundManager menuSounds = null;
    static private boolean mainActive = true;
    static private boolean setupActive = false;
    static private boolean instructionsActive = false;
    static private boolean startedGame = false;
    static private int customizePlayerNum = 1;
    
    static void reset() {
        InstuctionsConnected = true;
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
//        else if (instructionsActive)
//            instructionsHandler(x, y, frame);
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
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static public void instructionsHand(int x, int y,Graphics2D g)throws FileNotFoundException, FontFormatException, IOException{
        if(instructionsActive)
        instructionsHandler(x,y,g);
    }

    static private void instructionsHandler(int x, int y,Graphics2D g)throws FileNotFoundException, FontFormatException, IOException {
//        if(Connect.gameStarted()==false) {     
//        }
//        else { 
//        }
        
           // if(!InstuctionsConnected){
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, Window.MENU_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT);
           // InstuctionsConnected = false;
      //  }
      
    //Title (Risk Rules)
            g.setColor(Color.black);
            g.setFont(new Font("Arial",Font.PLAIN,50));
            g.drawString("Risk Rules",300,90);         
    //1st Sentence (Spilts up Countries)
            g.drawString("1. Countries are randomly divided between the amount of players.",50,130);         
    //2nd Sentence ()
            g.drawString("2.",50,160);         
    //3rd Sentence ()
            g.drawString("3.",50,190);         
    //4th Sentence ()
            g.drawString("4.",50,220);         
    //5th Sentence ()
            g.drawString("5.",50,250);         
    //6th Sentence ()
            g.drawString("6.",50,280);         
    //7th Sentence ()
            g.drawString("7.",50,310);         
    //8th Sentence ()
            g.drawString("8.",50,340);         
    //9th Sentence ()
            g.drawString("9.",50,370);         
    //10th Sentence ()
            g.drawString("10.",50,400);         
    //11th Sentence ()
            g.drawString("11.",50,430);         
    //12th Sentence ()
            g.drawString("12.",50,460);         
    //13th Sentence ()
            g.drawString("13.",50,490);         
    //14th Sentence ()
            g.drawString("14.",50,520);         
    //15th Sentence ()
            g.drawString("15.",50,550);         
    //16th Sentence ()
            g.drawString("16.",50,580);         
    //17th Sentence ()
            g.drawString("17.",50,610);         
    //18th Sentence ()
            g.drawString("18.",50,640);         
    //19th Sentence ()
            g.drawString("19.",50,670);         
    //20th Sentence ()
            g.drawString("20.",50,700);         
    //21th Sentence ()
            g.drawString("21.",50,730);         
            
    }
        
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static public void startGame(Risk frame) {
        game = new Gameplay(frame);
        mainActive = false;
        setupActive = false;
        instructionsActive = false;
        startedGame = true;
    }
    
    static public void mouseClickHandler(Risk frame, int x, int y) {
        Button.mouseClickHandler(frame, x, y);
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
    
    static public boolean isSetupActive() {
        return setupActive;
    }
    
    static public boolean isMultiActive() {
        return instructionsActive;
    }
    
    static public void addCustomizePlayerNum(int inc) {
        customizePlayerNum += inc;
    }
    
    static public int getCustomizePlayerNum() {
        return customizePlayerNum;
    }
    
    static SoundManager getMenuSounds() {
        return MENU_SOUNDS;
    }
    
    static Gameplay getGame() {
        return(game);
    }
}
