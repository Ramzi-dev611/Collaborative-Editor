import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendingProcess {
    protected final String queue;
    protected final String message;
    public SendingProcess(String s, String m){
       queue = s;
       message = m;
    }
    public void send (){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare(this.queue,false,false,false,null);

            channel.basicPublish("",this.queue,null, this.message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
