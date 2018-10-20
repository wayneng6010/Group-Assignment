import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import sun.audio.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.lang.Runnable;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
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
//    String songList[] = {};
    DefaultListModel playlistModel = new DefaultListModel();
    ArrayList<String> songList = new ArrayList<String>();
    JList playlist = new JList();

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

    public musicPlayer() {
        songList.add("Hey");
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
        songList = db.getPlaylist();
        for (int i = 0; i < songList.size(); i++)
            {
                playlistModel.addElement(songList.get(i));
            }
        playlist.setModel(playlistModel);
        
        JPanel middle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middle.setBackground(new Color(143, 170, 220, 100));
        playlist.setPreferredSize(new Dimension(500, 250));  
        //set font
        playlistLbl.setFont(font);
        playlistLbl.setIcon(playlistIcon);
        playlist.setFont(font);
        // add components to middle panel
        middle.add(Box.createRigidArea(new Dimension(800, 15))); //create space
        middle.add(Box.createRigidArea(new Dimension(50, 0))); //create space
        middle.add(playlistLbl);
        middle.add(Box.createRigidArea(new Dimension(60, 0))); //create space
        middle.add(playlist);
        
        // bottom panel
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        // add components to bottom panel
        bottom.add(play);
        bottom.add(pause);
        bottom.add(resume);
        bottom.add(stop);
        bottom.add(Box.createRigidArea(new Dimension(260, 0))); //create space
        bottom.add(delete);
        
        bottom.setVisible(true);
//        t = new Thread(this);
//        String bip = "D:/_Object Oriented Development/Group Assignment/Images/music.mp3";
//        Media hit = new Media(new File(bip).toURI().toString());
//        mediaPlayer = new MediaPlayer(hit);
        

        //add JPanel to window
        add("North", top);
        add("Center", middle);
        add("South", bottom);
        
        // window properties
        setSize(800, 480);
        setTitle("Music Player");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
//        try {
//            as = new AudioStream(this.getClass().getResourceAsStream("music.wav"));
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Unable to play audio", "Alert", JOptionPane.ERROR_MESSAGE);
//        }
        

    }
    String filePath = null;
    String fileName = null;
    
    
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
                JOptionPane.showMessageDialog(null, filePath, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == addBtn) {
            
            songList.add(fileName);
            for (int i = 0; i < songList.size(); i++)
            {
                playlistModel.addElement(songList.get(i));
            }
            playlist.setModel(playlistModel);
//            playlist.validate();
//            playlist.repaint();
            JOptionPane.showMessageDialog(null, songList, "Error", JOptionPane.ERROR_MESSAGE);
        }
        else if (e.getSource() == play) {
            t = new Thread(this);
            t.start();           
        }
        else if (e.getSource() == pause) {
            t.suspend();
        }
        else if (e.getSource() == stop) {
            t.stop();
        }
        else if (e.getSource() == resume) {
            t.resume();
        }
        else if (e.getSource() == delete) {
            
        }
    }
    
    @Override
        public void run(){
            FileInputStream file;
            try{
                String selectedSong = playlist.getSelectedValue().toString();
                String filePath = db.getSongPath(selectedSong);
//                JOptionPane.showMessageDialog(null, filePath, "Error", JOptionPane.ERROR_MESSAGE);
                file = new FileInputStream(filePath); 
                mediaPlayer = new AdvancedPlayer(file);
                mediaPlayer.play();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(JavaLayerException e){
                e.printStackTrace();
            }
        }

}
