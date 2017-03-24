package algo;

import java.util.ArrayList;
import java.util.Scanner;

public class Thesarus {

    public static void main(String[] args) {
        ReturnUtil graph = GraphData.filename("synonyms-catalog-2.txt");
        
        System.out.println("Enter the meaning - ");
        Scanner s = new Scanner(System.in);
        
        String inputLine = s.nextLine();
        String[] inputWords = inputLine.split("[ ,.;]");
        ArrayList<Integer> words = new ArrayList<>();
        
        for (String inputWord : inputWords) {
            if (graph.wordToIndex.containsKey(inputWord)) {
                words.add(graph.wordToIndex.get(inputWord));
            } else {
                System.out.println(inputWord + " X");
            }
        }
        
        int result = rank.func(words, graph.graph);
        
        System.out.println("Word - " + graph.indexToWord.get(result));
    }
    
}
