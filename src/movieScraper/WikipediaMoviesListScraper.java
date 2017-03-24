/* ========== ========== ========== */
//       Author - Vamsi Sangam      //
/* ========== ========== ========== */

/* ========== About WikipediaMoviesListScraper.java ==========



 ========== ========== ========== ========== */
package movieScraper;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;

public class WikipediaMoviesListScraper {

    public static void main(String[] args) throws Exception {
        UserAgent userAgent = new UserAgent();
                // userAgent.setProxyHost("172.19.11.2");
        // userAgent.setProxyPort(8080);

        int count = 0;
        String url = "https://en.wikipedia.org/wiki/List_of_American_films_of_200" + count;
        userAgent.visit(url);
        
        Elements wikiTable = userAgent.doc.findEvery("<table class=\"wikitable sortable jquery-tablesorter\" style=\"width:100%;\">");
        
        for (Element table : wikiTable.getChildElements()) {
            for (Element row : table.findEach("<tr>")) {
                System.out.println(row.getText());
            }
        }

    }

}
