package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangePin extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(ChangePin.class.getName());

    String accNum, cardNum, cardPin;
    JPasswordField pswd1, pswd2;
    JButton changePin, back;

    ChangePin(String accNum, String cardNum, String cardPin) {
        this.accNum = accNum;
        this.cardNum = cardNum;
        this.cardPin = cardPin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm_bg.png"));
        Image i1a = i1.getImage().getScaledInstance(1300, 800, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel image = new JLabel(i1b);
        image.setBounds(0,0,1300,800);
        add(image);

        JLabel l1 = new JLabel("Change Your PIN");
        l1.setBounds(290,150,200,30);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        image.add(l1);

        JLabel l2 = new JLabel("New PIN");
        l2.setBounds(290,190,200,30);
        l2.setFont(new Font("Arial", Font.BOLD, 16));
        l2.setForeground(Color.WHITE);
        image.add(l2);

        pswd1 = new JPasswordField();
        pswd1.setBounds(430,190,100,25);
        pswd1.setFont(new Font("Arial", Font.BOLD, 16));
        pswd1.setBackground(new Color(65,125,128));
        pswd1.setForeground(Color.WHITE);
        image.add(pswd1);

        JLabel l3 = new JLabel("Re-enter New PIN");
        l3.setBounds(290,230,200,30);
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setForeground(Color.WHITE);
        image.add(l3);

        pswd2 = new JPasswordField();
        pswd2.setBounds(430,230,100,25);
        pswd2.setFont(new Font("Arial", Font.BOLD, 16));
        pswd2.setBackground(new Color(65,125,128));
        pswd2.setForeground(Color.WHITE);
        image.add(pswd2);

        changePin = new JButton("CHANGE");
        changePin.setBounds(600,350,150,35);
        changePin.setFont(new Font("Arial", Font.BOLD, 14));
        changePin.setForeground(Color.WHITE);
        changePin.setBackground(new Color(65,125,128));
        changePin.addActionListener(this);
        image.add(changePin);

        back = new JButton("BACK");
        back.setBounds(600, 400,150,35);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(65,125,125));
        back.addActionListener(this);
        image.add(back);

        setLayout(null);
        setSize(1300, 800);
        setLocation(130,10);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new Atm(accNum, cardNum, cardPin);
            setVisible(false);
            return;
        }

        String pin1 = new String(pswd1.getPassword());
        String pin2 = new String(pswd2.getPassword());

        if (!pin1.equals(pin2)) {
            JOptionPane.showMessageDialog(null, "Entered PIN does not match");
            return;
        } else if (pin1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter PIN");
            return;
        }

        try {
            ConnectDB con = new ConnectDB();
            String q1 = "UPDATE transactions SET card_pin = ? WHERE card_pin = ?";
            String q2 = "UPDATE atm_login SET card_pin = ? WHERE card_pin = ?";

            try (PreparedStatement pstmt1 = con.connection.prepareStatement(q1);
                 PreparedStatement pstmt2 = con.connection.prepareStatement(q2)) {

                pstmt1.setString(1, pin1);
                pstmt1.setString(2, cardPin);

                pstmt2.setString(1, pin1);
                pstmt2.setString(2, cardPin);

                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
            }
            JOptionPane.showMessageDialog(null, "PIN changes successfully");
            new Atm(accNum, cardNum, cardPin);
            setVisible(false);
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Database Connection Error", E);
            JOptionPane.showMessageDialog(null, "PIN Change Failed");
        }

    }

    public static void main(String[] args) {
        new ChangePin("", "", "");
    }
}
