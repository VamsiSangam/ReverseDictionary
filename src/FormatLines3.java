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

public class FormatLines3 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        String line;
        
        line = s.nextLine();
        
        while (!line. endsWith("123")) {            
            String[] words = line.split("[ ]");
            
            if (words.length <= 0) {
                break;
            }
            
            String text = "";
            
            int count = 0;
            for (int i = 0; i < words.length; ++i) {
                if (words[i].equals("-")) {
                    ++count;
                }
                
                if (count == 0) {
                    text += words[i];
                }
            }
            
            System.out.println(text);
            line = s.nextLine();
        }
    }
}
