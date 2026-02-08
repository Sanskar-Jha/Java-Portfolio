package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    JTextField tFAccNum;
    JPasswordField pFLoginPin;
    JButton login;
    JLabel lCreateAcc;

    Main() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank_icon.png"));
        Image i1a = i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel bankIcon = new JLabel(i1b);
        bankIcon.setBounds(350,20,100,100);
        add(bankIcon);

        JLabel l1 = new JLabel("WELCOME TO");
        l1.setBounds(310, 130, 200, 40); // Set position
        l1.setFont(new Font("Arial", Font.BOLD, 28)); // Set font
        l1.setForeground(Color.WHITE); // Set color or Text color
        add(l1);

        JLabel l2 = new JLabel("SJ BANK");
        l2.setBounds(330, 170, 200, 40);
        l2.setFont(new Font("Arial", Font.BOLD, 34));
        l2.setForeground(Color.WHITE);
        add(l2);

        JLabel lAccNum = new JLabel("Account Number :");
        lAccNum.setBounds(130, 240,200, 30);
        lAccNum.setFont(new Font("Arial", Font.BOLD, 20));
        lAccNum.setForeground(Color.WHITE);
        add(lAccNum);

        tFAccNum = new JTextField(12);
        tFAccNum.setBounds(420, 240, 230, 30);
        tFAccNum.setFont(new Font("Arial", Font.BOLD, 20));
        add(tFAccNum);

        JLabel lLoginPin = new JLabel("Login PIN :");
        lLoginPin.setBounds(130, 290,200, 30);
        lLoginPin.setFont(new Font("Arial", Font.BOLD, 20));
        lLoginPin.setForeground(Color.WHITE);
        add(lLoginPin);

        pFLoginPin = new JPasswordField(4);
        pFLoginPin.setBounds(420, 290, 230, 30);
        pFLoginPin.setFont(new Font("Arial", Font.BOLD, 20));
        add(pFLoginPin);

        login = new JButton("LOGIN");
        login.setBounds(330, 350,150, 30);
        login.setFont(new Font("Arial", Font.BOLD, 16));
        login.setForeground(Color.WHITE);
        login.setBackground(Color.BLACK); // Set background color
        login.addActionListener(this); // Click event
        add(login);

        // Make clickable label using MouseAdapter
        lCreateAcc = new JLabel("Don't have an account? Open new account");
        lCreateAcc.setBounds(250, 390, 350, 30);
        lCreateAcc.setFont(new Font("Arial", Font.BOLD, 16));
        lCreateAcc.setForeground(Color.WHITE);
        lCreateAcc.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Makes it look clickable

        lCreateAcc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new OpenAccFormOne();
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lCreateAcc.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lCreateAcc.setForeground(Color.WHITE);
            }
        });
        add(lCreateAcc);

        setLayout(null);
        setSize(850,480); // Set frame size
        setLocation(360, 150); // Set location
        getContentPane().setBackground(new Color(151, 118, 153, 255));
        setUndecorated(true); // Remove 'Minimize', 'Close'.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop running in the background even after you close the window.
        setVisible(true); // Set visibility, by default false
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        int accNum = Integer.parseInt(tFCardNum.getText()); // Keep as String to avoid overflow
        String accNum = tFAccNum.getText();
        String loginPin = new String(pFLoginPin.getPassword());

        if (loginPin.isEmpty() || accNum.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter your Account Number and PIN"); // Display success or error message
            return;
        }

        try {
            ConnectDB con = new ConnectDB();
//            String q = "SELECT * FROM bank_login WHERE account_number = '"+accNum+"' AND login_pin = '"+name+"'";
//            con.statement.executeUpdate(q);  //Insert
            // Database Security
            // It is vulnerable to SQL Injection. An attacker could type ' OR '1'='1 into your text field and log in without a password. We use a PreparedStatement. It’s cleaner and much safer
            String q = "SELECT * FROM bank_login WHERE account_number = ? AND login_pin = ?";
            // Resources will close automatically here using "try-with-resources"
            try (PreparedStatement pstmt = con.connection.prepareStatement(q)) {
                pstmt.setString(1, accNum);
                pstmt.setString(2, loginPin);

                // Execute and check the result
                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        new ServicesOffer(accNum);
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Account Number and PIN. Please try again.");
                    }
                }
            }
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Database connection failed", E);
            JOptionPane.showMessageDialog(null, "Database Connection Error");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}