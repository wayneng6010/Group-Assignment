import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InstallSoftware extends JFrame implements ActionListener{
    JLabel lbl = new JLabel("Categories ");
    JComboBox cbx = new JComboBox();
    JButton btnSearch = new JButton("Search");
    JList list = new JList();
    JLabel lbl1 = new JLabel("Software Discription: ");
    JLabel lbl2 = new JLabel("..........");
    JButton btnInstall = new JButton("Install");
    
public InstallSoftware(){
        setLayout(new BorderLayout());
        setSize(400,250);
        setTitle("Install Software");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel top = new JPanel();
        top.add(lbl);
        top.add(cbx);
        cbx.addItem("Microsft Word");
        cbx.addItem("Microsoft Excel");
        cbx.addItem("Adobe PhotoShop");
        cbx.addItem("Adobe Premiere Pro"); 
        top.add(btnSearch);
        add("North", top);
       
        
        JPanel middle = new JPanel();
        String installList[]= {"Microsoft Word", "Microsoft Excel", "Adobe PhotoShop", "Adobe Premiere Pro"};
        list = new JList(installList);
        list.setSelectedIndex(2);
        middle.add(list);
        add("Center", middle);
        
        JPanel bottom = new JPanel();
        bottom.add(lbl1);
        bottom.add(lbl2);
        bottom.setLayout(new FlowLayout());
        bottom.add(btnInstall);
        add("South", bottom);
        
        setVisible(true);    
}

public void actionPerformed(ActionEvent e){

    }
}

