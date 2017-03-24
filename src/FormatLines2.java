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

public class FormatLines2 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        String line;
        
        line = s.nextLine();
        
        while (!line. endsWith("123")) {
            if (line.length() == 0) {
                line = s.nextLine();
            }
            
            line =  line.replace(line.charAt(line.indexOf(" ") + 1), '-');
            
            System.out.println(line);
            line = s.nextLine();
        }
    }
    
}
