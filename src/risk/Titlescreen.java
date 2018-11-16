
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
import javax.swing.JFrame;

public class Titlescreen {
    static private boolean mainActive;
    static private boolean singleActive;
    static private boolean multiActive;
    static private boolean onSingleButton;
    static private boolean onMultiButton;
    static private boolean onExitButton;
    static private boolean onHomeButton;
    static private boolean onHostButton;
    static private boolean onJoinButton;
    static private boolean onMuteButton;
    static private boolean drawMute;
    static private boolean drawnBoard;
    static private Image mainImage;
    static private Image multiImage;
    static private Image emberImage;
    static private Image muteOnImage;
    static private Image muteOffImage;
    static private SoundManager menuSounds = null;
    static private int fontSize;
    static int timeCount = 0;
    
    static void reset(){
        Window.currentFrame.setSize(Window.MENU_WINDOW_WIDTH, Window.MENU_WINDOW_HEIGHT);
        mainActive = true;
        singleActive = false;
        multiActive = false;
        onSingleButton = false;
        onMultiButton = false;
        onExitButton = false;
        onHomeButton = false;
        onHostButton = false;
        onJoinButton = false;
        onMuteButton = false;
        drawMute = false;
        drawnBoard = false;
        mainImage = Toolkit.getDefaultToolkit().getImage("./TitleScreenGothic.png");
        multiImage = Toolkit.getDefaultToolkit().getImage("./multiMenu.png");
        emberImage = Toolkit.getDefaultToolkit().getImage("./Floating Embers.gif");
        muteOnImage = Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
        muteOffImage = Toolkit.getDefaultToolkit().getImage("./speakerIconMute.png");
        menuSounds = new SoundManager();
        menuSounds.addSound("titlemusic.wav");
        menuSounds.addSound("swordClashTitleScreen.wav");
        menuSounds.addSound("multiButtonCheer.wav");
        menuSounds.addSound("terr_noise.wav");
        menuSounds.loop("titlemusic.wav");
        fontSize = 20;
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
        if(!drawMute)
            g.drawImage(muteOnImage,760,760,20,20,frame);
        else
            g.drawImage(muteOffImage,760,760,20,20,frame);
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Viner.ttf"))).deriveFont(Font.PLAIN,fontSize));
        
        // Singleplayer button detection & sound effect
        if((x>280&&x<483&&y>412&&y<487)) {
            if(onSingleButton==false)
                menuSounds.play("swordClashTitleScreen.wav");
            onSingleButton = true;
            g.setColor(Color.white);
        } else {
            onSingleButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Singleplayer", 320, 455);
        
        // Multiplayer button detection & sound effect
        if((x>280&&x<483&&y>520&&y<595)) {
            if(onMultiButton==false)
                menuSounds.play("swordClashTitleScreen.wav");
            onMultiButton = true;
            g.setColor(Color.white);
        } else {
            onMultiButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Multiplayer", 320, 563);
        
        // Exit button detection & sound effect
        if(x>280 && x<483 && y>620 && y<700) {
            if(onExitButton==false)
                menuSounds.play("swordClashTitleScreen.wav");
            onExitButton = true;
            g.setColor(Color.white);
        } else {
            onExitButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Exit", 360, 668);
        
        // Mute button detection
        if(x>740 && x<800 && y>740 && y<800)
        { onMuteButton = true; }
        else
        { onMuteButton = false; }
        
    }
    
    static private void singleHandler(int x, int y, Main frame) {
        if(!drawnBoard) {
            RiskMap riskMap = new RiskMap(Toolkit.getDefaultToolkit().getImage("./riskMap.jpg"));
            frame.setBounds(280,60,Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT);
            frame.setTitle("Risk - Singleplayer");
            mainActive = false;
            drawnBoard = true;
        }
        RiskMap.draw(frame);
        RiskMap.fillBorders();
    }
    
    static private void multiHandler(int x, int y, Main frame)throws FileNotFoundException, FontFormatException, IOException {
        if(Connect.gameStarted()==false){
            g.drawImage(emberImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
            g.drawImage(multiImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MENU_WINDOW_HEIGHT,frame);
            if(!drawMute)
                g.drawImage(muteOnImage,760,760,20,20,frame);
            else
                g.drawImage(muteOffImage,760,760,20,20,frame);
            try {
                g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,45));
                g.setColor(Color.white);
                g.drawString(InetAddress.getLocalHost().getHostAddress(), 261, 490);
                g.drawString(Connect.getHost(), 261, 580);
            }
            catch (UnknownHostException e)
            { e.printStackTrace(); }

            // Home button detection
            if(x>13 && x<111 && y>730 && y<783)
            { onHomeButton = true; }
            else
            { onHomeButton = false; }

            // Host button detection
            if(x>256 && x<430 && y>666 && y<760)
            { onHostButton = true; }
            else
            { onHostButton = false; }

            // Join button detection
            if(x>477 && x<649 && y>666 && y<760)
            { onJoinButton = true; }
            else
            { onJoinButton = false; }

            // Mute button detection
            if(x>740 && x<800 && y>740 && y<800)
            { onMuteButton = true;}
            else 
            { onMuteButton = false;}
        }
        else //If connected 
        {
            if(!drawnBoard) {
                Window.addWindow(Window.MAP_WINDOW_WIDTH, Window.MAP_WINDOW_HEIGHT, "Risk - Multiplayer");
                RiskMap riskMap = new RiskMap(Toolkit.getDefaultToolkit().getImage("./riskMap.jpg"));
                frame.dispose();
                mainActive = false;
                drawnBoard = true;
            }
            RiskMap.draw(frame);
        }
    }
    
    static public void pressedButton() {
        if (onSingleButton) { activateSingleButton(); }
        else if (onMultiButton) { activateMultiButton(); }
        else if (onExitButton) { activateExitButton(); }
        else if (onHomeButton) { activateHomeButton(); }
        else if (onHostButton) { activateHostButton(); }
        else if (onJoinButton) { activateJoinButton(); }
        else if (onMuteButton) { activateMuteButton(); }
    }
    
    static private void activateSingleButton() {
        mainActive = false;
        singleActive = true;
        multiActive = false;
        onSingleButton = false;
        Connect.setGameStarted(true);
    }
    
    static private void activateMultiButton() {
        mainActive = false;
        singleActive = false;
        multiActive = true;
        onMultiButton = false;
    }
    
    static private void activateExitButton() {
        System.exit(0);
    }
    
    static private void activateHomeButton() {
        mainActive = true;
        singleActive = false;
        multiActive = false;
        if (singleActive) {
            
        }
        else if (multiActive)
            Connect.deleteAllCharsFromHost();
        onHomeButton = false;
    }
    
    static private void activateHostButton() {
        menuSounds.play("multiButtonCheer.wav");
        Connect.hostGame();
        onHostButton = false;
    }
    
    static private void activateJoinButton() {
        menuSounds.play("multiButtonCheer.wav");
        Connect.connectToGame();
        onJoinButton = false;
    }
    
    static private void activateMuteButton() {
        SoundManager.toggleMute();
        drawMute = !drawMute;
    }
    
    static public boolean isActive()
    { return mainActive || singleActive || multiActive; }
    
    static SoundManager getMenuSounds(){
        return menuSounds;
    }
}
