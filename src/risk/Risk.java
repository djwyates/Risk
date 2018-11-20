// Created by Liam Young, Austin Wood, Adam Oliva, and most importantly, David Yates
package risk;


import java.*;
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
    int mouseX, mouseY;
    boolean bcActive=false;
    
    public static void main(String[] args) {
        Window.addWindow(Window.MENU_WINDOW_WIDTH, Window.MENU_WINDOW_HEIGHT, "Risk");
    }
    
    public Risk() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + "     " + y);
                if(Titlescreen.gameIsStarted()&&bcActive){
                    BorderCreator.startBorder(x, y);
                }
                else if(Titlescreen.gameIsStarted())
                
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
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
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                } else if (e.getKeyCode() == KeyEvent.VK_C) {
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_0) {
                        Connect.addToHost("0");
                    } else if (e.getKeyCode() == KeyEvent.VK_1) {
                        Connect.addToHost("1");
                    } else if (e.getKeyCode() == KeyEvent.VK_2) {
                        Connect.addToHost("2");
                    } else if (e.getKeyCode() == KeyEvent.VK_3) {
                        Connect.addToHost("3");
                    } else if (e.getKeyCode() == KeyEvent.VK_4) {
                        Connect.addToHost("4");
                    } else if (e.getKeyCode() == KeyEvent.VK_5) {
                        Connect.addToHost("5");
                    } else if (e.getKeyCode() == KeyEvent.VK_6) {
                        Connect.addToHost("6");
                    } else if (e.getKeyCode() == KeyEvent.VK_7) {
                        Connect.addToHost("7");
                    } else if (e.getKeyCode() == KeyEvent.VK_8) {
                        Connect.addToHost("8");
                    } else if (e.getKeyCode() == KeyEvent.VK_9) {
                        Connect.addToHost("9");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
                        Connect.addToHost("0");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                        Connect.addToHost("1");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                        Connect.addToHost("2");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                        Connect.addToHost("3");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                        Connect.addToHost("4");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                        Connect.addToHost("5");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                        Connect.addToHost("6");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                        Connect.addToHost("7");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                        Connect.addToHost("8");
                    } else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                        Connect.addToHost("9");
                    } else if (e.getKeyCode() == KeyEvent.VK_PERIOD) {
                        Connect.addToHost(".");
                    } else if (e.getKeyCode() == KeyEvent.VK_DECIMAL) {
                        Connect.addToHost(".");
                    } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        if(Connect.getHost().length() > 0)
                            Connect.deleteCharFromHost();
                    }
                    if(Connect.getHost().length()>19)
                        Connect.deleteCharFromHost();
                }
                
                if (Connect.gameStarted())
                {
                    if(e.getKeyCode() == KeyEvent.VK_W){
                        //BorderCreator.Reset();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_S){
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_A){
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_D){
                    }
                }                                                
                
                repaint();
            }
            
            public void keyReleased(KeyEvent e)
            {
                if (Connect.gameStarted())
                {
                    if(e.getKeyCode() == KeyEvent.VK_W){
                        BorderCreator.Reset();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_S){
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_A){
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_D){
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
        
        
        if(Titlescreen.isActive()){
            try  {
                Titlescreen.titlescreenHandler(mouseX, mouseY, this);
            } catch (FontFormatException ex) {
                Logger.getLogger(Risk.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Risk.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Connect.isClient()) {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.black);
            g.drawString("The Client",100,150);
        } else {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.black);
            g.drawString("The Server",100,150);
        }
        if(bcActive)
            BorderCreator.drawLines(mouseX, mouseY, g);
        
        
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

    // //////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }

    // //////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
}
