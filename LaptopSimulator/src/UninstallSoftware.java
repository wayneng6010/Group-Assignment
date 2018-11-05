import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UninstallSoftware  extends JFrame implements ActionListener{
    JList list = new JList();
    JButton btnInstall = new JButton("Uninstall");
    
    DBHandler db = new DBHandler();
    
public UninstallSoftware(){
    
        setLayout(new BorderLayout());
        setSize(500,200);
        setTitle("Uninstall Software");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel middle = new JPanel();
        String[] installArr = {};
        ArrayList<String> installList = new ArrayList<>();
        installArr = db.getInstalledSoftware().toArray(installArr);
        list = new JList(installArr);
        list.setSelectedIndex(2);
        middle.add(list);
        add("North", middle);
        
        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(btnInstall);
        add("South", bottom);
        
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
    

