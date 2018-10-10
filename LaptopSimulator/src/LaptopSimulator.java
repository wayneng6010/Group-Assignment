import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LaptopSimulator extends JFrame implements ActionListener{
    //laptop startup animation 
    Icon startupGif = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/startup.gif");
    JLabel startupAnimation = new JLabel();
    
    //laptop shutdown animation
    Icon shutdownGif = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/shutdown.gif");
    JLabel shutdownAnimation = new JLabel();
    
    //notepad background and text
    Icon notepadBG = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/notepadbg.jpg");
    JLabel notepadLbl = new JLabel();
    JTextArea notepadTxt = new JTextArea(8,20); //row, column

    //window title
    JLabel windowTitle = new JLabel("Laptop Simulator");
    
    //menu bar components
    JLabel menuLbl = new JLabel("Menu", SwingConstants.CENTER);
    
    //notepad button 
    Icon notepadIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/Notepad.png");
    JButton notepadBtn = new JButton("Notepad", notepadIcon);
    
    //install software button
    Icon installIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/software.png");
    JButton installBtn = new JButton("Install Software", installIcon);
    
    //uninstall software button
    Icon uninstallIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/uninstall.png");
    JButton uninstallBtn = new JButton("Uninstall Software", uninstallIcon);
    
    //video player button
    Icon openSoftwareIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/opensoftware.png");
    JButton openSoftwareBtn = new JButton("Open Software", openSoftwareIcon);
    
    //music player button
    Icon musicPlayerIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/musicplayer.png");
    JButton musicPlayerBtn = new JButton("Music Player", musicPlayerIcon);
    
    //settings button
    Icon settingIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/setting.png");
    JButton settingBtn = new JButton("Settings", settingIcon);
    
    //control button components
    //power button
    Icon powerIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/power.png");
    JButton powerBtn = new JButton(powerIcon);
    
    //power on or off (false = off)
    boolean power = false;
    
    //home button
    Icon homeIcon = new ImageIcon("D:/_Object Oriented Development/Group Assignment/Images/home.png");
    JButton homeBtn = new JButton(homeIcon);

    public static void main(String[] args) {
        LaptopSimulator home = new LaptopSimulator();
    }
    JPanel screen;
    public LaptopSimulator(){
        //Screen
        screen = new JPanel();
        //Preferences
        screen.setPreferredSize(new Dimension(1400, 30));
        screen.setBackground(Color.black);
        // add startup animation and shutdown animation
        screen.add(startupAnimation);
        screen.add(shutdownAnimation);
        
        //Menu Bar
        JPanel menu = new JPanel();
        //Preferences
        menu.setPreferredSize(new Dimension(500, 30));
        menu.setBackground(new Color(143, 170, 220, 100));
        
        //add componenets to menu bar
        //declare dimension for button size and spaces between buttons
        Dimension btnSize = new Dimension(350, 70);
        Dimension spaceBetweenBtn = new Dimension(300, 50);
        //declare font for buttons
        Font buttonFont = new Font("Calibri", Font.BOLD, 30);
        menu.add(Box.createRigidArea(new Dimension(300, 30)));
        
        //menu label
        menuLbl.setPreferredSize(btnSize);
        menu.add(menuLbl);
        menu.add(Box.createRigidArea(spaceBetweenBtn));
        menuLbl.setFont(new Font("Bookman Old Style", Font.BOLD, 64));
        
        //notepad button
        notepadBtn.setPreferredSize(btnSize);
        menu.add(notepadBtn);
        menu.add(Box.createRigidArea(spaceBetweenBtn));
        notepadBtn.setFont(buttonFont);
        
        //install button
        installBtn.setPreferredSize(btnSize);
        menu.add(installBtn);
        menu.add(Box.createRigidArea(spaceBetweenBtn)); //create space
        installBtn.setFont(buttonFont);
        
        //uninstall button
        uninstallBtn.setPreferredSize(btnSize);
        menu.add(uninstallBtn);
        menu.add(Box.createRigidArea(spaceBetweenBtn)); //create space
        uninstallBtn.setFont(buttonFont);
        
        //open software button
        openSoftwareBtn.setPreferredSize(btnSize);
        menu.add(openSoftwareBtn);
        menu.add(Box.createRigidArea(spaceBetweenBtn)); //create space
        openSoftwareBtn.setFont(buttonFont);
        
        //music player button
        musicPlayerBtn.setPreferredSize(btnSize);
        menu.add(musicPlayerBtn);
        menu.add(Box.createRigidArea(spaceBetweenBtn)); //create space
        musicPlayerBtn.setFont(buttonFont);
        
        
        //Control Bar
        JPanel controlBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //Preferences
        //add space to the left of controlBtn JPanel
        controlBtn.add(Box.createRigidArea(new Dimension(50,0)));
        powerBtn.setPreferredSize(new Dimension(80, 80));
        homeBtn.setPreferredSize(new Dimension(80, 80));
        //add componenets to control bar
        controlBtn.add(powerBtn);
        controlBtn.add(homeBtn);
        controlBtn.setPreferredSize(new Dimension(500, 100));
        controlBtn.setBackground(new Color(213, 223, 239, 50));
        //title bar
        controlBtn.add(Box.createRigidArea(new Dimension(410, 0))); //create space
        windowTitle.setFont(new Font("Bookman Old Style", Font.BOLD, 70));
        controlBtn.add(windowTitle);
        //settings button
        settingBtn.setPreferredSize(new Dimension(200, 70));
        controlBtn.add(Box.createRigidArea(new Dimension(255, 0))); //create space
        controlBtn.add(settingBtn);
        settingBtn.setFont(buttonFont);
        
        // position each JPanel in the window frame 
        add("West", screen);
        add("North", controlBtn);
        add("East", menu);
        
        // window properties
        setSize(1900, 1000);
        setTitle("Laptop Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        
        //button is disabled before switching on the laptop 
//        homeBtn.setEnabled(false);
//        notepadBtn.setEnabled(false);
//        installBtn.setEnabled(false);
//        uninstallBtn.setEnabled(false);
//        openSoftwareBtn.setEnabled(false);
//        musicPlayerBtn.setEnabled(false);
//        settingBtn.setEnabled(false);
        
        
        //add action listeners to buttons
        powerBtn.addActionListener(this);
        homeBtn.addActionListener(this);
        notepadBtn.addActionListener(this);
        installBtn.addActionListener(this);
        uninstallBtn.addActionListener(this);
        openSoftwareBtn.addActionListener(this);
        musicPlayerBtn.addActionListener(this);
        settingBtn.addActionListener(this);
        
    }
    
    public void notepad(){
        JLayeredPane notepadPnl = new JLayeredPane();
         //add notepad background
        //set up layered jpanel
        JPanel layerOne = new JPanel();
        
        //let text to be in next line when exceed the bourdaries
        notepadTxt.setLineWrap(true);
        notepadTxt.setWrapStyleWord(true);
        //let text displayed not editable
        notepadTxt.setEditable(false);
        //let text has transporent background
        notepadTxt.setOpaque(false);
        
        //display notepad background
        notepadLbl.setIcon(notepadBG);
        notepadLbl.setVisible(true);
        
        //set boundaries(x offset, y offset, width, height)
        layerOne.setBounds(0, 0, 1500, 1000);
        layerOne.add(notepadLbl);
        
        JPanel layerTwo = new JPanel();
        layerTwo.add(Box.createRigidArea(new Dimension(0, 500)));//add spaces
        layerTwo.setOpaque(false);//let text has transporent background
        //set font of text
        notepadTxt.setFont(new Font("Calibri", Font.BOLD, 60));
        //set color of text
        notepadTxt.setForeground(Color.GRAY);
        layerTwo.setBounds(220, 110, 1100, 600);
        layerTwo.add(notepadTxt);
        
        //add JPanel layerOne to 1st layer, JPanel layerTwo ro 2nd layer
        notepadPnl.add(layerOne, new Integer(0),0);
        notepadPnl.add(layerTwo, new Integer(1),0);

        notepadPnl.setVisible(true);
        screen.setLayout(new BorderLayout());
        screen.add(notepadPnl, BorderLayout.CENTER);
    }
    
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource() == powerBtn){
            if(power){
                power = false;
                //cannot click power button while switching off laptop
                powerBtn.setEnabled(false);
                
                //display startup animation
                shutdownAnimation.setVisible(true);
                shutdownAnimation.setIcon(shutdownGif);
                
                //disable every button when switching off
                homeBtn.setEnabled(false);
                notepadBtn.setEnabled(false);
                installBtn.setEnabled(false);
                uninstallBtn.setEnabled(false);
                openSoftwareBtn.setEnabled(false);
                musicPlayerBtn.setEnabled(false);
                settingBtn.setEnabled(false);
                
                //timer which function will executed after 7 secs
                Timer t1 = new javax.swing.Timer(7000, new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent e){
                        shutdownAnimation.setVisible(false);
                        shutdownAnimation.setIcon(null);
                        powerBtn.setEnabled(true);
                    }
                });
                t1.start();//start timer
                t1.setRepeats(false);//timer only run once
            }
            else{
                power = true;
                //cannot click power button while switching on laptop
                powerBtn.setEnabled(false);
                //add component to screen panel
                startupAnimation.setVisible(true);
                startupAnimation.setIcon(startupGif);
                
                //disable every button when switching on
                homeBtn.setEnabled(false);
                notepadBtn.setEnabled(false);
                installBtn.setEnabled(false);
                uninstallBtn.setEnabled(false);
                openSoftwareBtn.setEnabled(false);
                musicPlayerBtn.setEnabled(false);
                settingBtn.setEnabled(false);
                
                //timer which function will executed after 7 secs
                Timer t2 = new javax.swing.Timer(7000, new ActionListener(){
                @Override
                    public void actionPerformed(ActionEvent e){
                        
                        //remove startup animation
                        startupAnimation.setVisible(false);
                        startupAnimation.setIcon(null);
                        
                        //enable every buttons once laptop is switched on
                        powerBtn.setEnabled(true);
                        homeBtn.setEnabled(true);
                        notepadBtn.setEnabled(true);
                        installBtn.setEnabled(true);
                        uninstallBtn.setEnabled(true);
                        openSoftwareBtn.setEnabled(true);
                        musicPlayerBtn.setEnabled(true);
                        settingBtn.setEnabled(true);
                    }
                });
                t2.start();//start timer
                t2.setRepeats(false);//timer only run once
            }
        }
        if(e.getSource() == notepadBtn){
//            if(power){
                new notepad();
//            }else{
//                JOptionPane.showMessageDialog(null, "Laptop has not been switched on", "Error", JOptionPane.ERROR_MESSAGE);
//            }
            
        }
        if(e.getSource() == musicPlayerBtn){
            new musicPlayer();
        }
        
    }
    
}
