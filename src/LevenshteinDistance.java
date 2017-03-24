
import java.util.Scanner;

/* ========== ========== ========== */
//       Author - Vamsi Sangam      //
/* ========== ========== ========== */

/* ========== About LevenshteinDistance.java ==========



========== ========== ========== ========== */


public class LevenshteinDistance {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
//        String word1 = s.nextLine();
//        String word2 = s.nextLine();
        
        String word1 = "there";
        String word2 = "their";
        
        System.out.println("Edit distance = " + editDistance(word1, word2));
    }
    
    public static int editDistance(String word1, String word2) {
        int[][] arr = new int[word1.length() + 1][word2.length() + 1];
        
        // Init
        for (int i = 0; i <= word1.length(); ++i) {
            arr[i][0] = i;
        }
        
        for (int i = 0; i <= word2.length(); ++i) {
            arr[0][i] = i;
        }
        
        for (int i = 1; i <= word1.length(); ++i) {
            for (int j = 1; j <= word2.length(); ++j) {
                int edit = 1;
                
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    edit = 0;
                }
                
                arr[i][j] = minimum(arr[i - 1][j - 1] + edit, arr[i - 1][j] + 1, arr[i][j - 1] + 1);
            }
        }
        
        System.out.println("Edit Distance Matrix - ");
        for (int i = 0; i <= word1.length(); ++i) {
            for (int j = 0; j <= word2.length(); ++j) {
                System.out.print(arr[i][j] + " ");
            }
            
            System.out.println();
        }
        
        return arr[word1.length()][word2.length()];
    }
    
    public static int minimum(int... numbers) {
        int min = numbers[0];
        
        for (int i = 1; i < numbers.length; ++i) {
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }
        
        return min;
    }

}
