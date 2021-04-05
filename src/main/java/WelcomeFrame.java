import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class WelcomeFrame extends JFrame implements FocusListener {
    protected JPanel panLeft;
    protected JTextArea leftLabel;
    protected ImageIcon logo;

    protected JPanel panRight ;
    protected JLabel errorMessage;
    protected JTextField queueName;
    protected JButton signIn;

    private void buildZoneLeft(){
        panLeft = new JPanel();
        panLeft.setLayout(null);
        leftLabel = new JTextArea();
        leftLabel.setText("Welcome To Collab-editor, the BEST place for you to CO-WORK \n"
        +"Insert your prefered username and join your Coworkersto create and inovate \n"+
                "Good Luck !");
        leftLabel.setEditable(false);
        leftLabel.setBorder(BorderFactory.createEmptyBorder());
        leftLabel.setBackground(new Color(0x021647));
        leftLabel.setFont(new Font("Helvatica", Font.PLAIN, 20));
        leftLabel.setForeground(Color.white);
        leftLabel.setLineWrap(true);
        leftLabel.setBounds(100, 400, 486, 500);
        logo = new ImageIcon("assets/logo.png");
        JLabel l = new JLabel();
        l.setBounds(280, 160, 320, 320);
        l.setIcon(logo);
        panLeft.add(l);
        panLeft.add(leftLabel);
        panLeft.setBackground(new Color(0x021647));
    }
    private void buildZoneRight(){
        panRight = new JPanel();
        panRight.setLayout(null);
        errorMessage = new JLabel("Please insert a username before loging in !");
        errorMessage.setForeground(Color.RED);
        errorMessage.setBackground(new Color(0xffb0b0));
        errorMessage.setBorder(BorderFactory.createLineBorder(new Color(0xffb0b0),5,true));
        errorMessage.setFont(new Font("Helvatica", Font.PLAIN,20));
        errorMessage.setOpaque(true);
        errorMessage.setVisible(false);
        errorMessage.setBounds(120,200,400,60);

        queueName = new JTextField("Your UserName !! ");
        queueName.setForeground(Color.gray);
        queueName.setFont(new Font("Helvatica", Font.ITALIC, 20));
        queueName.setHorizontalAlignment(SwingConstants.CENTER);
        queueName.addFocusListener(this);
        queueName.setBounds(120, 300, 500, 60);

        signIn = new JButton("Sign UP");
        signIn.setBackground(Color.green);
        signIn.setForeground(Color.white);
        signIn.setFocusable(false);
        signIn.addActionListener(e ->{
            if(queueName.getText().equals("") || queueName.getText().equals("Your UserName !! ")) {
                errorMessage.setVisible(true);
                queueName.grabFocus();
            }
            else{
                new Sender(queueName.getText());
            }
        });
        signIn.setBounds(320, 400,120,50);

        panRight.add(queueName);
        panRight.add(errorMessage);
        panRight.add(signIn);
        panRight.setBackground(new Color(0x021647));
    }

    public WelcomeFrame (){
        buildZoneLeft();
        buildZoneRight();
        setTitle("Welcome to Collab-Editor");
        getContentPane().setBackground(new Color(0xE6021647, true));
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new GridLayout(1,2, 10,0));
        add(panLeft);
        add(panRight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(queueName.getText().equals("Your UserName !! ")){
            queueName.setText("");
            queueName.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(queueName.getText().equals("")){
            queueName.setText("Your UserName !! ");
            queueName.setForeground(Color.gray);
        }
    }

    public static void main (String []args){
        new WelcomeFrame();
    }
}
