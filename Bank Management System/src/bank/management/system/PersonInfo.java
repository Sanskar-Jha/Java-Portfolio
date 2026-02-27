package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonInfo extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(PersonInfo.class.getName());

    String accNum;
    JButton back;
    JLabel name, fatherName, dOB, gender, email, maritalStatus, address, city, pinCode, state, religion, category, income, education, occupation, panNum, aadhaarNum, sCitizen, existingAcc, accType, facility;

    PersonInfo(String accNum) {
        this.accNum = accNum;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank_icon.png"));
        Image i1a = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel bankIcon = new JLabel(i1b);
        bankIcon.setBounds(20, 20, 100, 100);
        add(bankIcon);

        JLabel l1 = new JLabel("Account Holder Information");
        l1.setBounds(160, 60, 600, 40);
        l1.setFont(new Font("Arial", Font.BOLD, 30));
        add(l1);

        // --- Column 1: Personal Details ---
        createLabel("Name:", 50, 140);
        name = createValueLabel(250, 140);

        createLabel("Father's Name:", 50, 180);
        fatherName = createValueLabel(250, 180);

        createLabel("Date of Birth:", 50, 220);
        dOB = createValueLabel(250, 220);

        createLabel("Gender:", 50, 260);
        gender = createValueLabel(250, 260);

        createLabel("Email:", 50, 300);
        email = createValueLabel(250, 300);

        createLabel("Marital Status:", 50, 340);
        maritalStatus = createValueLabel(250, 340);

        createLabel("Address:", 50, 380);
        address = createValueLabel(250, 380);

        createLabel("City:", 50, 420);
        city = createValueLabel(250, 420);

        createLabel("Pin Code:", 50, 460);
        pinCode = createValueLabel(250, 460);

        createLabel("State:", 50, 500);
        state = createValueLabel(250, 500);

        // --- Column 2: Financial/Additional Details ---
        createLabel("Religion:", 450, 140);
        religion = createValueLabel(620, 140);

        createLabel("Category:", 450, 180);
        category = createValueLabel(620, 180);

        createLabel("Income:", 450, 220);
        income = createValueLabel(620, 220);

        createLabel("Education:", 450, 260);
        education = createValueLabel(620, 260);

        createLabel("Occupation:", 450, 300);
        occupation = createValueLabel(620, 300);

        createLabel("PAN Number:", 450, 340);
        panNum = createValueLabel(620, 340);

        createLabel("Aadhaar Number:", 450, 380);
        aadhaarNum = createValueLabel(620, 380);

        createLabel("Senior Citizen:", 450, 420);
        sCitizen = createValueLabel(620, 420);

        createLabel("Existing Account:", 450, 460);
        existingAcc = createValueLabel(620, 460);

        createLabel("Account Type:", 450, 500);
        accType = createValueLabel(620, 500);

        createLabel("Facility:", 50, 540);
        facility = createValueLabel(250, 540);

        back = new JButton("BACK");
        back.setBounds(50,700,100,30);
        back.setFont(new Font("Arial", Font.BOLD, 14));
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setSize(850, 780);
        setLocation(360,20);
        getContentPane().setBackground(new Color(222, 222, 222));
        setUndecorated(true);

        // Auto-load data from DB when initialized
        loadDataFromDatabase();

        setVisible(true);
    }

    // Helper methods to keep code clean and fix overlapping bounds
    private void createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 30);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label);
    }

    private JLabel createValueLabel(int x, int y) {
        JLabel label = new JLabel("-");
        label.setBounds(x, y, 300, 30);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.BLUE);
        add(label);
        return label;
    }

    private void loadDataFromDatabase() {
        try {
            ConnectDB con = new ConnectDB();
            String q = "SELECT * FROM acc_holder_info WHERE account_number = ?";
            try (PreparedStatement pstmt = con.connection.prepareStatement(q)) {
                pstmt.setString(1, accNum);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        name.setText(resultSet.getString("name"));
                        fatherName.setText(resultSet.getString("father_name"));
                        dOB.setText(resultSet.getString("dob"));
                        gender.setText(resultSet.getString("gender"));
                        email.setText(resultSet.getString("email"));
                        maritalStatus.setText(resultSet.getString("marital_status"));
                        address.setText(resultSet.getString("address"));
                        city.setText(resultSet.getString("city"));
                        pinCode.setText(resultSet.getString("pin_code"));
                        state.setText(resultSet.getString("state"));
                        religion.setText(resultSet.getString("religion"));
                        category.setText(resultSet.getString("category"));
                        income.setText(resultSet.getString("income"));
                        education.setText(resultSet.getString("education"));
                        occupation.setText(resultSet.getString("occupation"));
                        panNum.setText(resultSet.getString("pan_number"));
                        aadhaarNum.setText(resultSet.getString("aadhaar_number"));
                        sCitizen.setText(resultSet.getString("senior_citizen"));
                        existingAcc.setText(resultSet.getString("existing_account"));
                        accType.setText(resultSet.getString("account_type"));
                        facility.setText(resultSet.getString("facility"));

    //                    name.setEditable(false);  // Set JTextField to non-editable so user can't change them here
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Data Not Found", e);
            JOptionPane.showMessageDialog(null, "Data Not Found");
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
        new PersonInfo("");
    }
}