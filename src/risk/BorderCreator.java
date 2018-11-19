
package risk;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BorderCreator{
    private static ArrayList<BorderCreator> lines = new ArrayList<BorderCreator>();
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private boolean firstPoint;
    private boolean secondPoint;
    private static ArrayList<Integer> xvals = new ArrayList<Integer>();
    private static ArrayList<Integer> yvals = new ArrayList<Integer>();
    private static Color color;
    private boolean beginPoint;
    
    
    BorderCreator(){
        firstPoint = false;
        secondPoint = false;
        beginPoint = false;
        x1=-1;
        x2=-1;
        y1=-1;
        y2=-1;
    }
    
    static void Reset(){
        lines.clear();
        xvals.clear();
        yvals.clear();
    }
    
    static void startBorder(int x,int y){
        BorderCreator lastLine;
        BorderCreator currentLine;
        if(lines.isEmpty()){
            BorderCreator firstLine = new BorderCreator();
            lines.add(firstLine);
            firstLine.beginPoint=true;
            lastLine = firstLine;
        }
        else
            lastLine = lines.get(lines.size() - 1);
        
        
        if(lastLine.firstPoint==false){
            lastLine.x1 = x;
            lastLine.y1 = y;
            lastLine.firstPoint = true;
        }
        else if(!lastLine.secondPoint){
            lastLine.x2 = x;
            lastLine.y2 = y;
            lastLine.secondPoint = true;
        }
        
        if(lastLine.secondPoint){
            BorderCreator newLine = new BorderCreator();
            currentLine = newLine;
            lines.add(newLine);
            currentLine.x1 = lastLine.x2;
            currentLine.y1 = lastLine.y2;
            currentLine.firstPoint=true;
        }
        else{
            currentLine = lastLine;
        }
        
        /////////////////////////////////////////////////
        if(currentLine.beginPoint){
            xvals.add(currentLine.x1);
            yvals.add(currentLine.y1);
        }
        else if(currentLine.secondPoint){
            xvals.add(lastLine.x2);
            yvals.add(lastLine.y2);
        }
        
        
        System.out.println("");
        System.out.println("");
        System.out.println("");
        for(int i=0;i<xvals.size();i++){
            System.out.print(xvals.get(i)+",");
        }
        System.out.println("");
        for(int i=0;i<yvals.size();i++){
            System.out.print(yvals.get(i)+",");
        }
        
    }
    
    static void drawLines(int x, int y, Graphics2D g){
        Color oldColor = g.getColor();
        if(color==null)
            System.out.println("Null.");
        g.setColor(color);
        
        for(BorderCreator l : lines){
            if(l.secondPoint){
                g.drawString(l.x1 + " " + l.y1, l.x1, l.y1);
                g.drawString(l.x2 + " " + l.y2, l.x2, l.y2);
                g.drawLine(l.x1, l.y1, l.x2, l.y2);
            }
            else if(!l.secondPoint && l.firstPoint){
                g.drawString(l.x1 + " " + l.y1, l.x1, l.y1);
                g.drawString(x + " " + y, x, y);
                g.drawLine(l.x1, l.y1, x, y);
            }
        }
        
        
        
        g.setColor(oldColor);
    }
    
    static void tick(int x,int y,Graphics2D G){
        int r = (int) (Math.random()*255);
        int g = (int) (Math.random()*255);
        int b = (int) (Math.random()*255);
        color = new Color(r,g,b);
        drawLines(x,y,G);
    }
}
