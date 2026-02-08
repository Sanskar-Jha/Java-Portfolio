package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deposit extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(Deposit.class.getName());

    String accNum, cardNum, cardPin;
    JButton deposit, back;
    JTextField tFEnterAmount;

    Deposit(String accNum, String cardNum, String cardPin) {
        this.accNum = accNum;
        this.cardNum = cardNum;
        this.cardPin = cardPin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm_bg.png"));
        Image i1a = i1.getImage().getScaledInstance(1300, 800, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel image = new JLabel(i1b);
        image.setBounds(0,0,1300,800);
        add(image);

        JLabel l1 = new JLabel("Enter Deposit Amount");
        l1.setBounds(290,150,400,30);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        image.add(l1);

        tFEnterAmount = new JTextField();
        tFEnterAmount.setBounds(290,190,300,25);
        tFEnterAmount.setFont(new Font("Arial", Font.BOLD, 16));
        tFEnterAmount.setForeground(Color.WHITE);
        tFEnterAmount.setBackground(new Color(65,125,128));
        image.add(tFEnterAmount);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(620,350,130,35);
        deposit.setFont(new Font("Arial", Font.BOLD, 14));
        deposit.setForeground(Color.WHITE);
        deposit.setBackground(new Color(65,125,128));
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("BACK");
        back.setBounds(620,400,130,35);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setForeground(Color.WHITE);
        back.setBackground(new Color(65,125,128));
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

        String amountStr = tFEnterAmount.getText();
//        String date = new Date().toString(); // Date is object. Convert to String OR Use Timestamp returns "yyyy-MM-dd HH:mm:ss" instead "Sun Feb 08 04:01:31 IST 2026"
        Timestamp date = new Timestamp(System.currentTimeMillis());

        if (amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter Amount");
            return;
        }

        // Input Validation
        double amount = Double.parseDouble(amountStr);
        try {
            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Amount must be greater than zero");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount");
            return;
        }

        double balance = 0.0;
        try {
            ConnectDB con = new ConnectDB();
            // 1. Calculate Current Balance
            String q1 = "SELECT * FROM bank_transactions WHERE card_number = ? AND card_pin = ?";

            try (PreparedStatement pstmt1 = con.connection.prepareStatement(q1)) {
                pstmt1.setString(1, cardNum);
                pstmt1.setString(2, cardPin);
                ResultSet resultSet = pstmt1.executeQuery();

                while (resultSet.next()) {
                    String type = resultSet.getString("type");
                    double amt = resultSet.getDouble("amount");
                    if (type.equalsIgnoreCase("Deposit")) {
                        balance += amt;
                    } else {
                        balance -= amt;
                    }
                }

                String q2 = "INSERT INTO bank_transactions VALUES(?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt2 = con.connection.prepareStatement(q2)) {
                    pstmt2.setString(1, accNum);
                    pstmt2.setString(2, cardNum);
                    pstmt2.setString(3, cardPin);
                    pstmt2.setTimestamp(4, date);
                    pstmt2.setString(5, "Deposit");
                    pstmt2.setDouble(6, amount); // Store as a number! Storing money as a String in a database makes it impossible to use SQL functions like SUM() or AVG().
                    pstmt2.setDouble(7, balance);

                    pstmt2.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                new Atm(accNum, cardNum, cardPin);
                setVisible(false);
            }
        } catch (Exception E) {
                logger.log(Level.SEVERE, "Error saving Deposit", E);
                JOptionPane.showMessageDialog(null, "Database Error. Try again later.");
        }
    }

    public static void main(String[] args) {
        new Deposit("", "", "");
    }
}