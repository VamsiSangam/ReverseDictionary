/* ========== Author : Vamsi Sangam (RIT2013063) ========== */

/*
 *
 * This file was used to convert synonyms list
 * this format -
 *
 * Word - Synonym, Synonym, Synonym
 *
 * to this format -
 * 
 * Word Synonym Synonym Synonym
 *
 * This file takes the unordered culmination of all synonyms
 * list collected from various sources and generates a list
 * which has unique entries and synonyms from all the sources.
 *
 * This file also converts all the words to lower case for data
 * consistency and sorts the final record.
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class FormatFile1 {

    public static void main(String[] args) {
        formatFile("list.txt", "synonyms-list.txt");
    }

    public static void formatFile(String inputFileName, String outputFileName) {
        try {
            // Buffered Reader to read from a file
            BufferedReader br = new BufferedReader(new FileReader(inputFileName));

            // We will store words and their synonyms in the form of and Adjacency List
            ArrayList<WordNode> adjList = new ArrayList<>();

            // HashTable for checking if a word is already present or not
            HashSet<String> wordsInserted = new HashSet<>();

            // Hash Table which maps a word inserted to its index in the adjacency List
            HashMap<String, Integer> wordsInsertedIndex = new HashMap<>();

            String line;

            while ((line = br.readLine()) != null) {    // Until there's a line to read
                int firstSpace;
                
                // To extract the word -> substring till first occurence of space
                String word = line.substring(0, firstSpace = line.indexOf(" "));
                word = word.toLowerCase();  // Converting to Lower case

                if (!wordsInserted.contains(word)) {
                    // This word was never dealt before
                    // So, insert this word to Adjacency List
                    // and then add all the synonyms
                    
                    adjList.add(new WordNode(word));
                    wordsInserted.add(word);
                    wordsInsertedIndex.put(word, adjList.size() - 1);
                    
                    // Extract everything after hyphen
                    line = line.substring(firstSpace + 3);
                    // Split synonyms by comma
                    String[] synonyms = line.split("[,]");

                    for (String synonym : synonyms) {
                        synonym = synonym.trim().toLowerCase();
                        
                        if (synonym.length() > 0) {
                            if (synonym.indexOf(' ') != -1) {
                                // Ignore Compound Word Synonym
                                continue;
                            }
                            
                            adjList.get(adjList.size() - 1).addSynonym(synonym);
                        }
                    }
                } else {
                    // This word was dealt before, so check if this entry can
                    // give us new synonyms, if yes, add them to existing list
                    line = line.substring(firstSpace + 3);
                    String[] synonyms = line.split("[,]");

                    for (String synonym : synonyms) {
                        synonym = synonym.trim().toLowerCase();

                        if (synonym.indexOf(' ') != -1) {
                            // Ignore Compound Word Synonym
                            continue;
                        }

                        if (!adjList.get(wordsInsertedIndex.get(word)).isSynonymInserted(synonym) && synonym.length() > 0) {
                            // This is a new synonym to the existing word, add it to the list of synonyms
                            adjList.get(wordsInsertedIndex.get(word)).addSynonym(synonym);
                        }
                    }
                }
            }
            
            br.close();     // Closing Buffered Reader
            Collections.sort(adjList);  // Sorting records -> O(N log N)
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
            
            // Printing Adjacency List
            for (int i = 0; i < adjList.size(); ++i) {
                bw.write(adjList.get(i).getWord() + " ");
                
                for (String synonym : adjList.get(i).getSynonyms()) {
                    bw.write(synonym + " ");
                }

                bw.newLine();
            }
            
            bw.close();     // Closing Buffered Writer
        } catch (Exception ex) {
            System.out.println("Exception in FormatFile1.formatFile() - " + ex.getMessage());
        }
    }
}

class WordNode implements Comparable<WordNode> {

    private final String word;    // Stores the word

    // Stores the synonyms
    private final ArrayList<String> synonyms;

    // This is to check if a particular synonym is
    // already present as the synonym of this word
    private final HashSet<String> synonymsInserted;

    public WordNode(String word) {
        this.word = word;
        synonyms = new ArrayList<>();
        synonymsInserted = new HashSet<>();
    }

    public String getWord() {
        return word;
    }
    
    // Adds a synonym to the list and
    // to the HashSet, for further use -> O(1)
    public void addSynonym(String synonym) {
        this.synonyms.add(synonym);
        this.synonymsInserted.add(synonym);
    }

    // Checks for synonym's existence in HashSet - O(1)
    public boolean isSynonymInserted(String synonym) {
        return synonymsInserted.contains(synonym);
    }
    
    // Returns a new object which is an
    // ArrayList of synonyms of this word
    public ArrayList<String> getSynonyms() {
        // To prevent encapsulation breach
        return new ArrayList<>(synonyms);
    }

    @Override
    public int compareTo(WordNode o) {
        return this.word.compareTo(o.getWord());
    }
}
