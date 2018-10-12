import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Settings extends JFrame implements ActionListener
{

    JLabel lblChange = new JLabel ("Change Wallpaper");
    JButton btnCFile = new JButton ("Choose File");
    JButton btnSave = new JButton ("Save");
    
    JLabel lblSystem = new JLabel ("System Information");
    JButton btnView = new JButton ("View");
    

    public static void main(String[] args) {
     Settings St = new Settings();
    }
    public Settings(){
        setLayout(new FlowLayout());
        setSize(400,300);
        setTitle("Settings");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(300,100));
      
        
              top.setLayout(new FlowLayout());
              top.add(lblChange);
              top.add(btnCFile);
              top.add(btnSave);
              
         JPanel middle=new JPanel();
         middle.setPreferredSize(new Dimension(300,100));
         middle.add(Box.createRigidArea(new Dimension(-120, 10)));
         
         middle.add(lblSystem);
         middle.add(btnView);
               
    
        
                  add("North",top);//add the top panel at the north
                  add("South",middle);
                  
                  btnCFile.addActionListener(this);
                  btnSave.addActionListener(this);
                  btnView.addActionListener(this);
                  setVisible(true);
}
    public void actionPerformed (ActionEvent e){
    
    }
     
}


