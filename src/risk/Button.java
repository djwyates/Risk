
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
    static private Image phaseImage = Toolkit.getDefaultToolkit().getImage("./deploy.png");
    static private Image phaseButtonImage = Toolkit.getDefaultToolkit().getImage("./DeployButton.png");
    static private boolean muteOn = false;
    static private boolean mouseHoldOn = false;
    static private boolean onSetup = false;
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
    static private boolean onPhaseButton = false;
    static private boolean onPhase = false;
    static private boolean onsliderR = false;
    static private boolean onsliderG = false;
    static private boolean onsliderB = false;
    //slider positions
    static private int rsp[] = {60,283},gsp[] = {60,324},bsp[] = {60,365};
    //min and max x for sliders
    static private int MIN_SLIDER_XPOS = 63, MAX_SLIDER_XPOS = 375;
    //slider distance from min_slider
    static private int rsd,gsd,bsd;
    //slider width/height used in hitbox
    static private final int SLIDER_DIAMETER = 30;
    //Color of color sample
    static private Color colorSample = Color.black;
    //Converts (distance of sliders) -> (RGB 0-255)
    static private final double DIS_TO_RGB=0.8175;
    //RGB values from conversion
    static private int RGB[] = {0,0,0};
    //Array of colors to assign to players.
    static private ArrayList<Color> playerColors = new ArrayList<Color>();
    static private boolean runFirst = true;
    
    
    static public void mouseClickHandler(Risk frame, int x, int y) {
        mouseHoldOn = false;
        onsliderR = false;
        onsliderG = false;
        onsliderB = false;
        if (onSetup) { activateSetupButton(); }
        else if (onInstructions) { activateMultiButton(); }
        else if (onExit) { activateExitButton(); }
        else if (onHome) { activateHomeButton(); }
        else if (onHost) { activateHostButton(); }
        else if (onJoin) { activateJoinButton(); }
        else if (onMute) { activateMuteButton(); }
        else if (onBack) { activateBackButton(frame); }
        else if (onPhaseButton) { activatePhaseClickButton(); }
        else if (onPhase) { activatePhaseButton(); }
        else if (onStart) { activateStartButton(frame); }
        else if (onMinus) { activateMinusButton(); }
        else if (onPlus) { activatePlusButton(); }
        else if (onPlayerDec) { activatePlayerDec(); }
        else if (onPlayerInc) { activatePlayerInc(); }
        else if (onPhase) { activatePhaseButton(); }
    }
    
    static public void mouseDraggedHandler(Risk frame, int x, int y) {
        if (!mouseHoldOn) {
            onsliderR = false;
            onsliderG = false;
            onsliderB = false;
            onsliderR = x>rsp[0] && x<rsp[0]+SLIDER_DIAMETER && y>rsp[1] && y<rsp[1]+SLIDER_DIAMETER;
            onsliderG = x>gsp[0] && x<gsp[0]+SLIDER_DIAMETER && y>gsp[1] && y<gsp[1]+SLIDER_DIAMETER;
            onsliderB = x>bsp[0] && x<bsp[0]+SLIDER_DIAMETER && y>bsp[1] && y<bsp[1]+SLIDER_DIAMETER;
        }
        mouseHoldOn = true;
    }
    
    static public void mainHandler(Risk frame, int x, int y) {
        // Singleplayer button detection & sound effect
        if((x>280&&x<483&&y>412&&y<487)) {
            if(!onSetup)
                Titlescreen.getMenuSounds().play("swordClashTitleScreen.wav");
            onSetup = true;
            g.setColor(Color.white);
        } else {
            onSetup = false;
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
        onMute = x>740 && x<800 && y>740 && y<800;
        drawMute(frame, 760, 760);
    }
    
    static public void setupHandler(int x, int y) throws FileNotFoundException, FontFormatException, IOException {
        if(runFirst){
            runFirst=false;
            for (int i = 0; i < 70; i++)
                playerColors.add(i, new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
        }
        // Start button detection & drawing of text
        onStart = x>447 && x<772 && y>599 && y<672;
        if (onStart)
            g.setColor(Color.red);
        else
            g.setColor(Color.white);
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,60));
        g.drawString("START", 563, 657);
        // Player number +/- detection & drawing of number
        onMinus = x>246 && x<278 && y>150 && y<180;
        onPlus = x>344 && x<374 && y>150 && y<180;
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,40));
        g.setColor(Color.white);
        g.drawString("" + Gameplay.getNumPlayers(), 300, 180);
        // Player color detection
        onPlayerDec = x>246 && x<278 && y>195 && y<235;
        onPlayerInc = x>344 && x<374 && y>195 && y<235;
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
            rsp[0]=x-(SLIDER_DIAMETER/4);
            if(rsp[0]<MIN_SLIDER_XPOS)
                rsp[0]=MIN_SLIDER_XPOS;
            if(rsp[0]>MAX_SLIDER_XPOS)
                rsp[0]=MAX_SLIDER_XPOS;
            rsd=rsp[0]-MIN_SLIDER_XPOS;
        }
        if(onsliderG){
            g.drawOval(gsp[0], gsp[1], 15, 15);
            gsp[0]=x-(SLIDER_DIAMETER/4);
            if(gsp[0]<MIN_SLIDER_XPOS)
                gsp[0]=MIN_SLIDER_XPOS;
            if(gsp[0]>MAX_SLIDER_XPOS)
                gsp[0]=MAX_SLIDER_XPOS;
            
            gsd=gsp[0]-MIN_SLIDER_XPOS;
        }
        if(onsliderB){
            g.drawOval(bsp[0], bsp[1], 15, 15);
            bsp[0]=x-(SLIDER_DIAMETER/4);
            if(bsp[0]<MIN_SLIDER_XPOS)
                bsp[0]=MIN_SLIDER_XPOS;
            if(bsp[0]>MAX_SLIDER_XPOS)
                bsp[0]=MAX_SLIDER_XPOS;
            bsd=bsp[0]-MIN_SLIDER_XPOS;
        }
        RGB[0]=(int)(rsd*DIS_TO_RGB);RGB[1]=(int)(gsd*DIS_TO_RGB);RGB[2]=(int)(bsd*DIS_TO_RGB);
        colorSample = new Color(RGB[0],RGB[1],RGB[2]);
        
        if(playerColors.get(Titlescreen.getCustomizePlayerNum()-1)!=colorSample)
            playerColors.set(Titlescreen.getCustomizePlayerNum()-1, colorSample);
        
        onHome = x > 17 && x < 143 && y > 721 && y < 784;
        onMute = x > 740 && x < 800 && y > 740 && y < 800;
    }
    
    static public void instructionsHandler(Risk frame, int x, int y) throws FileNotFoundException, FontFormatException, IOException {
            onHome = x > 17 && x < 143 && y > 721 && y < 784;
            onMute = x>740 && x<800 && y>740 && y<800;
    }
    
    static private void activateSetupButton() {
        Titlescreen.activateSetup();
        Connect.setGameStarted(true);
        onSetup = false;
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
    
    static private void activatePhaseClickButton() {
        Titlescreen.getGame().switchTurnHandler();
        onPhaseButton = false;
    }
    
    static private void activatePhaseButton() {
        switch(Titlescreen.getGame().getPhase()) {
            case DEPLOY:
                Titlescreen.getGame().deployFunction();
                break;
            case ATTACK:
                Titlescreen.getGame().attackFunction();
                break;
            case FORTIFY:
                Titlescreen.getGame().fortifyFunction();
                break;
        }
        onPhase = false;
    }
    
    static private void activateStartButton(Risk frame) {
        Titlescreen.getMenuSounds().play("multiButtonCheer.wav");
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
        if (Gameplay.getNumPlayers()+1 <= 70){
            Gameplay.addNumPlayers(1);
        }
    }
    
    static private void activatePlayerDec() {
        if (Titlescreen.getCustomizePlayerNum()-1 >= 2 || Titlescreen.getCustomizePlayerNum()-1 == 1){
            Titlescreen.addCustomizePlayerNum(-1);
            rsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getRed())/DIS_TO_RGB);
            gsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getGreen())/DIS_TO_RGB);
            bsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getBlue())/DIS_TO_RGB);
            rsp[0]=MIN_SLIDER_XPOS+rsd;
            gsp[0]=MIN_SLIDER_XPOS+gsd;
            bsp[0]=MIN_SLIDER_XPOS+bsd;
        }
    }
    
    static private void activatePlayerInc() {
        if (Titlescreen.getCustomizePlayerNum()+1 <= Gameplay.getNumPlayers()){
            Titlescreen.addCustomizePlayerNum(1);
            rsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getRed())/DIS_TO_RGB)+1;
            gsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getGreen())/DIS_TO_RGB)+1;
            bsd = (int) ((playerColors.get(Titlescreen.getCustomizePlayerNum()-1).getBlue())/DIS_TO_RGB)+1;
            rsp[0]=MIN_SLIDER_XPOS+rsd;
            gsp[0]=MIN_SLIDER_XPOS+gsd;
            bsp[0]=MIN_SLIDER_XPOS+bsd;
        }
    }
    
    static public void drawMute(Risk frame, int x, int y) {
        if(muteOn)
            muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIconMute.png");
        else
            muteImage = Toolkit.getDefaultToolkit().getImage("./speakerIcon.png");
        g.drawImage(muteImage, x, y, 20, 20, frame);
    }
    
    static public void drawPhaseClickButton(Risk frame, int xDrawPos, int yDrawPos, int xMousePos, int yMousePos) {
        if(Titlescreen.getGame().getPhase() == Gameplay.Phase.DEPLOY){
        if (detectPhaseClick(xMousePos, yMousePos)) {
            g.setColor(new Color(255,0,0));
            g.setFont(new Font("AMARILLO",Font.BOLD,25));
            g.drawString("Attack",717,830);
            phaseButtonImage = Toolkit.getDefaultToolkit().getImage("./DeployButtonHighlight.png");
            onPhaseButton = true;
        }
        else {
            phaseButtonImage = Toolkit.getDefaultToolkit().getImage("./DeployButton.png");
            onPhaseButton = false;
        }
        }if(Titlescreen.getGame().getPhase() == Gameplay.Phase.ATTACK){
        if (detectPhaseClick(xMousePos, yMousePos)) {
            g.setColor(new Color(0,0,255));
            g.setFont(new Font("AMARILLO",Font.BOLD,25));
            g.drawString("Fortify",714,830);
            phaseButtonImage = Toolkit.getDefaultToolkit().getImage("./AttackButtonHighlight.png");
            onPhaseButton = true;
        }
        else {
            phaseButtonImage = Toolkit.getDefaultToolkit().getImage("./AttackButton.png");
            onPhaseButton = false;
        }
        }if(Titlescreen.getGame().getPhase() == Gameplay.Phase.FORTIFY){
            g.setColor(new Color(255,0,0));
            g.setFont(new Font("AMARILLO",Font.BOLD,25));
            g.drawString("Next Turn",697,830);
        if (detectPhaseClick(xMousePos, yMousePos)) {
            phaseButtonImage = Toolkit.getDefaultToolkit().getImage("./FortifyButtonHighlight.png"); //Add FortifyButtonHighlight
            onPhaseButton = true;
        }
        else {
            phaseButtonImage = Toolkit.getDefaultToolkit().getImage("./FortifyButton.png");
            onPhaseButton = false;
        }
        }
        g.drawImage(phaseButtonImage, xDrawPos, yDrawPos, frame);
    }
    
    static public void drawPhaseButton(Risk frame, int xDrawPos, int yDrawPos, int xMousePos, int yMousePos) {
        switch (Titlescreen.getGame().getPhase()) {
            case DEPLOY:
                phaseImage = Toolkit.getDefaultToolkit().getImage("./deploy.png");
                break;
            case ATTACK:
                phaseImage = Toolkit.getDefaultToolkit().getImage("./attack.png");
                break;
            case FORTIFY:
                phaseImage = Toolkit.getDefaultToolkit().getImage("./fortify.png");
                break;
        }
        g.drawImage(phaseImage, xDrawPos, yDrawPos, 260, 100, frame);
        onPhase = detectPhase(xMousePos, yMousePos);
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
    
    static private boolean detectPhaseClick(int x, int y) {
        int xBoundaryPos[] = { 719,790,790,719 };
        int yBoundaryPos[] = { 830,830,881,881 };
        Polygon boundary = new Polygon(xBoundaryPos, yBoundaryPos, 4); // Note to self: the third variable is the number of points in the polygon
        return(boundary.contains(x, y));
    }
    
    static private boolean detectPhase(int x, int y) {
        int xBoundaryPos[] = { 860,860,1100,1100 };
        int yBoundaryPos[] = { 800,895,895,800 };
        Polygon boundary = new Polygon(xBoundaryPos, yBoundaryPos, 4);
        return(boundary.contains(x, y));
    }
    
    static private boolean detectBack(int x, int y) {
        int xBoundaryPos[] = { 43,4,42,43,117,116,42 };
        int yBoundaryPos[] = { 91,62,33,49,50,71,71 };
        Polygon boundary = new Polygon(xBoundaryPos, yBoundaryPos, 7);
        return(boundary.contains(x, y));
    }
    
    static ArrayList<Color> getPlayerColors() {
        return playerColors;
    }
}
