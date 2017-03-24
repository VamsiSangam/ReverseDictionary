/* ========== Author : Vamsi Sangam (RIT2013063) ========== */

/*
 *
 * This program takes synonyms-list.txt as input
 * which has words in the format -
 *
 * Word Synonym Synonym Synonym
 *
 * Then it processes the input to generate a single list
 * of words which occur anywhere in the list of synonyms
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class FormatFile2 {

    public static void main(String[] args) {
        generateWordsCatalog("synonyms-catalog.txt", "words-catalog-3.txt");
    }

    public static void generateWordsCatalog(String inputFileName, String outputFileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFileName));
            
            
            ArrayList<String> wordsCatalog = new ArrayList<>();
            HashSet<String> wordsInserted = new HashSet<>();
            
            String line;
            int nodes = 0;
            int edges = 0;
            
            while ((line = br.readLine()) != null) {
                String[] words = line.split("[ ]");
                
                for (String word : words) {
                    ++edges;
                    if (!wordsInserted.contains(word)) {
                        wordsCatalog.add(word);
                        wordsInserted.add(word);
                        ++nodes;
                    }
                }
            }
            
            br.close();
            Collections.sort(wordsCatalog);
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
            
            for (String word : wordsCatalog) {
                bw.write(word);
                bw.newLine();
            }
            
            bw.close();
            System.out.println("Nodes = " + nodes + ", edges = " + edges);
        } catch (Exception ex) {
            System.out.println("Exception - " + ex.getMessage());
        }
    }
}
