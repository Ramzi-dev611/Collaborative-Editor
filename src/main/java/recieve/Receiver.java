package recieve;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Receiver extends JFrame {
    protected ArrayList<JLabel> labs;
    protected ArrayList<JTextArea> fields;
    protected ArrayList<JPanel> pans;
    protected ArrayList<String> queues;


    private void constructZone(int index){
        JLabel l = new JLabel("User "+(index+1)+ " said");
        l.setFont(new Font("Boli MV",Font.ITALIC, 20));
        l.setPreferredSize(new Dimension(15,50));
        l.setHorizontalTextPosition(SwingConstants.CENTER);
        labs.add(l);

        JTextArea t = new JTextArea();
        t.setEditable(false);t.setLineWrap(true);
        t.setFont(new Font("Helvatica", Font.PLAIN, 22));
        new ReceiveProcess().recieve((String)queues.toArray()[index], t);
        fields.add(t);
        JScrollPane scroll = new JScrollPane(t);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel p = new JPanel();
        p.setBackground(new Color(0xc7caf0));
        p.setLayout(new BorderLayout(15,15));
        p.add(l, BorderLayout.NORTH);
        p.add(scroll, BorderLayout.CENTER);
        pans.add(p);
    }

    public Receiver(){
        QueuesFactory fact = new QueuesFactory();
        queues = fact.getQueus();
        labs = new ArrayList<>(queues.size());
        fields = new ArrayList<>(queues.size());
        pans = new ArrayList<>(queues.size());
        for (int i =0; i< queues.size(); i++){
            constructZone(i);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new GridLayout(1,queues.size(), 20, 20));
        for (JPanel p : pans){
            add(p);
        }
        setVisible(true);
    }
    public static void main(String args[]){
        new Receiver();
    }
}
