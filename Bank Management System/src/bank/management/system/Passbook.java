package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Passbook extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(Passbook.class.getName());

    String accNum;
    JLabel displayTransaction;
    JButton back;

    Passbook(String accNum) {
        this.accNum = accNum;

        JLabel l1 = new JLabel("Passbook");
        l1.setBounds(320, 20, 200, 40);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        add(l1);

        JLabel lDate = new JLabel("Date");
        lDate.setBounds(30, 80, 200, 30);
        lDate.setFont(new Font("Arial", Font.BOLD, 16));
        add(lDate);

        JLabel lTransactions = new JLabel("Transactions");
        lTransactions.setBounds(330, 80, 200, 50);
        lTransactions.setFont(new Font("Arial", Font.BOLD, 16));
        add(lTransactions);

        JLabel lBal = new JLabel("Balance");
        lBal.setBounds(650, 80, 200, 30);
        lBal.setFont(new Font("Arial", Font.BOLD, 16));
        add(lBal);

        displayTransaction = new JLabel();
        displayTransaction.setBounds(20, 50, 800, 400);
        displayTransaction.setFont(new Font("Arial", Font.PLAIN, 16));
        add(displayTransaction);

        back = new JButton("BACK");
        back.setBounds(50,430,100,30);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setSize(800, 500);
        setLocation(400,150);
        getContentPane().setBackground(new Color(222, 222, 222));
        setUndecorated(true);

        loadDataFromDatabase();
        setVisible(true);
    }

    private void loadDataFromDatabase() {
        StringBuilder statementText = new StringBuilder("<html><table width='700'>");

        try {
            ConnectDB con = new ConnectDB();
            String q = "SELECT * FROM transactions WHERE account_number = ?";
            try (PreparedStatement pstmt = con.connection.prepareStatement(q)) {
                pstmt.setString(1, accNum);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    while (resultSet.next()) {
                        String date = resultSet.getString("date");
                        String type = resultSet.getString("type");
                        double amount = resultSet.getDouble("amount");
                        double balance = resultSet.getDouble("balance");

                        // Creating a table row with 3 columns
                        statementText.append("<tr>")
                                .append("<td width='250' align='left'>").append(date).append("</td>")
                                .append("<td width='200'>").append(type).append(" &nbsp; ").append(amount).append("</td>")
                                .append("<td width='100' align='right'>").append(" &nbsp; ").append(balance).append("</td>")
                                .append("</tr>");
                    }
                    statementText.append("</table></html>");
                    displayTransaction.setText(statementText.toString());
                }
            }
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Database connection error", E);
            JOptionPane.showMessageDialog(null, "Database connection error");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            new ServicesOffer(accNum);
            setVisible(false);
            return;
        }
    }

    public static void main(String[] args) {
        new Passbook("");
    }
}