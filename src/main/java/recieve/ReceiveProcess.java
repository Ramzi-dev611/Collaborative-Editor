package recieve;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.swing.*;

public class ReceiveProcess {
    public void recieve(String queue, JTextArea text){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try{
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queue, false, false, false, null);
            DeliverCallback callback = (tag, delivery) -> {
                text.setText(new String(delivery.getBody(), "UTF-8"));
            };
            channel.basicConsume(queue, true, callback, tag->{});
        }catch(Exception e){

        }
    }

}
