import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Sender extends JFrame implements FocusListener, KeyListener, DocumentListener {
    protected SendProcess snd; protected String name; protected int position;
    // zone north
    protected JPanel pan1; protected JLabel title; protected JLabel logo;
    // zone east
    protected JPanel pan2; protected JPanel firstBlock; protected JPanel secondBlock; protected JPanel thirdBlock;
    protected ArrayList<String> roomsExchanges; protected ArrayList <JButton> rooms; protected JPanel roomsPan;
    protected JTextField insertion; protected JButton submition; protected JButton refresh;
    protected JButton currentRoom;
    // zone center
    protected JPanel pan3; protected JTextArea field;

    public Sender(String name){
        this.name = name;
        snd = new SendProcess("application"+name);
        rooms = new ArrayList<JButton>(0);
        roomsExchanges = snd.getExchanges();
        constructZone1();
        constructZone2();
        constructZone3();
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
        submition.addActionListener(e -> submissionEvent(e));
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

        roomsPan = new JPanel();
        roomsPan.setBackground(new Color(0xb3ffb3));
        roomsPan.setLayout(null);
        roomsPan.setPreferredSize(new Dimension(200,roomsExchanges.size()*53));
        for (int i =0; i<roomsExchanges.size(); i++){
            String exchange = (String)roomsExchanges.toArray()[i];
            JButton b = new JButton(exchange.substring(11));
            b.setFocusable(false);
            b.setForeground(Color.white);
            b.setBackground(new Color (0x6b6f80));
            b.setBounds(30,20+50*i, 200, 40);
            b.addActionListener(e->newRoomEvent(e, b));
            rooms.add(b);
            roomsPan.add(b);
        }
        JScrollPane scrollPane = new JScrollPane(roomsPan);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBounds(10,60,280,300);

        //secondBlock
        secondBlock= new JPanel();
        secondBlock.setBackground(new Color(0xb3ffb3));
        secondBlock.setBounds(0, 150, 300, 380);
        secondBlock.setLayout(null);
        secondBlock.add(lab2);
        secondBlock.add(scrollPane);

        //refresh
        refresh = new JButton("Refresh Rooms");
        refresh.setBackground(Color.red);
        refresh.setForeground(Color.white);
        refresh.setFocusable(false);
        refresh.addActionListener(e->refreshActionEvent(e));
        // thirdBock
        thirdBlock = new JPanel();
        thirdBlock.setLayout(new BorderLayout());
        thirdBlock.add(refresh, BorderLayout.CENTER);
        thirdBlock.setBounds(0, 550, 300, 50);

        //pan
        pan2.setBackground(new Color(0xb3ffb3));
        pan2.setLayout(null);
        pan2.add(firstBlock);
        pan2.add(secondBlock);
        pan2.add(thirdBlock);
        pan2.setPreferredSize(new Dimension(300,0));
    }

    private void constructZone3(){
        pan3 = new JPanel();
        field = new JTextArea();
        field.setFont(new Font("Helvetica",Font.PLAIN, 25));
        field.setEditable(false);
        field.setLineWrap(true);
        field.addKeyListener(this);
        field.getDocument().addDocumentListener(this);
        JScrollPane scroll1 = new JScrollPane(field);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pan3.setBorder(new EmptyBorder(15,15,15,15));
        pan3.setLayout(new BorderLayout());
        pan3.add(scroll1);
        pan3.setPreferredSize(new Dimension(100,100));
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
            insertion.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
            if(insertion.getText().equals("")){
                insertion.setText("Name your new room");
                insertion.setForeground(Color.gray);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_DELETE || e.getKeyCode() <= KeyEvent.VK_ALPHANUMERIC){
            if (field.isEditable() && field.hasFocus()) {
                position = field.getCaretPosition();
                String message = field.getText();
                snd.send(message, "application" + currentRoom.getText());
            }
        }
    }

    public void submissionEvent(ActionEvent e){
        if (insertion.getText().equals("") || insertion.getText().equals("Name your new room")){
            insertion.setBorder(BorderFactory.createLineBorder(Color.red, 1));
            insertion.grabFocus();
        }else {
            snd.exchangeDeclaration("application"+insertion.getText());
            roomsExchanges.add("application"+insertion.getText());
            JButton b = new JButton(insertion.getText());
            b.setFocusable(false);
            b.setForeground(Color.white);
            b.setBackground(new Color (0x6b6f80));
            b.setBounds(30,20+50*rooms.size(), 200, 40);
            b.addActionListener(e1->newRoomEvent(e, b));
            rooms.add(b);
            roomsPan.setPreferredSize(new Dimension(200, 53*roomsExchanges.size()));
            insertion.setForeground(Color.gray);
            insertion.setText("Name your new room");
            if(currentRoom!= null){
                currentRoom.setBackground(new Color (0x6b6f80));
                snd.unbindFromExchange(currentRoom.getText());
            }
            b.setBackground(Color.GREEN);
            currentRoom= b;
            snd.bindToExchange("application"+currentRoom.getText());
            snd.recieve(field);
            field.setEditable(true);
            field.grabFocus();
            roomsPan.add(b);
        }
    }

    public void newRoomEvent (ActionEvent e, JButton b){
        if(field.isEditable() == false){
            field.setEditable(true);
            b.setBackground(Color.green);
            currentRoom = b;
            snd.bindToExchange("application"+currentRoom.getText());
            snd.recieve(field);
        }else{
            if(currentRoom == b){
                field.setEditable(false);
                b.setBackground(new Color (0x6b6f80));
                field.setText("");
                snd.unbindFromExchange("application"+b.getText());
                currentRoom = null;
            }else{
                currentRoom.setBackground(new Color (0x6b6f80));
                snd.unbindFromExchange("application"+currentRoom.getText());
                b.setBackground(Color.green);
                currentRoom = b;
                snd.bindToExchange("application"+b.getText());
                field.setText("");
                snd.recieve(field);
            }
        }
        field.grabFocus();
    }

    public void refreshActionEvent (ActionEvent e){
        ArrayList<String> tmp = snd.getExchanges();
        for (String i : tmp){
            if(!roomsExchanges.contains(i)){
                roomsExchanges.add(i);
                JButton b = new JButton(i.substring(11));
                b.setFocusable(false);
                b.setForeground(Color.white);
                b.setBackground(new Color (0x6b6f80));
                b.setBounds(30,20+50*rooms.size(), 200, 40);
                b.addActionListener(e1->newRoomEvent(e , b));
                rooms.add(b);
                roomsPan.setPreferredSize(new Dimension(200, 53*roomsExchanges.size()));
                roomsPan.add(b);
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        field.setCaretPosition(position);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        field.setCaretPosition(position);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        field.setCaretPosition(position);
    }
}
