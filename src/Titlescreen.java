
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
    static private final SoundManager MENU_SOUNDS = new SoundManager();
    static private final Image MAIN_IMAGE = Toolkit.getDefaultToolkit().getImage("./images/titleScreen.png");
    static private final Image SETUP_IMAGE = Toolkit.getDefaultToolkit().getImage("./images/gameSetup.png");
    static private final Image INSTRUCTIONS_IMAGE = Toolkit.getDefaultToolkit().getImage("./images/instructions.png");
    static private final Image HbackImage = Toolkit.getDefaultToolkit().getImage("./images/backButtonHighlight.png");
    static private final Image backImage = Toolkit.getDefaultToolkit().getImage("./images/backButton.png");
    static private Gameplay game = null;
    static private boolean mainActive = true;
    static private boolean setupActive = false;
    static private boolean instructionsActive = false;
    static private boolean gameStarted = false;
    static private int customizePlayerNum = 1;

    static void reset() {
        MENU_SOUNDS.clearSounds();
        MENU_SOUNDS.addSound("./sounds/themeMusic.wav");
        MENU_SOUNDS.addSound("./sounds/swordClash.wav");
        MENU_SOUNDS.addSound("./sounds/cheer.wav");
        MENU_SOUNDS.addSound("./sounds/tap.wav");
        MENU_SOUNDS.loop("./sounds/themeMusic.wav");
    }

    static void titlescreenHandler(Risk frame, int x, int y) throws FontFormatException, IOException {
        if (mainActive)
            mainHandler(x, y, frame);
        else if (setupActive)
            setupHandler(x, y, frame);
        else if (instructionsActive)
            instructionsHandler(x, y, frame);
        else if (gameStarted)
            game.drawAndSoundHandler(frame, x, y);
    }

    static private void mainHandler(int x, int y, Risk frame) throws FileNotFoundException, FontFormatException, IOException {
        g.drawImage(MAIN_IMAGE,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("fonts/viner.ttf"))).deriveFont(Font.PLAIN,20));
        Button.mainHandler(frame, x, y);
    }

    static private void setupHandler(int x, int y, Risk frame) throws FileNotFoundException, FontFormatException, IOException {
        g.drawImage(SETUP_IMAGE,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
        g.drawImage(backImage,20,720, frame);
        Button.setupHandler(x, y);
        if(x > 17 && x < 143 && y > 721 && y < 784)
                g.drawImage(HbackImage,20, 720, frame);
        Button.drawMute(frame, 760, 760);
    }

    static private void instructionsHandler(int x, int y, Risk frame)throws FileNotFoundException, FontFormatException, IOException {
        g.drawImage(INSTRUCTIONS_IMAGE, 0, 0, frame);
        g.drawImage(backImage,20, 720, frame);
        Button.instructionsHandler(frame, x, y);
        if(x > 17 && x < 143 && y > 721 && y < 784)
                g.drawImage(HbackImage,20, 720, frame);

        Button.drawMute(frame, 760, 760);
//
        g.setColor(Color.green);
        g.setFont(new Font("Arial",Font.PLAIN,40));
        g.drawString("Deploy",50,130);
//
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,17));
        g.drawString(". This Phase is the first phase of your turn.",50,160);
//
        g.drawString(". Click one of your countries and type how many troops you would like to deploy.",50,190);
//
        g.drawString(". The More Players you decide to have, the less troops you can deploy.",50,220);
//
        g.setColor(Color.red);
        g.setFont(new Font("Arial",Font.PLAIN,40));
        g.drawString("Attacking",50,260);
//
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,17));
        g.drawString(".  This Phase comes after Deploy",50,280);
//
        g.drawString(".  The object of an attack is to capture a territory by defeating all the opposing armies in thqat country.",50,310);
//
        g.drawString(".  You can only attack territories adjacent to you. ",50,340);
//
        g.drawString(".  Click on the country you would like to attack with," , 50 , 370);
        g.drawString("and after click an enemy's territory you would like to attack. ",50,390);
//
        g.drawString(".   If the defender's troops are higher than yours, you lose.",50,420);
//
        g.drawString(".  After an attack, the player can decide to attack again or pass.",50,450);

        g.setColor(Color.blue);
        g.setFont(new Font("Arial",Font.PLAIN,40));
        g.drawString("Fortify",50,490);
//
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.PLAIN,17));
        g.drawString(".  This Phase comes after Attack.",50,520);
//
        g.drawString(".  You can transfer troops from one of your countries, to another one of your countries.",50,550);
//
        g.drawString(".  Click on the country you want to take troops from and click another country.",50,580);
        g.drawString("you would like to deposit troops into, then enter the amount of troops to give.",50,600);
//
        g.drawString(".  You may only transfer troops once per turn.",50,630);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static public void startGame(Risk frame) {
        game = new Gameplay(frame);
        mainActive = false;
        setupActive = false;
        instructionsActive = false;
        gameStarted = true;
    }

    static public void mouseClickHandler(Risk frame, int x, int y) {
        Button.mouseClickHandler(frame, x, y);
        if (gameStarted)
            game.mouseClickHandler(x, y);
    }

    static public void mouseDraggedHandler(Risk frame, int x, int y) {
        Button.mouseDraggedHandler(frame, x, y);
    }

    static public void keyPressedHandler(String key) {
        if (game != null)
            game.keyPressedHandler(key);
    }

    static public void activateMain() {
        mainActive = true;
        setupActive = false;
        instructionsActive = false;
        game = null;
        gameStarted = false;
        if (game != null)
            game.victoryAchieved = false;
    }

    static public void activateSetup() {
        mainActive = false;
        setupActive = true;
        instructionsActive = false;
    }

    static public void activateInstructions() {
        mainActive = false;
        setupActive = false;
        instructionsActive = true;
    }

    static public void startedGame() {
        mainActive = false;
        gameStarted = true;
    }

    static public boolean gameStarted(){
        return gameStarted;
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

    static public boolean isInstructionsActive() {
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
