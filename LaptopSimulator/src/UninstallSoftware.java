import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UninstallSoftware  extends JFrame implements ActionListener{
    JList list = new JList();
    JLabel lbl1 = new JLabel("Softwares:");
    JButton btnInstall = new JButton("Uninstall");
    Font font1 = new Font("Segoe UI", Font.BOLD, 22);//font
    Font font2 = new Font("Times", Font.PLAIN, 20);//font
    DBHandler db = new DBHandler();
    
public UninstallSoftware(){
    
        setLayout(new BorderLayout());
        setSize(600,400);
        setTitle("Uninstall Software");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel middle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] installArr = {};
        ArrayList<String> installList = new ArrayList<>();
        installArr = db.getInstalledSoftware().toArray(installArr);
        list = new JList(installArr);
        lbl1.setFont(font1);
        lbl1.setPreferredSize(new Dimension(600, 20));
        lbl1.setHorizontalAlignment(SwingConstants.CENTER);

        list.setFixedCellWidth(600);
        list.setFont(font2);
        list.setSelectedIndex(2);
        //align list item center
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        middle.setPreferredSize(new Dimension(600, 280));
        middle.add(Box.createRigidArea(new Dimension(1, 20))); //create space
        middle.add(lbl1);
        middle.add(Box.createRigidArea(new Dimension(1, 20))); //create space
        middle.add(list);
        add("North", middle);
        
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnInstall.setFont(font1);
        btnInstall.setPreferredSize(new Dimension(200, 40));
        bottom.setPreferredSize(new Dimension(600, 100));
        bottom.add(btnInstall);
        add("Center", bottom);
        
        setVisible(true);
        
        btnInstall.addActionListener(this);
}

public void actionPerformed(ActionEvent e){
    if (e.getSource() == btnInstall){
        String selectedSoftware = list.getSelectedValue().toString();
        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure to uninstall " + selectedSoftware + " ?" ,"Confirmation", JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
            if(list.isSelectionEmpty()){
                    JOptionPane.showMessageDialog(null, "Please select a software in the list.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    boolean success = db.UninstallSoftware(selectedSoftware);
                    if(success){
                        DefaultListModel softwareListModel = new DefaultListModel();
                        ArrayList<String> softwareListArr = new ArrayList<String>();
                        softwareListModel.clear();//clear the model before setting an updated one
                        softwareListArr = db.getInstalledSoftware();
                        for (int i = 0; i < softwareListArr.size(); i++){
                            softwareListModel.addElement(softwareListArr.get(i));
                        }
                        list.setModel(softwareListModel);
                        JOptionPane.showMessageDialog(null, selectedSoftware + " uninstall successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Failed to uninstall." + selectedSoftware, "Error", JOptionPane.ERROR_MESSAGE);
                    }
            }
        }
    }
}
}
    

