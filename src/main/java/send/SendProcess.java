package send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendProcess {
    private Connection connection;
    private String queue;
    public SendProcess(String q){
        queue = q;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queue, false, false, false, null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public  SendProcess(){ }
    public void send(String message){
        try{
            Channel channel = connection.createChannel();
            channel.basicPublish("", queue, null, message.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
