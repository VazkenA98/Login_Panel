import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

public class Login_page extends JFrame implements ActionListener {
    private JButton New_but = new JButton("New");
    private JButton Enter_but = new JButton("Enter");
    private JButton Path_but = new JButton("Path");
    private JLabel caption = new JLabel("Main Page");
    ShowTime s1 = new ShowTime("date");
    ShowTime s2 = new ShowTime("time");
    private JLabel inspDate = new JLabel(".......");
    private JLabel time = new JLabel();
    private JLabel datee = new JLabel();
    private JPanel p = new JPanel();

    public Login_page() {
        setLayout(new FlowLayout());

        p.setLayout(null);
        p.add(inspDate);
        p.add(datee);
        p.add(time);
        p.add(caption);
        p.add(New_but);
        p.add(Enter_but);
        p.add(Path_but);
        New_but.setBounds(110, 180, 220, 50);
        Enter_but.setBounds(110, 250, 220, 50);
        Path_but.setBounds(110, 320, 220, 50);
        p.setPreferredSize(new Dimension(450, 450));
        p.setBackground(new Color(180, 220, 220));
        inspDate.setBounds(0, 410, 220, 50);
        datee.setBounds(340, 390, 220, 50);
        time.setBounds(350, 410, 220, 50);
        datee.setFont(new Font("sans-serif", Font.PLAIN, 14));
        inspDate.setFont(new Font("sans-serif", Font.PLAIN, 16));
        time.setFont(new Font("sans-serif", Font.PLAIN, 16));

        add(p);

        caption.setBounds(110, 50, 220, 75);
        caption.setFont(new Font("Serif", Font.BOLD, 48));
        New_but.addActionListener(this);
        Enter_but.addActionListener(this);
        Path_but.addActionListener(this);
        Date d = new Date();
        setSize(500, 520);
        setVisible(true);
        final long timeInterval = 1000;
        Runnable runnable = new Runnable() {
            public void run() {
                while (true) {
                    // ------- code for task to run
                    datee.setText(s1.getTime());
                    time.setText(s2.getTime());
                    try {
                        inspDate.setText(s1.getInspDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // ------- ends here
                    if (inspDate.getText().contains("0")) {
                        System.exit(0);
                    }
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

        if (e.getSource() == New_but) {
            CreatAccount_Page cap = new CreatAccount_Page();
            setVisible(false);//vorbesi main pages kotsvi
        }
        if (e.getSource() == Enter_but) {
            Login_Account la = new Login_Account();
            setVisible(false);
        }
        if (e.getSource() == Path_but) {
            String s = JDBC.getsqlPath();
            JOptionPane.showMessageDialog(null, s);
        }


    }
}
