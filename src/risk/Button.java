
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
import java.util.ArrayList;
import static risk.Risk.g;

public class Button {
    Risk mainframe;
    static private Image muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
    static private Image backImage = Toolkit.getDefaultToolkit().getImage("./backButton.png");
    static private Image fortifyImage = Toolkit.getDefaultToolkit().getImage("./FortifyButton.png");
    static private boolean mouseIsHolding=false;
    static private boolean muteOn = false;
    static private boolean onPlay = false;
    static private boolean onInstructions = false;
    static private boolean onExit = false;
    static private boolean onMinus = false;
    static private boolean onPlus = false;
    static private boolean onPlayerDec = false;
    static private boolean onPlayerInc = false;
    static private boolean onStart = false;
    static private boolean onHome = false;
    static private boolean onHost = false;
    static private boolean onJoin = false;
    static private boolean onMute = false;
    static private boolean onBack = false;
    static private boolean onFortify = false;
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
    //Converts (distance of sliders) -> (RGB 0-255)
    static private final double disToRGB=0.8175;
    //RGB values from conversion
    static private int RGB[] = {0,0,0};
    //Array of colors to assign to players.
    static private ArrayList<Color> playerColors = new ArrayList<Color>();
    static private boolean runFirst = true;
    
    
    static public void mouseClickHandler(Risk frame, int x, int y) {
        if (onPlay) { activateSingleButton(); }
        else if (onInstructions) { activateMultiButton(); }
        else if (onExit) { activateExitButton(); }
        else if (onHome) { activateHomeButton(); }
        else if (onHost) { activateHostButton(); }
        else if (onJoin) { activateJoinButton(); }
        else if (onMute) { activateMuteButton(); }
        else if (onBack) { activateBackButton(frame); }
        else if (onFortify) { activateFortifyButton(frame); }
        else if (onStart) { activateStartButton(frame); }
        else if (onMinus) { activateMinusButton(); }
        else if (onPlus) { activatePlusButton(); }
        else if (onPlayerDec) { activatePlayerDec(); }
        else if (onPlayerInc) { activatePlayerInc(); }
        if (Titlescreen.isSetupActive())
            sliderHandler(x, y);
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
    
    static public void mainHandler(Risk frame, int x, int y) {
        // Singleplayer button detection & sound effect
        if((x>280&&x<483&&y>412&&y<487)) {
            if(!onPlay)
                Titlescreen.getMenuSounds().play("swordClashTitleScreen.wav");
            onPlay = true;
            g.setColor(Color.white);
        } else {
            onPlay = false;
            g.setColor(Color.red);
        }
        g.drawString("Play", 358, 455);
        // Multiplayer button detection & sound effect
        if((x>280&&x<483&&y>520&&y<595)) {
            if(!onInstructions)
                Titlescreen.getMenuSounds().play("swordClashTitleScreen.wav");
            onInstructions = true;
            g.setColor(Color.white);
        } else {
            onInstructions = false;
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
    
    static public void setupHandler(int x, int y) throws FileNotFoundException, FontFormatException, IOException {
        if(runFirst){
            runFirst=false;
            for (int i = 0; i < 70; i++) {
                playerColors.add(Player.getBlankColor());
            }
        }
        // Start button detection & drawing of text
        if (x>447 && x<772 && y>599 && y<672) {
            onStart = true;
            g.setColor(Color.red);
        } else {
            onStart = false;
            g.setColor(Color.white);
        }
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,60));
        g.drawString("START", 563, 657);
        // Player number +/- detection & drawing of number
        if (x>246 && x<278 && y>150 && y<180)
            onMinus = true;
        else
            onMinus = false;
        if (x>344 && x<374 && y>150 && y<180)
            onPlus = true;
        else
            onPlus = false;
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,40));
        g.setColor(Color.white);
        g.drawString("" + Gameplay.getNumPlayers(), 300, 180);
        // Player color detection
        if (x>246 && x<278 && y>195 && y<235)
            onPlayerDec = true;
        else
            onPlayerDec = false;
        if (x>344 && x<374 && y>195 && y<235)
            onPlayerInc = true;
        else
            onPlayerInc = false;
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,40));
        g.setColor(Color.white);
        g.drawString("" + Titlescreen.getCustomizePlayerNum(), 300, 230);
        // Color sliders & oval
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,20));
        g.setColor(Color.RED);
        g.fillOval(rsp[0], rsp[1], 15, 15);
        g.drawString(""+RGB[0],390,294);
        g.setColor(Color.GREEN);
        g.fillOval(gsp[0], gsp[1], 15, 15);
        g.drawString(""+RGB[1],390,335);
        g.setColor(Color.BLUE);
        g.fillOval(bsp[0], bsp[1], 15, 15);
        g.drawString(""+RGB[2],390,376);
        g.setColor(colorSample);
        g.fillOval(30, 400, 355, 355);
        g.setColor(Color.white);
        if(onsliderR){
            
            g.drawOval(rsp[0], rsp[1], 15, 15);
            rsp[0]=x-(sliderOffset/2);
            if(rsp[0]<min_slider)
                rsp[0]=min_slider;
            if(rsp[0]>max_slider)
                rsp[0]=max_slider;
            rsd=rsp[0]-min_slider;
        }
        if(onsliderG){
            
            g.drawOval(gsp[0], gsp[1], 15, 15);
            gsp[0]=x-(sliderOffset/2);
            if(gsp[0]<min_slider)
                gsp[0]=min_slider;
            if(gsp[0]>max_slider)
                gsp[0]=max_slider;
            
            gsd=gsp[0]-min_slider;
        }
        if(onsliderB){
            
            g.drawOval(bsp[0], bsp[1], 15, 15);
            bsp[0]=x-(sliderOffset/2);
            if(bsp[0]<min_slider)
                bsp[0]=min_slider;
            if(bsp[0]>max_slider)
                bsp[0]=max_slider;
            bsd=bsp[0]-min_slider;
        }
        RGB[0]=(int)(rsd*disToRGB);RGB[1]=(int)(gsd*disToRGB);RGB[2]=(int)(bsd*disToRGB);
        colorSample = new Color(RGB[0],RGB[1],RGB[2]);
        
        
        if(playerColors.get(Titlescreen.getCustomizePlayerNum()-1)!=colorSample)
            playerColors.set(Titlescreen.getCustomizePlayerNum()-1, colorSample);
    }
    
    static public void instructionsHandler(Risk frame, int x, int y) throws FileNotFoundException, FontFormatException, IOException {
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
    
    static private void sliderHandler(int x, int y) {
        if(x>rsp[0] && x<rsp[0]+sliderOffset && y>rsp[1] && y<rsp[1]+sliderOffset){
            if (onsliderR)
                onsliderR = false;
            else
                onsliderR = true;
            onsliderG = false;
            onsliderB = false;
        }
        else if(x>gsp[0] && x<gsp[0]+sliderOffset && y>gsp[1] && y<gsp[1]+sliderOffset){
            onsliderR = false;
            if (onsliderG)
                onsliderG = false;
            else
                onsliderG = true;
            onsliderB = false;
        }
        else if(x>bsp[0] && x<bsp[0]+sliderOffset && y>bsp[1] && y<bsp[1]+sliderOffset){
            onsliderR = false;
            onsliderG = false;
            if (onsliderB)
                onsliderB = false;
            else
                onsliderB = true;
        }
        else {
            onsliderR = false;
            onsliderG = false;
            onsliderB = false;
        }
    }
    
    static private void activateSingleButton() {
        Titlescreen.activateSingle();
        Connect.setGameStarted(true);
        onPlay = false;
    }
    
    static private void activateMultiButton() {
        Titlescreen.activateMulti();
        onInstructions = false;
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
    
    static private void activateFortifyButton(Risk frame) {
        Gameplay.switchTurnAccessor();
        System.out.println("Fortify activated");
    }
    
    static private void activateStartButton(Risk frame) {
        Titlescreen.startGame(frame);
        onStart = false;
    }
    
    static private void activateMinusButton() {
        if (Gameplay.getNumPlayers()-1 >= 2) {
            Gameplay.addNumPlayers(-1);
            if (Titlescreen.getCustomizePlayerNum() > Gameplay.getNumPlayers())
                Titlescreen.addCustomizePlayerNum(-1);
        }
    }
    
    static private void activatePlusButton() {
        if (Gameplay.getNumPlayers()+1 <= 70)
            Gameplay.addNumPlayers(1);
    }
    
    static private void activatePlayerDec() {
        if (Titlescreen.getCustomizePlayerNum()-1 >= 2 || Titlescreen.getCustomizePlayerNum()-1 == 1){
            Titlescreen.addCustomizePlayerNum(-1);
            rsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getRed())/disToRGB);
            gsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getGreen())/disToRGB);
            bsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getBlue())/disToRGB);
            rsp[0]=min_slider+rsd;
            gsp[0]=min_slider+gsd;
            bsp[0]=min_slider+bsd;
        }
    }
    
    static private void activatePlayerInc() {
        if (Titlescreen.getCustomizePlayerNum()+1 <= Gameplay.getNumPlayers()){
            Titlescreen.addCustomizePlayerNum(1);
            rsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getRed())/disToRGB)+1;
            gsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getGreen())/disToRGB)+1;
            bsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getBlue())/disToRGB)+1;
            rsp[0]=min_slider+rsd;
            gsp[0]=min_slider+gsd;
            bsp[0]=min_slider+bsd;
        }
    }
    static ArrayList<Color> getPlayerColors(){
        return playerColors;
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
    
    static public void drawFortifyButton(Risk frame, int xDrawPos, int yDrawPos, int xMousePos, int yMousePos) {
        if (detectFortify(xMousePos, yMousePos)) {
            fortifyImage = Toolkit.getDefaultToolkit().getImage("./FortifyButtonHighlight.png");
            onFortify = true;
        }
        else {
            fortifyImage = Toolkit.getDefaultToolkit().getImage("./FortifyButton.png");
            onFortify = false;
        }
        
        g.drawImage(fortifyImage, xDrawPos, yDrawPos, frame);
    }
    
    static private boolean detectBack(int x, int y) {
        int xBoundaryPos[] = { 43,4,42,43,117,116,42 };
        int yBoundaryPos[] = { 91,62,33,49,50,71,71 };
        Polygon boundary = new Polygon(xBoundaryPos, yBoundaryPos, 7);
        return(boundary.contains(x, y));
    }
    
    static private boolean detectFortify(int x, int y) {
        int xBoundaryPos[] = {438,489,489,438};
        int yBoundaryPos[] = {332,332,383,383};
        Polygon fBoundary = new Polygon(xBoundaryPos, yBoundaryPos, 4); // Note to self: the third variable is the number of points in the polygon
        return(fBoundary.contains(x, y));
    }
}
