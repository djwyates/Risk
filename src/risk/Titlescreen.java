
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
    static private int fontSize;
    static private boolean onSingleButton;
    static private boolean onMultiButton;
    static private boolean onExitButton;
    static private boolean onHomeButton;
    static private boolean onHostButton;
    static private boolean onJoinButton;
    static private boolean onMuteButton;
    static private boolean mute;
    static private Image mainImage;
    static private Image multiImage;
    static private Image emberImage;
    static private Image speakerOn;
    static private Image speakerOff;
    static private SoundManager menuSounds = null;
    static private SoundManager buttonSound = null;
    static private Image e;
    //MULTIPLAYER SOUNDS
    static private SoundManager multiButtonSound = null;
    static int timeCount = 0;
    
    static void reset(){
        mute=false;
        mainActive=true;
        fontSize=20;
        onSingleButton=false;
        onMultiButton=false;
        onExitButton=false;
        onHomeButton=false;
        onHostButton=false;
        onJoinButton=false;
        onMuteButton=false;
        mainImage=Toolkit.getDefaultToolkit().getImage("./TitleScreenGothic.png");
        multiImage=Toolkit.getDefaultToolkit().getImage("./multiMenu.png");
        emberImage=Toolkit.getDefaultToolkit().getImage("./Floating Embers.gif");
        speakerOn=Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
        speakerOff=Toolkit.getDefaultToolkit().getImage("./speakerIconMute.png");
        menuSounds=new SoundManager();
        menuSounds.addSound("titlemusic.wav");
        menuSounds.addSound("swordClashTitleScreen.wav");
        menuSounds.addSound("multiButtonCheer.wav");
        menuSounds.loop("titlemusic.wav");
        
        timeCount=0;
    }
    
    static void drawMenu(int mousePos [],Main m) throws FontFormatException, IOException{
        //Array of mouse position separated
        int x = mousePos[0];
        int y = mousePos[1];
        //if (isActive())
        //    menuSounds.loop("titlemusic.wav");
        if (mainActive)
        { mainHandler(x, y, m); }
        else if (singleActive)
        { singleHandler(x, y, m); }
        else if (multiActive)
        { multiHandler(x, y, m); }
    }
    
    static private void mainHandler(int x, int y, Main m) {
        // Draw main
        g.drawImage(mainImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
        if(mute==false)
            g.drawImage(speakerOn,760,760,20,20,m);
        else
            g.drawImage(speakerOff,760,760,20,20,m);
        g.setFont(new Font("Viner Hand ITC", Font.ROMAN_BASELINE, fontSize));
        
        // Singleplayer button detection & sound effect
        if((x>280&&x<483&&y>412&&y<487)) {
            if(onSingleButton==false) {
                menuSounds.play("swordClashTitleScreen.wav");
            }
            onSingleButton = true;
            g.setColor(Color.white);
        } else {
            onSingleButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Singleplayer", 320, 450);
        
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
        g.drawString("Multiplayer", 320, 560);
        
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
        g.drawString("Exit", 360, 665);
        
        // Mute button detection
        if(x>740 && x<800 && y>740 && y<800) {
            onMuteButton = true;
        } else {
            onMuteButton = false;
        }
        
        g.setColor(Color.red);
        timeCount++;
        //System.out.println(timeCount);
    }
    
    static private void singleHandler(int x, int y, Main m) {
        g.drawImage(mainImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
    }
    
    static private void multiHandler(int x, int y, Main m) throws FileNotFoundException, FontFormatException, IOException {
        g.drawImage(emberImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
        g.drawImage(multiImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
        if(!mute)
            g.drawImage(speakerOn,760,760,20,20,m);
        else
            g.drawImage(speakerOff,760,760,20,20,m);
        try {
            g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("Allan.ttf"))).deriveFont(Font.PLAIN,45));
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
        {onMuteButton = true;} 
        else 
        {onMuteButton = false;}
    }
    
    static public void pressedButton() {
        if (onSingleButton) { onSingleButton = false; activateFirstButton(); }
        else if (onMultiButton) { onMultiButton = false; activateSecondButton(); }
        else if (onExitButton) { onExitButton = false; activateThirdButton(); }
        else if (onHomeButton) { onHomeButton = false; mainActive=true; multiActive=false; Connect.deleteAllCharsFromHost(); }
        else if (onHostButton) { onHostButton = false; menuSounds.play("multiButtonCheer.wav"); Connect.hostGame();} // todo: implement Connect.hostGame();
        else if (onJoinButton) { onJoinButton = false; menuSounds.play("multiButtonCheer.wav"); Connect.connectToGame();} // todo: implement Connect.connectToGame();
        else if (onMuteButton) { SoundManager.toggleMute(); if(mute==false)mute=true; else mute=false;}
    }
    
    static private void activateFirstButton() {
        singleActive = true;
        mainActive = false;
        multiActive = false;
    }
    
    static private void activateSecondButton() {
        multiActive = true;
        mainActive = false;
        singleActive = false;
    }
    
    static private void activateThirdButton()
    { System.exit(0); }
    
    static public boolean isActive()
    { return mainActive || singleActive || multiActive; }
}
