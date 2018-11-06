import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InstallSoftware extends JFrame implements ActionListener{
    JLabel lbl = new JLabel("Softwares: ");
    JList list = new JList();
    JLabel lbl1 = new JLabel("Software Description: ");
    JTextArea lbl2 = new JTextArea("");
    JButton btnInstall = new JButton("Install");
    Font font1 = new Font("Segoe UI", Font.BOLD, 22);//font
    Font font2 = new Font("Times", Font.PLAIN, 20);//font
    DBHandler db = new DBHandler();
    
public InstallSoftware(){
        setLayout(new FlowLayout());
        setSize(600,470);
        setTitle("Install Software");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel top = new JPanel();
        lbl.setFont(font1);
        top.add(lbl);
        top.setPreferredSize(new Dimension(600, 40));
        add("North", top);
       
        JPanel middle = new JPanel(new FlowLayout());
        
        String installList[]= {"Microsoft Access", "Microsoft Excel","Microsoft Outlook", "Adobe Photoshop", "Adobe After Effects", "Adobe Illustrator"};
        list = new JList(installList);
        list.setFont(font2);
        list.setFixedCellWidth(600);
        list.setSelectedIndex(2);
        //align list item center
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        middle.add(list);
        middle.setPreferredSize(new Dimension(600, 180));
        add("Center", middle);
        
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lbl1.setFont(font1);
        lbl2.setFont(font2);
        lbl2.setBounds(0,0, 580, 100);
        lbl2.setPreferredSize(new Dimension(580, 80));
        //let text displayed not editable
        lbl2.setEditable(false);
        //let text has transporent background
        lbl2.setOpaque(false);
        lbl2.setLineWrap(true);
        lbl2.setWrapStyleWord(true);
        bottom.add(lbl1);
        bottom.add(lbl2);
        JPanel bottom1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnInstall.setFont(font1);
        btnInstall.setPreferredSize(new Dimension(200, 40));
        bottom1.add(btnInstall);
        bottom1.setPreferredSize(new Dimension(600, 45));
        bottom.add(bottom1);
        bottom.setPreferredSize(new Dimension(600, 210));
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
                    JOptionPane.showMessageDialog(null, selectedSoftware + " has been successfully installed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, selectedSoftware + " already been installed.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}

