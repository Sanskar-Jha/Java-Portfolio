package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServicesOffer extends JFrame implements ActionListener {
    String accNum;
    JLabel lAccHolderPro, lPassbook, lGoToAtm;
    JButton exit;

    ServicesOffer(String accNum) {
        this.accNum = accNum;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank_icon.png"));
        Image i1a = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel bankIcon = new JLabel(i1b);
        bankIcon.setBounds(20, 20, 100, 100);
        add(bankIcon);

        JLabel l1 = new JLabel("Services Offer");
        l1.setBounds(160, 60, 600, 40);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        add(l1);

        lAccHolderPro = new JLabel("Account Holder Profile");
        lAccHolderPro.setBounds(300, 250, 200, 30);
        lAccHolderPro.setFont(new Font("Arial", Font.BOLD, 16));
        lAccHolderPro.setForeground(Color.BLACK);
        lAccHolderPro.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lAccHolderPro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PersonInfo(accNum);
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lAccHolderPro.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lAccHolderPro.setForeground(Color.BLACK);
            }
        });
        add(lAccHolderPro);

        lPassbook = new JLabel("Bank Passbook");
        lPassbook.setBounds(320, 300, 200, 30);
        lPassbook.setFont(new Font("Arial", Font.BOLD, 16));
        lPassbook.setForeground(Color.BLACK);
        lPassbook.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lPassbook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Passbook(accNum);
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lPassbook.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lPassbook.setForeground(Color.BLACK);
            }
        });
        add(lPassbook);

        lGoToAtm = new JLabel("Go To ATM");
        lGoToAtm.setBounds(330, 350, 200, 30);
        lGoToAtm.setFont(new Font("Arial", Font.BOLD, 16));
        lGoToAtm.setForeground(Color.BLACK);
        lGoToAtm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lGoToAtm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AtmLogin(accNum);
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lGoToAtm.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lGoToAtm.setForeground(Color.BLACK);
            }
        });
        add(lGoToAtm);

        exit = new JButton("EXIT");
        exit.setBounds(330,450,100,30);
        exit.setFont(new Font("Arial", Font.BOLD, 14));
        exit.setForeground(Color.WHITE);
        exit.setBackground(Color.BLACK);
        exit.addActionListener(this);
        add(exit);

        setLayout(null);
        setSize(850, 780);
        setLocation(360,20);
        getContentPane().setBackground(new Color(222, 222, 222));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ServicesOffer("");
    }
}
