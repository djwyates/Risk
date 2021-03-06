
package risk;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javafx.scene.shape.Polyline;

public class BorderCreator{
    private static ArrayList<BorderCreator> lines = new ArrayList<BorderCreator>();
    private static ArrayList<Integer> xvals = new ArrayList<Integer>();
    private static ArrayList<Integer> yvals = new ArrayList<Integer>();
    private static Color color;
    
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private boolean firstPoint;
    private boolean secondPoint;
    private boolean beginPoint;
    
    
    BorderCreator(){
    }
    
    static void Reset(){
        lines.clear();
        xvals.clear();
        yvals.clear();
    }
    
    static void startBorder(int x,int y){
        BorderCreator lastLine;
        BorderCreator currentLine;
        //Checks to see if array is empty, creates firstline that is also lastline, if not then lastline is determined by arraysize-1
        if(lines.isEmpty()){
            BorderCreator firstLine = new BorderCreator();
            lines.add(firstLine);
            firstLine.beginPoint=true;
            lastLine = firstLine;
        }
        else
            lastLine = lines.get(lines.size() - 1);
        
        //Point detection
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
        
        //adding point values to arraylists
        if(currentLine.beginPoint){
            xvals.add(currentLine.x1);
            yvals.add(currentLine.y1);
        }
        else if(lastLine.secondPoint){
            xvals.add(lastLine.x2);
            yvals.add(lastLine.y2);
        }
        
        //printing point information to be pasted
        System.out.println("");
        System.out.println("");
        System.out.print("int x[] = {");
        for(int i=0;i<xvals.size();i++){
            if(i==xvals.size()-1)
                System.out.print(xvals.get(i));
            else
                System.out.print(xvals.get(i)+",");
        }
        System.out.print("};");
        System.out.println("");
        System.out.print("          int y[] = {");
        for(int i=0;i<yvals.size();i++){
            if(i==yvals.size()-1)
                System.out.print(yvals.get(i));
            else
                System.out.print(yvals.get(i)+",");
        }
        System.out.print("};");
        System.out.println("");
        
    }
    
    static void drawLines(int x, int y, Graphics2D g){
        Color oldColor = g.getColor();
        g.setColor(Color.BLUE);
        //Converting arraylists to arrays for drawPolygon
        int xValNormArray [] = new int[xvals.size()+1];
        int yValNormArray [] = new int[yvals.size()+1];
        int lasti=0;
        for(int i=0;i<xvals.size();i++){
            xValNormArray[i] = xvals.get(i);
            yValNormArray[i] = yvals.get(i);
            lasti++;
        }
        //Setting lastpoint to mouse position
        xValNormArray[lasti] = x;
        yValNormArray[lasti] = y;
        g.drawPolygon(xValNormArray, yValNormArray, xValNormArray.length);
        
        
        
        
        g.setColor(oldColor);
    }
}
