
package risk;

import java.awt.Color;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import static risk.Risk.g;

public class Button {
    static private Image muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
    static private Image backImage = Toolkit.getDefaultToolkit().getImage("./backButton.png");
    static private boolean muteOn = false;
    static private boolean onSingle = false;
    static private boolean onMulti = false;
    static private boolean onExit = false;
    static private boolean onHome = false;
    static private boolean onHost = false;
    static private boolean onJoin = false;
    static private boolean onMute = false;
    static private boolean onBack = false;
    
    static public void mouseClickHandler(Risk frame) {
        if (onSingle) { activateSingleButton(); }
        else if (onMulti) { activateMultiButton(); }
        else if (onExit) { activateExitButton(); }
        else if (onHome) { activateHomeButton(); }
        else if (onHost) { activateHostButton(); }
        else if (onJoin) { activateJoinButton(); }
        else if (onMute) { activateMuteButton(); }
        else if (onBack) { activateBackButton(frame); }
    }
    
    static public void mainHandler(int x, int y) {
        // Singleplayer button detection & sound effect
        if((x>280&&x<483&&y>412&&y<487)) {
            if(!onSingle)
                Titlescreen.getMenuSounds().play("swordClashTitleScreen.wav");
            onSingle = true;
            g.setColor(Color.white);
        } else {
            onSingle = false;
            g.setColor(Color.red);
        }
        g.drawString("Singleplayer", 320, 455);
        
        // Multiplayer button detection & sound effect
        if((x>280&&x<483&&y>520&&y<595)) {
            if(!onMulti)
                Titlescreen.getMenuSounds().play("swordClashTitleScreen.wav");
            onMulti = true;
            g.setColor(Color.white);
        } else {
            onMulti = false;
            g.setColor(Color.red);
        }
        g.drawString("Multiplayer", 320, 563);
        
        // Exit button detection & sound effect
        if(x>280 && x<483 && y>620 && y<700) {
            if(!onExit)
                Titlescreen.getMenuSounds().play("swordClashTitleScreen.wav");
            onExit = true;
            g.setColor(Color.white);
        } else {
            onExit = false;
            g.setColor(Color.red);
        }
        g.drawString("Exit", 360, 668);
        
        // Mute button detection
        if(x>740 && x<800 && y>740 && y<800)
        { onMute = true; }
        else
        { onMute = false; }
    }
    
    static public void singleHandler(int x, int y) {
        
    }
    
    static public void multiHandler(int x, int y) {
        // Home button detection
            if(x>13 && x<111 && y>730 && y<783)
                onHome = true;
            else
                onHome = false;

            // Host button detection
            if(x>256 && x<430 && y>666 && y<760)
                onHost = true;
            else
                onHost = false;

            // Join button detection
            if(x>477 && x<649 && y>666 && y<760)
                onJoin = true;
            else
                onJoin = false;

            // Mute button detection
            if(x>740 && x<800 && y>740 && y<800)
                onMute = true;
            else 
                onMute = false;
    }
    
    static private void activateSingleButton() {
        Titlescreen.activateSingle();
        Connect.setGameStarted(true);
        onSingle = false;
    }
    
    static private void activateMultiButton() {
        Titlescreen.activateMulti();
        onMulti = false;
    }
    
    static private void activateExitButton() {
        System.exit(0);
    }
    
    static private void activateHomeButton() {
        Titlescreen.activateMain();
        Connect.deleteAllCharsFromHost();
        onHome = false;
    }
    
    static private void activateHostButton() {
        Titlescreen.getMenuSounds().play("multiButtonCheer.wav");
        Connect.hostGame();
        onHost = false;
    }
    
    static private void activateJoinButton() {
        Titlescreen.getMenuSounds().play("multiButtonCheer.wav");
        Connect.connectToGame();
        onJoin = false;
    }
    
    static private void activateMuteButton() {
        SoundManager.toggleMute();
        muteOn = !muteOn;
    }
    
    static private void activateBackButton(Risk frame) {
        Window.changeWindow(frame, Window.USER_SCREEN_WIDTH/4+Window.USER_SCREEN_WIDTH/25, Window.USER_SCREEN_HEIGHT/12+Window.USER_SCREEN_HEIGHT/45, Window.MENU_WINDOW_WIDTH, Window.MENU_WINDOW_HEIGHT, "Risk");
        Titlescreen.activateMain();
    }
    
    static public void drawMute(Risk frame, int x, int y) {
        if(muteOn)
            muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIconMute.png");
        else
            muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
        g.drawImage(muteImage, x, y, 20, 20, frame);
    }
    
    static public void drawBack(Risk frame, int xDrawPos, int yDrawPos, int xMousePos, int yMousePos) {
        if (detectBack(xMousePos, yMousePos)) {
            backImage = Toolkit.getDefaultToolkit().getImage("./backButtonHighlight.png");
            onBack = true;
        }
        else {
            backImage = Toolkit.getDefaultToolkit().getImage("./backButton.png");
            onBack = false;
        }
        g.drawImage(backImage, xDrawPos, yDrawPos, frame);
    }
    
    static private boolean detectBack(int x, int y) {
        int xBoundaryPos[] = { 43,4,42,43,117,116,42 };
        int yBoundaryPos[] = { 91,62,33,49,50,71,71 };
        Polygon boundary = new Polygon(xBoundaryPos, yBoundaryPos, 7);
        return(boundary.contains(x, y));
    }
}
