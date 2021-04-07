import com.rabbitmq.client.*;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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

    public void send(String message, String exchange){
        try{
            Channel channel = connection.createChannel();
            channel.basicPublish(exchange, "", null, message.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void exchangeDeclaration(String exchange){
        try{
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchange, "fanout");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void bindToExchange(String exchange){
        try{
            Channel channel = connection.createChannel();
            channel.queueBind(queue, exchange ,"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void unbindFromExchange(String exchange){
        try{
            Channel channel = connection.createChannel();
            channel.queueUnbind(queue, exchange ,"");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getExchanges (){
        String loginPassword = "guest:guest";
        String encoded = DatatypeConverter.printBase64Binary(loginPassword.getBytes());
        ArrayList<String> queues= new ArrayList<String>();
        try {
            URL url = new URL("http://localhost:15672/api/exchanges");
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Authorization", "Basic "+encoded);
            String response = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream() ));
            String line;
            while ((line = in.readLine()) != null){
                response+= line;
            }
            String[] result = response.split("\"name\":\"");
            for(int i =1; i<result.length; i++) {
                String el = result[i];
                String res =""; int j= 0;
                while(el.charAt(j) != '"'){
                    res+= el.charAt(j);
                    j++;
                }
                if(res.matches("application.*")){
                    queues.add(res);
                }
            }
        }catch (Exception e){
            e.printStackTrace();}
        return queues;
    }

    public void recieve(JTextArea field){
        try{
            Channel channel = connection.createChannel();
            DeliverCallback callBack = (tag, delivery) ->{
              String message = new String(delivery.getBody(), "UTF-8");
              field.setText(message);
            };
            channel.basicConsume(queue, true, callBack, tag->{});
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
