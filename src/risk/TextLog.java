
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
import java.util.logging.Level;
import java.util.logging.Logger;
import static risk.Risk.g;
public class TextLog {
    private static String input = "";
    private static ArrayList<TextLog> statements = new ArrayList<TextLog>();
    private String statement;
    private int x;
    private int y;
    
    TextLog(String _statement){
        statement=_statement;
        x=20;
        y=857;
        for(int i=0;i<statements.size();i++){
            statements.get(i).y-=25;
        }
    }
    
    static void createStatement(String _state){
        TextLog _statement = new TextLog(_state);
        statements.add(_statement);
    }
    
    static void drawStatements(Graphics2D g){
        //Storing old sets for colors,text,etc
        Color oldColor = g.getColor();
        Font oldFont = g.getFont();
        SetFontsAndColors(14);
        
        
        
        for(TextLog s : statements){
            if(s.y<500){
                statements.remove(s);
            }
            g.drawString(s.statement, s.x, s.y);
        }
        
        //Setting font back to old sets
        g.setColor(oldColor);
        g.setFont(oldFont);
    }
    static void drawInput(Graphics2D g) throws FileNotFoundException, FontFormatException, IOException{
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
    
    static void SetFontsAndColors(int fontSize){
        g.setColor(Color.RED);
        try {
            g.setFont( Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("FontFiles/Viner.ttf"))).deriveFont(Font.PLAIN,fontSize));
        } catch (FontFormatException ex) {
            Logger.getLogger(TextLog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
