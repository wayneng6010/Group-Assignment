import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class notepad extends JFrame implements ActionListener{
    
    JLabel lbl1 = new JLabel("Enter text: ");
    JTextField noteTxtFld = new JTextField(35);
    
    JButton submitBtn = new JButton("Submit");
    
    String note;
    
    public notepad(){
        JPanel top = new JPanel();
        top.add(lbl1);
        top.add(noteTxtFld);
        
        JPanel bottom = new JPanel();
        bottom.add(submitBtn);

        // position each JPanel in the window frame 
        add("North", top);
        add("South", bottom);
        
        //window properties
        setSize(500, 100);
        setTitle("Notepad");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //add action listeners to buttons
        submitBtn.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == submitBtn){
            note = noteTxtFld.getText();
            //close all windows
            java.awt.Window win[] = java.awt.Window.getWindows(); 
                for(int i=0;i<win.length;i++){ 
                win[i].dispose(); 
            } 
            LaptopSimulator ls = new LaptopSimulator();
            ls.power = true;
//            ls.notepadTxt.setPreferredSize(new Dimension(1000,500));
            ls.notepadTxt.setText(note);
            ls.notepad();
        }
    }
}
