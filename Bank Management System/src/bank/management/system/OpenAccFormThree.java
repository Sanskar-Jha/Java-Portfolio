package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenAccFormThree extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(OpenAccFormThree.class.getName());

    String formNum, name, fatherName, dOB, gender, email, maritalStatus, address,city, pinCode, state, religion, category, income, education, occupation, panNum, aadhaarNum, seniorCitizen, existingAcc;
    JButton cancel, submit;
    JCheckBox cBoxAtmCard, cBoxNetBanking, cBoxMobBanking, cBoxEmailAlert, cBoxChequeBook, cBoxEStmt;
    JRadioButton radioBtnSavingAcc, radioBtnCurrAcc, radioBtnFixedDeposit, radioBtnRecurringDeposit;

    OpenAccFormThree(String formNum, String name, String fatherName, String dOB, String gender, String email, String maritalStatus, String address, String city, String pinCode, String state, String religion, String category, String income, String education, String occupation, String panNum, String aadhaarNum, String seniorCitizen, String existingAcc) {
        this.formNum = formNum;
        this.name = name;
        this.fatherName = fatherName;
        this.dOB = dOB;
        this.gender = gender;
        this.email = email;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.city = city;
        this.pinCode = pinCode;
        this.state = state;
        this.religion = religion;
        this.category = category;
        this.income = income;
        this.education = education;
        this.occupation = occupation;
        this.panNum = panNum;
        this.aadhaarNum = aadhaarNum;
        this.seniorCitizen = seniorCitizen;
        this.existingAcc = existingAcc;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank_icon.png"));
        Image i1a = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel bankIcon = new JLabel(i1b);
        bankIcon.setBounds(20,20,100,100);
        add(bankIcon);

        JLabel l1 = new JLabel("Account Details");
        l1.setBounds(290, 60, 200, 30);
        l1.setFont(new Font("Arial", Font.BOLD, 22));
        add(l1);

        JLabel l2 = new JLabel("Page 3");
        l2.setBounds(700, 20, 50, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 14));
        add(l2);

        JLabel lFormNum = new JLabel("Form No : ");
        lFormNum.setBounds(700,40,100,30);
        lFormNum.setFont(new Font("Arial", Font.BOLD, 14));
        add(lFormNum);

        JLabel formNumVal = new JLabel(formNum);
        formNumVal.setBounds(775,40,60,30);
        formNumVal.setFont(new Font("Arial", Font.BOLD, 14));
        add(formNumVal);

        JLabel lAccType = new JLabel("Account Type :");
        lAccType.setBounds(100,140,200,30);
        lAccType.setFont(new Font("Arial", Font.BOLD, 20));
        add(lAccType);

        radioBtnSavingAcc = new JRadioButton("Saving Account");
        radioBtnSavingAcc.setBounds(300,140,150,30);
        radioBtnSavingAcc.setFont(new Font("Arial", Font.BOLD, 16));
        radioBtnSavingAcc.setBackground(new Color(168, 229, 177));
        add(radioBtnSavingAcc);

        radioBtnCurrAcc = new JRadioButton("Current Account");
        radioBtnCurrAcc.setBounds(520,140,150,30);
        radioBtnCurrAcc.setFont(new Font("Arial", Font.BOLD, 16));
        radioBtnCurrAcc.setBackground(new Color(168, 229, 177));
        add(radioBtnCurrAcc);

        radioBtnFixedDeposit = new JRadioButton("Fixed Deposit Account");
        radioBtnFixedDeposit.setBounds(300,190,200,30);
        radioBtnFixedDeposit.setFont(new Font("Arial", Font.BOLD, 16));
        radioBtnFixedDeposit.setBackground(new Color(168, 229, 177));
        add(radioBtnFixedDeposit);

        radioBtnRecurringDeposit = new JRadioButton("Recurring Deposit Account");
        radioBtnRecurringDeposit.setBounds(520,190,250,30);
        radioBtnRecurringDeposit.setFont(new Font("Arial", Font.BOLD, 16));
        radioBtnRecurringDeposit.setBackground(new Color(168, 229, 177));
        add(radioBtnRecurringDeposit);

        ButtonGroup bGAccType = new ButtonGroup();
        bGAccType.add(radioBtnSavingAcc);
        bGAccType.add(radioBtnCurrAcc);
        bGAccType.add(radioBtnFixedDeposit);
        bGAccType.add(radioBtnRecurringDeposit);

        JLabel lServicesRequired = new JLabel("Services Required :");
        lServicesRequired.setBounds(100,240,200,30);
        lServicesRequired.setFont(new Font("Arial", Font.BOLD, 20));
        add(lServicesRequired);

        cBoxAtmCard = new JCheckBox("ATM Card");
        cBoxAtmCard.setBounds(300,240,150,30);
        cBoxAtmCard.setFont(new Font("Arial", Font.BOLD, 16));
        cBoxAtmCard.setBackground(new Color(168, 229, 177));
        add(cBoxAtmCard);

        cBoxNetBanking = new JCheckBox("Internet Banking");
        cBoxNetBanking.setBounds(300,290,150,30);
        cBoxNetBanking.setFont(new Font("Arial", Font.BOLD, 16));
        cBoxNetBanking.setBackground(new Color(168, 229, 177));
        add(cBoxNetBanking);

        cBoxMobBanking = new JCheckBox("Mobile Banking");
        cBoxMobBanking.setBounds(300,340,150,30);
        cBoxMobBanking.setFont(new Font("Arial", Font.BOLD, 16));
        cBoxMobBanking.setBackground(new Color(168, 229, 177));
        add(cBoxMobBanking);

        cBoxEmailAlert = new JCheckBox("Email Alert");
        cBoxEmailAlert.setBounds(300,390,150,30);
        cBoxEmailAlert.setFont(new Font("Arial", Font.BOLD, 16));
        cBoxEmailAlert.setBackground(new Color(168, 229, 177));
        add(cBoxEmailAlert);

        cBoxChequeBook = new JCheckBox("Cheque Book");
        cBoxChequeBook.setBounds(300,440,150,30);
        cBoxChequeBook.setFont(new Font("Arial", Font.BOLD, 16));
        cBoxChequeBook.setBackground(new Color(168, 229, 177));
        add(cBoxChequeBook);

        cBoxEStmt = new JCheckBox("E-Statement");
        cBoxEStmt.setBounds(300,490,150,30);
        cBoxEStmt.setFont(new Font("Arial", Font.BOLD, 16));
        cBoxEStmt.setBackground(new Color(168, 229, 177));
        add(cBoxEStmt);

        JCheckBox cBoxTermNCond = new JCheckBox("I Here by declared that the above entered details correct to the best of my knowledge.", true); // By default, check
        cBoxTermNCond.setBounds(100,640,600,20);
        cBoxTermNCond.setFont(new Font("Arial", Font.BOLD, 12));
        cBoxTermNCond.setBackground(new Color(168, 229, 177));
        add(cBoxTermNCond);

        cancel = new JButton("Cancel");
        cancel.setBounds(250,670,100,30);
        cancel.setFont(new Font("Arial", Font.BOLD, 14));
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.addActionListener(this);
        add(cancel);

        submit = new JButton("Submit");
        submit.setBounds(500,670,100,30);
        submit.setFont(new Font("Arial", Font.BOLD, 14));
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.BLACK);
        submit.addActionListener(this);
        add(submit);

        setLayout(null);
        setSize(850, 780);
        setLocation(360,20);
        getContentPane().setBackground(new Color(168, 229, 177));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // 1. Move the facility logic to a separate method (Fixes the Warning: It's possible to extract method returning 'facility' from a long surrounding method)
    private String getSelectedFacilities() {
        StringBuilder facility = new StringBuilder();
        if (cBoxAtmCard.isSelected()) facility.append("ATM Card, ");
        if (cBoxNetBanking.isSelected()) facility.append("Internet Banking, ");
        if (cBoxMobBanking.isSelected()) facility.append("Mobile Banking, ");
        if (cBoxEmailAlert.isSelected()) facility.append("Email Alert, ");
        if (cBoxChequeBook.isSelected()) facility.append("Cheque Book, ");
        if (cBoxEStmt.isSelected()) facility.append("E-Statement");

        String result = facility.toString().trim();
        return result.isEmpty() ? "ATM Card" : result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            System.exit(0);
            return;
        }

        String accType = null;
        if (radioBtnSavingAcc.isSelected()) accType = "Saving Account";
        else if (radioBtnCurrAcc.isSelected()) accType = "Current Account";
        else if (radioBtnFixedDeposit.isSelected()) accType = "Fixed Deposit Account";
        else if (radioBtnRecurringDeposit.isSelected()) accType = "Recurring Deposit Account";

        if (accType == null) {
            JOptionPane.showMessageDialog(null, "Please Select Account Type");
            return;
        }

        // Call the extracted method
        String facility = getSelectedFacilities();

        // Generate Account Number and Login Pin & Card Number and Pin
        Random ran = new Random();
        // Account Number and Login Pin
        long randomPart1 = Math.abs(ran.nextLong() % 100000000L);
        String accNum = String.format("0007%08d", randomPart1);
        String loginPin = String.format("%04d", ran.nextInt(10000));
        // Card Number and Pin
        long randomPart2 = Math.abs(ran.nextLong() % 100000000L);
        String rawNum = String.format("07032003%08d", randomPart2);
        String cardNum = rawNum.replaceAll(".{4}", "$0 ").trim();
        String cardPin = String.format("%04d", ran.nextInt(10000));

//        String facility = "";
//        if (cBoxAtmCard.isSelected()) {
//            facility += "ATM Card";
//        } else if (cBoxNetBanking.isSelected()) {
//            facility += "Internet Banking";
//        } else if (cBoxMobBanking.isSelected()) {
//            facility += "Mobile Banking";
//        } else if (cBoxEmailAlert.isSelected()) {
//            facility += "Email Alert";
//        } else if (cBoxChequeBook.isSelected()) {
//            facility += "Cheque Book";
//        } else if (cBoxEStmt.isSelected()) {
//            facility += "E-Statement";
//        }

        try {
            ConnectDB con = new ConnectDB();
//            String q1 = "INSERT INTO open_acc_form_three VALUES(?, ?, ?, ?, ?)";
            String q2 = "INSERT INTO atm_login VALUES(?, ?, ?)";
            String q3 = "INSERT INTO bank_login VALUES(?, ?)";
            String q4 = "INSERT INTO person_info VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt2 = con.connection.prepareStatement(q2);
                PreparedStatement pstmt3 = con.connection.prepareStatement(q3);
                PreparedStatement pstmt4 = con.connection.prepareStatement(q4)) {
//                pstmt1.setString(1, formNum);
//                pstmt1.setString(2, accType);
//                pstmt1.setString(3, cardNum);
//                pstmt1.setString(4, cardPin);
//                pstmt1.setString(5, facility);

                pstmt2.setString(1, accNum);
                pstmt2.setString(2, cardNum);
                pstmt2.setString(3, cardPin);

                pstmt3.setString(1, accNum);
                pstmt3.setString(2, loginPin);

                pstmt4.setString(1, formNum);
                pstmt4.setString(2, loginPin);
                pstmt4.setString(3, accNum);
                pstmt4.setString(4, name);
                pstmt4.setString(5, fatherName);
                pstmt4.setString(6, dOB);
                pstmt4.setString(7, gender);
                pstmt4.setString(8, email);
                pstmt4.setString(9, maritalStatus);
                pstmt4.setString(10, address);
                pstmt4.setString(11, city);
                pstmt4.setString(12, pinCode);
                pstmt4.setString(13, state);
                pstmt4.setString(14, religion);
                pstmt4.setString(15, category);
                pstmt4.setString(16, income);
                pstmt4.setString(17, education);
                pstmt4.setString(18, occupation);
                pstmt4.setString(19, panNum);
                pstmt4.setString(20, aadhaarNum);
                pstmt4.setString(21, seniorCitizen);
                pstmt4.setString(22, existingAcc);
                pstmt4.setString(23, accType);
                pstmt4.setString(24, cardNum);
                pstmt4.setString(25, cardPin);
                pstmt4.setString(26, facility);

//                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
                pstmt3.executeUpdate();
                pstmt4.executeUpdate();
                // Display Card Number and Pin
                JOptionPane.showMessageDialog(null, "Account Number : " + accNum + "\nLogin Pin" + loginPin + "\n\nCard Number : " + cardNum + "\nPin : " + cardPin);
                new Main();
                setVisible(false);
            }
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Error saving Form Three", E);
            JOptionPane.showMessageDialog(null, "Error saving Form. Try again later.");
        }
    }

    public static void main(String[] args) {
        new OpenAccFormThree("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }
}