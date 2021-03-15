import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class User1 extends JFrame {
    protected JTextArea text;
    protected JLabel label ;
    protected JScrollPane scrollable;
    protected JTextField message = new JTextField("");
    public void sending (){
        message.setText("Your message is being sent ...");
        String contant = text.getText();
        SendingProcess sender = new SendingProcess("editeur1", contant);
        sender.send();
        message.setBounds(300, 10, 200 ,30);
        message.setBorder(null);
        message.setEditable(false);
        add(message);
    }
    public void endSending(){
        message.setText("");
    }
    public User1(){
        super("User One Prompt");
        text = new JTextArea();
        label = new JLabel("Write something here !");
        scrollable = new JScrollPane(text);
        text.setSize(300,300);
        text.setVisible(true);
        text.setLineWrap(true);
        text.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                sending();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                sending();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                sending();
            }
        });
        text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                endSending();
            }
        });
        scrollable.setBounds(240,50,300,150);
        scrollable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollable.setVisible(true);
        label.setLabelFor(text);
        label.setBounds(40,100,150,50);
        add(scrollable);
        add(label);
        setLayout(null);
        setVisible(true);
        setSize(800,400);
        setResizable(false);
    }

    public static void main(String[] args) {
        new User1();
    }
}
