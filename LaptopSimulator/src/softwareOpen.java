import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class softwareOpen extends JFrame implements ActionListener{
    
    ArrayList<String> SoftwareListArr = new ArrayList<String>();
    DBHandler db = new DBHandler();
       //ps button 
    Icon PSIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/photoshop.png");
    JButton PSBtn = new JButton(PSIcon);
    Icon PS = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/PSUI.png");
    JLabel lblPS = new JLabel();
	
    //after effect button
    Icon aeIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/ae.png");
    JButton aeBtn = new JButton(aeIcon);
    Icon AE = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/aeUI.jpg");
    JLabel lblAE = new JLabel();
    
    //adobe illustrator button
    Icon aiIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/ai.png");
    JButton aiBtn = new JButton(aiIcon);
	Icon AI = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/aiUI.jpg");
    JLabel lblAI = new JLabel();
    
    //access button
    Icon accessIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/access.jpg");
    JButton accessBtn = new JButton(accessIcon);
	Icon Access = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/accUI.jpg");
    JLabel lblAccess = new JLabel();
    
    //outlook button
    Icon outlookIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/outlook.jpg");
    JButton outlookBtn = new JButton(outlookIcon);
	Icon Outlook = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/outlookUI.jpg");
    JLabel lblOutlook = new JLabel();
    
    //excel button
    Icon excelIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/excel.jpg");
    JButton excelBtn = new JButton(excelIcon);
    Icon excel = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/excelUI.png");
    JLabel lblExcel = new JLabel();
    //String note;
    public static void main(String[] args) {
        softwareOpen jf= new softwareOpen();
    }
    public softwareOpen(){
        SoftwareListArr = db.getSoftware();
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        
        for (int i = 0; i < SoftwareListArr.size(); i++){
            switch(SoftwareListArr.get(i)){
                case "Adobe After Effects": 
                    top.add(aeBtn);
                    break;
                case "Adobe Illustrator": 
                    top.add(aiBtn);
                    break;
                case "Adobe Photoshop": 
                    top.add(PSBtn);
                    break;
                case "Microsoft Access": 
                    bottom.add(accessBtn);
                    break;
                case "Microsoft Excel": 
                    bottom.add(excelBtn);
                    break;
                case "Microsoft Outlook": 
                    bottom.add(outlookBtn);
                    break;
            }
        }
            
        
        top.setBackground(new Color(143, 170, 220, 100));
        top.setPreferredSize(new Dimension(350, 150));
        bottom.setBackground(new Color(143, 170, 220, 100));
        bottom.setPreferredSize(new Dimension(350, 100));
        // position each JPanel in the window frame 
        add("North", top);
        add("South", bottom);
        
        //window properties
        setSize(350,250);
        setTitle("Open Software");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        aeBtn.addActionListener(this);
        aiBtn.addActionListener(this);
        PSBtn.addActionListener(this);
        accessBtn.addActionListener(this);
        excelBtn.addActionListener(this);
        outlookBtn.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e){
        java.awt.Window win[] = java.awt.Window.getWindows(); 
        for(int i=0;i<win.length;i++){ 
            win[i].dispose(); 
        } 
        LaptopSimulator ls = new LaptopSimulator(true);
        ls.screen.removeAll();
        ls.screen.setLayout(new FlowLayout());
        if(e.getSource() == aeBtn){
            lblAE.setIcon(AE);
            ls.screen.add(lblAE);
        }
        else if(e.getSource() == aiBtn){
            lblAI.setIcon(AI);
            ls.screen.add(lblAI);
        }
        else if(e.getSource() == PSBtn){
            lblPS.setIcon(PS);
            ls.screen.add(lblPS);
        }
        else if(e.getSource() == accessBtn){
            lblAccess.setIcon(Access);
            ls.screen.add(lblAccess);
        }
        else if(e.getSource() == excelBtn){
            lblExcel.setIcon(excel);
            ls.screen.add(lblExcel);
        }
        else if(e.getSource() == outlookBtn){
            lblOutlook.setIcon(Outlook);
            ls.screen.add(lblOutlook);
        }
    }
}