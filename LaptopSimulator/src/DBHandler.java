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
//            JOptionPane.showMessageDialog(null, songName, "Error", JOptionPane.ERROR_MESSAGE);

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
    
    public boolean addSong(String songName, String songPath){
        try {
        ResultSet results = myStatement.executeQuery
                ("SELECT SongPath FROM Playlist WHERE SongName=\"" + songName + "\"");
//            JOptionPane.showMessageDialog(null, songName, "Error", JOptionPane.ERROR_MESSAGE);
            if (results.next() == false) {
                String writeString = "INSERT INTO Playlist VALUES(" + null + ",\"" + songName + "\",\"" + songPath + "\")";
                try {
                    myStatement.executeUpdate(writeString);
                }
                catch (SQLException sqle) {
                    return false; 
                }
                    return true; // inserted OK
            }else{
                return false; 
            }
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return false; 
    }
    
    public boolean deleteSong(String songName){
        String deleteString = "DELETE FROM Playlist WHERE SongName=\"" + songName + "\"" ;
        try {
            myStatement.executeUpdate(deleteString);
        }
        catch (SQLException sqle) {
            return false; 
        }
            return true; // inserted OK
    }
    
    //Settings
    public boolean changeWallpaper(String path){
        String deleteString = "DELETE FROM wallpaper";
        String writeString = "INSERT INTO wallpaper VALUES(\"" + path + "\")";
        try {
            myStatement.executeUpdate(deleteString);
            myStatement.executeUpdate(writeString);
        }
        catch (SQLException sqle) {
            return false; 
        }
            return true; // inserted OK
    }
    
    public String getWallpaperPath(){
        String result = "";
        try {
            ResultSet results = myStatement.executeQuery
                ("SELECT * FROM wallpaper");
//            JOptionPane.showMessageDialog(null, songName, "Error", JOptionPane.ERROR_MESSAGE);
            if (results.next()) {
                result = results.getString(1);
            }else{
                result = "D:/_Object Oriented Development/Group Assignment/Images/wallpaper.jpg"; // default wallpaper
            }
            results.close();
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return result;
    }
    
    //get system information
     public ArrayList<String> getSysInfo(){
        ArrayList<String> result = new ArrayList<String>();
        try {
            ResultSet results = myStatement.executeQuery
                ("SELECT * FROM system_information");
//            JOptionPane.showMessageDialog(null, songName, "Error", JOptionPane.ERROR_MESSAGE);
            if (results.next()) {
                result.add(results.getString(1));
                result.add(results.getString(2));
                result.add(results.getString(3));
                result.add(results.getString(4));
                result.add(results.getString(5));
            }
            results.close();
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return result;
    }
    
    //install software
    public boolean installSoftware(String name){
        try {
        ResultSet results = myStatement.executeQuery
                ("SELECT * FROM SoftwareInstall WHERE Software=\"" + name + "\"");
//            JOptionPane.showMessageDialog(null, songName, "Error", JOptionPane.ERROR_MESSAGE);
            if (results.next() == false) {
                String writeString = "INSERT INTO SoftwareInstall VALUES(\"" + name + "\")";
                try {
                    myStatement.executeUpdate(writeString);
                }
                catch (SQLException sqle) {
                    return false; 
                }
                    return true; // inserted OK
            }else{
                return false; 
            }
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return false; 
    }
    
    //get installed software
    public ArrayList<String> getInstalledSoftware(){
        ArrayList<String> result = new ArrayList<String>();
        int counter = 0;
        try {
            ResultSet results = myStatement.executeQuery
                ("SELECT * FROM SoftwareInstall");
//            JOptionPane.showMessageDialog(null, songName, "Error", JOptionPane.ERROR_MESSAGE);
            while (results.next()) {
                result.add(results.getString(1));
            }
            results.close();
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return (result);
    }
    //uninstall software
    public boolean UninstallSoftware(String name){
        String deleteString = "DELETE FROM SoftwareInstall WHERE Software=\"" + name + "\"" ;
        try {
            myStatement.executeUpdate(deleteString);
        }
        catch (SQLException sqle) {
            return false; 
        }
            return true; // inserted OK
    }

    //get software description
    public String getDescription(String softwareName){
        String result = "";
        try {
            ResultSet results = myStatement.executeQuery
                ("SELECT Description FROM Software WHERE Software=\"" + softwareName + "\"");
//            JOptionPane.showMessageDialog(null, Description, "Error", JOptionPane.ERROR_MESSAGE);

            if (results.next()) {
                result = results.getString(1);
            }else{
                JOptionPane.showMessageDialog(null, "Description not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            results.close();
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return result;
    }
    
    //get file from database
    public String getFile(String fileName){
        String result ="";
        try{
            //select the file and display the notepsf
            ResultSet results = myStatement.executeQuery
                ("SELECT note FROM notepad WHERE fileName=\"" + fileName + "\"");
            if (results.next()) {
                result = results.getString(1);
            }else{
                JOptionPane.showMessageDialog(null, "Record not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            results.close();
        }
        catch (SQLException sqle){
            out.println(sqle);
        }
        return result;
    }
    //save the note into a new fileName in database
    public boolean saveFile(String fileName, String notepad){
        String writeString = "INSERT INTO notepad (fileName, note) VALUES(\"" + fileName + "\",\""+notepad+"\")";
        try {
            myStatement.executeUpdate(writeString);
        }
        catch (SQLException sqle) {
            return false; 
        }
            return true; // inserted OK
    }

    //get fileName
    public ArrayList<String> getFiles() {
        ArrayList<String> fileList = new ArrayList<String>();
        try {
            ResultSet results = myStatement.executeQuery
                ("SELECT fileName FROM notepad");
            while (results.next()) {
                fileList.add(results.getString(1));
            }
            // STEP 5
            results.close();
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return fileList;
    }
    //get softwareName
    public ArrayList<String> getSoftware() {
        ArrayList<String> SoftwareList = new ArrayList<String>();
        try {
            ResultSet results = myStatement.executeQuery
                ("SELECT * FROM SoftwareInstall");
            while (results.next()) {
                SoftwareList.add(results.getString("Software"));
            }
            // STEP 5
            results.close();
        }
        catch (SQLException sqle) {
            out.println(sqle);
        }
        return SoftwareList;
    }
}
