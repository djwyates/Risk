// Created by Liam Young, Austin Wood, Adam Oliva, and most importantly, David Yates
package risk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Risk extends JFrame implements Runnable
{
    public static Image image;
    public static Graphics2D g;

    Thread relaxer;
    static int mouseX, mouseY;
    boolean bcActive=false;
    
    public static void main(String[] args) {
        Window.addWindow(Window.MENU_WINDOW_WIDTH, Window.MENU_WINDOW_HEIGHT, "Risk");
    }
    
    public Risk() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Button.setMouseIsHolding();
//                if(Titlescreen.gameIsStarted()&&bcActive){
//                    BorderCreator.startBorder(x, y);
//                }
                if(Titlescreen.gameIsStarted())    
                    repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Button.setMouseIsHolding();
                if (e.BUTTON1 == e.getButton()) {
                    System.out.println(e.getX() + "   " + e.getY());
                    Titlescreen.mouseClickHandler(Window.currentFrame, e.getX(), e.getY());
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                String key = String.valueOf(e.getKeyChar());
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_0:
                    case KeyEvent.VK_1:
                    case KeyEvent.VK_2:
                    case KeyEvent.VK_3:
                    case KeyEvent.VK_4:
                    case KeyEvent.VK_5:
                    case KeyEvent.VK_6:
                    case KeyEvent.VK_7:
                    case KeyEvent.VK_8:
                    case KeyEvent.VK_9:
                    case KeyEvent.VK_NUMPAD0:
                    case KeyEvent.VK_NUMPAD1:
                    case KeyEvent.VK_NUMPAD2:
                    case KeyEvent.VK_NUMPAD3:
                    case KeyEvent.VK_NUMPAD4:
                    case KeyEvent.VK_NUMPAD5:
                    case KeyEvent.VK_NUMPAD6:
                    case KeyEvent.VK_NUMPAD7:
                    case KeyEvent.VK_NUMPAD8:
                    case KeyEvent.VK_NUMPAD9:
                        Connect.addCharToHost(key);
                        Titlescreen.keyPressedHandler(key);
                        break;
                    case KeyEvent.VK_PERIOD:
                    case KeyEvent.VK_DECIMAL:
                        Connect.addCharToHost(".");
                        break;
                    case KeyEvent.VK_BACK_SPACE:
                        Titlescreen.keyPressedHandler("backspace");
                        if(Connect.getHost().length() > 0)
                            Connect.deleteCharFromHost();
                        break;
                    case KeyEvent.VK_ENTER:
                        Titlescreen.keyPressedHandler("enter");
                        break;
                    default:
                        break;
                }
                if(Connect.getHost().length()>19)
                    Connect.deleteCharFromHost();
                if (Connect.gameStarted()) {
                    switch (e.getKeyCode()) {
                    //BorderCreator.Reset();
                        case KeyEvent.VK_W:
                            break;
                        case KeyEvent.VK_S:
                            break;
                        case KeyEvent.VK_A:
                            break;
                        case KeyEvent.VK_D:
                            break;
                        default:
                            break;
                    }
                }                                                
                
                repaint();
            }
            
            public void keyReleased(KeyEvent e)
            {
                if (Connect.gameStarted())
                {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W:
                            BorderCreator.Reset();
                            break;
                        case KeyEvent.VK_S:
                            break;
                        case KeyEvent.VK_A:
                            break;
                        case KeyEvent.VK_D:
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        init();
        start();
    }

    public void paint(Graphics gOld)
    {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height)
        {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        if (Window.animateFirstTime)
        {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};        
        
        
        // far outer border
        g.setColor(Color.black);
        g.fillRect(0, 0, Window.xsize, Window.ysize);               
        // ----------------

        // background      
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
        
        try  {
            if (Titlescreen.isActive()){
                    Titlescreen.titlescreenHandler(this, mouseX, mouseY);
                    Titlescreen.instructionsHand(mouseX, mouseY, g);
            }
            else
                Titlescreen.getGame().drawAndSoundHandler(this, mouseX, mouseY);
        } catch (FontFormatException ex) {
            Logger.getLogger(Risk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Risk.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
//        if(bcActive)
//            BorderCreator.drawLines(mouseX, mouseY, g);
        
        gOld.drawImage(image, 0, 0, null);
        
    }

    // //////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
    // //////////////////////////////////////////////////////////////////////////
    public void destroy() {
         
    }
    // //////////////////////////////////////////////////////////////////////////
    // needed for implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 1.0/Window.frameRate; // time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            }
            catch (InterruptedException e) {
            }
        }
    }


    public static void reset() {
        Titlescreen.reset();
    }


    public void animate() {
        if (Window.animateFirstTime) {
            Window.animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            reset();
        }
                
    }

    // /////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }

    // /////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
}
