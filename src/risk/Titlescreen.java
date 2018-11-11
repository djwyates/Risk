
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
    static private Image mainImage;
    static private Image multiImage;
    static private Image emberImage;
    static private Image muteImage;
    static private sound menuMusic = null;
    static private sound buttonSound = null;
    static private boolean mute=false;
    static int timeCount = 0;
    
    static void reset(){
        mainActive=true;
        fontSize=20;
        onFirstButton=false;
        onSecondButton=false;
        onThirdButton=false;
        mainImage=Toolkit.getDefaultToolkit().getImage("./TitleScreenGothic.png");
        multiImage=Toolkit.getDefaultToolkit().getImage("./TitleScreenGothic.png");
        emberImage=Toolkit.getDefaultToolkit().getImage("./Floating Embers.gif");
        muteImage=Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
        menuMusic=new sound("titlemusic.wav");
        mute=false;
        
        timeCount=0;
    }
    
    static void drawMenu(int mousePos [],Main m, String host){
        //Array of mouse position separated
        int x = mousePos[0];
        int y = mousePos[1];
        if (mainActive)
        { drawMain(x, y, m); }
        else if (singleActive)
        { drawSingle(x, y, m); }
        else if (multiActive)
        { drawMulti(x, y, m, host); }
    }
    
    static private void drawMain(int x, int y, Main m) {
        // Draw backgroung and set font
        //g.drawImage(emberImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
        g.drawImage(mainImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
        g.drawImage(muteImage,760,760,20,20,m);
        g.setFont(new Font("Viner Hand ITC", Font.ROMAN_BASELINE, fontSize));
        
        if(mute)
            menuMusic=null;
        else if(menuMusic==null)
            menuMusic=new sound("titlemusic.wav");
        
        // Singleplayer button
        if((x>280&&x<483&&y>412&&y<487)) {
            if(onFirstButton==false && !mute){
                buttonSound=new sound("swordClashTitleScreen.wav");
            }
            onFirstButton = true;
            g.setColor(Color.white);
            
        } else {
            onFirstButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Singleplayer", 320, 450);
        
        // Multiplayer button
        if((x>280&&x<483&&y>520&&y<595)) {
            if(onSecondButton==false && !mute){
                buttonSound=new sound("swordClashTitleScreen.wav");
            }
            onSecondButton = true;
            g.setColor(Color.white);
        } else {
            onSecondButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Multiplayer", 320, 560);
        
        // Exit button
        if(x>280 && x<483 && y>620 && y<700) {
            if(onThirdButton==false && !mute){
                buttonSound=new sound("swordClashTitleScreen.wav");
            }
            onThirdButton = true;
            g.setColor(Color.white);
        } else {
            onThirdButton = false;
            g.setColor(Color.red);
        }
        g.drawString("Exit", 360, 665);
        
        
        g.setColor(Color.red);
        timeCount++;
        System.out.println(timeCount);
    }
    
    static private void drawSingle(int x, int y, Main m) {
        System.out.println("In drawSingle");
        g.drawImage(mainImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
    }
    
    static private void drawMulti(int x, int y, Main m, String host) {
        
   
          
        g.drawImage(multiImage,0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT,m);
        //g.drawString("Enemies IP Address:", 50, 450);
        //g.draw3DRect(260, 430, 120, 30, true);
        
        try {
            g.setFont(new Font("Viner Hand ITC", Font.ROMAN_BASELINE, 30));
            g.setColor(Color.orange);
            g.drawString("YOUR IP ADDRESS: " + InetAddress.getLocalHost().getHostAddress(), 50, 450);
            g.drawString("ENTER ENEMY'S IP ADDRESS: " + host, 50, 500);
        }
        catch (UnknownHostException e)
        { e.printStackTrace(); }
                 
     
    }
    
    static public void pressedButton() {
        if (onFirstButton) { onFirstButton = false; activateFirstButton(); }
        else if (onSecondButton) { onSecondButton = false; activateSecondButton(); }
        else if (onThirdButton) { onThirdButton = false; activateThirdButton(); }
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
    
    static public void checkMusicLoop() {
        if (!mute && menuMusic.donePlaying)
            menuMusic = new sound("titlemusic.wav");
    }
    
    static public boolean isActive()
    { return mainActive || singleActive || multiActive; }
    
    static boolean getMute()
    { return mute; }
    
    static void setMute(boolean m)
    { mute=m; }
}