-- TO DO
CREATE DATABASE sj_bank;

--Stores personal details.
CREATE TABLE users (
    form_no INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    father_name VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    gender VARCHAR(20) NOT NULL,
    email VARCHAR(80) UNIQUE NOT NULL,
    marital_status VARCHAR(30) NOT NULL,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(30) NOT NULL,
    pin_code INT NOT NULL,
    state VARCHAR(50) NOT NULL,
    religion VARCHAR(30) NOT NULL,
    category VARCHAR(30) NOT NULL,
    income VARCHAR(50) NOT NULL,
    education VARCHAR(30) NOT NULL,
    occupation VARCHAR(50) NOT NULL,
    pan_no VARCHAR(20) UNIQUE NOT NULL,
    aadhaar_no VARCHAR(20) UNIQUE NOT NULL,
    senior_citizen BOOLEAN DEFAULT FALSE,
    existing_acc BOOLEAN DEFAULT FALSE,
    acc_type VARCHAR(50) DEFAULT 'Saving Account',
    facility VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--Each user has a bank account.
CREATE TABLE accounts (
    form_no INT,
    acc_no VARCHAR(20) PRIMARY KEY,
    acc_pin CHAR(4) NOT NULL,
    balance DECIMAL(12,2) DEFAULT 0.00,
    account_type VARCHAR(20),
    FOREIGN KEY (form_no) REFERENCES users(form_no)
);

--ATM card linked with account.
CREATE TABLE atm (
    acc_no VARCHAR(20),
    card_no VARCHAR(20) PRIMARY KEY,
    card_pin CHAR(4) NOT NULL,
    FOREIGN KEY (acc_no) REFERENCES accounts(acc_no)
);

--CREATE TABLE acc_holder_credentials (
--    acc_no VARCHAR(20) PRIMARY KEY,
--    login_pin CHAR(4) NOT NULL,
--    card_no VARCHAR(20) UNIQUE NOT NULL,
--    card_pin CHAR(4) NOT NULL,
--    balance DECIMAL(15,2) DEFAULT 0.00,
--    pan_no VARCHAR(20),
--    FOREIGN KEY (pan_no) REFERENCES acc_holder_info(pan_no)
--    ON DELETE CASCADE
--);

CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    form_no INT,
    acc_no VARCHAR(20),
    transaction_type VARCHAR(20) NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (form_no) REFERENCES accounts(form_no)
    ON DELETE CASCADE
);