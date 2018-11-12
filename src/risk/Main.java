// Created by Liam Young, Austin Wood, Adam Oliva, and most importantly, David yates
package risk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.net.*;
import java.io.*;

public class Main extends JFrame implements Runnable
{
    public static Image image;
    public static Graphics2D g;
    final int portNumber = 5657;
    public static boolean gameStarted = false;
    String host = new String();
    public static boolean isConnecting = false;
    public static boolean isClient;

    Thread relaxer;
    int mousePos []= new int[2];


    public static void main(String[] args)
    {
        Main frame = new Main();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Risk");
        frame.setResizable(true);
    }    
    public Main()
    {
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                e.getX();
                e.getY();
                if(e.getX()>760 && e.getX()<800 && e.getY()>760 && e.getY()<800 && Titlescreen.getMute()==false)
                    Titlescreen.setMute(true);
                else if(e.getX()>760 && e.getX()<800 && e.getY()>760 && e.getY()<800 && Titlescreen.getMute()==true)
                    Titlescreen.setMute(false);
                
                
                repaint();
            }
        });

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                if (e.BUTTON1 == e.getButton())
                {
                    Titlescreen.pressedButton();
                }
                repaint();
            }
        });
        
       
        addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
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
                    if (!isConnecting)
                    {
                        try {
                            isConnecting = true;
                            System.out.println("is connecting true");
                            ServerHandler.recieveConnect(portNumber);   //5657
                            System.out.println("after recieveConnect");
                            if (ServerHandler.connected)
                            {
                                isClient = false;
                                gameStarted = true;
                                isConnecting = false;
                            }
                        }
                        catch (IOException ex)
                        {
                            System.out.println("Cannot host server: " + ex.getMessage());
                            isConnecting = false;
                        }                       
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_C)
                {
                    if (!isConnecting)
                    {                   
                            try
                            {                 
                                isConnecting = true;
                                ClientHandler.connect(host, portNumber);
                                if (ClientHandler.connected)
                                {
                                    isClient = true;
                                    gameStarted = true;
                                    isConnecting = false;
                                }
                            }
                            catch (IOException ex)
                            {
                                System.out.println("Cannot join server: " + ex.getMessage());
                                isConnecting = false;
                            }                    
                    }
                }
                else
                {
                    if (!gameStarted)
                    {
                        if (e.getKeyCode() == KeyEvent.VK_0)
                        {
                            host += "0";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_1)
                        {
                            host += "1";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_2)
                        {
                            host += "2";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_3)
                        {
                            host += "3";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_4)
                        {
                            host += "4";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_5)
                        {
                            host += "5";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_6)
                        {
                            host += "6";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_7)
                        {
                            host += "7";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_8)
                        {
                            host += "8";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_9)
                        {
                            host += "9";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_PERIOD)
                        {
                            host += ".";
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        {
                            if(host.length() > 0)
                                host=host.substring(0, host.length()-1);
                        }
                    }
                }
                
                if (gameStarted)
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
                
                repaint();
            }
            
            public void keyReleased(KeyEvent e)
            {  
                if (gameStarted)
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
            Titlescreen.drawMenu(mousePos, this, host);
        }
        
        
//        if (!gameStarted)
//        {
//            g.setFont(new Font("Comic Sans", Font.ROMAN_BASELINE, 20));
//            g.setColor(Color.black);
//            g.drawString("Not Connected",100,150);
//            
//        }
        else if (isClient)
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
    public void run()
    {
        while (true)
        {
            animate();
            repaint();
            double seconds = 1.0/Window.frameRate; // time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try
            {
                Thread.sleep(miliseconds);
            }
            catch (InterruptedException e)
            {
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
        if(Titlescreen.isActive())
            Titlescreen.checkMusicLoop();
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
