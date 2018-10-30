import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;

// JLayer - Open Source MP3 Library (Link -> http://www.javazoom.net/javalayer/javalayer.html)
// used to play mp3 files 
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class musicPlayer extends JFrame implements ActionListener, Runnable {
    //dbhandler
    DBHandler db = new DBHandler();
    
    Thread t; // thread 
    AdvancedPlayer mediaPlayer; //media player
    Font font = new Font("Segoe UI", Font.BOLD, 22);//font
    Font font1 = new Font("Times New Roman", Font.BOLD, 20);//font
    
    //top panel components
    JLabel chooseLbl = new JLabel("Add Music");
    JButton chooseBtn = new JButton("Choose File");
    JFileChooser fileChooser = new JFileChooser();//file chooser
    JLabel fileLbl = new JLabel("No file chosen");
    Icon addIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/add.png");
    JButton addBtn = new JButton("Add To Playlist", addIcon);
    
    //middle panel components
    Icon playlistIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/playlist.png");
    JLabel playlistLbl = new JLabel("Playlist");
    DefaultListModel playlistModel = new DefaultListModel();
    ArrayList<String> songListArr = new ArrayList<String>();
    JList playlistList = new JList();
    
    //bottom panel components
    Icon playIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/play.png");
    JButton play = new JButton(playIcon);
    
    Icon stopIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/stop.png");
    JButton stop = new JButton(stopIcon);
    
    Icon pauseIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/pause.png");
    JButton pause = new JButton(pauseIcon);
    
    Icon resumeIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/resume.png");
    JButton resume = new JButton(resumeIcon);
    
    Icon deleteIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/delete.png");
    JButton delete = new JButton(deleteIcon);
    
    //instance variables
    String filePath = null;
    String fileName = null;
    String selectedSongPlay = "";
    
    public musicPlayer() {
        //top panels
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //set font
        chooseLbl.setFont(font);
        chooseBtn.setFont(font1);
        fileLbl.setFont(font1);
        addBtn.setFont(font1);
        fileLbl.setPreferredSize(new Dimension(270, 20)); 
        // add components to top panel
        top.add(Box.createRigidArea(new Dimension(0, 50))); //create space
        top.add(chooseLbl);
        top.add(Box.createRigidArea(new Dimension(20, 0))); //create space
        top.add(chooseBtn);
        top.add(fileLbl);
        top.add(Box.createRigidArea(new Dimension(10, 0))); //create space
        top.add(addBtn);
        
        //middle panel 
        //retrieve data from database
        songListArr = db.getPlaylist();
        for (int i = 0; i < songListArr.size(); i++)
            {
                playlistModel.addElement(songListArr.get(i));
            }
        playlistList.setModel(playlistModel);

        JPanel middle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middle.setBackground(new Color(143, 170, 220, 100));
        playlistList.setFixedCellHeight(30);
        playlistList.setFixedCellWidth(500);
        playlistList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//only can select item at a time

        //set font
        playlistLbl.setFont(font);
        playlistLbl.setIcon(playlistIcon);
        playlistList.setFont(font);
        // add components to middle panel
        middle.add(Box.createRigidArea(new Dimension(800, 15))); //create space
        middle.add(Box.createRigidArea(new Dimension(50, 0))); //create space
        middle.add(playlistLbl);
        middle.add(Box.createRigidArea(new Dimension(60, 0))); //create space
        middle.add(playlistList);
        //add scroll pane to JList playlist
        JScrollPane scrollPane = new JScrollPane(playlistList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        middle.add(scrollPane);
        
        // bottom panel
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // button default properties
        pause.setEnabled(false);
        resume.setEnabled(false);
        stop.setEnabled(false);
        // add components to bottom panel
        bottom.add(play);
        bottom.add(pause);
        bottom.add(resume);
        bottom.add(stop);
        bottom.add(Box.createRigidArea(new Dimension(260, 0))); //create space
        bottom.add(delete);
        
        bottom.setVisible(true);

        //add JPanel to window
        add("North", top);
        add("Center", middle);
        add("South", bottom);
        
        // window properties
        setSize(800, 480);
        setTitle("Music Player");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        chooseBtn.addActionListener(this);
        addBtn.addActionListener(this);
        play.addActionListener(this);
        pause.addActionListener(this);
        stop.addActionListener(this);
        resume.addActionListener(this);
        delete.addActionListener(this);
        
        this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            if(pause.isEnabled() == true){
                t.stop();//stop music player when the window is closed
            }
        }
        });
        
    }
    
    
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == chooseBtn) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 Files", "mp3");//only mp3 format of files can be shown
            chooser.setFileFilter(filter);//only mp3 format of file can be shown
            int returnVal = chooser.showOpenDialog(this); //show file chooser window
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                fileName = chooser.getSelectedFile().getName();//get mp3 file name
                filePath = chooser.getSelectedFile().getAbsolutePath();//get mp3 file path
                filePath = filePath.replace('\\', '/');// replace the backslash to forward slash in filePath
                fileLbl.setText(fileName);
//                JOptionPane.showMessageDialog(null, filePath, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == addBtn) {
            if(filePath != null){
                playlistModel.clear();//clear the model before setting an updated one
                boolean addSucess = db.addSong(fileName, filePath);//store song data into database
                if (!addSucess){
                    JOptionPane.showMessageDialog(null, "This MP3 file already in the playlist", "Error", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    songListArr.add(fileName);
                }
                for (int i = 0; i < songListArr.size(); i++)
                {
                    playlistModel.addElement(songListArr.get(i));
                }
                playlistList.setModel(playlistModel);
            }else{
                JOptionPane.showMessageDialog(null, "Please upload a MP3 file first.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getSource() == play) {
            //check if any song is selected in JList playlist
            if(playlistList.isSelectionEmpty()){
                JOptionPane.showMessageDialog(null, "Please select a song in the playlist.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }else{
                play.setEnabled(false);
                pause.setEnabled(true);
                resume.setEnabled(true);
                stop.setEnabled(true);
                t = new Thread(this);
                t.start();
            }
        }
        else if (e.getSource() == pause) {
            t.suspend();
        }
        else if (e.getSource() == stop) {
            play.setEnabled(true);
            pause.setEnabled(false);
            resume.setEnabled(false);
            stop.setEnabled(false);
            t.stop();
        }
        else if (e.getSource() == resume) {
            t.resume();
        }
        else if (e.getSource() == delete) {
            //check if any song is selected in JList playlist
            if(playlistList.isSelectionEmpty()){
                JOptionPane.showMessageDialog(null, "Please select a song in the playlist.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }else{
                String selectedSong = playlistList.getSelectedValue().toString();
                int confirm;
                boolean success;
                confirm = JOptionPane.showConfirmDialog (null, "Are sure to delete this song from playlist?", "Delete song", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){
                    try{
                        if(t.isAlive() && (selectedSongPlay.equals(selectedSong))){
                            t.stop();//stop the music
                            play.setEnabled(true);
                            pause.setEnabled(false);
                            resume.setEnabled(false);
                            stop.setEnabled(false);
                        }
                    }catch(Exception ev){

                    }
                    success = db.deleteSong(selectedSong);
                    if(success){
                        playlistModel.clear();//clear the model before setting an updated one
                        songListArr = db.getPlaylist();
                        for (int i = 0; i < songListArr.size(); i++){
                            playlistModel.addElement(songListArr.get(i));
                        }
                        playlistList.setModel(playlistModel);
                    }else{
                        JOptionPane.showMessageDialog(null, "Failed to delete song.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        }
    }
    
    @Override
        public void run(){
            FileInputStream file;
            
            try{
                selectedSongPlay = playlistList.getSelectedValue().toString();
                String filePath = db.getSongPath(selectedSongPlay);
//                JOptionPane.showMessageDialog(null, filePath, "Error", JOptionPane.ERROR_MESSAGE);
                file = new FileInputStream(filePath); 
                mediaPlayer = new AdvancedPlayer(file);
                mediaPlayer.play();
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null, "MP3 file not found.", "Error", JOptionPane.ERROR_MESSAGE);
                play.setEnabled(true);
                pause.setEnabled(false);
                resume.setEnabled(false);
                stop.setEnabled(false);
                int confirm = JOptionPane.showConfirmDialog (null, "Do you want to remove this song from the playlist since it cannot be found?", "File Not Found", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){
                    boolean success = db.deleteSong(selectedSongPlay);
                    if(success){
                        playlistModel.clear();//clear the model before setting an updated one
                        songListArr = db.getPlaylist();
                        for (int i = 0; i < songListArr.size(); i++){
                            playlistModel.addElement(songListArr.get(i));
                        }
                        playlistList.setModel(playlistModel);
                    }else{
                        JOptionPane.showMessageDialog(null, "Failed to delete song.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                e.printStackTrace();
            }catch(JavaLayerException e){
                e.printStackTrace();
            }
        }
}
