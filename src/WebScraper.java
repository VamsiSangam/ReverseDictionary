/* ========== Author : Vamsi Sangam (RIT2013063) ========== */

/*
 *
 * This file has a method which is a Web Scraping Program
 * which uses the Web Scraping Library Jaunt
 *
 * It takes words from the file wordsCatalog.txt and queries
 * thesaurus.com for the words' synonyms and then writes them
 * into synonyms-catalog.txt. The progress is printed to the console.
 *
 */
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WebScraper {

    public static void main(String[] args) {
        scrapeThesaurus("words-catalog-3.txt", "synonyms-catalog-3.txt");
    }

    public static void scrapeThesaurus(String inputWordsFile, String outputWordsFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputWordsFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputWordsFile));
            
            String inputWord;

            while ((inputWord = br.readLine()) != null) {
                if (!isAlphabetical(inputWord)) {
                    continue;
                }
                
                System.out.println(inputWord + " started...");
                UserAgent userAgent = new UserAgent();
                // userAgent.setProxyHost("172.19.11.2");
                // userAgent.setProxyPort(8080);
                
                String url = "http://www.thesaurus.com/browse/" + inputWord;
                userAgent.visit(url);
                Elements relevancyLists = userAgent.doc.findEvery("<div class=\"relevancy-list\">");

                bw.write(inputWord + " ");

                for (Element child : relevancyLists.getChildElements()) {
                    for (Element ul : child.findEvery("<ul>").getChildElements()) {
                        // System.out.println(child.innerHTML());
                        for (Element items : ul.findEvery("<li>").getChildElements()) {
                            items = items.getFirst("<a>");
                            items = items.getFirst("<span>");
                            String synonym =  items.innerHTML();
                            
                            if (synonym.indexOf(' ') == -1 && synonym.equals(synonym.toLowerCase()) && !isNumeric(synonym) && !synonym.startsWith("-") && isAlphabetical(synonym)) {
                                bw.write(synonym + " ");
                            }
                        }

                    }
                }

                bw.newLine();
                System.out.println(inputWord + " done...");
            }

            br.close();
            bw.close();
        } catch (Exception ex) {
            System.out.println("Exception - " + ex.getMessage());
        }
    }
    
    public static boolean isNumeric(String word) {
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) >= '0' && word.charAt(i) <= '9') {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean isAlphabetical(String word) {
        for (int i = 0; i < word.length(); ++i) {
            if (!((word.charAt(i) >= 'a' && word.charAt(i) <= 'z') || word.charAt(i) == '-')) {
                return false;
            }
        }
        
        return true;
    }
}
