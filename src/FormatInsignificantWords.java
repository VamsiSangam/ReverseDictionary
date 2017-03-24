
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

/* ========== ========== ========== */
//       Author - Vamsi Sangam      //
/* ========== ========== ========== */

/* ========== About FormatInsignificantWords.java ==========

Extracting all the unique words from the
final list of words and meanings

========== ========== ========== ========== */


public class FormatInsignificantWords {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("MeaningsWithoutInsignificant.txt"));
        String line;
        ArrayList<String> wordsList = new ArrayList<>();
        int max = -1;
        
        while ((line = br.readLine()) != null) {
            String[] words = line.split("[ >]");
            String text = words[0];
            
            if (!words[1].equals("-")) {
                int i = 0;
                text = "";
                
                while (!words[i].equals("-")) {
                    text += words[i] + " ";
                    ++i;
                }
            }
            
            text = text.trim();
            
            if (!wordsList.contains(text)) {
                wordsList.add(text);
                
                if (text.length() > max) {
                    max = text.length();
                }
            }
        }
        
        Collections.sort(wordsList);
        System.out.println("Total list size - " + wordsList.size() + ", max = " + max);
        
        br.close();
        
        BufferedWriter bw = new BufferedWriter(new FileWriter("words-catalog.txt"));
        
        for (String str : wordsList) {
            bw.write(str);
            bw.newLine();
        }
        
        bw.close();
    }

}
