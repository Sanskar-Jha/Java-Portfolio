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

public class Withdrawal extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(Withdrawal.class.getName());

    String accNum, cardNum, cardPin;
    JButton withdrawal, back;
    JTextField tFEnterAmount;

    Withdrawal(String accNum, String cardNum, String cardPin) {
        this.accNum = accNum;
        this.cardNum = cardNum;
        this.cardPin = cardPin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm_bg.png"));
        Image i1a = i1.getImage().getScaledInstance(1300, 800, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel image = new JLabel(i1b);
        image.setBounds(0,0,1300,800);
        add(image);

        JLabel l1 = new JLabel("MAXIMUM WITHDRAWAL is Rs 10,000");
        l1.setBounds(290,150,400,30);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        image.add(l1);

        JLabel l2 = new JLabel("Enter  Amount");
        l2.setBounds(290,190,400,30);
        l2.setFont(new Font("Arial", Font.BOLD, 16));
        l2.setForeground(Color.WHITE);
        image.add(l2);

        tFEnterAmount = new JTextField();
        tFEnterAmount.setBounds(290,220,300,25);
        tFEnterAmount.setFont(new Font("Arial", Font.BOLD, 16));
        tFEnterAmount.setForeground(Color.WHITE);
        tFEnterAmount.setBackground(new Color(65,125,128));
        image.add(tFEnterAmount);

        withdrawal = new JButton("WITHDRAWAL");
        withdrawal.setBounds(600,350,150,35);
        withdrawal.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawal.setForeground(Color.WHITE);
        withdrawal.setBackground(new Color(65,125,128));
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        back = new JButton("BACK");
        back.setBounds(600,400,150,35);
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
        Timestamp date = new Timestamp(System.currentTimeMillis());

        if (amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter Withdraw Amount");
            return;
        }

        double amount = Double.parseDouble(amountStr);
        // Input Validation
        try {
            if (amount <= 0) {
                JOptionPane.showMessageDialog(null, "Amount must be greater than zero");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount");
            return;
        }

        try {
            ConnectDB con = new ConnectDB();
            // 1. Calculate Current Balance
            String q1 = "SELECT * FROM bank_transactions WHERE (account_number = ? OR card_number = ?) AND card_pin = ?";
            double balance = 0.0;

            try (PreparedStatement pstmt1 = con.connection.prepareStatement(q1)) {
                pstmt1.setString(1, accNum);
                pstmt1.setString(2, cardNum);
                pstmt1.setString(3, cardPin);
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
            }

            // 2. Check Insufficient Funds
            if (amount > 10000) {
                JOptionPane.showMessageDialog(null, "Maximum withdrawal limit is Rs. 10,000");
                return;
            }

            if (balance < amount) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance. Current Balance: Rs " + balance);
                return;
            }

            // 3. Record the Withdrawal
            String q2 = "INSERT INTO bank_transactions VALUES(?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt2 = con.connection.prepareStatement(q2)) {
                pstmt2.setString(1, accNum);
                pstmt2.setString(2, cardNum);
                pstmt2.setString(3, cardPin);
                pstmt2.setTimestamp(4, date);
                pstmt2.setString(5, "Withdrawal");
                pstmt2.setDouble(6, amount);
                pstmt2.setDouble(7, balance);
                pstmt2.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
            new Atm(accNum, cardNum, cardPin);
            setVisible(false);
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Error saving Withdrawal", E);
            JOptionPane.showMessageDialog(null, "Database Error. Try again later.");
        }
    }

    public static void main(String[] args) {
        new Withdrawal("", "", "");
    }
}