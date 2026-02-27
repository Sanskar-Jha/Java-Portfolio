package bank.management.system;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FastCash extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(FastCash.class.getName());

    String accNum, cardNum, cardPin;
    JButton withdrawal100, withdrawal500, withdrawal1000, withdrawal2000, withdrawal5000, withdrawal10000, back;

    FastCash(String accNum, String cardNum, String cardPin){
        this.accNum = accNum;
        this.cardNum = cardNum;
        this.cardPin = cardPin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm_bg.png"));
        Image i1a = i1.getImage().getScaledInstance(1300, 800, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel image = new JLabel(i1b);
        image.setBounds(0,0,1300,800);
        add(image);

        JLabel l1 = new JLabel("Select Withdrawal Amount");
        l1.setBounds(290,150,400,30);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        image.add(l1);

        withdrawal100 = new JButton("Rs. 100");
        withdrawal100.setBounds(275, 265,150,35);
        withdrawal100.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawal100.setForeground(Color.WHITE);
        withdrawal100.setBackground(new Color(65,125,125));
        withdrawal100.addActionListener(this);
        image.add(withdrawal100);

        withdrawal500 = new JButton("Rs. 500");
        withdrawal500.setBounds(275, 310,150,35);
        withdrawal500.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawal500.setForeground(Color.WHITE);
        withdrawal500.setBackground(new Color(65,125,125));
        withdrawal500.addActionListener(this);
        image.add(withdrawal500);

        withdrawal1000 = new JButton("Rs. 1000");
        withdrawal1000.setBounds(275, 355,150,35);
        withdrawal1000.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawal1000.setForeground(Color.WHITE);
        withdrawal1000.setBackground(new Color(65,125,125));
        withdrawal1000.addActionListener(this);
        image.add(withdrawal1000);

        withdrawal2000 = new JButton("Rs. 2000");
        withdrawal2000.setBounds(275, 400,150,35);
        withdrawal2000.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawal2000.setForeground(Color.WHITE);
        withdrawal2000.setBackground(new Color(65,125,125));
        withdrawal2000.addActionListener(this);
        image.add(withdrawal2000);

        withdrawal5000 = new JButton("Rs. 5000");
        withdrawal5000.setBounds(600, 265,150,35);
        withdrawal5000.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawal5000.setForeground(Color.WHITE);
        withdrawal5000.setBackground(new Color(65,125,125));
        withdrawal5000.addActionListener(this);
        image.add(withdrawal5000);

        withdrawal10000 = new JButton("Rs. 10,000");
        withdrawal10000.setBounds(600, 310,150,35);
        withdrawal10000.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawal10000.setForeground(Color.WHITE);
        withdrawal10000.setBackground(new Color(65,125,125));
        withdrawal10000.addActionListener(this);
        image.add(withdrawal10000);

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

        // Remove "Rs. " and any commas like in "10,000"
        String amountStr = ((JButton)e.getSource()).getText().substring(4).replace(",", "");
        double withdrawalAmount = Double.parseDouble(amountStr);
        Timestamp date = new Timestamp(System.currentTimeMillis());
        double balance = 0.0;

        try {
            ConnectDB con = new ConnectDB();
            String q = "SELECT * FROM transactions WHERE (account_number = ? OR card_number = ?) AND card_pin = ?";
            try (PreparedStatement pstmt = con.connection.prepareStatement(q)) {
                pstmt.setString(1, accNum);
                pstmt.setString(2, cardNum);
                pstmt.setString(3, cardPin);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    while (resultSet.next()) {
                        String type = resultSet.getString("type");
                        double amt = Double.parseDouble(resultSet.getString("amount"));

                        if (type.equalsIgnoreCase("Deposit")) {
                            balance += amt;
                        } else {
                            balance -= amt;
                        }
                    }
                }

                // Check Insufficient Funds
                if (withdrawalAmount > 10000) {
                    JOptionPane.showMessageDialog(null, "Maximum withdrawal limit is Rs. 10,000");
                    return;
                }

                if (balance < withdrawalAmount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance. Current Balance: Rs " + balance);
                    return;
                }

                String q2 = "INSERT INTO transactions VALUES(?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt2 = con.connection.prepareStatement(q2)) {
                    pstmt2.setString(1, accNum);
                    pstmt2.setString(2, cardNum);
                    pstmt2.setString(3, cardPin);
                    pstmt2.setTimestamp(4, date);
                    pstmt2.setString(5, "Withdrawal");
                    pstmt2.setDouble(6, withdrawalAmount);
                    pstmt2.setDouble(7, balance);
                    pstmt2.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Rs. " + withdrawalAmount + " Debited Successfully");
            }
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Database Connection Error", E);
        }
        new Atm(accNum, cardNum, cardPin);
        setVisible(false);
    }

    public static void main(String[] args) {
        new FastCash("", "", "");
    }
}