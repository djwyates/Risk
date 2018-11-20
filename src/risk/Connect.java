
package risk;

import java.io.IOException;

public class Connect {
    static private final int PORT_NUMBER = 5657;
    static private String host = new String();
    static private boolean gameStarted = false;
    static private boolean isConnecting = false;
    static private boolean isClient;
    
    static public void hostGame() {
        if (!isConnecting) {
            try {
                isConnecting = true;
                System.out.println("is connecting true");
                ServerHandler.recieveConnect(PORT_NUMBER);
                System.out.println("after recieveConnect");
                if (ServerHandler.connected) {
                    isClient = false;
                    gameStarted = true;
                    isConnecting = false;
                    System.out.println("Connected as server");
                }
            }
            catch (IOException ex) {
                System.out.println("Cannot host server: " + ex.getMessage());
                isConnecting = false;
            }                       
        }
    }
    
    static public void connectToGame() {
        if (!isConnecting) {                   
                try {                 
                    isConnecting = true;
                    ClientHandler.connect(host, PORT_NUMBER);
                    if (ClientHandler.connected) {
                        isClient = true;
                        gameStarted = true;
                        isConnecting = false;
                        System.out.println("Connected as client");
                    }
                }
                catch (IOException ex) {
                    System.out.println("Cannot join server: " + ex.getMessage());
                    isConnecting = false;
                }                    
        }
    }
    
    static public void addToHost (String character)
    { if (!gameStarted) host += character; }
    
    static public void deleteCharFromHost()
    { if (!gameStarted) host = host.substring(0, host.length()-1); }
    
    static public void deleteAllCharsFromHost()
    { host = ""; }
    
    static public String getHost()
    { return(host); }
    
    static public boolean gameStarted()
    { return(gameStarted); }
    
    static public void setGameStarted(boolean value)
    { gameStarted = value; }
    
    static public boolean isClient()
    { return(isClient); }
}
