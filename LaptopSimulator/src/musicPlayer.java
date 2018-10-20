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
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class musicPlayer extends JFrame implements ActionListener, Runnable {
    
    Thread t; 
    AdvancedPlayer mediaPlayer;
    JButton play = new JButton("Play");
    JButton stop = new JButton("Stop");
    JButton pause = new JButton("Pause");

    public musicPlayer() {
        
        JFrame player = new JFrame("Music Player");
//        player.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        player.setLocationRelativeTo(null);
        player.setLayout(new GridLayout(1, 3, 15, 5));//GridLayout(int rows, int cols, int horizontalgap, int verticalgap)
        player.add(play);
        player.add(stop);
        player.add(pause);
        player.pack();
        player.setVisible(true);
        t = new Thread(this);
//        String bip = "D:/_Object Oriented Development/Group Assignment/Images/music.mp3";
//        Media hit = new Media(new File(bip).toURI().toString());
//        mediaPlayer = new MediaPlayer(hit);
        
        play.addActionListener(this);
        pause.addActionListener(this);
        stop.addActionListener(this);

//        try {
//            as = new AudioStream(this.getClass().getResourceAsStream("music.wav"));
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Unable to play audio", "Alert", JOptionPane.ERROR_MESSAGE);
//        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            t.start();           
        }
        else if (e.getSource() == pause) {
            t.suspend();
        }
        else if (e.getSource() == stop) {
            t.stop();
        }
        
    }
    
    @Override
        public void run(){
            FileInputStream file;
            try{
            file = new FileInputStream("D:/_Object Oriented Development/Group Assignment/Images/music.mp3"); 
            mediaPlayer = new AdvancedPlayer(file);
            mediaPlayer.play();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(JavaLayerException e){
                e.printStackTrace();
            }
        }

}
