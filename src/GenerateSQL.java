
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/* ========== ========== ========== */
//       Author - Vamsi Sangam      //
/* ========== ========== ========== */

/* ========== About GenerateSQL.java ==========



 ========== ========== ========== ========== */
public class GenerateSQL {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("MeaningsCatalog.txt"));
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> meanings = new ArrayList<>();
        String line;
        int count = 1;
        String meaning = "";

        while ((line = br.readLine()) != null) {
            String[] arr = line.split("[|]");
            //System.out.println(arr[0] + "***" + arr[1]);
            if (words.contains(arr[0])) {
                ++count;
                //System.out.println(line);
                meaning += "<br><br>(" + count + ") " + arr[1];
            } else {
                if (words.size() == meanings.size() + 1) {
                    meanings.add(meaning);
                }

                words.add(arr[0]);
                count = 1;
                meaning = "(" + count + ") " + arr[1];
            }
        }

        if (words.size() == meanings.size() + 1) {
            meanings.add(meaning);
        }

        br.close();

        System.out.println("Words = " + words.size() + ", Meanings = " + meanings.size());

        BufferedWriter bw = new BufferedWriter(new FileWriter("WordsAndMeanings.txt"));

        for (int i = 0; i < words.size() && i < meanings.size(); ++i) {
            bw.write(words.get(i) + "|" + meanings.get(i));
            bw.newLine();
        }

        bw.close();

    }

}
