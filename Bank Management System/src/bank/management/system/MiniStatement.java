package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MiniStatement extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(MiniStatement.class.getName());

    String accNum, cardNum, cardPin;
    JLabel displayTransaction;
    JButton exit;

    MiniStatement(String accNum, String cardNum, String cardPin) {
        this.accNum = accNum;
        this.cardNum = cardNum;
        this.cardPin = cardPin;

        JLabel l1 = new JLabel("Mini Statement");
        l1.setBounds(20,20,400,30);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        add(l1);

        displayTransaction = new JLabel();
        displayTransaction.setBounds(20,40,400,200);
        displayTransaction.setFont(new Font("Arial", Font.BOLD, 16));
        add(displayTransaction);

        exit = new JButton("Exit");
        exit.setBounds(20,550,60,25);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.WHITE);
        exit.addActionListener(this);
        add(exit);

        setLayout(null);
        setSize(400, 600);
        setLocation(400,50);
        getContentPane().setBackground(new Color(222, 222, 222));
        setUndecorated(true);

        loadDataFromDatabase();
        setVisible(true);
    }

    private void loadDataFromDatabase() {
        // Use StringBuilder with HTML for multi-line support in JLabel
        StringBuilder statementText = new StringBuilder("<html>");
        double balance = 0.0;

        try {
            ConnectDB con = new ConnectDB();
            String q = "SELECT date, type, amount FROM transactions WHERE (account_number = ? OR card_number = ?) AND card_pin = ? ORDER BY date DESC LIMIT 10";
            try (PreparedStatement pstmt = con.connection.prepareStatement(q)) {
                pstmt.setString(1, accNum);
                pstmt.setString(2, cardNum);
                pstmt.setString(3, cardPin);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    while (resultSet.next()) {
                        String date = resultSet.getString("date");
                        String type = resultSet.getString("type");
                        String amt = resultSet.getString("amount");

                        // Append each transaction to the string
                        statementText.append(date).append("&nbsp;&nbsp;&nbsp;&nbsp;")
                                .append(type).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                                .append(amt).append("<br>");

                        if (type.equalsIgnoreCase("Deposit")) {
                            balance += Double.parseDouble(amt);
                        } else {
                            balance -= Double.parseDouble(amt);
                        }
                    }
                    statementText.append("<br><br><b>Total Balance: Rs ").append(balance).append("</b></html>");
                    displayTransaction.setText(statementText.toString());
                }
            }
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Database Connection Error", E);
            JOptionPane.showMessageDialog(null, "Unable to Load Mini-Statement");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            setVisible(false);
            return;
        }
    }

    public static void main(String[] args) {
        new MiniStatement("", "", "");
    }
}