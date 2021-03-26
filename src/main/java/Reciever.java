import javax.swing.*;
import java.awt.*;

public class Reciever extends JFrame {
    protected JLabel first;
    protected JLabel second;
    protected JTextArea firstText;
    protected JTextArea secondText;
    protected JPanel pan1;
    protected JPanel pan2;

    private void constructZone1(){
        pan1 = new JPanel();
        pan1.setBackground(new Color(0xcce6ff));
        pan1.setLayout(null);
        first = new JLabel("firstUser Said");
        first.setFont(new Font("Helvetica", Font.ITALIC, 32));
        first.setBounds(580, 20, 300, 75);
        firstText= new JTextArea();
        firstText.setEditable(false);
        firstText.setLineWrap(true);
        firstText.setFont(new Font("Comic Sans", Font.PLAIN, 22));
        JScrollPane scroll1 = new JScrollPane(firstText);
        scroll1.setBounds(5, 100, 1352, 250);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        RecieveProcess rp = new RecieveProcess();
        rp.recieve("Sender1", firstText);
        pan1.add(first);
        pan1.add(scroll1);

    } protected void constructZone2(){
        pan2 = new JPanel();
        pan2.setBackground(new Color(0xcce6ff));
        pan2.setLayout(null);
        second = new JLabel("secondUser Said");
        second.setFont(new Font("Helvetica", Font.ITALIC, 32));
        second.setBounds(580, 20, 300, 75);
        secondText= new JTextArea();
        secondText.setEditable(false);
        secondText.setFont(new Font("Comic Sans", Font.PLAIN, 22));
        secondText.setBounds(5, 100, 1352, 250);
        RecieveProcess rp = new RecieveProcess();
        rp.recieve("Sender2", secondText);
        pan2.add(second);
        pan2.add(secondText);
    }

    public Reciever(){
        constructZone1();
        constructZone2();
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1,0,20));
        add(pan1);
        add(pan2);
        setVisible(true);
    }
    public static void main(String args[]){
        new Reciever();
    }
}
