
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/* ========== ========== ========== */
//       Author - Vamsi Sangam      //
/* ========== ========== ========== */

/* ========== About FormatDictionaryDatabase.java ==========

 We have a database which has the word and its meaning.
 This program connects to the database, retrieves the
 relevant data and outputs it to a .txt file which is
 used for the main thesaurus algorithm.

 ========== ========== ========== ========== */
public class FormatDictionaryDatabase {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");     // Loading Driver
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mysql?user=root&password=mysql");      // Making connection to DB

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select word, definition from dictionary.entries");

        int lines = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("MeaningsCatalog.txt"));

        while (rs.next()) {
            String[] breaks = rs.getString(2).split("[\n]");
            String meaning = "";

            for (int i = 0; i < breaks.length; ++i) {
                meaning += " " + breaks[i].trim();
            }

            if (meaning.trim().length() != 0) {
                bw.write(rs.getString(1).toLowerCase() + "|" + meaning.trim().toLowerCase());
                bw.newLine();
            }

            ++lines;
        }

        System.out.println("Lines = " + lines);

        bw.close();
        st.close();
        con.close();
    }

}
