
package risk;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BorderCreator{
    private static Graphics2D g;
    private static ArrayList<BorderCreator> lines = new ArrayList<BorderCreator>();
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private boolean firstPoint;
    private boolean secondPoint;
    private static Color color = new Color(255,255,255);
    
    
    BorderCreator(){
        firstPoint=false;
        secondPoint=false;
    }
    
    static void Reset(){
        lines.clear();
    }
    
    static void startBorder(int x,int y){
        BorderCreator lastLine;
        BorderCreator currentLine;
        if(lines.isEmpty()){
            lines.add(new BorderCreator());
            lastLine = lines.get(lines.size() - 1);
        }
        else
            lastLine = lines.get(lines.size() - 1);
        
        
        if(lastLine.secondPoint){
            BorderCreator newLine = new BorderCreator();
            currentLine = newLine;
        }
        else
            currentLine=lastLine;
        
        if(!currentLine.firstPoint){
            currentLine.x1 = x;
            currentLine.y1 = y;
            currentLine.firstPoint = true;
        }
        else if(!currentLine.secondPoint){
            currentLine.x2 = x;
            currentLine.y2 = y;
            currentLine.secondPoint = true;
        }
        
    }
    
    static void drawLines(int x, int y){
        g.setColor(color);
        for(BorderCreator l : lines){
            if(l.secondPoint)
                g.drawLine(l.x1, l.x2, l.y1, l.y2);
            else if(!l.secondPoint && l.firstPoint){
                g.drawLine(l.x1, l.x2, x, y);
            }
        }
    }
    
    static void tick(int x,int y){
        int r = (int) (Math.random()*255);
        int g = (int) (Math.random()*255);
        int b = (int) (Math.random()*255);
        color = new Color(r,g,b);
        drawLines(x,y);
    }
}
