
package risk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import static risk.Main.g;
import java.net.*;

public class Titlescreen {
    static private boolean mainActive;
    static private boolean singleActive;
    static private boolean multiActive;
    static private int fontSize;
    static private boolean onFirstButton;
    static private boolean onSecondButton;
    static private boolean onThirdButton;
    static private boolean onHomeButton;
    static private boolean onHostButton;
    static private boolean onJoinButton;
    static private boolean onMuteButton;
    static private Image mainImage;
    static private Image multiImage;
    static private Image emberImage;
    static private Image muteImage;
    static private SoundManager menuSounds = null;
    static private SoundManager buttonSound = null;
    static private Image e;
    //MULTIPLAYER SOUNDS
    static private SoundManager multiButtonSound = null;
    static int timeCount = 0;
    
    static void reset(){
        mainActive=true;
        fontSize=20;
        onFirstButton=false;
        onSecondButton=false;
        onThirdButton=false;
        onHomeButton=false;
        onHostButton=false;
        onJoinButton=false;
        onMuteButton=false;
        mainImage=Toolkit.getDefaultToolkit().getImage("./TitleScreenGothic.png");
        multiImage=Toolkit.getDefaultToolkit().getImage("./multiMenu.png");
        emberImage=Toolkit.getDefaultToolkit().getImage("./Floating Embers.gif");
        muteImage=Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
        menuSounds=new SoundManager();
        menuSounds.addSound("titlemusic.wav");
        menuSounds.addSound("swordClashTitleScreen.wav");
        menuSounds.addSound("multiButtonCheer.wav");
        menuSounds.loop("titlemusic.wav");
        
        timeCount=0;
    }
    
    static void drawMenu(int mousePos [],Main m){
        //Array of mouse position separated
        int x = mousePos[0];
        int y = mousePos[1];
        //if (isActive())
        //    menuSounds.loop("titlemusic.wav");
        if (mainActive)
        { drawMain(x, y, m); }
        else if (singleActive)
        { drawSingle(x, y, m); }
        else if (multiActive)
        { drawMulti(x, y, m); }
    }
    
    static private void drawMain(int x, int y, Main m) {
        // Draw backgroung and set font
        
        g.drawImage(mainImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
        g.drawImage(muteImage,760,760,20,20,m);
        g.setFont(new Font("Viner Hand ITC", Font.ROMAN_BASELINE, fontSize));
        
        // Singleplayer button detection
        if((x>280&&x<483&&y>412&&y<487)) {
            if(onFirstButton==false) {
                menuSounds.play("swordClashTitleScreen.wav");
            }
            onFirstButton = true;
            g.setColor(Color.white);
        } else {
            onFirstButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Singleplayer", 320, 450);
        
        // Multiplayer button detection
        if((x>280&&x<483&&y>520&&y<595)) {
            if(onSecondButton==false)
                menuSounds.play("swordClashTitleScreen.wav");
            onSecondButton = true;
            g.setColor(Color.white);
        } else {
            onSecondButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Multiplayer", 320, 560);
        
        // Exit button detection
        if(x>280 && x<483 && y>620 && y<700) {
            if(onThirdButton==false)
                menuSounds.play("swordClashTitleScreen.wav");
            onThirdButton = true;
            g.setColor(Color.white);
        } else {
            onThirdButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Exit", 360, 665);
        
        // Mute button detection
        if(x>760 && x<800 && y>760 && y<800) {
            onMuteButton = true;
        } else {
            onMuteButton = false;
        }
        
        g.setColor(Color.red);
        timeCount++;
        //System.out.println(timeCount);
    }
    
    static private void drawSingle(int x, int y, Main m) {
        g.drawImage(mainImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
    }
    
    static private void drawMulti(int x, int y, Main m) {
        g.drawImage(emberImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
        g.drawImage(multiImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
        try {
            g.setFont(new Font("Allan", Font.ROMAN_BASELINE, 45));
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
    }
    
    static public void pressedButton() {
        if (onFirstButton) { onFirstButton = false; activateFirstButton(); }
        else if (onSecondButton) { onSecondButton = false; activateSecondButton(); }
        else if (onThirdButton) { onThirdButton = false; activateThirdButton(); }
        else if (onHomeButton) { onHomeButton = false; mainActive=true; multiActive=false; }
        else if (onHostButton) { menuSounds.play("multiButtonCheer.wav"); }
        else if (onJoinButton) { menuSounds.play("multiButtonCheer.wav"); }
        else if (onMuteButton) { SoundManager.toggleMute(); }
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
