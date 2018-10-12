import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class softwareOpen extends JFrame {
        //implements ActionListener{
       
    //ps button 
    Icon PSIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/photoshop.png");
    JButton PSBtn = new JButton(PSIcon);
    
    //after effect button
    Icon aeIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/ae.png");
    JButton aeBtn = new JButton(aeIcon);
    
    //adobe illustrator button
    Icon aiIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/ai.png");
    JButton aiBtn = new JButton(aiIcon);
    
    //access button
    Icon accessIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/access.jpg");
    JButton accessBtn = new JButton(accessIcon);
    
    //outlook button
    Icon outlookIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/outlook.jpg");
    JButton outlookBtn = new JButton(outlookIcon);
    
    //excel button
    Icon excelIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/excel.jpg");
    JButton excelBtn = new JButton(excelIcon);
    
    //chrome button
    Icon chromeIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/chrome.png");
    JButton chromeBtn = new JButton(chromeIcon);
    
    //spotify button
    Icon spotifyIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/spotify2.png");
    JButton spotifyBtn = new JButton(spotifyIcon);
    
    //java button
    Icon javaIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/java.png");
    JButton javaBtn = new JButton(javaIcon);
    
    //antivirus button
    Icon antivirusIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/antivirus.png");
    JButton antivirusBtn = new JButton(antivirusIcon);
    
    //avira button
    Icon aviraIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/avira.png");
    JButton aviraBtn = new JButton(aviraIcon);
    
    //mcafee button
    Icon mcafeeIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/mcafee.png");
    JButton mcafeeBtn = new JButton(mcafeeIcon);
   
    //String note;
    public static void main(String[] args) {
        softwareOpen jf= new softwareOpen();
    }
    public softwareOpen(){
        JPanel top = new JPanel();

        top.add(PSBtn);
        top.add(accessBtn);
        top.add(chromeBtn);
        top.add(antivirusBtn);
        
        JPanel middle = new JPanel();

        middle.add(aeBtn);
        middle.add(excelBtn);
        middle.add(spotifyBtn);
        middle.add(aviraBtn);
        
        JPanel bottom = new JPanel();
        bottom.add(aiBtn);
        bottom.add(outlookBtn);
        bottom.add(javaBtn);
        bottom.add(mcafeeBtn);

        // position each JPanel in the window frame 
        add("North", top);
        add("Center", middle);
        add("South", bottom);
        
        //window properties
        setSize(500, 315);
        setTitle("Open Software");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //add action listeners to buttons
        //submitBtn.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        //if(e.getSource() == submitBtn){
          //  note = noteTxtFld.getText();
            //close all windows
            //java.awt.Window win[] = java.awt.Window.getWindows(); 
              //  for(int i=0;i<win.length;i++){ 
                //win[i].dispose(); 
            //} 
            //LaptopSimulator ls = new LaptopSimulator();
            //ls.menuLbl.setText(note);
        //}
    //}
}
}