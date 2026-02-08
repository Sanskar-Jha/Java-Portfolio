package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BalEnquiry extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(BalEnquiry.class.getName());

    String accNum, cardNum, cardPin;
    JButton back;
    JLabel totalBal;

    BalEnquiry(String accNum, String cardNum, String cardPin) {
        this.accNum = accNum;
        this.cardNum = cardNum;
        this.cardPin = cardPin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm_bg.png"));
        Image i1a = i1.getImage().getScaledInstance(1300, 800, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel image = new JLabel(i1b);
        image.setBounds(0,0,1300,800);
        add(image);

        JLabel l1 = new JLabel("Your Current Balance is Rs ");
        l1.setBounds(290,200,400,30);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        image.add(l1);
        
        totalBal = new JLabel();
        totalBal.setBounds(500,200,400,30);
        totalBal.setFont(new Font("Arial", Font.BOLD, 16));
        totalBal.setForeground(Color.WHITE);
        image.add(totalBal);

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

        loadDataFromDatabase();
        setVisible(true);
    }

    private void loadDataFromDatabase() {
        try {
            ConnectDB con = new ConnectDB();
            String q = "SELECT SUM(CASE WHEN type = 'Deposit' THEN amount ELSE -amount END) as balance FROM bank_transactions WHERE card_number = ? AND card_pin = ?";

            try (PreparedStatement pstmt = con.connection.prepareStatement(q)) {
                pstmt.setString(1, cardNum);
                pstmt.setString(2, cardPin);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        double balance = resultSet.getDouble("balance");
                        totalBal.setText("" + balance);
                    } else {
                        totalBal.setText("Not Found");
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Database Connection Error", e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new Atm(accNum, cardNum, cardPin);
            setVisible(false);
            return;
        }
    }

    public static void main(String[] args) {
        new BalEnquiry("", "", "");
    }
}