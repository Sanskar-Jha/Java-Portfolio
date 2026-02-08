package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AtmLogin extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(AtmLogin.class.getName());

    String accNum;
    JLabel lCardNum, lCardPin;
    JTextField tFCardNum;
    JPasswordField pFCardPin;
    JButton signin;

    AtmLogin(String accNum) {
        this.accNum = accNum;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank_icon.png"));
        Image i1a = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel bankIcon = new JLabel(i1b);
        bankIcon.setBounds(20,20,100,100);
        add(bankIcon);

        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/bank_bg_add_on.png"));
        Image i3a = i3.getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT);
        ImageIcon i3b = new ImageIcon(i3a);
        JLabel bankBg = new JLabel(i3b);
        bankBg.setBounds(630, 280, 200, 200);
        add(bankBg);

        JLabel l1 = new JLabel("WELCOME To SJ ATM");
        l1.setBounds(230, 50, 450, 40);
        l1.setFont(new Font("Arial", Font.BOLD, 38));
        l1.setForeground(Color.WHITE);
        add(l1);

        lCardNum = new JLabel("Card No :");
        lCardNum.setBounds(230, 180, 100, 30);
        lCardNum.setFont(new Font("Arial", Font.BOLD, 20));
        lCardNum.setForeground(Color.WHITE);
        add(lCardNum);

        tFCardNum = new JTextField(16);
        tFCardNum.setBounds(330, 180, 230, 30);
        tFCardNum.setFont(new Font("Arial", Font.BOLD, 20));
        add(tFCardNum);

        lCardPin = new JLabel("PIN :");
        lCardPin.setBounds(230, 240,50, 30);
        lCardPin.setFont(new Font("Arial", Font.BOLD, 20));
        lCardPin.setForeground(Color.WHITE);
        add(lCardPin);

        pFCardPin = new JPasswordField(4);
        pFCardPin.setBounds(330, 240, 230, 30);
        pFCardPin.setFont(new Font("Arial", Font.BOLD, 20));
        add(pFCardPin);

        signin = new JButton("SIGN IN");
        signin.setBounds(330, 290,230, 30);
        signin.setFont(new Font("Arial", Font.BOLD, 16));
        signin.setForeground(Color.WHITE);
        signin.setBackground(Color.BLACK); // Set background color
        signin.addActionListener(this); // Click event
        add(signin);

        setLayout(null);
        setSize(850,480);
        setLocation(360, 150);
        getContentPane().setBackground(new Color(93, 152, 141, 255));
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cardNum = tFCardNum.getText();
        String cardPin = new String(pFCardPin.getPassword()); // Convert char[] to String

        try {
            if (e.getSource() == signin) {
                ConnectDB con = new ConnectDB();
//                ResultSet resultSet = con.statement.executeQuery(q); //Retrieve
                String q = "SELECT * FROM atm_login WHERE card_number = ? AND card_pin = ?";
                try (PreparedStatement pstmt = con.connection.prepareStatement(q)) {
                    pstmt.setString(1, cardNum);
                    pstmt.setString(2, cardPin);
                    try (ResultSet resultSet = pstmt.executeQuery()) {
                        if (resultSet.next()) {
                            setVisible(false);
                            new Atm(accNum, cardNum, cardPin);
                            return;
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN"); // Display success or error message
                        }
                    }
                }
            }
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Database connection failed", E);
            JOptionPane.showMessageDialog(null, "Database Connection Error");
        }
    }

    public static void main(String[] args) {
        new AtmLogin("");
    }
}