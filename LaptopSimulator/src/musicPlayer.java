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

public class musicPlayer extends JFrame implements ActionListener {
    
    AdvancedPlayer mediaPlayer;
    JButton play = new JButton("Play");
    JButton stop = new JButton("Stop");
    JButton pause = new JButton("Pause");

    public musicPlayer() {
        try{
            FileInputStream file = new FileInputStream("D:/_Object Oriented Development/Group Assignment/Images/music.mp3"); 
            mediaPlayer = new AdvancedPlayer(file);
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(JavaLayerException e){
            e.printStackTrace();
        }

        JFrame player = new JFrame("Music Player");
        player.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        player.setLayout(new GridLayout(1, 3, 15, 5));//GridLayout(int rows, int cols, int horizontalgap, int verticalgap)
        player.add(play);
        player.add(stop);
        player.add(pause);
        player.pack();
        player.setVisible(true);
        
//        String bip = "D:/_Object Oriented Development/Group Assignment/Images/music.mp3";
//        Media hit = new Media(new File(bip).toURI().toString());
//        mediaPlayer = new MediaPlayer(hit);
        
        play.addActionListener(this);
//        try {
//            as = new AudioStream(this.getClass().getResourceAsStream("music.wav"));
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Unable to play audio", "Alert", JOptionPane.ERROR_MESSAGE);
//        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            try{
                mediaPlayer.play();
                Timer t = new javax.swing.Timer(7000, new ActionListener(){
            @Override
                public void actionPerformed(ActionEvent e){
                    java.awt.Window win[] = java.awt.Window.getWindows(); 
                for(int i=0;i<win.length;i++){ 
                win[i].dispose(); 
            } 
            LaptopSimulator ls = new LaptopSimulator();
                }
            });
            t.start();
            }catch(JavaLayerException ev){
            ev.printStackTrace();
            }
            
        }
        if (e.getSource() == stop) {
            mediaPlayer.stop();
        }
        if (e.getSource() == pause) {
            mediaPlayer.close();
        }
    }

}
