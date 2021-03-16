import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;
import java.awt.*;

public class Reciever extends JFrame implements Runnable {
    protected JScrollPane scroll1; protected JScrollPane scroll2;
    protected JTextArea text1; protected JTextArea text2;
    protected JTextField label1; protected JTextField label2;
    protected String recievingQueue1; protected String recievingQueue2;

    public void recieve(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (Connection connection = connectionFactory.newConnection()){
            Channel channel1 = connection.createChannel();
            Channel channel2 = connection.createChannel();
            channel1.queueDeclare(recievingQueue1, false, false,false, null);
            channel2.queueDeclare(recievingQueue2, false, false,false, null);
            DeliverCallback callback1 = (consummerTag, delivery) -> {
                text1.setText(new String (delivery.getBody(), "UTF-8"));
                System.out.println(new String (delivery.getBody(), "UTF-8"));
            };
            DeliverCallback callback2 = (consummerTag, delivery) -> {
                text2.setText(new String (delivery.getBody(), "UTF-8"));
                System.out.println(new String (delivery.getBody(), "UTF-8"));
            };
            channel1.basicConsume(recievingQueue1, true, callback1, consummerTag -> {});
            channel2.basicConsume(recievingQueue2, true, callback2, consummerTag -> {});
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Reciever(String q1, String q2){
        super ("Viewer");
        recievingQueue1=q1;
        recievingQueue2=q2;
        text1 = new JTextArea();
        text2 = new JTextArea();
        text1.setLineWrap(true); text1.setVisible(true); text1.setSize(500,250);text1.setEditable(false);
        text2.setLineWrap(true); text2.setVisible(true); text2.setSize(500,250);text2.setEditable(false);
        scroll1= new JScrollPane(text1);
        scroll2= new JScrollPane(text2);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll1.setBounds(200, 30, 500,250);
        scroll2.setBounds(200, 350, 500,250);
        scroll1.setVisible(true);
        scroll2.setVisible(true);
        label1 = new JTextField("User One Said");
        label1.setBounds(200, 10, 300, 20);
        label1.setBorder(null);label1.setEditable(false);label1.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label1.setForeground(new Color(11,12,16));
        label2 = new JTextField("User One Said");
        label2.setBounds(200, 320, 300, 20);
        label2.setBorder(null);label2.setEditable(false);label2.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label2.setForeground(new Color(11,12,16));
        new Thread(this).start();
        setVisible(true);
        setLayout(null);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(scroll1);
        add(label1);
        add(scroll2);
        add(label2);
    }

    public static void main(String[] args) {
        new Reciever("editeur1", "editeur2");
    }

    @Override
    public void run() {
        recieve();
    }
}
