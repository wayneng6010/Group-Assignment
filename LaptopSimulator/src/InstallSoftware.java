import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InstallSoftware extends JFrame implements ActionListener{
    JLabel lbl = new JLabel("Categories: ");
    JList list = new JList();
    JLabel lbl1 = new JLabel("Software Discription: ");
    JLabel lbl2 = new JLabel("..........");
    JButton btnInstall = new JButton("Install");
    DBHandler db = new DBHandler();
    
public InstallSoftware(){
        setLayout(new FlowLayout());
        setSize(1000,300);
        setTitle("Install Software");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel top = new JPanel();
        top.add(lbl);
        top.setPreferredSize(new Dimension(1000, 20));
        add("North", top);
       
        JPanel middle = new JPanel(new FlowLayout());
        String installList[]= {"Microsoft Access", "Microsoft Excel","Microsoft Outlook", "Adobe Photoshop", "Adobe After Effects", "Adobe Illustrator"};
        list = new JList(installList);
        list.setSelectedIndex(2);
        middle.add(list);
        middle.setPreferredSize(new Dimension(1000, 100));
        add("Center", middle);
        
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.add(lbl1);
        bottom.add(lbl2);
        JPanel bottom1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom1.add(btnInstall);
        bottom1.setPreferredSize(new Dimension(1000, 35));
        bottom.add(bottom1);
        bottom.setPreferredSize(new Dimension(1000, 180));
        add("South", bottom);
        
        setVisible(true);    
        
        //timer which function will executed after 0.5 secs
        Timer t1 = new javax.swing.Timer(500, new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e){
                String SelectedSoftware = list.getSelectedValue().toString();
                String desp = db.getDescription(SelectedSoftware);
                lbl2.setText(desp);
            }
        });
        t1.start();//start timer
        btnInstall.addActionListener(this);
    }

public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnInstall) {
            if(list.isSelectionEmpty()){
                JOptionPane.showMessageDialog(null, "Please select a software in the list.", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                String selectedSoftware = list.getSelectedValue().toString();
                boolean success = db.installSoftware(selectedSoftware);
                if (success){
                    JOptionPane.showMessageDialog(null, selectedSoftware + " has been successfully installed.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, selectedSoftware + " already been installed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

