package send;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class WelcomeFrame extends JFrame implements FocusListener {
    protected JTextField input;
    protected JButton connect;
    protected JLabel err;
    public WelcomeFrame (){
        //err
        err = new JLabel("Please type a valid name");
        err.setForeground(Color.red);
        err.setBounds(110, 115, 200, 50);
        err.setVisible(false);
        // input Field
        input = new JTextField("Type you name here");
        input.setForeground(Color.gray);
        input.setFont(new Font("Boli MV", Font.ITALIC, 22));
        input.addFocusListener(this);
        input.setBounds(110,175,200,70);
        // Submit  button
        connect =new JButton("Sign in");
        connect.setFont(new Font("Helvatica",Font.BOLD, 18));
        connect.setBackground(new Color(0x3bb357));
        connect.setForeground(Color.white);
        connect.setFocusable(false);
        connect.addActionListener(e -> {
            String name = input.getText();
            if (name.equals("")){
                input.setBorder(BorderFactory.createLineBorder(Color.red, 3));
                err.setVisible(true);
            }else {
                this.dispose();
                new Sender(name);
            }
        });
        connect.setBounds(160,300, 100, 50);

        setTitle("Welcome to The Collaborative Editor");
        setLayout(null);
        setSize(420,420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(err);
        add(input);
        add(connect);
        setVisible(true);

    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==input){
            if(input.getForeground()== Color.gray){
                input.setText("");
                input.setForeground(Color.black);
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()==input){
            if(input.getText().equals("")){
                input.setText("Name your new room");
                input.setForeground(Color.gray);
            }
        }

    }
}
