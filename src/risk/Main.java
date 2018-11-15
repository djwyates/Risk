// Created by Liam Young, Austin Wood, Adam Oliva, and most importantly, David yates
package risk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JFrame implements Runnable
{
    public static Image image;
    public static Graphics2D g;

    Thread relaxer;
    int mousePos []= new int[2];
    static int listOfXPresses[] = new int [20];
    static int listOfYPresses[] = new int [20];
    int i=-1;
    
    
    public static void main(String[] args) {
        Window.addWindow(Window.MENU_WINDOW_WIDTH, Window.MENU_WINDOW_HEIGHT, "Risk");
    }
    
    public Main() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                e.getX();
                e.getY();
                Titlescreen.ChangeDice(e.getX(),e.getY());
                if(i>=0){
                    listOfXPresses[i] = e.getX();
                    listOfYPresses[i] = e.getY();
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(" ");
                System.out.println(i);
                System.out.println("List of x" + listOfXPresses[0]+","+listOfXPresses[1]+","+listOfXPresses[2]+","+listOfXPresses[3]+","+listOfXPresses[4]+","+listOfXPresses[5]+","+listOfXPresses[6]+","+listOfXPresses[7]+","+listOfXPresses[8]+","+listOfXPresses[9]+","+listOfXPresses[10]+","+listOfXPresses[11]+","+listOfXPresses[12]+","+listOfXPresses[13]+","+listOfXPresses[14]+","+listOfXPresses[15]+","+listOfXPresses[16]);
                System.out.println("List of y" + listOfYPresses[0]+","+listOfYPresses[1]+","+listOfYPresses[2]+","+listOfYPresses[3]+","+listOfYPresses[4]+","+listOfYPresses[5]+","+listOfYPresses[6]+","+listOfYPresses[7]+","+listOfYPresses[8]+","+listOfYPresses[9]+","+listOfYPresses[10]+","+listOfYPresses[11]+","+listOfYPresses[12]+","+listOfYPresses[13]+","+listOfYPresses[14]+","+listOfYPresses[15]+","+listOfYPresses[16]);
                }
                i++;
                //System.out.println(e.getX() + "        " + e.getY());
                System.out.println(e.getX() + "        " + e.getY());
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.BUTTON1 == e.getButton())
                    Titlescreen.pressedButton();
                repaint();
            }
        });
        
       
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseMoved(MouseEvent e)
            {
                mousePos[0]=e.getX();
                mousePos[1]=e.getY();
                repaint();
            }
        });

        addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_Q)
                {
                    
                }
                else if (e.getKeyCode() == KeyEvent.VK_C)
                {
                    
                }
                else
                {
                    if (!Connect.gameStarted())
                    {
                        if (e.getKeyCode() == KeyEvent.VK_0)
                            Connect.addToHost("0");
                        else if (e.getKeyCode() == KeyEvent.VK_1)
                            Connect.addToHost("1");
                        else if (e.getKeyCode() == KeyEvent.VK_2)
                            Connect.addToHost("2");
                        else if (e.getKeyCode() == KeyEvent.VK_3)
                            Connect.addToHost("3");
                        else if (e.getKeyCode() == KeyEvent.VK_4)
                            Connect.addToHost("4");
                        else if (e.getKeyCode() == KeyEvent.VK_5)
                            Connect.addToHost("5");
                        else if (e.getKeyCode() == KeyEvent.VK_6)
                            Connect.addToHost("6");
                        else if (e.getKeyCode() == KeyEvent.VK_7)
                            Connect.addToHost("7");
                        else if (e.getKeyCode() == KeyEvent.VK_8)
                            Connect.addToHost("8");
                        else if (e.getKeyCode() == KeyEvent.VK_9)
                            Connect.addToHost("9");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD0)
                            Connect.addToHost("0");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1)
                            Connect.addToHost("1");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2)
                            Connect.addToHost("2");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3)
                            Connect.addToHost("3");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4)
                            Connect.addToHost("4");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5)
                            Connect.addToHost("5");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6)
                            Connect.addToHost("6");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7)
                            Connect.addToHost("7");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8)
                            Connect.addToHost("8");
                        else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9)
                            Connect.addToHost("9");
                        else if (e.getKeyCode() == KeyEvent.VK_PERIOD)
                            Connect.addToHost(".");
                        else if (e.getKeyCode() == KeyEvent.VK_DECIMAL)
                            Connect.addToHost(".");
                        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                            if(Connect.getHost().length() > 0)
                                Connect.deleteCharFromHost();
                        }
                        if(Connect.getHost().length()>19)
                            Connect.deleteCharFromHost();
                    }
                }
                
                if (Connect.gameStarted())
                {
                    if(e.getKeyCode() == KeyEvent.VK_W){
                        for(int i=0;i<listOfYPresses.length;i++){
                            listOfYPresses[i]=0;
                            listOfXPresses[i]=0;
                        }
                        i=0;
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
                Titlescreen.titlescreenHandler(mousePos, this);
            } catch (FontFormatException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
//        if (!gameStarted)
//        {
//            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//            g.setColor(Color.black);
//            g.drawString("Not Connected",100,150);
//            
//        }
        else if (Connect.isClient())
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.black);
            g.drawString("The Client",100,150);
        }
        else
        {
            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
            g.setColor(Color.black);
            g.drawString("The Server",100,150);
        }
        
//            try
//            {
//                g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//                g.setColor(Color.black);
//                g.drawString("Your IP address: " + InetAddress.getLocalHost().getHostAddress(), Window.getX(10), Window.getY(20));
//                g.drawString("Enter IP address: " + host, Window.getX(10), Window.getY(60));
//            }
//            catch (UnknownHostException e)
//            {
//                e.printStackTrace();
//            }

            
        g.drawLine(Window.getX(0),Window.getY(0),Window.getWidth2(),Window.getY(0));  
        String mousePosString = new String(mousePos[0] + "   " + mousePos[1]);
        g.drawString(mousePosString, mousePos[0], mousePos[1]);
             

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


    public void animate()
    {
        if (Window.animateFirstTime)
        {
            Window.animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height)
            {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }

            reset();
        }
    }

    // //////////////////////////////////////////////////////////////////////////
    public void start()
    {
        if (relaxer == null)
        {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }

    // //////////////////////////////////////////////////////////////////////////
    public void stop()
    {
        if (relaxer.isAlive())
        {
            relaxer.stop();
        }
        relaxer = null;
    }
}
