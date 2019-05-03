import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Account extends JFrame implements ActionListener {
    private JTextField Name_f = new JTextField(20);
    private JPasswordField Pass_f =new JPasswordField(20);
    private JButton Login_but = new JButton("Login");
    private JButton Back_but = new JButton("Back");
    private JPanel p = new JPanel();
    private JLabel Name_l = new JLabel("Name :");
    private JLabel Pass_l = new JLabel("Password :");
    private JLabel caption = new JLabel("Login Page");
    ImageIcon icon = new ImageIcon("Show.bmp");
    private JLabel lab = new JLabel(icon);
    private JCheckBox Check = new JCheckBox("");
    ShowTime s1 = new ShowTime("date");
    ShowTime s2 = new ShowTime("time");

    private  JLabel time = new JLabel();
    private  JLabel datee = new JLabel();
    public Login_Account(){
        setLayout(new FlowLayout());
        p.setLayout(null);
        p.add(datee);
        p.add(time);

        p.add(caption);
        p.add(Name_f);
        p.add(Pass_f);
        p.add(Login_but);
        p.add(Back_but);
        p.add(Name_l);
        p.add(Pass_l);
        p.add(lab);
        p.add(Check);
        Name_f.setBounds(110, 100, 220, 50);
        Pass_f.setBounds(110, 170, 220, 50);
        Name_l.setBounds(50, 110, 50, 25);
        Pass_l.setBounds(50, 180, 50, 25);
        Back_but.setBounds(230, 240, 100, 50);
        Login_but.setBounds(110, 240, 100, 50);
        Check.setBounds(350, 180, 18, 32);

        datee.setBounds(340, 330, 220, 50);
        time.setBounds(350, 350, 220, 50);


        datee.setFont(new Font("sans-serif", Font.PLAIN, 14));

        time.setFont(new Font("sans-serif", Font.PLAIN, 16));


        caption.setBounds(115,10,220,75);
        caption.setFont(new Font("Serif", Font.BOLD, 44));
        lab.setBounds(370, 180, 32, 32);

        Check.setBackground(new Color(190,120,220));
        p.setPreferredSize(new Dimension(450,400));
        p.setBackground(new Color(190,120,220));

        Check.addActionListener(this);
        Login_but.addActionListener(this);
        Back_but.addActionListener(this);


        add(p);
        setSize(500,500);
        setVisible(true);

        final long timeInterval = 1000;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    // ------- code for task to run
                    datee.setText(s1.getTime());
                    time.setText(s2.getTime());

                    // ------- ends here
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back_but) {
            Login_page lp = new Login_page();
            setVisible(false);
        }
        if(Check.isSelected()){
            Pass_f.setEchoChar((char)0);
        }else{
            Pass_f.setEchoChar('*');
        }
        if(e.getSource()==Login_but){
            String searchEame = Name_f.getText();
            String searchPassword = new String(Pass_f.getPassword());
            if(searchEame.isEmpty() || searchPassword.isEmpty()){
                JOptionPane.showMessageDialog(null, "Missing Info");
            }else{
                JDBC data = new JDBC(1,"",searchEame,"",searchPassword);

            }

        }
    }
}
