package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenAccFormTwo extends JFrame implements ActionListener {
    private static final Logger logger = Logger.getLogger(OpenAccFormTwo.class.getName());

    JButton next;
    JRadioButton radioBtnSCitizenYes, radioBtnSCitizenNo, radioBtnExistingAccYes, radioBtnExistingAccNo;
    JTextField tFPanNum, tFAadhaarNum;
    JComboBox<String> cBoxReligion, cBoxCategory, cBoxIncome, cBoxEducation, cBoxOccupation;
    String formNum, name, fatherName, dOB, gender, email, maritalStatus, address,city, pinCode, state;

    OpenAccFormTwo(String formNum, String name, String fatherName, String dOB, String gender, String email, String maritalStatus, String address, String city, String pinCode, String state) {
        super("Open New Account");
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

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank_icon.png"));
        Image i1a = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i1b = new ImageIcon(i1a);
        JLabel bankIcon = new JLabel(i1b);
        bankIcon.setBounds(20,20,100,100);
        add(bankIcon);

        JLabel l1 = new JLabel("Additional Details");
        l1.setBounds(290, 60, 200, 30);
        l1.setFont(new Font("Arial", Font.BOLD, 22));
        add(l1);

        JLabel l2 = new JLabel("Page 2");
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

        JLabel lReligion = new JLabel("Religion :");
        lReligion.setBounds(150,140,100,30);
        lReligion.setFont(new Font("Arial", Font.BOLD, 20));
        add(lReligion);

        String[] religion = {"Select", "Hindu", "Muslim", "Sikh", "Christian", "Other"};
        cBoxReligion = new JComboBox<>(religion);
        cBoxReligion.setBounds(350,140,320,30);
        cBoxReligion.setFont(new Font("Arial", Font.BOLD, 14));
        cBoxReligion.setBackground(new Color(183, 223, 194));
        add(cBoxReligion);

        JLabel lCategory = new JLabel("Category :");
        lCategory.setBounds(150,190,100,30);
        lCategory.setFont(new Font("Arial", Font.BOLD, 20));
        add(lCategory);

        String[] Category = {"Select", "General", "OBC", "SC", "ST", "Other"};
        cBoxCategory = new JComboBox<>(Category);
        cBoxCategory.setBounds(350,190,320,30);
        cBoxCategory.setFont(new Font("Arial", Font.BOLD, 14));
        cBoxCategory.setBackground(new Color(183, 223, 194));
        add(cBoxCategory);

        JLabel lIncome = new JLabel("Income :");
        lIncome.setBounds(150,240,100,30);
        lIncome.setFont(new Font("Arial", Font.BOLD, 20));
        add(lIncome);

        String[] Income = {"Select", "upto ₹4 Lakh", "₹4 Lakh - ₹8 Lakh", "₹8 Lakh - ₹12 Lakh", "₹12 Lakh - ₹16 Lakh", "₹16 Lakh - ₹20 Lakh", "₹20 Lakh - ₹24 Lakh", "Above ₹24 Lakh"};
        cBoxIncome = new JComboBox<>(Income);
        cBoxIncome.setBounds(350,240,320,30);
        cBoxIncome.setFont(new Font("Arial", Font.BOLD, 14));
        cBoxIncome.setBackground(new Color(183, 223, 194));
        add(cBoxIncome);

        JLabel lEducation = new JLabel("Educational :");
        lEducation.setBounds(150,290,150,30);
        lEducation.setFont(new Font("Arial", Font.BOLD, 20));
        add(lEducation);

        String[] educational = {"Select", "Non-Graduate", "Graduate", "Post-Graduate", "PhD", "Professor", "Others"};
        cBoxEducation = new JComboBox<>(educational);
        cBoxEducation.setBounds(350,290,320,30);
        cBoxEducation.setFont(new Font("Arial", Font.BOLD, 14));
        cBoxEducation.setBackground(new Color(183, 223, 194));
        add(cBoxEducation);

        JLabel lOccupation = new JLabel("Occupation :");
        lOccupation.setBounds(150,340,150,30);
        lOccupation.setFont(new Font("Arial", Font.BOLD, 20));
        add(lOccupation);

        String[] occupation = {"Select", "Salaried", "Self-Employed", "Business", "Student", "Retired", "Others"};
        cBoxOccupation = new JComboBox<>(occupation);
        cBoxOccupation.setBounds(350,340,320,30);
        cBoxOccupation.setFont(new Font("Arial", Font.BOLD, 14));
        cBoxOccupation.setBackground(new Color(183, 223, 194));
        add(cBoxOccupation);

        JLabel lPanNum = new JLabel("PAN Number :");
        lPanNum.setBounds(150,390,200,30);
        lPanNum.setFont(new Font("Arial", Font.BOLD, 20));
        add(lPanNum);

        tFPanNum = new JTextField();
        tFPanNum.setBounds(350,390,320,30);
        tFPanNum.setFont(new Font("Arial", Font.BOLD, 18));
        add(tFPanNum);

        JLabel lAadhaarNum = new JLabel("Aadhaar Number :");
        lAadhaarNum.setBounds(150,440,200,30);
        lAadhaarNum.setFont(new Font("Arial", Font.BOLD, 20));
        add(lAadhaarNum);

        tFAadhaarNum = new JTextField();
        tFAadhaarNum.setBounds(350,440,320,30);
        tFAadhaarNum.setFont(new Font("Arial", Font.BOLD, 18));
        add(tFAadhaarNum);

        JLabel lSCitizen = new JLabel("Senior Citizen :");
        lSCitizen.setBounds(150,490,200,30);
        lSCitizen.setFont(new Font("Arial", Font.BOLD, 20));
        add(lSCitizen);

        radioBtnSCitizenYes = new JRadioButton("Yes");
        radioBtnSCitizenYes.setBounds(350,490,100,30);
        radioBtnSCitizenYes.setFont(new Font("Arial", Font.BOLD, 14));
        radioBtnSCitizenYes.setBackground(new Color(168, 229, 177));
        add(radioBtnSCitizenYes);

        radioBtnSCitizenNo = new JRadioButton("No");
        radioBtnSCitizenNo.setBounds(460,490,100,30);
        radioBtnSCitizenNo.setFont(new Font("Arial", Font.BOLD, 14));
        radioBtnSCitizenNo.setBackground(new Color(168, 229, 177));
        add(radioBtnSCitizenNo);

        ButtonGroup bGSCitizen = new ButtonGroup();
        bGSCitizen.add(radioBtnSCitizenYes);
        bGSCitizen.add(radioBtnSCitizenNo);

        JLabel labelExistingAcc = new JLabel("Existing Account :");
        labelExistingAcc.setBounds(150,540,200,30);
        labelExistingAcc.setFont(new Font("Arial", Font.BOLD, 20));
        add(labelExistingAcc);

        radioBtnExistingAccYes = new JRadioButton("Yes");
        radioBtnExistingAccYes.setBounds(350,540,100,30);
        radioBtnExistingAccYes.setFont(new Font("Arial", Font.BOLD, 18));
        radioBtnExistingAccYes.setBackground(new Color(168, 229, 177));
        add(radioBtnExistingAccYes);

        radioBtnExistingAccNo = new JRadioButton("No");
        radioBtnExistingAccNo.setBounds(460,540,100,30);
        radioBtnExistingAccNo.setFont(new Font("Arial", Font.BOLD, 18));
        radioBtnExistingAccNo.setBackground(new Color(168, 229, 177));
        add(radioBtnExistingAccNo);

        ButtonGroup bGExistingAcc = new ButtonGroup();
        bGExistingAcc.add(radioBtnExistingAccYes);
        bGExistingAcc.add(radioBtnExistingAccNo);

        next = new JButton("Next");
        next.setBounds(590,670,80,30);
        next.setFont(new Font("Arial", Font.BOLD, 14));
        next.setForeground(Color.BLACK);
        next.setBackground(Color.WHITE);
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
        // JComboBox, it returns an Object, not a String.
        String religion = (String) cBoxReligion.getSelectedItem();
        String category = (String) cBoxCategory.getSelectedItem();
        String income = (String) cBoxIncome.getSelectedItem();
        String education = (String) cBoxEducation.getSelectedItem();
        String occupation = (String) cBoxOccupation.getSelectedItem();
        String panNum = tFPanNum.getText();
        String aadhaarNum = tFAadhaarNum.getText();
        String seniorCitizen = radioBtnSCitizenYes.isSelected() ? "Yes" : (radioBtnSCitizenNo.isSelected() ? "No" : null);
        String existingAcc = radioBtnExistingAccYes.isSelected() ? "Yes" : (radioBtnExistingAccNo.isSelected() ? "No" : null);

        try {
            // "Select".equals(var) is safer than var.equals("Select")
            boolean isInvalidDropdown = "Select".equals(religion) || "Select".equals(category) || "Select".equals(income) || "Select".equals(education) || "Select".equals(occupation);

            // Validation: Ensure dropdowns aren't on "Select" and fields aren't empty
            if (isInvalidDropdown || panNum.isEmpty() || aadhaarNum.isEmpty() || seniorCitizen == null || existingAcc == null) {
                JOptionPane.showMessageDialog(null, "Please fill all mandatory fields");
            } else if (aadhaarNum.length() != 12) {
                JOptionPane.showMessageDialog(null, "Aadhaar Number must be 12 digits");
            } else {
                new OpenAccFormThree(formNum, name, fatherName, dOB, gender, email, maritalStatus, address,city, pinCode, state, religion, category, income, education, occupation, panNum, aadhaarNum, seniorCitizen, existingAcc);
                setVisible(false);
            }
        } catch (Exception E) {
            logger.log(Level.SEVERE, "Error saving Form Two", E);
        }
    }

    public static void main(String[] args) {
        new OpenAccFormTwo("", "", "", "", "", "", "", "", "", "", "");
    }
}