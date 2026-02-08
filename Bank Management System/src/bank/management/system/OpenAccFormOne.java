package bank.management.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.sql.PreparedStatement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenAccFormOne extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(OpenAccFormOne.class.getName());

    JButton next;
    JRadioButton radioBtnMale, radioBtnFemale, radioBtnMarried, radioBtnUnmarried, radioBtnOther;
    JTextField tFName, tFFatherName, tFEmail, tFAddress, tFCity, tFPinCode, tFState;
    JDateChooser dateChooser;

    // Generate 4-Digit Form Number
    Random ran = new Random();
//    String generateFormNum = String.valueOf(ran.nextInt(9000) + 1000); // OR
    String generateFormNum = String.format("%04d", ran.nextInt(10000));

    OpenAccFormOne() {
        super("Open New Account");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank_icon.png"));
        Image i1a = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel bankIcon = new JLabel(i1b);
        bankIcon.setBounds(20, 20, 100, 100);
        add(bankIcon);

        JLabel l1 = new JLabel("APPLICATION FORM NO. " + generateFormNum);
        l1.setBounds(160, 60, 600, 30);
        l1.setFont(new Font("Arial", Font.BOLD, 36));
        add(l1);

        JLabel l2 = new JLabel("Page 1");
        l2.setBounds(750, 20, 50, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 14));
        add(l2);

        JLabel l3 = new JLabel("Personal Details");
        l3.setBounds(290, 100, 200, 30);
        l3.setFont(new Font("Arial", Font.BOLD, 22));
        add(l3);

        JLabel lName = new JLabel("Name :");
        lName.setBounds(100, 160, 100, 30);
        lName.setFont(new Font("Arial", Font.BOLD, 20));
        add(lName);

        tFName = new JTextField();
        tFName.setBounds(270, 160, 400, 30);
        tFName.setFont(new Font("Arial", Font.BOLD, 18));
        add(tFName);

        JLabel lFatherName = new JLabel("Father's Name :");
        lFatherName.setBounds(100, 210, 200, 30);
        lFatherName.setFont(new Font("Arial", Font.BOLD, 20));
        add(lFatherName);

        tFFatherName = new JTextField();
        tFFatherName.setBounds(270, 210, 400, 30);
        tFFatherName.setFont(new Font("Arial", Font.BOLD, 18));
        add(tFFatherName);

        JLabel lDOB = new JLabel("Date of Birth :");
        lDOB.setBounds(100, 260, 200, 30);
        lDOB.setFont(new Font("Arial", Font.BOLD, 20));
        add(lDOB);

        // Calendar
        dateChooser = new JDateChooser();
        dateChooser.setBounds(270,260,400,30);
        dateChooser.setForeground(new Color(105, 105, 105));
        add(dateChooser);

        JLabel lGender = new JLabel("Gender");
        lGender.setBounds(100,310,150,30);
        lGender.setFont(new Font("Arial", Font.BOLD, 20));
        add(lGender);

        radioBtnMale = new JRadioButton("Male");
        radioBtnMale.setBounds(270, 310,100,30);
        radioBtnMale.setFont(new Font("Arial", Font.BOLD, 14));
        radioBtnMale.setBackground(new Color(168, 229, 177));
        add(radioBtnMale);

        radioBtnFemale = new JRadioButton("Female");
        radioBtnFemale.setBounds(380, 310,100,30);
        radioBtnFemale.setFont(new Font("Arial", Font.BOLD, 14));
        radioBtnFemale.setBackground(new Color(168, 229, 177));
        add(radioBtnFemale);

        // ButtonGroup use for radio button functionality
        ButtonGroup bGGender = new ButtonGroup();
        bGGender.add(radioBtnMale);
        bGGender.add(radioBtnFemale);

        JLabel lEmail = new JLabel("Email address :");
        lEmail.setBounds(100,360,200,30);
        lEmail.setFont(new Font("Arial", Font.BOLD, 20));
        add(lEmail);

        tFEmail = new JTextField();
        tFEmail.setBounds(270, 360, 400, 30);
        tFEmail.setFont(new Font("Arial", Font.BOLD, 18));
        add(tFEmail);

        JLabel lMaritalStatus = new JLabel("Marital Status :");
        lMaritalStatus.setBounds(100,410,200,30);
        lMaritalStatus.setFont(new Font("Arial", Font.BOLD, 20));
        add(lMaritalStatus);

        radioBtnMarried = new JRadioButton("Married");
        radioBtnMarried.setBounds(270, 410, 100, 30);
        radioBtnMarried.setFont(new Font("Arial", Font.BOLD, 14));
        radioBtnMarried.setBackground(new Color(168, 229, 177));
        add(radioBtnMarried);

        radioBtnUnmarried = new JRadioButton("Unmarried");
        radioBtnUnmarried.setBounds(380, 410, 120, 30);
        radioBtnUnmarried.setFont(new Font("Arial", Font.BOLD, 14));
        radioBtnUnmarried.setBackground(new Color(168, 229, 177));
        add(radioBtnUnmarried);

        radioBtnOther = new JRadioButton("Other");
        radioBtnOther.setBounds(510, 410, 100, 30);
        radioBtnOther.setFont(new Font("Arial", Font.BOLD, 14));
        radioBtnOther.setBackground(new Color(168, 229, 177));
        add(radioBtnOther);

        ButtonGroup bGMaritalStus = new ButtonGroup();
        bGMaritalStus.add(radioBtnMarried);
        bGMaritalStus.add(radioBtnUnmarried);
        bGMaritalStus.add(radioBtnOther);

        JLabel lAddress = new JLabel("Address :");
        lAddress.setBounds(100,460,200,30);
        lAddress.setFont(new Font("Arial", Font.BOLD, 20));
        add(lAddress);

        tFAddress = new JTextField();
        tFAddress.setBounds(270, 460, 400, 30);
        tFAddress.setFont(new Font("Arial", Font.BOLD, 18));
        add(tFAddress);

        JLabel lCity = new JLabel("City:");
        lCity.setBounds(100,510,200,30);
        lCity.setFont(new Font("Arial", Font.BOLD, 20));
        add(lCity);

        tFCity = new JTextField();
        tFCity.setBounds(270, 510, 400, 30);
        tFCity.setFont(new Font("Arial", Font.BOLD, 18));
        add(tFCity);

        JLabel lPinCode = new JLabel("Pin Code :");
        lPinCode.setBounds(100,560,200,30);
        lPinCode.setFont(new Font("Arial", Font.BOLD, 20));
        add(lPinCode);

        tFPinCode = new JTextField();
        tFPinCode.setBounds(270, 560, 400, 30);
        tFPinCode.setFont(new Font("Arial", Font.BOLD, 18));
        add(tFPinCode);

        JLabel lState = new JLabel("State :");
        lState.setBounds(100,610,200,30);
        lState.setFont(new Font("Arial", Font.BOLD, 20));
        add(lState);

        tFState = new JTextField();
        tFState.setBounds(270, 610, 400, 30);
        tFState.setFont(new Font("Arial", Font.BOLD, 18));
        add(tFState);

        next = new JButton("Next");
        next.setBounds(590,670,80,30);
        next.setFont(new Font("Arial", Font.BOLD, 14));
        next.setForeground(Color.WHITE);
        next.setBackground(Color.BLACK);
        next.addActionListener(this);
        add(next);

        setLayout(null);
        setSize(850, 780);
        setLocation(360,20);
        getContentPane().setBackground(new Color(168, 229, 177));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String formNum = generateFormNum;
        String name = tFName.getText();
        String fatherName = tFFatherName.getText();
//        String dOB = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        // Safer Date extraction
        String dOB = "";
        if (dateChooser.getDate() != null) {
            dOB = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        }

        String gender = radioBtnMale.isSelected() ? "Male" : (radioBtnFemale.isSelected() ? "Female" : null);
        String email = tFEmail.getText();
        String maritalStatus = radioBtnMarried.isSelected() ? "Married" : (radioBtnUnmarried.isSelected() ? "Unmarried" : (radioBtnOther.isSelected() ? "Other" : null));
        String address = tFAddress.getText();
        String city = tFCity.getText();
        String pinCode = tFPinCode.getText();
        String state = tFState.getText();

        try {
            if (name.isEmpty() || fatherName.isEmpty() || dOB.isEmpty() || gender == null || email.isEmpty() || maritalStatus == null || address.isEmpty() || city.isEmpty() || pinCode.isEmpty() || state.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all mandatory fields");
            } else {
//                ConnectDB con = new ConnectDB();
//                String q = "INSERT INTO open_acc_form_one VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//                PreparedStatement pstmt = con.connection.prepareStatement(q);
//                pstmt.setString(1, formNum);
//                pstmt.setString(2, name);
//                pstmt.setString(3, fatherName);
//                pstmt.setString(4, dOB);
//                pstmt.setString(5, gender);
//                pstmt.setString(6, email);
//                pstmt.setString(7, maritalStatus);
//                pstmt.setString(8, address);
//                pstmt.setString(9, city);
//                pstmt.setString(10, pinCode);
//                pstmt.setString(11, state);

                // NOTE: Only call executeUpdate() once without the String 'q1' because pstmt already contains the query.
//                pstmt.executeUpdate(q);
//                pstmt.executeUpdate();
                // OR pass data through constructor
                new OpenAccFormTwo(formNum, name, fatherName, dOB, gender, email, maritalStatus, address,city, pinCode, state); // Passing formNum to link the data
                setVisible(false);
            }
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Error saving Form One", E);
        }
    }

    public static void main(String[] args) {
        new OpenAccFormOne();
    }
}