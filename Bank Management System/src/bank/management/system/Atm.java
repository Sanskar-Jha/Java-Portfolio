package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Atm extends JFrame implements ActionListener {
    String accNum, cardNum, cardPin;
    JButton deposit, withdrawal, fastCash, miniStatement, pinChange, balEnquiry, exit;

    Atm(String accNum, String cardNum, String cardPin) {
        this.accNum = accNum;
        this.cardNum = cardNum;
        this.cardPin = cardPin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm_bg.png"));
        Image i1a = i1.getImage().getScaledInstance(1300, 800, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel image = new JLabel(i1b);
        image.setBounds(0,0,1300,800);
        add(image);

        JLabel l1 = new JLabel("Please Select Your Transaction");
        l1.setBounds(290,150,250,35);
        l1.setFont(new Font("Arial", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);
        image.add(l1);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(275, 265,200,35);
        deposit.setFont(new Font("Arial", Font.BOLD, 14));
        deposit.setForeground(Color.WHITE);
        deposit.setBackground(new Color(65,125,125));
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawal = new JButton("WITHDRAWAL");
        withdrawal.setBounds(275, 310,200,35);
        withdrawal.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawal.setForeground(Color.WHITE);
        withdrawal.setBackground(new Color(65,125,125));
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        fastCash = new JButton("FAST CASH");
        fastCash.setBounds(275, 355,200,35);
        fastCash.setFont(new Font("Arial", Font.BOLD, 14));
        fastCash.setForeground(Color.WHITE);
        fastCash.setBackground(new Color(65,125,125));
        fastCash.addActionListener(this);
        image.add(fastCash);

        miniStatement = new JButton("MINI STATEMENT");
        miniStatement.setBounds(275, 400,200,35);
        miniStatement.setFont(new Font("Arial", Font.BOLD, 14));
        miniStatement.setForeground(Color.WHITE);
        miniStatement.setBackground(new Color(65,125,125));
        miniStatement.addActionListener(this);
        image.add(miniStatement);

        pinChange = new JButton("PIN CHANGE");
        pinChange.setBounds(550, 265,200,35);
        pinChange.setFont(new Font("Arial", Font.BOLD, 14));
        pinChange.setForeground(Color.WHITE);
        pinChange.setBackground(new Color(65,125,125));
        pinChange.addActionListener(this);
        image.add(pinChange);

        balEnquiry = new JButton("BALANCE ENQUIRY");
        balEnquiry.setBounds(550, 310,200,35);
        balEnquiry.setFont(new Font("Arial", Font.BOLD, 14));
        balEnquiry.setForeground(Color.WHITE);
        balEnquiry.setBackground(new Color(65,125,125));
        balEnquiry.addActionListener(this);
        image.add(balEnquiry);

        exit = new JButton("EXIT");
        exit.setBounds(550, 400,200,35);
        deposit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.WHITE);
        exit.setBackground(new Color(65,125,125));
        exit.addActionListener(this);
        image.add(exit);

        setLayout(null);
        setSize(1300, 800);
        setLocation(130,10);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deposit) {
            new Deposit(accNum, cardNum, cardPin);
            setVisible(false);
        } else if (e.getSource() == withdrawal) {
            new Withdrawal(accNum, cardNum, cardPin);
            setVisible(false);
        } else if (e.getSource() == fastCash) {
            new FastCash(accNum, cardNum, cardPin);
            setVisible(false);
        } else if (e.getSource() == miniStatement) {
            new MiniStatement(accNum, cardNum, cardPin);
        } else if (e.getSource() == pinChange) {
            new ChangePin(accNum, cardNum, cardPin);
            setVisible(false);
        } else if (e.getSource() == balEnquiry) {
            new BalEnquiry(accNum, cardNum, cardPin);
            setVisible(false);
        } else if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Atm("", "", "");
    }
}