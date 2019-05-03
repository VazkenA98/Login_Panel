import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatAccount_Page extends JFrame implements ActionListener {
    private JTextField Name_text = new JTextField(20);
    private JTextField Email_text = new JTextField(20);
    private JTextField Id_text = new JTextField(20);
    private JPasswordField password = new JPasswordField(20);
    private JLabel Name_lab = new JLabel("Name :");
    private JLabel Email_lab = new JLabel("E-mail :");
    private JLabel Id_lab = new JLabel("ID :");
    private JLabel Password_lab = new JLabel("Password :");
    private JLabel Passvalid = new JLabel("Password must be more than 8 characters");
    private JLabel caption = new JLabel("Creat Account");
    private JPanel p = new JPanel();
    ImageIcon icon = new ImageIcon("Show.bmp");
    private JButton Back_but = new JButton("back");
    private JButton Creat_but = new JButton("Creat");
    private JLabel lab = new JLabel(icon);
    ShowTime s1 = new ShowTime("date"); //obj for calling date from class "ShowTime"
    ShowTime s2 = new ShowTime("time"); //obj for calling time from class "ShowTime"
    private  JLabel time = new JLabel();
    private  JLabel datee = new JLabel();
    private JCheckBox Check = new JCheckBox("");

    public CreatAccount_Page() {
        setLayout(new FlowLayout());
        p.setLayout(null);
        p.add(datee);
        p.add(time);
        p.add(caption);
        p.add(Name_text);
        p.add(Email_text);
        p.add(Id_text);
        p.add(Passvalid);
        p.add(password);
        p.add(Name_lab);
        p.add(Email_lab);
        p.add(Id_lab);
        p.add(Password_lab);
        p.add(Back_but);
        p.add(Creat_but);
        p.add(lab);
        p.add(Check);

        Name_text.setBounds(110, 100, 220, 50);
        Email_text.setBounds(110, 170, 220, 50);
        Id_text.setBounds(110, 240, 220, 50);
        Passvalid.setBounds(100, 275, 250, 50);
        password.setBounds(110, 310, 220, 50);
        caption.setBounds(95,20,250,75);
        caption.setFont(new Font("Serif", Font.BOLD, 40));
        Name_lab.setBounds(50, 110, 50, 25);
        Email_lab.setBounds(50, 180, 50, 25);
        Id_lab.setBounds(50, 250, 50, 25);
        Password_lab.setBounds(30, 320, 70, 25);
        Back_but.setBounds(230, 370, 100, 50);
        Creat_but.setBounds(110, 370, 100, 50);
        Check.setBounds(350, 320, 18, 32);
        lab.setBounds(370, 320, 32, 32);
        datee.setBounds(340, 410, 220, 50);
        time.setBounds(350, 430, 220, 50);
        //................coloring panels...............
        p.setPreferredSize(new Dimension(450, 480));
        p.setBackground(new Color(180, 220, 120));
        Check.setBackground(new Color(180, 220, 120));
        //...............setting fonts for time......................
        datee.setFont(new Font("sans-serif", Font.PLAIN, 14));
        time.setFont(new Font("sans-serif", Font.PLAIN, 16));
        add(p);
        //............Action Listener for buttons.......................
        Back_but.addActionListener(this);
        Check.addActionListener(this);
        Creat_but.addActionListener(this);
        setSize(500, 540);
        setVisible(true);
        //..............implementing methot "runnable" for thread...........
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
        //...........creating thread to start time..............
        Thread thread = new Thread(runnable);
        thread.start();
}

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean flag = false;
        if (e.getSource() == Back_but) {
            Login_page lp = new Login_page();
            setVisible(false);
        }
        if(Check.isSelected()){
            password.setEchoChar((char)0);
        }else{
            password.setEchoChar('*');
        }
        if(e.getSource()==Creat_but) {
            String name = Name_text.getText();
            String email = Email_text.getText();
            String idacount = Id_text.getText();
            String passwordacount = new String(password.getPassword());
            if (passwordacount.length() < 8) {
                Passvalid.setVisible(true);
                CreatAccount_Page p = new CreatAccount_Page();
                setVisible(false);
            } else {
                flag = true;
            }
            if (flag) {
                if (name.isEmpty() || email.isEmpty() || idacount.isEmpty() || passwordacount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Missing Info");
                } else {
                    JDBC data = new JDBC(2, name, email, idacount, passwordacount);
                    data.getsqlPath();
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");
                }
            }
        }
    }

}

