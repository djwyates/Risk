
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
    private static String input = "";
    private static ArrayList<TextLog> statements = new ArrayList<TextLog>();
    private String statement;
    private Color color;
    private int x;
    private int y;
    
    TextLog(String _statement){
        statement=_statement;
        x=20;
        y=857;
        color = Color.black;
        for(int i=0;i<statements.size();i++){
            statements.get(i).y-=25;
            statements.get(i).color = new Color(0+((statements.size()-i-1)*15),0+((statements.size()-i-1)*15),0+((statements.size()-i-1)*15));
        }
    }
    
    static void createStatement(String _state){
        TextLog _statement = new TextLog(_state);
        statements.add(_statement);
    }
    
    static void drawStatements(Graphics2D g) throws FontFormatException, IOException{
        //Storing old sets for colors,text,etc
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();
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
        g.setColor(oldColor);
        g.setFont(oldFont);
    }
    static void drawInput(Graphics2D g) throws FileNotFoundException, FontFormatException, IOException {
        //Storing old sets for colors,text,etc
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();
        SetFontsAndColors(20);
        
        
        g.drawString(input, 20, 890);
        
        
        //Setting font back to old sets
        g.setColor(oldColor);
        g.setFont(oldFont);
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
        g.setColor(Color.RED);
        g.setFont( Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Allan.ttf"))).deriveFont(Font.PLAIN,fontSize));
    }
}
