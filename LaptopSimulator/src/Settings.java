import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
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
    Font font1 = new Font("Segoe UI", Font.BOLD, 22);//font
    Font font2 = new Font("Times", Font.BOLD, 20);//font
    //instance variables
    String filePath = null;
    String selectedSongPlay = "";
    
    public static void main(String[] args) {
     Settings St = new Settings();
    }
    public Settings(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(710,160);
        setTitle("Settings");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel top = new JPanel();
        top.setPreferredSize(new Dimension(700, 50));
        top.setLayout(new FlowLayout(FlowLayout.LEFT));
        lblChange.setFont(font1);
        btnCFile.setFont(font2);
        fileName.setFont(font2);
        btnSave.setFont(font2);
        fileName.setPreferredSize(new Dimension(150, 20)); 
        top.add(lblChange);
        top.add(Box.createRigidArea(new Dimension(25, 0))); //create space
        top.add(btnCFile);
        top.add(fileName);
        top.add(Box.createRigidArea(new Dimension(60, 0))); //create space
        top.add(btnSave);

        JPanel middle = new JPanel(new FlowLayout(FlowLayout.LEFT));
        middle.setPreferredSize(new Dimension(700,50));
        lblSystem.setFont(font1);
        btnView.setFont(font2);
        middle.add(lblSystem);
        middle.add(Box.createRigidArea(new Dimension(12, 0))); //create space
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
            if(filePath != null){
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
            }else{
                JOptionPane.showMessageDialog(null, "Please upload an image file first.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        if (e.getSource() == btnView) {
            ArrayList<String> sysInfo = new ArrayList<String>();
            sysInfo = db.getSysInfo();
            JOptionPane.showMessageDialog(null, "Operating System: " + sysInfo.get(0) +
                                "\nRAM: " + sysInfo.get(1) +
                                "\nProcessor: " + sysInfo.get(2) +
                                "\nHard Disk Drive: " + sysInfo.get(3) +
                                "\nStorage: " + sysInfo.get(4), "System Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
     
}