
package risk;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static risk.Risk.g;
public class TextLog {
    static public enum Colors { DEPLOY, ATTACK, FORTIFY, SYSTEM }
    private static String input = "";
    private static ArrayList<TextLog> statements = new ArrayList<TextLog>();
    private static Color systemColor  = new Color(47,79,79),
                         deployColor  = new Color(0,100,0),
                         attackColor  = new Color(139,0,0),  
                         fortifyColor = new Color(25,25,112),
                         inputColor   = systemColor;
    private String statement;
    private Color color;
    private int x;
    private int y;
    
    TextLog(String s,Gameplay.Phase p){
        color = systemColor;
        if(p!=null)
            switch(p){
                case DEPLOY:
                    color = deployColor;
                    break;
                case ATTACK:
                    color = attackColor;
                    break;
                case FORTIFY:
                    color = fortifyColor;
                    break;
            }
        
            
        statement=s;
        x=20;
        y=855;
        for(int i=0;i<statements.size();i++){
            statements.get(i).y-=35;
            System.out.println(statements.get(i).color.getAlpha() + " alpha " + i );
            statements.get(i).color = new Color(statements.get(i).color.getRed(),statements.get(i).color.getGreen(),statements.get(i).color.getBlue(),statements.get(i).color.getAlpha()-15);
        }
    }
    
    static void createStatement(String str,Gameplay.Phase p){
        TextLog s = new TextLog(str,p);
        statements.add(s);
    }
    static void drawStatements(Graphics2D g) throws FontFormatException, IOException{
        //Storing old sets for colors,text,etc
        Color oColor = g.getColor();
        Font oFont = g.getFont();
        SetFontsAndColors(20);
        for(int i=0;i<statements.size();i++){
            if(statements.get(i).y<500){
                statements.remove(statements.get(i));
                i++;
            }
            g.setColor(statements.get(i).color);
            g.drawString(statements.get(i).statement, statements.get(i).x, statements.get(i).y);
        }
        
        //Setting font back to old sets
        g.setColor(oColor);
        g.setFont(oFont);
    }
    static void drawInput(Graphics2D g) throws FileNotFoundException, FontFormatException, IOException {
        //Storing old sets for colors,text,etc
        Color oColor = g.getColor();
        Font oFont = g.getFont();
        SetFontsAndColors(20);
        g.setColor(inputColor);
        
        g.drawString(input, 20, 890);
        
        
        //Setting font back to old sets
        g.setColor(oColor);
        g.setFont(oFont);
    }
    static void addToInput(String s){
        input+=s;
    }
    static void removeOneInput(){
        if(input.length()-1>-1)
        input = input.substring(0, input.length()-1);
    }
    static void clearInput(){
        input="";
    }
    
    static void SetFontsAndColors(int fontSize) throws FileNotFoundException, IOException, FontFormatException{
        g.setFont(Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,fontSize));
    }
}
