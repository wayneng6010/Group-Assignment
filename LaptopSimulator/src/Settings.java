import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;

public class Settings extends JFrame implements ActionListener
{

    JLabel lblChange = new JLabel ("Change Wallpaper");
    JButton btnCFile = new JButton ("Choose File");
    JButton btnSave = new JButton ("Save");
    JLabel fileName = new JLabel ("No file chosen");
    JLabel lblSystem = new JLabel ("System Information");
    JButton btnView = new JButton ("View");
    DBHandler db = new DBHandler();

    //instance variables
    String filePath = null;
    String selectedSongPlay = "";
    
    public static void main(String[] args) {
     Settings St = new Settings();
    }
    public Settings(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(550,150);
        setTitle("Settings");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(550,50));
        top.setLayout(new FlowLayout(FlowLayout.LEFT));
        top.add(lblChange);
        top.add(btnCFile);
        top.add(fileName);
        top.add(Box.createRigidArea(new Dimension(150, 0))); //create space
        top.add(btnSave);

        JPanel middle=new JPanel(new FlowLayout(FlowLayout.LEFT));
        middle.setPreferredSize(new Dimension(300,50));
         
        middle.add(lblSystem);
        middle.add(btnView);
    
        add(top,BorderLayout.NORTH);//add the top panel at the north
        add(middle,BorderLayout.SOUTH);

        btnCFile.addActionListener(this);
        btnSave.addActionListener(this);
        btnView.addActionListener(this);
        setVisible(true);
}
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == btnCFile) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
            chooser.setFileFilter(filter);//only image format of file can be shown
            int returnVal = chooser.showOpenDialog(this); //show file chooser window
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                fileName.setText(chooser.getSelectedFile().getName());//get image file name
                filePath = chooser.getSelectedFile().getAbsolutePath();//get image file path
                filePath = filePath.replace('\\', '/');// replace the backslash to forward slash in filePath
//                JOptionPane.showMessageDialog(null, filePath, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
     
        if (e.getSource() == btnSave) {
            db.changeWallpaper(filePath);
            //close all windows
            java.awt.Window win[] = java.awt.Window.getWindows(); 
                for(int i=0;i<win.length;i++){ 
                win[i].dispose(); 
            } 
            LaptopSimulator ls = new LaptopSimulator(true);
            ls.wallpaperJpg = new ImageIcon(filePath);
            ls.screen.removeAll();
            ls.wallpaper();
        }
    }
     
}