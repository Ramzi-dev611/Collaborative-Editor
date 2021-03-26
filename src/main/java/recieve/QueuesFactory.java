package recieve;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class QueuesFactory {
    public ArrayList<String> getQueus (){
        String loginPassword = "guest:guest";
        String encoded = DatatypeConverter.printBase64Binary(loginPassword.getBytes());
        ArrayList<String> queues= new ArrayList<String>();
        try {
            URL url = new URL("http://localhost:15672/api/queues");
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
}
