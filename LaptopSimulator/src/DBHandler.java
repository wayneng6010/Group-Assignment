import static java.lang.System.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DBHandler {
    private Statement myStatement;
    
    public DBHandler() {// constructor
        try { // for exception purposes
             //  **********  OTHER DRIVER - UCANACCESS ***********
           
           //Step #1: Registering the Ucanacess Driver / Linking the Driver to jdbc - Ucanaccess - jar folder.
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String url = "jdbc:ucanaccess://D:/_Object Oriented Development/Group Assignment/MusicPlayerDB.accdb";
            
            Connection StudentDB = DriverManager.getConnection(url, "admin", ""); 
            
             myStatement = StudentDB.createStatement();
            
        }
        // The following exceptions must be caught
        catch (ClassNotFoundException cnfe) {
            out.println(cnfe);
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
    }// end of the constructor
    
    public ArrayList<String> getPlaylist() {
        ArrayList<String> songList = new ArrayList<String>();
        try {
            ResultSet results = myStatement.executeQuery
                ("SELECT SongName FROM Playlist ORDER BY ID");
            while (results.next()) {
                songList.add(results.getString(1));
            }
            // STEP 5
            results.close();
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return songList;
    }
    
    public String getSongPath(String songName){
        String result = "";
        try {
            ResultSet results = myStatement.executeQuery
                ("SELECT SongPath FROM Playlist WHERE SongName=\"" + songName + "\"");
            JOptionPane.showMessageDialog(null, songName, "Error", JOptionPane.ERROR_MESSAGE);

            if (results.next()) {
                result = results.getString(1);
            }else{
                JOptionPane.showMessageDialog(null, "Song not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            results.close();
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return result;
    }
}
