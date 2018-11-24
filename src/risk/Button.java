
package risk;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static risk.Risk.g;

public class Button {
    Risk mainframe;
    static private Image muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
    static private Image backImage = Toolkit.getDefaultToolkit().getImage("./backButton.png");
    static private boolean mouseIsHolding=false;
    static private boolean muteOn = false;
    static private boolean onSingle = false;
    static private boolean onMulti = false;
    static private boolean onExit = false;
    static private boolean onHome = false;
    static private boolean onHost = false;
    static private boolean onJoin = false;
    static private boolean onMute = false;
    static private boolean onBack = false;
    static private boolean onsliderR = false;
    static private boolean onsliderG = false;
    static private boolean onsliderB = false;
    //slider positions
    static private int rsp[] = {63,283},gsp[] = {63,324},bsp[] = {63,365};
    //min and max x for sliders
    static private int min_slider = 63,max_slider = 375;
    //slider distance from min_slider
    static private int rsd,gsd,bsd;
    //slider width/height used in hitbox
    static private int sliderOffset = 15;
    //Color of color sample
    static private Color colorSample = Color.black;
    //
    static private final double disToRGB=0.8173;
    
    
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
    static void setMouseIsHolding(){
        if(mouseIsHolding)
            mouseIsHolding=false;
        else
            mouseIsHolding=true;
    }
    static boolean getMouseIsHolding(){
        return mouseIsHolding;
    }
    static public void setupHandler(Risk frame, int x, int y) {
        Color oColor = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(rsp[0], rsp[1], 15, 15);
        g.setColor(Color.GREEN);
        g.fillOval(gsp[0], gsp[1], 15, 15);
        g.setColor(Color.BLUE);
        g.fillOval(bsp[0], bsp[1], 15, 15);
        g.setColor(colorSample);
        g.fillOval(30, 400, 355, 355);
        g.setColor(oColor);
        if(x>rsp[0] && x<rsp[0]+sliderOffset && y>rsp[1] && y<rsp[1]+sliderOffset){
            System.out.println("r");
            
            onsliderR = true;
            onsliderG = false;
            onsliderB = false;
        }
        else if(x>gsp[0] && x<gsp[0]+sliderOffset && y>gsp[1] && y<gsp[1]+sliderOffset){
            System.out.println("g");
            onsliderR = false;
            onsliderG = true;
            onsliderB = false;
        }
        else if(x>bsp[0] && x<bsp[0]+sliderOffset && y>bsp[1] && y<bsp[1]+sliderOffset){
            System.out.println("b");
            onsliderR = false;
            onsliderG = false;
            onsliderB = true;
        }
        else{
            onsliderR = false;
            onsliderG = false;
            onsliderB = false;
        }
        if(onsliderR){
            rsp[0]=x-(sliderOffset/2);
            if(rsp[0]<min_slider)
                rsp[0]=min_slider;
            if(rsp[0]>max_slider)
                rsp[0]=max_slider;
            
            rsd=rsp[0]-min_slider;
        }
        if(onsliderG){
            gsp[0]=x-(sliderOffset/2);
            if(gsp[0]<min_slider)
                gsp[0]=min_slider;
            if(gsp[0]>max_slider)
                gsp[0]=max_slider;
            
            gsd=gsp[0]-min_slider;
        }
        if(onsliderB){
            bsp[0]=x-(sliderOffset/2);
            if(bsp[0]<min_slider)
                bsp[0]=min_slider;
            if(bsp[0]>max_slider)
                bsp[0]=max_slider;
            
            bsd=bsp[0]-min_slider;
        }
        //System.out.println(rsd + " " + gsd + " " + bsd);
        colorSample = new Color((int)(rsd*disToRGB),(int)(gsd*disToRGB),(int)(bsd*disToRGB));
    }
    
    static public void mainHandler(Risk frame, int x, int y) {
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
        g.drawString("Play", 358, 455);
        
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
        g.drawString("Intructions", 330, 563);
        
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
        
        // Mute button detection and drawing
        if(x>740 && x<800 && y>740 && y<800)
        { onMute = true; }
        else
        { onMute = false; }
        drawMute(frame, 760, 760);
    }
    
    static public void multiHandler(Risk frame, int x, int y) throws FileNotFoundException, FontFormatException, IOException {
            // Drawws IP addresses
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
            drawMute(frame, 760, 760);
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
        Window.changeWindow(frame, Window.MENU_WINDOW_WIDTH, Window.MENU_WINDOW_HEIGHT, "Risk");
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
