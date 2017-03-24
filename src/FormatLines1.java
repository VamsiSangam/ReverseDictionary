/* ========== Author : Vamsi Sangam (RIT2013063) ========== */

/*
 *
 * This file was used to convert synonyms list
 * on the web page to a suitable format, which is -
 *
 * Word - Synonym, Synonym, Synonym
 *
 */

import java.util.Scanner;

public class FormatLines1 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        String line;
        
        line = s.nextLine();
        
        while (!line.startsWith("zest")) {
            String text = line;
            
            String line2 = s.nextLine();
            String line3 = s.nextLine();
            String line4 = s.nextLine();
//            if (line2.length() == 0) {
//                line2 = s.nextLine();
//            }
            
            text = line + " - " + line3;
            
            System.out.println(text);
            
            line = s.nextLine();
        }
    }
    
}
