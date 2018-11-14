
package risk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import static risk.FrameManager.g;
import java.net.*;
import javax.swing.JFrame;

public class Titlescreen {
    static private boolean boardActive;
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
    static private Image mainImage;
    static private Image multiImage;
    static private Image emberImage;
    static private Image muteImage;
    static private Image BoardImage;
    static private Image Wall;
    static private SoundManager menuSounds = null;
    static private SoundManager buttonSound = null;
    static private Image e;
    //MULTIPLAYER SOUNDS
    static private SoundManager multiButtonSound = null;
    static int timeCount = 0;
    //Dice Variables
    static private int Dice;
    static private Image DiceImageOne;
    static private Image DiceImageTwo;
    static private Image DiceImageThree;
    static private Image DiceImageFour;
    static private Image DiceImageFive;
    static private Image DiceImageSix;
    
    static void reset(){
        boardActive = true;
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
        muteImage=Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
        BoardImage = Toolkit.getDefaultToolkit().getImage("./riskMap.jpg");
        Wall =  Toolkit.getDefaultToolkit().getImage("./WoodBack.jpg");
        menuSounds=new SoundManager();
        menuSounds.addSound("titlemusic.wav");
        menuSounds.addSound("swordClashTitleScreen.wav");
        menuSounds.addSound("multiButtonCheer.wav");
        menuSounds.loop("titlemusic.wav");
        
        //Dice Pictures
        DiceImageOne = Toolkit.getDefaultToolkit().getImage("./DiceOne.png");
        DiceImageTwo = Toolkit.getDefaultToolkit().getImage("./DiceTwo.png");
        DiceImageThree = Toolkit.getDefaultToolkit().getImage("./DiceThree.png");
        DiceImageFour = Toolkit.getDefaultToolkit().getImage("./DiceFour.png");
        DiceImageFive = Toolkit.getDefaultToolkit().getImage("./DiceFive.png");
        DiceImageSix = Toolkit.getDefaultToolkit().getImage("./DiceSix.png");
        
       Dice = (int)(Math.random()*6+1);
        
        timeCount=0;
    }
    
    static void drawDice(FrameManager f,int x,int y){
        
        if(!boardActive){
            if(Dice == 1)
                  g.drawImage(DiceImageOne,1186,65,150,150,f);
            else if(Dice == 2)
                 g.drawImage(DiceImageTwo,1186,65,150,150,f);
            else if(Dice == 3)
                 g.drawImage(DiceImageThree,1186,65,150,150,f);
            else if(Dice == 4)
                 g.drawImage(DiceImageFour,1186,65,150,150,f);
            else if(Dice == 5)
                 g.drawImage(DiceImageFive,1186,65,150,150,f);
            else if(Dice == 6)
                 g.drawImage(DiceImageSix,1186,65,150,150,f);
            
        }
    }
    static void ChangeDice(int x,int y){
        if(x > 1195 && x < 1326 && y > 74 && y < 204){
            int _dice = Dice;
                while(Dice == _dice){
                    Dice =(int)(Math.random()*6+1); 
                }
        }
    }
    
    static void drawMenu(int mousePos [],FrameManager m){
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
    
    static private void mainHandler(int x, int y, FrameManager m) {
        // Draw main
        g.drawImage(mainImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MAIN_WINDOW_HEIGHT,m);
        g.drawImage(muteImage,760,760,20,20,m);
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
        if(x>760 && x<800 && y>760 && y<800) {
            onMuteButton = true;
        } else {
            onMuteButton = false;
        }
        
        g.setColor(Color.red);
        timeCount++;
        //System.out.println(timeCount);
    }
    
       
    static private void singleHandler(int x, int y, FrameManager frame) {
        int boardWidth = 1371;
        int boardHeight = 912;
        
        if(boardActive){
            FrameManager.addWindow(boardWidth, boardHeight);
            mainActive = false;
            boardActive = false;
            frame.dispose();
        }
        g.drawImage(Wall,0,0,boardWidth,boardHeight,frame);
        g.drawImage(BoardImage,0,0,boardWidth-200,boardHeight,frame);
        drawDice(frame,x,y);
    }
    
    static private void multiHandler(int x, int y, FrameManager frame) {
        g.drawImage(emberImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MAIN_WINDOW_HEIGHT,frame);
        g.drawImage(multiImage,0,0,Window.MENU_WINDOW_WIDTH,Window.MAIN_WINDOW_HEIGHT,frame);
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
        if (onSingleButton) { onSingleButton = false; activateFirstButton(); }
        else if (onMultiButton) { onMultiButton = false; activateSecondButton(); }
        else if (onExitButton) { onExitButton = false; activateThirdButton(); }
        else if (onHomeButton) { onHomeButton = false; mainActive=true; multiActive=false; Connect.deleteAllCharsFromHost(); }
        else if (onHostButton) { onHostButton = false; menuSounds.play("multiButtonCheer.wav"); } // todo: implement Connect.hostGame();
        else if (onJoinButton) { onJoinButton = false; menuSounds.play("multiButtonCheer.wav"); } // todo: implement Connect.connectToGame();
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
