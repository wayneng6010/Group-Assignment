import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class notepad extends JFrame implements ActionListener{
    //create button, label, combobox
    //creating elements for the interface
    DBHandler db = new DBHandler();
    JLabel lblOpenFile = new JLabel("Open File");
    JButton btnOpen = new JButton("Open");
    JComboBox comboFile = new JComboBox();
    JLabel lblCreateNote = new JLabel("Create Note");
    JTextArea txtAreaCN = new JTextArea(5, 30);
    JLabel lblFileName = new JLabel("File Name");
    JTextField txtFile = new JTextField(5);
    JButton btnSubmit = new JButton("Submit and Save");
    ArrayList<String> notePadListArr = new ArrayList<String>();
    
    public static void main(String[] args) {
        //creating object 
        notepad notepad1 = new notepad();
    }
    
    public notepad(){
        //creating objects for top part
        JPanel top = new JPanel();
        //setting layout and background colour
        top.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        top.setBackground(new Color(143, 100, 220, 100));
        //adding elements into the ui
        top.add(lblOpenFile);
        top.add(comboFile);
        notePadListArr = db.getFiles();
        //retrieve data from database to combo box
        for (int i = 0; i < notePadListArr.size(); i++)
            {
                comboFile.addItem(notePadListArr.get(i));
            }
        top.add(btnOpen);
       
        //creating objects for middle part
        JPanel middle = new JPanel();
        //setting layout and background colour
        middle.setLayout(new FlowLayout(FlowLayout.CENTER));
        middle.setBackground(new Color(143, 110, 220, 200));
        middle.setPreferredSize(new Dimension(10,10));
        //adding elements into the ui
        middle.add(lblFileName);
        middle.add(txtFile);
        middle.add(lblCreateNote);
        middle.add(txtAreaCN);
        
         //creating objects for middle part
        JPanel bottom = new JPanel();
        //setting layout and background colour
        bottom.setBackground(new Color(143, 100, 220, 100));
        bottom.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        //adding elements into the ui
        bottom.add(btnSubmit);
        

        // position each JPanel in the window frame 
        add("North", top);
        add("Center", middle);
        add("South", bottom);
        
        //window properties
        top.setLayout(new FlowLayout());
        setSize(685,210);
        setTitle("Notepad");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        btnOpen.addActionListener(this);
        btnSubmit.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnSubmit){
            String note;
            String fileName = txtFile.getText();
            note = txtAreaCN.getText();
            db.saveFile(fileName, note);
            //close all windows
            java.awt.Window win[] = java.awt.Window.getWindows(); 
                for(int i=0;i<win.length;i++){ 
                win[i].dispose(); 
            } 
            LaptopSimulator ls = new LaptopSimulator(true);
            ls.notepadTxt.setText(note);
            ls.notepad();
        }
        else if(e.getSource()==btnOpen){
            String selectedFile = "";
            selectedFile = comboFile.getSelectedItem().toString();
            btnSubmit.setEnabled(false);
//            JOptionPane.showMessageDialog(null, db.getFile(selectedFile), "Error", JOptionPane.ERROR_MESSAGE);
//            txtAreaCN.setText(db.getFile(selectedFile));
            //close all windows
            java.awt.Window win[] = java.awt.Window.getWindows(); 
                for(int i=0;i<win.length;i++){ 
                win[i].dispose(); 
            } 
            LaptopSimulator ls = new LaptopSimulator(true);
            ls.notepadTxt.setText(db.getFile(selectedFile));
            ls.notepad();
        }
    }
}