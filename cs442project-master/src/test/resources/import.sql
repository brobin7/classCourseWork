CREATE TABLE Users(
user_id INT NOT NULL UNIQUE,
username VARCHAR(64) NOT NULL UNIQUE,
password VARCHAR(64) NOT NULL,
firstname VARCHAR(64) NOT NULL,
lastname VARCHAR(64) NOT NULL,
administrator INT(1) NOT NULL,
PRIMARY KEY(user_id)
);

CREATE TABLE Banks(
bank_id INT NOT NULL UNIQUE,
routing_number INT NOT NULL UNIQUE,
bank_name VARCHAR(64) NOT NULL,
PRIMARY KEY(bank_id)
);

CREATE TABLE BankAccounts(
account_id INT NOT NULL UNIQUE,
user_id INT NOT NULL,
bank_id INT NOT NULL,
account_number INT NOT NULL,
account_name VARCHAR(64) NOT NULL,
balance DOUBLE NOT NULL,
PRIMARY KEY(account_id),
FOREIGN KEY(user_id) REFERENCES Users(user_id),
FOREIGN KEY(bank_id) REFERENCES Banks(bank_id)
);

CREATE TABLE Merchants(
merchant_id INT NOT NULL UNIQUE,
category VARCHAR(64),
merchant_name VARCHAR(64),
PRIMARY KEY(merchant_id)
);

CREATE TABLE Transactions(
transaction_id INT NOT NULL UNIQUE,
account_id INT NOT NULL,
merchant_id INT NOT NULL,
transaction_date DATETIME NOT NULL,
changes DOUBLE NOT NULL,
balance DOUBLE NOT NULL,
PRIMARY KEY(transaction_id),
FOREIGN KEY(account_id) REFERENCES BankAccounts(account_id),
FOREIGN KEY(merchant_id) REFERENCES Merchants(merchant_id)
);

CREATE sequence hibernate_sequence start with 1;