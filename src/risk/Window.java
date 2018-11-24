
package risk;

import java.awt.Toolkit;
import javax.swing.JFrame;

public class Window {
    static final int XBORDER = 0;
    static final int YBORDER = 0;
    static final int YTITLE = 31;
    static final int USER_SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static final int USER_SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    static final int WINDOW_BORDER = 8;
    static final int MENU_WINDOW_WIDTH = 800;
    static final int MENU_WINDOW_HEIGHT = 800;
    static final int MAP_WINDOW_WIDTH = 1371;
    static final int MAP_WINDOW_HEIGHT = 912;
    static Risk currentFrame;
    static int MIN_X;
    static int MAX_X;
    static int MIN_Y;
    static int MAX_Y;

    static final double frameRate = 20.0;
    static boolean animateFirstTime = true;
    static int xsize = -1;
    static int ysize = -1;


/////////////////////////////////////////////////////////////////////////
    public static void addWindow(int width, int height, String title) {
        Risk frame = new Risk();
        currentFrame=frame;
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setTitle(title);
        frame.setResizable(false);
    }
    
    public static void changeWindow(Risk frame, int width, int height, String title) {
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setTitle(title);
    }
    
    public static int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }

    public static int getY(int y) {
        return (y + YTITLE );
//        return (y + YBORDER + YTITLE );
        
    }

    public static int getYNormal(int y) {
        return (-y + YTITLE + getHeight2());
//        return (-y + YBORDER + YTITLE + getHeight2());
        
    }
    
    public static int getWidth2() {
        return (xsize - (XBORDER + WINDOW_BORDER));
    }

    public static int getHeight2() {
//        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
        return (ysize - WINDOW_BORDER - YTITLE);
    }    
}
