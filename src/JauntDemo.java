/* ========== Author : Vamsi Sangam (RIT2013063) ========== */

/*
 *
 * This is a program which demonstrates the
 * working of a Java Web Scraping library Jaunt 
 *
 */

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;

public class JauntDemo {

    public static void main(String[] args) {
        try {
            UserAgent userAgent = new UserAgent();
            // userAgent.setProxyHost("172.19.11.2");
            // userAgent.setProxyPort(8080);
            String word = "aback";
            userAgent.visit("http://www.thesaurus.com/browse/" + word);
            Elements relevancyLists = userAgent.doc.findEvery("<div class=\"relevancy-list\">");
            
            System.out.print("aback ");
            
//            for (Element child: relevancyLists.getChildElements()) {
//                for (Element ul : relevancyLists.findEvery("<ul>").getChildElements()) {
//                    // System.out.println(child.innerHTML());
//                    for (Element items : child.findEvery("<li>").getChildElements()) {
//                        items = items.getFirst("<a>");
//                        items = items.getFirst("<span>");
//                        System.out.print(items.innerHTML() + " ");
//                    }
//                    
//                    break;
//                }
//                System.out.println();
//            }
            
            for (Element child: relevancyLists.getChildElements()) {
                for (Element ul : child.findEvery("<ul>").getChildElements()) {
                    // System.out.println(child.innerHTML());
                    for (Element items : ul.findEvery("<li>").getChildElements()) {
                        items = items.getFirst("<a>");
                        items = items.getFirst("<span>");
                        System.out.print(items.innerHTML() + " ");
                    }
                    
                }
            }
            
            System.out.println();
        }
        
        catch (Exception ex) {
            System.out.println("Exception - " + ex.getMessage());
        }
    }

}
