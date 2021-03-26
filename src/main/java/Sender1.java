import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Sender1 extends JFrame implements FocusListener, ActionListener, DocumentListener {
    // components of first Zone
    protected String name;
    protected JPanel pan1;
    protected JLabel title;
    protected JLabel logo;
    // Components of the second zone
    protected JPanel pan2;
    protected JPanel firstBlock;
    protected JPanel secondBlock;
    protected JButton room;
    protected JTextField insertion;
    protected JButton submition;
    // Components of the third zone
    protected JPanel pan3; protected JTextArea field;
    public Sender1(String name){
        this.name = name;
        // This is the header of the frame
        constructZone1();
        // This is the zone of the available Rooms
        constructZone2();
        // This is the zone of the Edition
        constructZone3();
        //frame Construction
        this.setTitle(name);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(pan1, BorderLayout.NORTH);
        this.add(pan2, BorderLayout.WEST);
        this.add(pan3, BorderLayout.CENTER);
        this.setVisible(true);
    }
    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==insertion){
            if(insertion.getForeground()== Color.gray){
                insertion.setText("");
                insertion.setForeground(Color.black);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()==insertion){
            if(insertion.getText().equals("")){
                insertion.setText("Name your new room");
                insertion.setForeground(Color.gray);
            }
        }

    }

    private  void constructZone1(){
        pan1 = new JPanel();
        title= new JLabel("Welcome "+name+" To the Collaborative Editor!");
        title.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        title.setForeground(new Color(0x4d0099));
        logo = new JLabel();
        logo.setIcon(new ImageIcon("assets/editlogo.png"));
        logo.setPreferredSize(new Dimension(75,75));
        pan1.setBackground(new Color(0xbfbfbf));
        pan1.setLayout(new GridLayout(1,3,0 ,0));
        pan1.setPreferredSize(new Dimension(100,150));
        pan1.add(logo, Integer.valueOf(0));
        pan1.add(title, Integer.valueOf(1));
    }

    private void constructZone2(){
        pan2 = new JPanel();
        //textfield
        JLabel lab = new JLabel("Create new room");
        lab.setForeground(Color.black);
        lab.setFont(new Font("Helvetica", Font.ITALIC, 16));
        lab.setBounds(10,30, 300, 50 );
        insertion = new JTextField("Name your new room");
        insertion.setFont(new Font("Helvetica" , Font.PLAIN, 16));
        insertion.setForeground(Color.gray);
        insertion.addFocusListener(this);
        insertion.setBounds(10, 80, 210, 40);
        //button
        submition = new JButton("ADD");
        submition.setBackground(new Color(0x00b300));
        submition.setForeground(Color.white);
        submition.setFocusable(false);
        submition.setBounds(230, 80,75,40);
        //firstBlock
        firstBlock = new JPanel();
        firstBlock.setBackground(new Color(0xb3ffb3));
        firstBlock.setBounds(0,0, 300, 130);
        firstBlock.setLayout(null);
        firstBlock.add(lab);
        firstBlock.add(insertion);
        firstBlock.add(submition);
        //room
        JLabel lab2 = new JLabel("Available Rooms");
        lab2.setForeground(Color.black);
        lab2.setFont(new Font("Helvetica", Font.ITALIC, 16));
        lab2.setBounds(10,0, 300, 50 );
        room = new JButton(name);
        room.setFocusable(false);
        room.setForeground(Color.white);
        room.setBackground(new Color (0x6b6f80));
        room.setBounds(50,50, 200, 40);
        room.addActionListener(this);
        //secondBlock
        secondBlock= new JPanel();
        secondBlock.setBackground(new Color(0xb3ffb3));
        secondBlock.setBounds(0, 150, 300, 1000);
        secondBlock.setLayout(null);
        secondBlock.add(room);
        secondBlock.add(lab2);
        //pan
        pan2.setBackground(new Color(0xb3ffb3));
        pan2.setLayout(null);
        pan2.add(firstBlock);
        pan2.add(secondBlock);
        pan2.setPreferredSize(new Dimension(300,0));
    }

    private void constructZone3(){
        pan3 = new JPanel();
        field = new JTextArea();
        field.setFont(new Font("Helvetica",Font.PLAIN, 25));
        field.setEditable(false);
        field.setLineWrap(true);
        field.getDocument().addDocumentListener(this);
        JScrollPane scroll = new JScrollPane(field);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pan3.setBorder(new EmptyBorder(15,15,15,15));
        pan3.setLayout(new BorderLayout());
        pan3.add(scroll);
        pan3.setPreferredSize(new Dimension(100,100));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==room){
            if(field.isEditable() == false){
                field.setEditable(true);
                room.setBackground(Color.green);
                field.grabFocus();
            }else{
                field.setEditable(false);
                room.setBackground(new Color (0x6b6f80));
            }
        }
    }

    public static void main (String []args){
        new Sender1("Sender1");
    }

    @Override
    public void insertUpdate(DocumentEvent e) {

            String message = field.getText();
            SendProcess snd = new SendProcess();
            snd.send(name, message);

    }

    @Override
    public void removeUpdate(DocumentEvent e) {
            String message = field.getText();
            SendProcess snd = new SendProcess();
            snd.send(name, message);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if(e.getDocument() == field){
            String message = field.getText();
            SendProcess snd = new SendProcess();
            snd.send(name, message);
        }
    }
}
