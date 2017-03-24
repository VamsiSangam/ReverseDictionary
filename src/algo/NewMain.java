package algo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rivu
 */
import java.util.*;
import java.io.*;

class GraphData {

    public static void main(String args[]) {
        ReturnUtil data =  filename("synonyms-catalog.txt");
        System.out.println("done");
    }

    public static ReturnUtil filename(String s) {
        Map<String, Integer> mp = new HashMap<>();
        Map<Integer, String> mp1 = new HashMap<>();
        ArrayList<ArrayList<Integer>> outer = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(s))) {
            String x;

            for (int i = 0; i < 50000; i++) {
                outer.add(new ArrayList<Integer>());
            }
            Integer cnt = 0;
            while ((x = br.readLine()) != null) {
                String[] y = x.split("[ ]");
                Integer yolo = mp.get(y[0]);
                Integer yo;

                if (yolo == null) {
                    mp.put(y[0], cnt);
                    mp1.put(cnt, y[0]);
                    yolo = cnt;
                    cnt++;
                }
                for (int i = 1; i < y.length; i++) {
                    yo = mp.get(y[i]);
                    if (yo == null) {
                        mp.put(y[i], cnt);
                        mp1.put(cnt, y[i]);
                        yo = cnt;
                        cnt++;
                    }
                    outer.get(yo).add(yolo);
                    // outer.get(yolo).add(yo);
                }
            }
//            for (int i = 0; i < cnt; i++) {
//                for (int j = 0; j < outer.get(i).size(); j++) {
//                    System.out.print(outer.get(i).get(j) + " ");
//                }
//                System.out.println();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ReturnUtil(mp1, mp, outer);
    }
}

class ReturnUtil {

    Map<Integer, String> indexToWord;
    Map<String, Integer> wordToIndex;
    ArrayList<ArrayList<Integer>> graph;

    public ReturnUtil(Map<Integer, String> indexToWord, Map<String, Integer> wordToIndex, ArrayList<ArrayList<Integer>> graph) {
        this.indexToWord = indexToWord;
        this.wordToIndex = wordToIndex;
        this.graph = graph;
    }
}
