import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class notepad extends JFrame implements ActionListener{
    JPanel gridbagPanel = new JPanel();
    GridBagConstraints gc = new GridBagConstraints();
    JLabel lblOpenFile = new JLabel("Open File");
    
    JButton btnOpen = new JButton("Open");
    JComboBox comboFile = new JComboBox();
    JLabel lblCreateNote = new JLabel("Create Note");
    JTextArea txtAreaCN = new JTextArea(8,30);
    JLabel lblFontFam = new JLabel("Font Family");
    JComboBox comboFam = new JComboBox();
    JLabel lblFontSize = new JLabel("Font Size");
    JComboBox comboSize = new JComboBox();
    JLabel lblFontStyle = new JLabel("Font Style");
    JComboBox comboStyle = new JComboBox();
    JButton btnSubmit = new JButton("Submit and Save");
    
    public static void main(String[] args) {
        notepad notepad = new notepad();
    }
    
    public notepad(){
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        //top.setPreferredSize(new Dimension(50,50));
        top.add(lblOpenFile);
        top.add(comboFile);
        top.add(btnOpen);
        
       
        JPanel middle = new JPanel();
        JPanel middle2 = new JPanel();
        middle.add(lblCreateNote);
        middle2.add(txtAreaCN);
        middle.setLayout(new FlowLayout(FlowLayout.LEFT,8,3)); 
        middle2.setBackground(new Color(143, 110, 220, 200));
        middle.setPreferredSize(new Dimension(50,50));
        
        middle.setBackground(new Color(143, 170, 220, 100));
        JPanel bottom = new JPanel();
        JPanel bottom2 = new JPanel();
        JPanel bottom3 = new JPanel();
        JPanel bottom4 = new JPanel();
        
        bottom.setPreferredSize(new Dimension(50,50));
        bottom.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        bottom2.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        bottom3.setLayout(new FlowLayout(FlowLayout.LEFT)); 
         bottom.add(lblFontFam);
         bottom.add(comboFam);
         bottom2.add(lblFontSize);
         bottom2.add(comboSize);
         bottom3.add(lblFontStyle);
         bottom3.add(comboStyle);
bottom4.setLayout(new BoxLayout(bottom4, BoxLayout.LINE_AXIS));
bottom4.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));


bottom4.add(Box.createRigidArea(new Dimension(10, 0)));

        
        bottom4.add(btnSubmit);
        

        // position each JPanel in the window frame 
        add("North", top);
        middle.add(middle2);
        add("Center", middle);
        bottom.add(bottom2);
        bottom.add(bottom3);
        bottom.add(bottom4);
        add("South", bottom);
        
        //window properties
        top.setLayout(new FlowLayout());
        setSize(500,270);
        setTitle("Notepad");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
//        add action listeners to buttons
        btnSubmit.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnSubmit){
            String note = txtAreaCN.getText();
            //close all windows
            java.awt.Window win[] = java.awt.Window.getWindows(); 
                for(int i=0;i<win.length;i++){ 
                win[i].dispose(); 
            } 
            LaptopSimulator ls = new LaptopSimulator();
            ls.notepadTxt.setText(note);
            ls.notepad();
        }
    }
}