
package risk;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import static risk.Main.g;

public class Button {
    static private Image muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
    static private boolean muteOn = false;
    static private boolean onSingle = false;
    static private boolean onMulti = false;
    static private boolean onExit = false;
    static private boolean onHome = false;
    static private boolean onHost = false;
    static private boolean onJoin = false;
    static private boolean onMute = false;
    static private boolean onBack = false;
    
    static public void releasedLeftClick() {
        if (onSingle) { activateSingleButton(); }
        else if (onMulti) { activateMultiButton(); }
        else if (onExit) { activateExitButton(); }
        else if (onHome) { activateHomeButton(); }
        else if (onHost) { activateHostButton(); }
        else if (onJoin) { activateJoinButton(); }
        else if (onMute) { activateMuteButton(); }
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
    
    static public void drawMute(int x, int y, Main frame) {
        if(muteOn)
            muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIconMute.png");
        else
            muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
        g.drawImage(muteImage, x, y, 20, 20, frame);
    }
}
