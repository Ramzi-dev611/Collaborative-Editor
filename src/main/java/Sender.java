import recieve.QueuesFactory;
import recieve.ReceiveProcess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Sender extends JFrame implements FocusListener, DocumentListener {
    protected SendProcess snd;
    protected String name;
    protected JPanel pan1;
    protected JLabel title;
    protected JLabel logo;
    protected JLabel block3lab;
    protected JPanel pan2;
    protected JPanel firstBlock;
    protected JPanel secondBlock;
    protected JPanel thirdBlock;
    protected JButton room;
    protected JButton addWriters;
    protected JTextField insertion;
    protected JButton submition;
    protected JPanel pan3;
    protected JTextArea field;
    protected ArrayList <String> othersQueues;
    protected ArrayList<JTextArea> otherAreas;
    protected ArrayList<JLabel> otherLabels;
    protected ArrayList<JPanel> otherPanels;
    protected JPanel othersPan;
    public Sender(String name, String old){
        this.name = name;
        snd = new SendProcess("application"+name);
        constructZone1();
        constructZone2();
        constructZone3(old);
        this.setTitle(name);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(pan1, BorderLayout.NORTH);
        this.add(pan2, BorderLayout.WEST);
        this.add(pan3, BorderLayout.CENTER);
        this.setVisible(true);
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
        room.addActionListener(e->{
            if(field.isEditable() == false){
                field.setEditable(true);
                room.setBackground(Color.green);
                field.grabFocus();
            }else{
                field.setEditable(false);
                room.setBackground(new Color (0x6b6f80));
            }
        });
        //secondBlock
        secondBlock= new JPanel();
        secondBlock.setBackground(new Color(0xb3ffb3));
        secondBlock.setBounds(0, 150, 300, 150);
        secondBlock.setLayout(null);
        secondBlock.add(room);
        secondBlock.add(lab2);
        //block3Lab
        block3lab = new JLabel("Check for other users");
        //addWriters
        addWriters = new JButton("Add Writers");
        addWriters.setFocusable(false);
        addWriters.setBackground(Color.red);
        addWriters.setForeground(Color.white);
        addWriters.addActionListener(e->{
            dispose();
            new TmpFrame(field.getText(), name);
        });
        //Block 3
        thirdBlock = new JPanel();
        thirdBlock.setBackground(new Color(0xb3ffb3));
        thirdBlock.setBounds(0, 543, 300, 50);
        thirdBlock.setLayout(new BorderLayout());
        thirdBlock.add(block3lab, BorderLayout.NORTH);
        thirdBlock.add(addWriters, BorderLayout.CENTER);

        //pan
        pan2.setBackground(new Color(0xb3ffb3));
        pan2.setLayout(null);
        pan2.add(firstBlock);
        pan2.add(secondBlock);
        pan2.add(thirdBlock);
        pan2.setPreferredSize(new Dimension(300,0));
    }

    private void constructZone3(String o){
        pan3 = new JPanel();
        field = new JTextArea();
        field.setFont(new Font("Helvetica",Font.PLAIN, 25));
        field.setEditable(false);
        field.setLineWrap(true);
        field.setText(o);
        field.getDocument().addDocumentListener(this);
        JScrollPane scroll1 = new JScrollPane(field);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pan3.setBorder(new EmptyBorder(15,15,15,15));
        pan3.setLayout(new GridLayout(2,1,20,20));
        pan3.add(scroll1);
        constructOther();
        pan3.setPreferredSize(new Dimension(100,100));
    }

    private void constructOther(){
        othersQueues = new ArrayList<String>();
        ArrayList<String> tmp = new QueuesFactory().getQueus();
        for (Iterator<String> it = tmp.iterator(); it.hasNext();){
            String q = it.next();
            if(q.matches("application.*") && !q.equals("application"+name)){
                othersQueues.add(q);
            }
        }
        int sz =  othersQueues.size();
        otherPanels = new ArrayList<JPanel>(sz);
        otherLabels = new ArrayList<JLabel>(sz);
        otherAreas = new ArrayList<JTextArea>(sz);
        othersPan = new JPanel();
        othersPan.setLayout(new GridLayout(1, sz,10,0));
        for (int index=0; index< sz; index++){
            JLabel l = new JLabel("User "+(String)othersQueues.toArray()[index]+ " said");
            l.setFont(new Font("Boli MV",Font.ITALIC, 20));
            l.setPreferredSize(new Dimension(15,50));
            l.setHorizontalTextPosition(SwingConstants.CENTER);
            otherLabels.add(l);
            JTextArea other = new JTextArea();
            other.setEditable(false);
            other.setLineWrap(true);
            other.setFont(new Font("Helvetica",Font.PLAIN, 25));
            new ReceiveProcess().recieve((String)othersQueues.toArray()[index], other);
            JScrollPane scroll = new JScrollPane(other);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            otherAreas.add(other);
            JPanel p = new JPanel();
            p.setBackground(new Color(0xc7caf0));
            p.setLayout(new BorderLayout());
            p.add(l, BorderLayout.NORTH);
            p.add(scroll, BorderLayout.CENTER);
            otherPanels.add(p);
            othersPan.add(p);
        }
        pan3.add(othersPan);
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


    @Override
    public void insertUpdate(DocumentEvent e) {
            String message = field.getText();
            snd.send(message);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
            String message = field.getText();
            snd.send(message);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if(e.getDocument() == field){
            String message = field.getText();
            snd.send(message);
        }
    }
}
