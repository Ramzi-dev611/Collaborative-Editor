import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TmpFrame extends JFrame {
    protected JButton button;
    protected String old;
    protected String name;

    public TmpFrame(String o, String n){
        old = o;
        name = n;
        button = new JButton("Confirm");
        button.setBackground(Color.GREEN);
        button.setFocusable(false);
        button.setFont(new Font("Helvetica", Font.BOLD, 18));
        button.setForeground(Color.white);
        button.addActionListener(e ->{
            dispose();
            new Sender(name, old);
        });
        setTitle("Are you sure ?");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(button);
        setSize(420,420);
        setVisible(true);
    }
}
